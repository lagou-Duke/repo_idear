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
        return new ResponseResult(true,200,"响应成 功",category);
    }



}
