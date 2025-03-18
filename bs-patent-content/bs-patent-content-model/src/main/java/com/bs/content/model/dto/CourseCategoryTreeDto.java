package com.bs.content.model.dto;


import com.bs.content.model.po.CourseCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description 专利分类树型结点dto
 * @author Mr.M
 * @date 2022/9/7 15:16
 * @version 1.0
 */
@Data
public class CourseCategoryTreeDto extends CourseCategory implements Serializable {

  List<CourseCategoryTreeDto> childrenTreeNodes;
}
