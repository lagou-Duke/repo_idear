package com.lagou.controller;


import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 分页与条件查询
     * */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo){


        PageInfo<Resource> resource = resourceService.findAllResource(resourceVo);
        return new ResponseResult(true,200,"响应成 功",resource);
    }


    /**
     * 添加或者修改资源
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){

        if(resource.getId()==null){
            resourceService.saveResource(resource);
            return new ResponseResult(true,200,"响应成 功",null);
        }else {
            resourceService.updateResource(resource);
            return new ResponseResult(true,200,"响应成 功",null);
        }

    }
    /**
     * 删除资源
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){


        resourceService.deleteResource(id);
        ResponseResult result = new ResponseResult(true, 200, "删除成功！", null);
        return result;
    }

}
