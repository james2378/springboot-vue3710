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
 *宠物知识：(PetKnowledge)表实体类
 *
 */
@TableName("`pet_knowledge`")
@Data
@EqualsAndHashCode(callSuper = false)
public class PetKnowledge implements Serializable {

    //PetKnowledge编号
    @TableId(value = "pet_knowledge_id", type = IdType.AUTO)
    private Integer pet_knowledge_id;
    // 标题
    @TableField(value = "`title`")
    private String title;
    // 封面
    @TableField(value = "`cover`")
    private String cover;
    // 视频
    @TableField(value = "`video`")
    private String video;
    // 发布人
    @TableField(value = "`publisher`")
    private String publisher;
    // 发布时间
    @TableField(value = "`release_time`")
    private String release_time;
    // 简介
    @TableField(value = "`brief_introduction`")
    private String brief_introduction;
    // 详情
    @TableField(value = "`details`")
    private String details;
    // 点击数
    @TableField(value = "hits")
    private Integer hits;
    // 点赞数
    @TableField(value = "praise_len")
    private Integer praise_len;


    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}
