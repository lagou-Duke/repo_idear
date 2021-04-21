package com.lagou.dao;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.Teacher;

import java.util.List;

public interface CourseMapper {

    /**
     * 根据课程名和id字段 进行多条件查询
     */
    public List<Course> findCourseByCondition(CourseVO courseVO);

    /**
     * 保存课程信息  新增课程和讲师————————————————
     */
    public void saveCourse(Course course);
    /**
     * 保存讲师信息
     */

    public void saveTeacher(Teacher teacher);

    /**
     * 根据课程id 回显课程信息和讲师信息
     */
    public CourseVO findCourseById(Integer id);

    /**
     * 修改课程信息   修改课程和讲师————————————————
     *
     */

    public void updateCourse(Course course);
    /**
     * 修改讲师信息
     */
    public void updateTeacher(Teacher teacher);


    /**
     * 修改课程状态
     * */
    public void updateCourseStatus(Course course);
}
