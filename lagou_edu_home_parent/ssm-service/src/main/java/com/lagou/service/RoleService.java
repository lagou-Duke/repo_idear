package com.lagou.service;

import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

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
     * 添加菜单选项
     */

    public void RoleContextMenu(RoleMenuVo roleMenuVo);

    /**
     * 删除角色
     */
    public void deleteRole(Integer id);

}
