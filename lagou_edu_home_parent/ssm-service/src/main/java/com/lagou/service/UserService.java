package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    /**
        查询所有用户  分页展示
    */
    public PageInfo findAllUserByPage(UserVO userVO);

    /**
     * 修改用户状态
     * */
    public void updateUserStatus(int id,String status);


    /**
     * 用户登录
     */
    public User login(User user) throws Exception;

    /**
     * 根据ID查询用户当前角色
     * */
    public List<Role> findUserRelationRoleById(int id);


    /**
     * 用户关联角色  1+1
     */
    public void userContextRole(UserVO userVO);

    /**
     * 获取用户权限信息
     * @param id
     * @return
     */
    public ResponseResult getUserPermissions(Integer id);


    /**
     * 通过MD5算法 向数据库中添加用户
     */
    public void add(User user) throws Exception;

}
