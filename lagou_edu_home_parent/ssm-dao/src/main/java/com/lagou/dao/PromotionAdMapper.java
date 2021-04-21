package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

public interface PromotionAdMapper {

    /**
    分页获取所有的广告列表
    */

    public List<PromotionAd> findAllAdByPage();



    /**
     修改状态
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);

    /**
        新建广告
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /**
        修改广告
     */
    public void updatePromotionAd(PromotionAd promotionAd);




    /**
     * 回显广告名称
     */
    public PromotionAd findPromotionAdById(int id);

}
