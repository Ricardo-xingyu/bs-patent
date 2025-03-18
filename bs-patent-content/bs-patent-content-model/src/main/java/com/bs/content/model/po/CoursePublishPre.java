package com.bs.content.model.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 专利发布
 * </p>
 *
 * @author itcast
 */
@Data
@TableName("course_publish_pre")
public class CoursePublishPre implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 机构ID
     */
    private Long companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 专利名称
     */
    private String name;

    /**
     * 标签
     */
    private String tags;

    /**
     * 创建人
     */
    private String username;

    /**
     * 大分类
     */
    private String mt;

    /**
     * 大分类名称
     */
    private String mtName;

    /**
     * 小分类
     */
    private String st;

    /**
     * 小分类名称
     */
    private String stName;


    /**
     * 专利图片
     */
    private String pic;

    /**
     * 专利介绍
     */
    private String description;


    /**
     * 提交时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 审核时间
     */
    private LocalDateTime auditDate;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


    /**
     * 申请人
     */
    private String applicant;

    /**
     * 发明人
     */
    private String inventor;

    /**
     * 国家
     */
    private String country;


}
