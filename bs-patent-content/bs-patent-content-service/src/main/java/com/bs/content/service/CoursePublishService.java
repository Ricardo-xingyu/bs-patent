package com.bs.content.service;

import com.bs.content.model.dto.CoursePreviewDto;

/**
 * @description 专利预览、发布接口
 * @author Mr.M
 * @date 2022/9/16 14:59
 * @version 1.0
 */
public interface CoursePublishService {


 /**
  * @description 获取专利预览信息
  * @param courseId 专利id
  * @return com.bs.content.model.dto.CoursePreviewDto
  * @author Mr.M
  * @date 2022/9/16 15:36
 */
   public CoursePreviewDto getCoursePreviewInfo(Long courseId);


    /**
     * @description 提交审核
     * @param courseId  专利id
     * @return void
     * @author Mr.M
     * @date 2022/9/18 10:31
     */
    public void commitAudit(Long companyId,Long courseId);

    /**
     * @description 专利发布接口
     * @param companyId 机构id
     * @param courseId 专利id
     * @return void
     * @author Mr.M
     * @date 2022/9/20 16:23
     */
    public void publish(Long companyId,Long courseId);




}
