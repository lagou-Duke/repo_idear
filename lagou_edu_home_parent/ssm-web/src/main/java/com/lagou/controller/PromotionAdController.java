package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.apache.ibatis.annotations.ResultMap;
import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;


    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVo promotionAdVo){

        PageInfo pageInfo = promotionAdService.findAllAdByPage(promotionAdVo);
        ResponseResult result = new ResponseResult(true, 200, "分页查询成功", pageInfo);
        return result;
    }

    /**
     * 图片上传
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {

        //1.判断文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }
        //2.获取项目部署路径
        // D:\apache-tomcat-8.5.56\webapps\ssm-web\
        String realPath = request.getServletContext().getRealPath("/");
        // D:\apache-tomcat-8.5.56\webapps\
        String webappsPath = realPath.substring(0,realPath.indexOf("ssm-web"));

        //3.获取原文件名
        String fileName = file.getOriginalFilename();
        //4.新文件名
        String newFileName = System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."));


        //5.上传文件
        String uploadPath = webappsPath+"upload\\";
        File filePath = new File(uploadPath,newFileName);

        //如果目录不存在就创建目录

        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录: " + filePath);
        }
        file.transferTo(filePath);

        //6.将文件名和文件路径返回
        Map<String,String> map = new HashMap<>();
        map.put("fileName",newFileName);
        //http://localhost:8080/
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult result = new ResponseResult(true,200,"响应成功",map);

        return result;
    }

    /**
     修改广告状态
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updateCourseStatus(@RequestParam int id, @RequestParam int status) {
        if (status == 1) {
            promotionAdService.updatePromotionAdStatus(id,status);
        }else {
            promotionAdService.updatePromotionAdStatus(id,0);

        }
        //保存修改后的状态,并返回
        Map<String, Integer> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;

    }

    /**
        新增或更新广告位置
    */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd) {
            if (promotionAd.getId() == null) {
                //新增
                Date date = new Date();
                promotionAd.setCreateTime(date);
                promotionAd.setUpdateTime(date);
                promotionAdService.savePromotionAd(promotionAd);
                return new ResponseResult(true,200,"新增成功",null);
            }else {
                //修改
                Date date = new Date();
                promotionAd.setUpdateTime(date);
                promotionAdService.updatePromotionAd(promotionAd);
                return new ResponseResult(true,200,"修改成功",null);
            }

        }

    /**
     * 回显广告名称
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(@RequestParam int id){
        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);
        ResponseResult result = new ResponseResult(true,200,"响应成功",promotionAd);
        return result;
    }


    }



