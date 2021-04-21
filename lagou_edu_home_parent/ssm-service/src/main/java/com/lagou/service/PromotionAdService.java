package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

public interface PromotionAdService {

/**
    分页获取所有的广告列表
*/

    public PageInfo findAllAdByPage(PromotionAdVo promotionAdVo);


    /**
     修改广告状态
     */
    public void updatePromotionAdStatus(int id ,int status);
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
