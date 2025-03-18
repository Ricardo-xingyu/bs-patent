package com.bs.content.api;

import com.bs.base.model.PageParams;
import com.bs.base.model.PageResult;
import com.bs.content.model.dto.AddCourseDto;
import com.bs.content.model.dto.CourseBaseInfoDto;
import com.bs.content.model.dto.EditCourseDto;
import com.bs.content.model.dto.QueryCourseParamsDto;
import com.bs.content.model.po.CourseBase;
import com.bs.content.service.CourseBaseInfoService;
import com.bs.content.util.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description 专利信息编辑接口
 * @author Mr.M
 * @date 2022/9/6 11:29
 * @version 1.0
 */
@Api(value = "专利编辑接口",tags = "专利编辑接口")
@RestController
public class CourseBaseInfoController {

    @Autowired
    CourseBaseInfoService courseBaseInfoService;


    @ApiOperation("专利查询接口")
    @PreAuthorize("hasAuthority('xc_teachmanager_course_list')")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody(required = false) QueryCourseParamsDto queryCourseParams){
        //取出用户身份
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        //机构id
        String userName = user.getUsername();

        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(userName, pageParams, queryCourseParams);

        return courseBasePageResult;

    }

    @ApiOperation("新增专利基础信息")
    @PostMapping("/course")
    public CourseBaseInfoDto createCourseBase(@RequestBody @Validated AddCourseDto addCourseDto){
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 1232141425L;
        return courseBaseInfoService.createCourseBase(companyId,addCourseDto);
    }


    @ApiOperation("根据id查询专利基础信息")
    @GetMapping("/course/{courseId}")
    public CourseBaseInfoDto getCourseBaseById(@PathVariable Long courseId){
        //取出当前用户身份
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SecurityUtil.XcUser user = SecurityUtil.getUser();
        System.out.println(user);
        return courseBaseInfoService.getCourseBaseInfo(courseId);
    }

    @ApiOperation("修改专利基础信息")
    @PutMapping("/course")
    public CourseBaseInfoDto modifyCourseBase(@RequestBody @Validated EditCourseDto editCourseDto){
        //机构id，由于认证系统没有上线暂时硬编码
        Long companyId = 1232141425L;
        return courseBaseInfoService.updateCourseBase(companyId,editCourseDto);
    }





}
