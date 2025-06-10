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
 *宠物领养：(PetAdoption)表实体类
 *
 */
@TableName("`pet_adoption`")
@Data
@EqualsAndHashCode(callSuper = false)
public class PetAdoption implements Serializable {

    //PetAdoption编号
    @TableId(value = "pet_adoption_id", type = IdType.AUTO)
    private Integer pet_adoption_id;
    // 宠物编号
    @TableField(value = "`pet_number`")
    private String pet_number;
    // 标题
    @TableField(value = "`title`")
    private String title;
    // 性别
    @TableField(value = "`gender`")
    private String gender;
    // 年龄
    @TableField(value = "`age`")
    private String age;
    // 发布者编号
    @TableField(value = "`publisher_number`")
    private Integer publisher_number;
    // 发布者姓名
    @TableField(value = "`publisher_name`")
    private String publisher_name;
    // 领养者编号
    @TableField(value = "`adopter_number`")
    private Integer adopter_number;
    // 领养者姓名
    @TableField(value = "`adopter_name`")
    private String adopter_name;
    // 领养者电话
    @TableField(value = "`adopter_telephone`")
    private String adopter_telephone;
    // 领养说明
    @TableField(value = "`adoption_instructions`")
    private String adoption_instructions;
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
