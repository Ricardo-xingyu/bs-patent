package com.bs.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @description 添加课程dto
 * @author Mr.M
 * @date 2022/9/7 17:40
 * @version 1.0
 */
@Data
@ApiModel(value="AddCourseDto", description="新增专利基本信息")
public class AddCourseDto {

 @NotEmpty(message = "专利名称不能为空")
 @ApiModelProperty(value = "专利名称", required = true)
 private String name;


 @ApiModelProperty(value = "专利标签")
 private String tags;

 @NotEmpty(message = "专利分类不能为空")
 @ApiModelProperty(value = "大分类", required = true)
 private String mt;

 @NotEmpty(message = "专利分类不能为空")
 @ApiModelProperty(value = "小分类", required = true)
 private String st;


 @ApiModelProperty(value = "专利介绍")
 private String description;

 @ApiModelProperty(value = "专利图片", required = true)
 private String pic;


// @ApiModelProperty(value = "qq")
// private String qq;
//
// @ApiModelProperty(value = "微信")
// private String wechat;
// @ApiModelProperty(value = "电话")
// private String phone;
//
// @ApiModelProperty(value = "有效期")
// private Integer validDays;
}
