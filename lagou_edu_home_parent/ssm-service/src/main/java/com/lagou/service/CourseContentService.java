package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentService {

    /**
     * 根据课程di查询课程信息（章节+课时）
     */

    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId);


    /**
     * 查询课程名称（回显课程名称）
     * */
    public Course findCourseByCourseId(Integer courseId);


    /**
     *  新增章节
     * */
    public void saveSection(CourseSection courseSection);

    /**
     *  修改章节
     * */

    public void updateSection(CourseSection courseSection);

    /**
     * 修改章节状态
     * */
    public void updateSectionStatus(int id,int status);


    /**
     * 新增课时信息
     */
    public void saveLesson(CourseLesson courseLesson);

    /**
     * 修改课时信息
     */
    public void updateLesson(CourseLesson courseLesson);
}
