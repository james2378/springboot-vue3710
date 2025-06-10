package com.project.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 *宠物信息：(PetInformation)表实体类
 *
 */
@TableName("`pet_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class PetInformation implements Serializable {

    //PetInformation编号
    @TableId(value = "pet_information_id", type = IdType.AUTO)
    private Integer pet_information_id;
    // 宠物编号
    @TableField(value = "`pet_number`")
    private String pet_number;
    // 标题
    @TableField(value = "`title`")
    private String title;
    // 宠物品种
    @TableField(value = "`pet_variety`")
    private String pet_variety;
    // 照片
    @TableField(value = "`photo`")
    private String photo;
    // 性别
    @TableField(value = "`gender`")
    private String gender;
    // 年龄
    @TableField(value = "`age`")
    private String age;
    // 是否绝育
    @TableField(value = "`sterilization`")
    private String sterilization;
    // 发布者编号
    @TableField(value = "`publisher_number`")
    private Integer publisher_number;
    // 发布者姓名
    @TableField(value = "`publisher_name`")
    private String publisher_name;
    // 疫苗接种情况
    @TableField(value = "`vaccination_status`")
    private String vaccination_status;
    // 详情
    @TableField(value = "`details`")
    private String details;
    // 点击数
    @TableField(value = "hits")
    private Integer hits;
    // 点赞数
    @TableField(value = "praise_len")
    private Integer praise_len;
    // 审核状态
    @TableField(value = "examine_state")
    private String examine_state;
    // 审核回复
    @TableField(value = "examine_reply")
    private String examine_reply;


    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}
