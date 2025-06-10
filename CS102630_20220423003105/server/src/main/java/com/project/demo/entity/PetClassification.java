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
 *宠物分类：(PetClassification)表实体类
 *
 */
@TableName("`pet_classification`")
@Data
@EqualsAndHashCode(callSuper = false)
public class PetClassification implements Serializable {

    //PetClassification编号
    @TableId(value = "pet_classification_id", type = IdType.AUTO)
    private Integer pet_classification_id;
    // 宠物品种
    @TableField(value = "`pet_variety`")
    private String pet_variety;


    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}
