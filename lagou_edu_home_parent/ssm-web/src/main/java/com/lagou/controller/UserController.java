package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;


    /**
     查询所有用户  分页展示
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){
        PageInfo pageInfo = userService.findAllUserByPage(userVO);
        ResponseResult result = new ResponseResult(true, 200, "查询成功", pageInfo);
        return result;
    }


    /**
     * 修改用户状态
     * */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id, @RequestParam String status){
        if("ENABLE".equalsIgnoreCase(status)){
            status = "DISABLE";
        }else {
            status = "ENABLE";
        }
        userService.updateUserStatus(id,status);
        return new ResponseResult(true,200,"状态修改完毕！",status);
    }


    /**
     * 用户登录
     */
    @RequestMapping("/login")
    public ResponseResult updateUserStatus(User user, HttpServletRequest request) throws Exception {


        User login = userService.login(user);

        if(login!=null){
            //保存用户id及access_token到session中
            HttpSession session = request.getSession();
            String access_token = UUID.randomUUID().toString();
            session.setAttribute("user_id",login.getId());
            session.setAttribute("access_token",access_token);

            //将查询出来的结果响应给前台
            Map<String,Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id",login.getId());

            //将查询出来的user 也存到map中
            map.put("User",login);

            return new ResponseResult(true,1,"登陆成功",map);
        }else {

            return new ResponseResult(true,400,"用户名密码错误",null);
        }

    }

/**
 * 根据ID查询用户当前角色
 * */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(int id) {

        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,200,"分配角色回显成功",roleList);

    }



    /**
    分配角色
    */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){

        userService.userContextRole(userVO);
        return new ResponseResult(true,200,"分配角色成功",null);

    }


    /**
     获取用户权限信息
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        //获取请求头中的 token
        String token = request.getHeader("Authorization");
        //获取session中的access_token
        HttpSession session = request.getSession();
        String access_token = (String)session.getAttribute("access_token");

        //判断
        if(token.equals(access_token)){
            Integer user_id = (Integer) session.getAttribute("user_id");

            ResponseResult result = userService.getUserPermissions(user_id);
            return result;
        }else {
            ResponseResult result = new ResponseResult(false,400,"获取失败",null);
            return result;

        }

    }

    /**
     * 注册功能
     * */
    @RequestMapping("/add")
    public ResponseResult add(@RequestBody User user) throws Exception {

       userService.add(user);
        return new ResponseResult(true,200,"新增用户成功",null);

    }



}
