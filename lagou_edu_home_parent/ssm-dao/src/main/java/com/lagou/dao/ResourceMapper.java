package com.lagou.dao;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {


    /**
     * 多条件查询 & 分页展示
     * @param resourceVo
     * @return
     */
    public List<Resource> findAllResource(ResourceVo resourceVo);


    /**
     * 添加资源
     */
    public void saveResource(Resource resource);


    /**
     * 修改资源
     */

    public void updateResource(Resource resource);

    /**
     * 删除资源
     */
    public void deleteResource(Integer id);

}
