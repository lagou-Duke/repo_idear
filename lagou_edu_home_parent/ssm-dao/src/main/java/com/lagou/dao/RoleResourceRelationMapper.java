package com.lagou.dao;

import com.lagou.domain.RoleResourceRelation;

public interface RoleResourceRelationMapper {
    //角色的id和删除角色与资源的关联关系
    public void deleteRoleResourceByRoleId(Integer id);


    //插入关联信息
    public void saveRoleResourceRelation(RoleResourceRelation roleResourceRelation);

}
