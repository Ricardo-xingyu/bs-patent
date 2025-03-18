package com.bs.content.api;

import com.bs.content.model.dto.CoursePreviewDto;
import com.bs.content.service.CoursePublishService;
import com.bs.content.model.dto.CoursePreviewDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @description 专利预览，发布
 * @author Mr.M
 * @date 2022/9/16 14:48
 * @version 1.0
 */
@Api(value = "专利预览发布接口",tags = "专利预览发布接口")
@Controller
public class CoursePublishController {


    @Autowired
    CoursePublishService coursePublishService;

    @ApiOperation("专利发布")
    @GetMapping("/coursepreview/{courseId}")
    public ModelAndView preview(@PathVariable("courseId") Long courseId) {

        //获取专利预览信息
        CoursePreviewDto coursePreviewInfo = coursePublishService.getCoursePreviewInfo(courseId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("model", coursePreviewInfo);
        modelAndView.setViewName("test");
        return modelAndView;
    }

    @ApiOperation("提交审核")
    @ResponseBody
    @PostMapping("/courseaudit/commit/{courseId}")
    public void commitAudit(@PathVariable("courseId") Long courseId){
        Long companyId = 1232141425L;
        coursePublishService.commitAudit(companyId,courseId);

    }

    @ApiOperation("专利发布")
    @ResponseBody
    @PostMapping ("/coursepublish/{courseId}")
    public void coursepublish(@PathVariable("courseId") Long courseId){
        Long companyId = 1232141425L;
        coursePublishService.publish(companyId,courseId);
    }


}
