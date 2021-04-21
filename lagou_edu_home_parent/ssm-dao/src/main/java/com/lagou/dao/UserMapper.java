package com.lagou.dao;

import com.lagou.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 多条件查询  分页展示
     * @param userVO
     * @return
     */
    public List<User> findAllUserByPage(UserVO userVO);



    /**
     * 修改用户状态
     * */
    public void updateUserStatus(@Param("id") int id,@Param("status") String status);


    /**
     * 用户登录
     */
    public User login(User user);

    /**
     * 根据ID查询用户当前角色
     * */
//    下面服用了  public List<Role> findUserRelationRoleById(int id);

    /**
     * 根据用户ID清空中间表
     */
    public void deleteUserContextRole(Integer userId);
    /**
     *  分配角色
     */

    public void userContextRole(User_Role_relation user_role_relation);


    /**
     * 1、根据用户id查询 角色信息
     */
    public List<Role> findUserRelationRoleById(int id);


    /**
     * 2、根据角色id（多个角色），查询父级菜单
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /**
     * 3、根据父级菜单 查询子级菜单
     */
    public List<Menu> findSubMenuByPid(int pid);


    /**
     * 4、查询用户对应的资源信息
     */

    public List<Resource> findResourceByRoleId(List<Integer> ids);

    /**
     * 通过MD5算法 向数据库中添加用户
     */
    public void add(User user);
}
