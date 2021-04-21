package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController   //相当于两个注解 @Controller @ResponseBody 返回json字符串
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    /**
     * 查询所有菜单
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = menuService.findAllMenu();
        ResponseResult result = new ResponseResult(true, 200, "响应成功", allMenu);
        return result;
    }


    /**
     *  添加或者修改菜单信息（回显菜单信息）
     *  添加 id值为  -1  ，不进行数据回显
     *  修改  id传递不为-1  进行数据回显
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(@RequestParam Integer id){
        if(id == -1){
            //添加操作 回显不需要查询 menu信息 (不需要子级菜单信息，需要父级菜单信息)
            //调用之前写好的查询 父子级菜单方法，传递-1 表示只查询父级菜单 父级菜单在数据库中对应的id全是-1 然后再通过map放入对应的key值里
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);
            //封装数据
            Map<String,Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",menuList);
            ResponseResult result = new ResponseResult(true,200,"添加回显成功",map);
            return result;

        }else {
            //修改操作  需要回显 父级&子级菜单信息
            //需要编写根据id查询出对应的菜单信息 赋值到key为menuInfo 里面 完成数据回显 父级菜单还是parentMenuList 参数-1 查好的传递就好了
            Menu menu = menuService.findMenuById(id);
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);

            Map<String,Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",menuList);

            ResponseResult result = new ResponseResult(true,200,"修改回显成功",map);
            return result;
        }

    }


    /**
     * 添加或者修改菜单
     */
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        if(menu.getId()==null){
            //新增操作
            menuService.saveMenu(menu);
            ResponseResult result = new ResponseResult(true, 200, "新增成功", null);
            return result;
        }else {
            menuService.updateMenu(menu);
            ResponseResult result = new ResponseResult(true, 200, "修改成功", null);
            return result;
        }


    }



}
