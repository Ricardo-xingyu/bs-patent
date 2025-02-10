package com.xuecheng.content.model.po;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 专利发布
 * </p>
 *
 * @author itcast
 */
@Data
@TableName("course_publish")
public class CoursePublish implements Serializable {

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
     * 专利等级
     */
    private String grade;

    /**
     * 专利图片
     */
    private String pic;

    /**
     * 专利介绍
     */
    private String description;

    /**
     * 课程营销信息，json格式
     */
    private String market;

    /**
     * 作者信息，json格式
     */
    private String teachers;

    /**
     * 发布时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createDate;

    /**
     * 上架时间
     */
    private LocalDateTime onlineDate;

    /**
     * 下架时间
     */
    private LocalDateTime offlineDate;

    /**
     * 发布状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;


}
