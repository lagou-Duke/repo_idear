package com.lagou.controller;


import com.lagou.domain.*;
import com.lagou.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.ref.PhantomReference;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleResourceRelationService roleResourceRelationService;

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(Role role){
        List<Role> roles = roleService.findAllRole(role);

        ResponseResult result = new ResponseResult(true, 200, "角色列表查询完毕!", roles);
        return result;
    }

    /**
     * 查询全部的父子菜单信息
     * */

    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        //查询所有的父子级菜单
        List<Menu> list = menuService.findSubMenuListByPid(-1);


        //将查出来的list中的所有菜单 放到key值为parentMenuList的集合中 方便响应
        Map<String,Object> map = new HashMap<>();
        map.put("parentMenuList",list);

        ResponseResult result = new ResponseResult(true, 200, "查询所有菜单！", map);
        return result;
    }

    /**
     * 根据角色id 查询出对应的菜单信息
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Integer> menu = roleService.findMenuByRoleId(roleId);

        ResponseResult result = new ResponseResult(true,200,"响应成功",menu);
        return result;
    }

    /**
     新建角色 (自行完成)
     */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        if(role.getId()==null){
            //新增操作
            //补全信息
            Date date = new Date();
            role.setCreatedTime(date);
            role.setUpdatedTime(date);
            role.setCreatedBy("15510792994");
            role.setUpdatedBy("15510792994");

            roleService.saveRole(role);
            return new ResponseResult(true,200,"新增成功",null);
        } else {
           //修改操作
            //补全信息
            Date date = new Date();
            role.setUpdatedTime(date);
            roleService.updateRole(role);
            return new ResponseResult(true,200,"修改成功",null);
        }


    }

    /**
     * 添加菜单选项 (先删除原来的菜单选项，后添加新选择的菜单选项)
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.RoleContextMenu(roleMenuVo);
        ResponseResult result = new ResponseResult(true,200,"响应成功",null);
        return result;
    }


    /**
     * 删除角色
     */

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        ResponseResult result = new ResponseResult(true,200,"删除角色成功",null);
        return result;

    }


    /**
     * 根据角色id查询资源信息
     * @param roleId
     * @return
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId){
        //获取资源信息
        List<Resource> resourceList =resourceService.findResourceListByRoleId(roleId);
        //获取资源分类信息
        ResourceCategory categoryById = resourceCategoryService.findResourceCategoryById(resourceList.get(0).getCategoryId());
        //封装信息
        HashMap<Object, Object> map = new HashMap<>();
        map.put("id",categoryById.getId());
        map.put("name",categoryById.getName());
        map.put("sort",categoryById.getSort());
        map.put("createdDateTime",categoryById.getCreatedTime());
        map.put("updateDateTime",categoryById.getUpdatedTime());
        map.put("createdBy",categoryById.getCreatedBy());
        map.put("updatedBy",categoryById.getUpdatedBy());
        //将查询出来的资源列表封装到resourceList 里面 与接口文档对应
        map.put("resourceList",resourceList);

        ResponseResult result = new ResponseResult(true, 200, "查询角色成功",map);
        return result;

    }


    /**
     *  为角色分配资源
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceRelationVO roleResourceRelationVO, HttpServletRequest httpServletRequest){

        roleResourceRelationVO.setPerson("system");
        roleResourceRelationService.saveRoleResourceRelation(roleResourceRelationVO);
        ResponseResult result = new ResponseResult(true, 200, "为角色分配成功", null);
        return result;
        /*return null;*/
    }

}
