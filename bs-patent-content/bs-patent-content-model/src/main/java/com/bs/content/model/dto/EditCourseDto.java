package com.bs.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @description 添加专利dto
 * @author Mr.M
 * @date 2022/9/7 17:40
 * @version 1.0
 */
@Data
@ApiModel(value="EditCourseDto", description="修改专利基本信息")
public class EditCourseDto extends AddCourseDto {

 @ApiModelProperty(value = "专利id", required = true)
 private Long id;

}
