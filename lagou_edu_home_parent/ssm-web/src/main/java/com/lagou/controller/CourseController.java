package com.lagou.controller;

import com.lagou.domain.Course;
import com.lagou.domain.CourseVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController   //相当于两个注解 @Controller @ResponseBody 返回json字符串
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 查询课程信息&条件查询 接口    @RequestBody注解用于 将获取前台传递的json数据封装成courseVO 对象
     * */
    @RequestMapping("/findCourseByCondition")
    public ResponseResult findCourseByCondition(@RequestBody CourseVO courseVO) {

        List<Course> courseList = courseService.findCourseByCondition(courseVO);

        //将返回的list集合  封装成ResponseResult对象 进行页面响应
        ResponseResult result = new ResponseResult(true, 200, "响应成功", courseList);
        return result;

    }


    /**
     * 图片上传接口
     *
     */
    @RequestMapping("/courseUpload")
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
     * 新增课程和讲师信息  或者修改
     * @RequestBody 和@RequestParam区别：
     *  Body是解析前台请求体中的json字符串，后台收到请求后可以进一步解析处理
     *  Param是url方式的参数传递 url中？后面传递参数 传递到后台进行解析
     *  一般param可以设置required 表示是否必须，默认为 true，必须。 defaultValue 可设置请求参数的默认值。value 为接收url的参数名（相当于key值）。
     */

    @RequestMapping("/saveOrUpdateCourse")
    public ResponseResult saveOrUpdateCourse(@RequestBody CourseVO courseVO){
        //  id 为空值 的时候就是新增操作
        if(courseVO.getId()==null){
            //调用service
            courseService.saveCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true,200,"新增成功",null);
            return responseResult;
        }else {
            //修改操作
            courseService.updateCourseOrTeacher(courseVO);
            ResponseResult responseResult = new ResponseResult(true,200,"修改成功",null);
            return responseResult;
        }



    }


    /**
     * 根据课程id 回显课程信息和讲师信息
     * @param id
     * @return
     */
    @RequestMapping("/findCourseById")
    public ResponseResult findCourseById(Integer id){
        CourseVO courseVO = courseService.findCourseById(id);

        ResponseResult result = new ResponseResult(true,200,"数据回显成功！",courseVO);

        return result;
    }


    /**
     * 修改课程和讲师信息   与上面的方法合并使用了 saveOrUpdateCourse
     */


    /**
     * 修改课程状态
     * */
    @RequestMapping("/updateCourseStatus")
    public ResponseResult updateCourseStatus(Integer id,Integer status){
        //调用service层
        courseService.updateCourseStatus(id,status);

        Map<String, Object> map = new HashMap<>();
        map.put("status",status);

        ResponseResult result = new ResponseResult(true,200,"状态修改成功！",map);
        return result;
    }
}
