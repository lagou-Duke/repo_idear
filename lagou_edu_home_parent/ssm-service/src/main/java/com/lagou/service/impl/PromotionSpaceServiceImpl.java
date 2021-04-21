package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.domain.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {
    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    /**
     获取所有的广告位
     */
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {
        List<PromotionSpace> promotionSpace = promotionSpaceMapper.findAllPromotionSpace();
        return promotionSpace;
    }


    /**
     添加广告位
     */
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {
       promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }


    /**
     * 回显广告位名臣
     */
    @Override
    public PromotionSpace findPromotionSpaceById(int id) {
        PromotionSpace promotionSpace = promotionSpaceMapper.findPromotionSpaceById(id);
        return promotionSpace;
    }


    /**
     修改广告位
     */
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {
        promotionSpace.setUpdateTime(new Date());
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);
    }


}
