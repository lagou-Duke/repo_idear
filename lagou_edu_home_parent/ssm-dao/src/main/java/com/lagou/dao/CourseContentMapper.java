package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseLesson;
import com.lagou.domain.CourseSection;

import java.util.List;

public interface CourseContentMapper {
    /**
     * 查询课程下的章节与课时信息
     * */

    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

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
    public void updateSectionStatus(CourseSection section);


    /**
     * 新增课时信息
     */
    public void saveLesson(CourseLesson courseLesson);

    /**
     * 修改课时信息
     */
    public void updateLesson(CourseLesson courseLesson);

}

