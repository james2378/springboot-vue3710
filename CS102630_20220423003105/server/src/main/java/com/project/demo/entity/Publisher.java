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
 *发布者：(Publisher)表实体类
 *
 */
@TableName("`publisher`")
@Data
@EqualsAndHashCode(callSuper = false)
public class Publisher implements Serializable {

    //Publisher编号
    @TableId(value = "publisher_id", type = IdType.AUTO)
    private Integer publisher_id;
    // 发布者编号
    @TableField(value = "`publisher_number`")
    private String publisher_number;
    // 发布者姓名
    @TableField(value = "`publisher_name`")
    private String publisher_name;
    // 用户编号
    @TableField(value = "user_id")
    private Integer userId;


    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;

}
