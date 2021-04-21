package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    /**
     查询所有用户  分页展示
     */
    @Override
    public PageInfo findAllUserByPage(UserVO userVO) {

        PageHelper.startPage(userVO.getCurrentPage(),userVO.getPageSize());
        List<User> user = userMapper.findAllUserByPage(userVO);

        PageInfo<User> pageInfo = new PageInfo<User>(user);
        return pageInfo;
    }


    /**
     * 修改用户状态
     * */
    @Override
    public void updateUserStatus(int id, String status) {
        userMapper.updateUserStatus(id,status);
    }


    /**
     * 用户登录
     */
    @Override
    public User login(User user) throws Exception {
        User user1 = userMapper.login(user);
        if(user1 !=null && Md5.verify(user.getPassword(),"lagou",user1.getPassword())){
            return user1;
        }else {
            return null;
        }
    }


    /**
     * 根据ID查询用户当前角色
     * */
    @Override
    public List<Role> findUserRelationRoleById(int id) {


        List<Role> roleList = userMapper.findUserRelationRoleById(id);
        return roleList;

    }


    /**
     * 用户关联角色  1+1
     *  1、先清空中间表
     *  2、再重新为用户添加角色信息
     */
    @Override
    public void userContextRole(UserVO userVO) {
        //清空中间表
        userMapper.deleteUserContextRole(userVO.getUserId());

        //重新选择角色id
        User_Role_relation user_role_relation = new User_Role_relation();
        for (Integer integer : userVO.getRoleIdList()) {
            //封装数据
            user_role_relation.setUserId(userVO.getUserId());
            user_role_relation.setRoleId(integer);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.userContextRole(user_role_relation);
        }

    }

    /**
     * 获取用户权限信息
     * @param id
     * @return
     */
    @Override
    public ResponseResult getUserPermissions(Integer id) {
        //1、获取用户关联的角色信息
        List<Role> roleList = userMapper.findUserRelationRoleById(id);

        //2、获取角色ID,保存到 list
        List<Integer> list = new ArrayList<>();

        for (Role role : roleList) {
            //把查询出的角色 里的id值 放到集合中  list里面放置的就是角色id
            list.add(role.getId());
        }

        //3、根据角色id查询 父菜单   (角色id作为参数)
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(list);

        //4、封装父菜单下的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenuByPid = userMapper.findSubMenuByPid(menu.getId());
        }

        //5、获取资源权限
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);

        //6、
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",parentMenu); //menuList: 菜单权限数据
        map.put("resourceList",resourceList);//resourceList: 资源权限数据
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);
        return result;
    }



    @Override
    public void add(User user) throws Exception {
        user.setPhone("15012341234");  //用户名
        String lagou = Md5.md5("123456", "lagou");
        user.setPassword(lagou);  //密码

        user.setName("Duke");


        Date date = new Date();
        user.setCreate_time(date);
        user.setUpdate_time(date);
        user.setStatus("ENABLE");
        user.setIs_del(1);

      userMapper.add(user);


    }


}
