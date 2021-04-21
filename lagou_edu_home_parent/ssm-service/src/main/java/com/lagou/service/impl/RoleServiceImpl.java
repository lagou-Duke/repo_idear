package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.ref.PhantomReference;
import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> roles = roleMapper.findAllRole(role);
        return roles;
    }


    /**
     * 根据角色id 查询出对应的菜单信息
     */
    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {
        List<Integer> list = roleMapper.findMenuByRoleId(roleId);
        return list;
    }


    /**
     新建角色 (自行完成)
     */
    @Override
    public void saveRole(Role role) {


        roleMapper.saveRole(role);

    }


    /**
     修改角色 (自行完成)
     */
    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }



    /**
     * 添加菜单选项
     */
    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
        // 清空中间表
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //新添加菜单
        Role_menu_relation role_menu_relation = new Role_menu_relation();
            //获取集合中的MenuId 也就是前台传递进来的 封装到Vo对象内部的id值
        for (Integer integer : roleMenuVo.getMenuIdList()) {
            role_menu_relation.setMenuId(integer);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            //其他数据的封装
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.roleContextMenu(role_menu_relation);
        }
    }


    /**
     * 删除角色
     */
    @Override
    public void deleteRole(Integer id) {

        //首先删除角色与菜单之间的关联关系，然后再删除角色信息
        roleMapper.deleteRoleContextMenu(id);;

        roleMapper.deleteRole(id);;


    }


}
