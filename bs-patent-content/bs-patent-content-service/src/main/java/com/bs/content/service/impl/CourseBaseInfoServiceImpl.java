package com.bs.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bs.base.exception.BSException;
import com.bs.base.model.PageParams;
import com.bs.base.model.PageResult;
import com.bs.content.mapper.CourseBaseMapper;
import com.bs.content.mapper.CourseCategoryMapper;
import com.bs.content.model.dto.AddCourseDto;
import com.bs.content.model.dto.CourseBaseInfoDto;
import com.bs.content.model.dto.EditCourseDto;
import com.bs.content.model.dto.QueryCourseParamsDto;
import com.bs.content.model.po.CourseBase;
import com.bs.content.model.po.CourseCategory;
import com.bs.content.service.CourseBaseInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description 专利信息管理业务接口实现类
 * @author Mr.M
 * @date 2022/9/6 21:45
 * @version 1.0
 */
@Service
public class  CourseBaseInfoServiceImpl  implements CourseBaseInfoService {


    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Override
    public PageResult<CourseBase> queryCourseBaseList(String user, PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {


        //构建查询条件对象
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();
        //构建查询条件，根据专利名称查询
        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),CourseBase::getName,queryCourseParamsDto.getCourseName());
        //构建查询条件，根据专利审核状态查询
        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),CourseBase::getAuditStatus,queryCourseParamsDto.getAuditStatus());
//构建查询条件，根据专利发布状态查询
//todo:根据专利发布状态查询
        //根据用户名称查询
        queryWrapper.eq(CourseBase::getCreatePeople,user);


        //分页对象
        Page<CourseBase> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        // 查询数据内容获得结果
        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);
        // 获取数据列表
        List<CourseBase> list = pageResult.getRecords();
        // 获取数据总数
        long total = pageResult.getTotal();
        // 构建结果集
        PageResult<CourseBase> courseBasePageResult = new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());
        return courseBasePageResult;


    }

    @Transactional
    @Override
    public CourseBaseInfoDto createCourseBase(Long companyId, AddCourseDto dto) {

        //合法性校验
        if (StringUtils.isBlank(dto.getName())) {
            BSException.cast("专利名称为空");
        }

        if (StringUtils.isBlank(dto.getMt())) {
            BSException.cast("专利分类为空");
        }

        if (StringUtils.isBlank(dto.getSt())) {
            BSException.cast("专利分类为空");
        }

        //新增对象
        CourseBase courseBaseNew = new CourseBase();
        //将填写的专利信息赋值给新增对象
        BeanUtils.copyProperties(dto,courseBaseNew);
        //设置审核状态
        courseBaseNew.setAuditStatus("202002");
        //设置发布状态
        courseBaseNew.setStatus("203001");
        //机构id
        courseBaseNew.setCompanyId(companyId);
        //添加时间
        courseBaseNew.setCreateDate(LocalDateTime.now());
        //添加创建人
        courseBaseNew.setCreatePeople(dto.getApplicant());
        //插入专利基本信息表
        int insert = courseBaseMapper.insert(courseBaseNew);
        if(insert<=0){
            throw new RuntimeException("新增专利基本信息失败");
        }
        Long courseId = courseBaseNew.getId();
        return getCourseBaseInfo(courseId);

    }


    @Autowired
    CourseCategoryMapper courseCategoryMapper;
    //根据专利id查询专利基本信息，包括基本信息和营销信息
    public CourseBaseInfoDto getCourseBaseInfo(long courseId){

        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if(courseBase == null){
            return null;
        }
//        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase,courseBaseInfoDto);

        //查询分类名称
        CourseCategory courseCategoryBySt = courseCategoryMapper.selectById(courseBase.getSt());
        courseBaseInfoDto.setStName(courseCategoryBySt.getName());
        CourseCategory courseCategoryByMt = courseCategoryMapper.selectById(courseBase.getMt());
        courseBaseInfoDto.setMtName(courseCategoryByMt.getName());

        return courseBaseInfoDto;

    }

    /**
     * @param companyId 机构id
     * @param dto       专利信息
     * @return com.bs.content.model.dto.CourseBaseInfoDto
     * @description 修改专利信息
     * @author Mr.M
     * @date 2022/9/8 21:04
     */


    @Transactional
    @Override
    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto) {

        //专利id
        Long courseId = dto.getId();
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if(courseBase==null){
            BSException.cast("专利不存在");
        }

        //校验本机构只能修改本机构的专利
        if(!courseBase.getCompanyId().equals(companyId)){
            BSException.cast("本机构只能修改本机构的专利");
        }

        //封装基本信息的数据
        BeanUtils.copyProperties(dto,courseBase);
        courseBase.setChangeDate(LocalDateTime.now());

        //更新专利基本信息
        int i = courseBaseMapper.updateById(courseBase);

        //查询专利信息
        CourseBaseInfoDto courseBaseInfo = this.getCourseBaseInfo(courseId);
        return courseBaseInfo;

    }



}
