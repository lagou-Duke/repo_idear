package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController   //相当于两个注解 @Controller @ResponseBody 返回json字符串
@RequestMapping("/courseContent")
public class CourseContentController {


    @Autowired
    private CourseContentService courseContentService;


    /**
     * 根据课程di查询课程信息（章节+课时）
     */
@RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(Integer courseId){
        List<CourseSection> list = courseContentService.findSectionAndLessonByCourseId(courseId);

        ResponseResult result = new ResponseResult(true, 200, "章节及课时信息查询成功！", list);
        return result;

    }


    /**
     * 查询课程名称（回显课程名称）
     * */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(Integer courseId){

        Course course = courseContentService.findCourseByCourseId(courseId);
        ResponseResult result = new ResponseResult(true, 200, "课程名称响应成功！", course);
        return result;

    }

    /**
     *  新增章节 huo 修改章节 (新增)
     * */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveSection(@RequestBody CourseSection courseSection){
        if(courseSection.getId()==null){
            //新增
            courseContentService.saveSection(courseSection);
            return new ResponseResult(true,200,"新增响应成功",null);
        }else {
            //修改  修改操作要有章节对用的id属性 如果没有就表示新增
            courseContentService.updateSection(courseSection);
            return new ResponseResult(true,200,"修改响应成功",null);
        }


    }

    /**
     * 修改章节状态
     * 状态，0:隐藏；1：待更新；2：已发布
     * */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam int id, @RequestParam int status){
        courseContentService.updateSectionStatus(id,status);

        Map<Object, Object> map = new HashMap<>();
        map.put("status",status);
        ResponseResult result = new ResponseResult(true, 200, "章节状态修改成功!", map);
        return result;
    }


    /**
     * 保存&修改课时
     *
     * */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson courseLesson){
        if(courseLesson.getId()==null){
            //课时id为空值  代表是新增
            courseContentService.saveLesson(courseLesson);
            return new ResponseResult(true,200,"响应成功",null);
        }else {
            //修改操作  课时di字段不为空
            courseContentService.updateLesson(courseLesson);
            return new ResponseResult(true,200,"响应成功",null);
        }
    }






}
