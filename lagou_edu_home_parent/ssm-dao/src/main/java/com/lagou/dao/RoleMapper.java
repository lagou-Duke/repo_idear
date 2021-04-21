package com.lagou.dao;

import com.lagou.domain.PromotionAd;
import com.lagou.domain.Role;
import com.lagou.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {

    /**
    查询角色列表(条件)
    */

    public List<Role> findAllRole(Role role);

    /**
     * 根据角色id 查询出对应的菜单信息
     */
    public List<Integer> findMenuByRoleId(Integer roleId);


    /**
     新建角色 (自行完成)
     */
    public void saveRole(Role role);

    /**
     修改角色 (自行完成)
     */
    public void updateRole(Role role);

    /**
     * 清空之前选择的菜单选项
     */

    public void deleteRoleContextMenu(Integer rid);

    /**
     * 添加菜单选项
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);


    /**
     * 删除角色
     */
    public void deleteRole(Integer id);



}
