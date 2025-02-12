package com.bs.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.content.model.dto.CourseCategoryTreeDto;
import com.bs.content.model.po.CourseCategory;

import java.util.List;


/**
 * <p>
 * 专利分类 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {
    public List<CourseCategoryTreeDto> selectTreeNodes(String id);
}
