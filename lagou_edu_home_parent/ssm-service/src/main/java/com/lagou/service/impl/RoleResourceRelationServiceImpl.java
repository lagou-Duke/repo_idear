package com.lagou.service.impl;

import com.lagou.dao.RoleResourceRelationMapper;
import com.lagou.domain.RoleResourceRelation;
import com.lagou.domain.RoleResourceRelationVO;
import com.lagou.service.RoleResourceRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RoleResourceRelationServiceImpl implements RoleResourceRelationService {
    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;


    @Override
    public void deleteRoleResourceByRoleId(Integer id) {
        roleResourceRelationMapper.deleteRoleResourceByRoleId(id);
    }

    @Override
    public void saveRoleResourceRelation(RoleResourceRelationVO roleResourceRelationVO) {
        //说过向中间表添加关联的时候，需要先清空关联关系，再添加
        deleteRoleResourceByRoleId(roleResourceRelationVO.getRoleId());
        String person = roleResourceRelationVO.getPerson();
        for (Integer integer:roleResourceRelationVO.getResourceIdList()){
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setResourceId(integer);
            //封装
            Date date=new Date();
            roleResourceRelation.setCreateDateTime(date);
            roleResourceRelation.setUpdateDateTime(date);
            roleResourceRelation.setRoleId(roleResourceRelationVO.getRoleId());
            roleResourceRelation.setCreatedBy(person);
            roleResourceRelation.setUpdatedBy(person);
            roleResourceRelationMapper.saveRoleResourceRelation(roleResourceRelation);

        }
    }
}
