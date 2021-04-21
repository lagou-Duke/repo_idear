package com.lagou.service;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;

import java.util.List;

public interface CourseService {
    /**
     * 根据课程名和id字段 进行多条件查询
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /**
     * 保存课程信息和讲师信息
     * */
    public void saveCourseOrTeacher(CourseVO courseVO);

    /**
     * 根据课程id 回显课程信息和讲师信息
     */
    public CourseVO findCourseById(Integer id);

    /**
     * 修改课程和讲师信息
     */
    public void updateCourseOrTeacher(CourseVO courseVO);

    /**
     * 修改课程状态
     */

    public void updateCourseStatus(int id,int status);


    
}
