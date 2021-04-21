package com.lagou.service.impl;

import com.lagou.dao.CourseContentMapper;
import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;
import com.lagou.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;

    /**
     * 根据课程di查询课程信息（章节+课时）
     */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        List<CourseSection> list = courseContentMapper.findSectionAndLessonByCourseId(courseId);
        return list;
    }


    /**
     * 查询课程名称（回显课程名称）
     * */

    @Override
    public Course findCourseByCourseId(Integer courseId) {
        Course course = courseContentMapper.findCourseByCourseId(courseId);
        return course;
    }

    /**
     *  新增章节
     * */
    @Override
    public void saveSection(CourseSection courseSection) {
        //补全信息
        Date date = new Date();
        courseSection.setCreateTime(date);
        courseSection.setUpdateTime(date);
        courseContentMapper.saveSection(courseSection);
    }



    /**
     *  修改章节
     * */
    @Override
    public void updateSection(CourseSection courseSection) {
        //补全信息
        Date date = new Date();
        courseSection.setUpdateTime(date);
        courseContentMapper.updateSection(courseSection);
    }


    /**
     * 修改章节状态
     * */
    @Override
    public void updateSectionStatus(int id,int status) {
        //补全信息
        CourseSection courseSection = new CourseSection();
        courseSection.setId(id);
        courseSection.setStatus(status);
        courseSection.setUpdateTime(new Date());
        //调用CourseContentMapper
        courseContentMapper.updateSectionStatus(courseSection);
    }



    /**
     * 新增课时信息
     */
    @Override
    public void saveLesson(CourseLesson courseLesson) {
        //补全信息
        Date date = new Date();
        courseLesson.setCreateTime(date);
        courseLesson.setUpdateTime(date);
        courseContentMapper.saveLesson(courseLesson);
    }
    /**
     * 修改课时信息
     */
    @Override
    public void updateLesson(CourseLesson courseLesson) {
        Date date = new Date();
        courseLesson.setUpdateTime(date);
        courseContentMapper.updateLesson(courseLesson);
    }


}
