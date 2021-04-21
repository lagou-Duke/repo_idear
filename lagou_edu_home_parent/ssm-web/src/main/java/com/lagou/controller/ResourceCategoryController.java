package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;



    /**
     * 查询所有资源分类
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){

        List<ResourceCategory> category = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true,200,"响应成功",category);
    }

    /**
     * 添加或者修改资源分类
     */

    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){

        if(resourceCategory.getId()==null){
            //没有id  就是新增
            resourceCategoryService.saveResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"新增成功",null);
            //有id  就是修改
        }else {
            resourceCategoryService.updateResourceCategory(resourceCategory);
            return new ResponseResult(true,200,"修改成功",null);
        }

    }
    /**
     * 删除资源分类
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){


        resourceCategoryService.deleteResourceCategory(id);
        ResponseResult result = new ResponseResult(true, 200, "删除成功！", null);
        return result;
    }

}
