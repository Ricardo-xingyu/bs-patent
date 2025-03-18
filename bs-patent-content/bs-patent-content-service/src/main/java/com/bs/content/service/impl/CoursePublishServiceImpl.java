package com.bs.content.service.impl;


import com.alibaba.fastjson.JSON;
import com.bs.base.exception.BSException;
import com.bs.base.exception.CommonError;
import com.bs.content.mapper.CourseBaseMapper;
import com.bs.content.mapper.CoursePublishMapper;
import com.bs.content.mapper.CoursePublishPreMapper;
import com.bs.content.model.dto.CourseBaseInfoDto;
import com.bs.content.model.dto.CoursePreviewDto;
import com.bs.content.model.po.CourseBase;
import com.bs.content.model.po.CoursePublish;
import com.bs.content.model.po.CoursePublishPre;
import com.bs.content.service.CourseBaseInfoService;
import com.bs.content.service.CoursePublishService;
import com.bs.messagesdk.model.po.MqMessage;
import com.bs.messagesdk.service.MqMessageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description TODO
 * @author Mr.M
 * @date 2022/9/16 15:37
 * @version 1.0
 */
@Service
public class CoursePublishServiceImpl implements CoursePublishService {

 @Autowired
 CourseBaseInfoService courseBaseInfoService;

 @Autowired
 CourseBaseMapper courseBaseMapper;

 @Autowired
 CoursePublishPreMapper coursePublishPreMapper;

 @Autowired
 CoursePublishMapper coursePublishMapper;

 @Autowired
 MqMessageService mqMessageService;



 @Override
 public CoursePreviewDto getCoursePreviewInfo(Long courseId) {

  //专利基本信息、营销信息
  CourseBaseInfoDto courseBaseInfo = courseBaseInfoService.getCourseBaseInfo(courseId);


  CoursePreviewDto coursePreviewDto = new CoursePreviewDto();
  coursePreviewDto.setCourseBase(courseBaseInfo);
  return coursePreviewDto;
 }

 @Override
 public void commitAudit(Long companyId, Long courseId) {

  //约束校验
  CourseBase courseBase = courseBaseMapper.selectById(courseId);
  //专利审核状态
  String auditStatus = courseBase.getAuditStatus();
  //当前审核状态为已提交不允许再次提交
  if("202003".equals(auditStatus)){
   BSException.cast("当前为等待审核状态，审核完成可以再次提交。");
  }
  //本机构只允许提交本机构的专利
  if(!courseBase.getCompanyId().equals(companyId)){
   BSException.cast("不允许提交其它机构的专利。");
  }

  //专利图片是否填写
  if(StringUtils.isEmpty(courseBase.getPic())){
   BSException.cast("提交失败，请上传专利图片");
  }

  //添加专利预发布记录
  CoursePublishPre coursePublishPre = new CoursePublishPre();
  //专利基本信息加部分营销信息
  CourseBaseInfoDto courseBaseInfo = courseBaseInfoService.getCourseBaseInfo(courseId);
  BeanUtils.copyProperties(courseBaseInfo,coursePublishPre);

  //设置预发布记录状态,已提交
  coursePublishPre.setStatus("202003");
  //机构id
  coursePublishPre.setCompanyId(companyId);
  //提交时间
  coursePublishPre.setCreateDate(LocalDateTime.now());
  CoursePublishPre coursePublishPreUpdate = coursePublishPreMapper.selectById(courseId);
  if(coursePublishPreUpdate == null){
   //添加专利预发布记录
   coursePublishPreMapper.insert(coursePublishPre);
  }else{
   coursePublishPreMapper.updateById(coursePublishPre);
  }

  //更新专利基本表的审核状态
  courseBase.setAuditStatus("202003");
  courseBaseMapper.updateById(courseBase);
 }

 @Transactional
 @Override
 public void publish(Long companyId, Long courseId) {

  //约束校验
  //查询专利预发布表
  CoursePublishPre coursePublishPre = coursePublishPreMapper.selectById(courseId);
  if(coursePublishPre == null){
   BSException.cast("请先提交专利审核，审核通过才可以发布");
  }
  //本机构只允许提交本机构的专利
  if(!coursePublishPre.getCompanyId().equals(companyId)){
   BSException.cast("不允许提交其它机构的专利。");
  }


  //专利审核状态
  String auditStatus = coursePublishPre.getStatus();
  //审核通过方可发布
  if(!"202004".equals(auditStatus)){
   BSException.cast("操作失败，专利审核通过方可发布。");
  }

  //保存专利发布信息
  saveCoursePublish(courseId);

  //保存消息表
  saveCoursePublishMessage(courseId);

  //删除专利预发布表对应记录
  coursePublishPreMapper.deleteById(courseId);

 }

 /**
  * @description 保存专利发布信息
  * @param courseId  专利id
  * @return void
  * @author Mr.M
  * @date 2022/9/20 16:32
  */
 private void saveCoursePublish(Long courseId){
  //整合专利发布信息
  //查询专利预发布表
  CoursePublishPre coursePublishPre = coursePublishPreMapper.selectById(courseId);
  if(coursePublishPre == null){
   BSException.cast("专利预发布数据为空");
  }

  CoursePublish coursePublish = new CoursePublish();

  //拷贝到专利发布对象
  BeanUtils.copyProperties(coursePublishPre,coursePublish);
  coursePublish.setStatus("203002");
  CoursePublish coursePublishUpdate = coursePublishMapper.selectById(courseId);
  if(coursePublishUpdate == null){
   coursePublishMapper.insert(coursePublish);
  }else{
   coursePublishMapper.updateById(coursePublish);
  }
  //更新专利基本表的发布状态
  CourseBase courseBase = courseBaseMapper.selectById(courseId);
  courseBase.setStatus("203002");
  courseBaseMapper.updateById(courseBase);

 }

 /**
  * @description 保存消息表记录，稍后实现
  * @param courseId  专利id
  * @return void
  * @author Mr.M
  * @date 2022/9/20 16:32
  */
 private void saveCoursePublishMessage(Long courseId){
  MqMessage mqMessage = mqMessageService.addMessage("course_publish", String.valueOf(courseId), null, null);
  if(mqMessage==null){
   BSException.cast(CommonError.UNKOWN_ERROR);
  }


 }




}
