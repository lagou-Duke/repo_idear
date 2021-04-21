package com.lagou.service;

import com.lagou.domain.RoleResourceRelationVO;

public interface RoleResourceRelationService {
    //角色的id和删除角色与资源的关联关系
    public void deleteRoleResourceByRoleId(Integer id);


    //插入关联信息
    public void saveRoleResourceRelation(RoleResourceRelationVO roleResourceRelationVO);


}
