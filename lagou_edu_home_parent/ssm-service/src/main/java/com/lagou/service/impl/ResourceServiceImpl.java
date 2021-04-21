package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;


    /**
     * 多条件查询 & 分页展示
     * @param resourceVo
     * @return
     */
    @Override
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo) {

        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());

        List<Resource> resource = resourceMapper.findAllResource(resourceVo);

        PageInfo<Resource> pageInfo = new PageInfo<Resource>(resource);

        return pageInfo;
    }


    /**
     * 添加资源
     */
    @Override
    public void saveResource(Resource resource) {
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");
        resourceMapper.saveResource(resource);
    }


    /**
     * 修改资源
     */
    @Override
    public void updateResource(Resource resource) {
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");
        resourceMapper.updateResource(resource);
    }



    /**
     * 删除资源
     */
    @Override
    public void deleteResource(Integer id) {
        resourceMapper.deleteResource(id);
    }


}
