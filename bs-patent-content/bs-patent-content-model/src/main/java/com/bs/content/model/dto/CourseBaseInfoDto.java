package com.bs.content.model.dto;


import com.bs.content.model.po.CourseBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @description 专利基本信息dto
 * @author Mr.M
 * @date 2022/9/7 17:44
 * @version 1.0
 */
@Data
public class CourseBaseInfoDto extends CourseBase {

// /**
//  * 咨询qq
//  */
// private String qq;
//
// /**
//  * 微信
//  */
// private String wechat;
//
// /**
//  * 电话
//  */
// private String phone;
//
// /**
//  * 有效期天数
//  */
// private Integer validDays;

 /**
  * 大分类名称
  */
 private String mtName;

 /**
  * 小分类名称
  */
 private String stName;

}
