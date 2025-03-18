package com.bs.content.service;


import com.bs.base.model.PageParams;
import com.bs.base.model.PageResult;
import com.bs.content.model.dto.AddCourseDto;
import com.bs.content.model.dto.CourseBaseInfoDto;
import com.bs.content.model.dto.EditCourseDto;
import com.bs.content.model.dto.QueryCourseParamsDto;
import com.bs.content.model.po.CourseBase;

/**
 * @description 专利基本信息管理业务接口
 * @author Mr.M
 * @date 2022/9/6 21:42
 * @version 1.0
 */
public interface CourseBaseInfoService  {

/**
 * @description 专利查询接口
 * @param pageParams 分页参数
 * @param queryCourseParamsDto 条件条件
 * @author Mr.M
 * @date 2022/9/6 21:44
 */
  PageResult<CourseBase> queryCourseBaseList(String user, PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

  /**
   * @description 添加专利基本信息
   * @param companyId  教学机构id
   * @param addCourseDto  专利基本信息
   * @return com.bs.content.model.dto.CourseBaseInfoDto
   * @author Mr.M
   * @date 2022/9/7 17:51
   */
  CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto addCourseDto);

  /**
   * @description 根据id查询专利基本信息
   * @param courseId  专利id
   * @return com.bs.content.model.dto.CourseBaseInfoDto
   * @author Mr.M
   * @date 2022/10/9 8:13
   */
  public CourseBaseInfoDto getCourseBaseInfo(long courseId);

  /**
   * @description 修改专利信息
   * @param companyId  机构id
   * @param dto  专利信息
   * @return com.bs.content.model.dto.CourseBaseInfoDto
   * @author Mr.M
   * @date 2022/9/8 21:04
   */
  public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto);




}
