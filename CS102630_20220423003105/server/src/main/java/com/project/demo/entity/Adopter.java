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
 *领养者：(Adopter)表实体类
 *
 */
@TableName("`adopter`")
@Data
@EqualsAndHashCode(callSuper = false)
public class Adopter implements Serializable {

    //Adopter编号
    @TableId(value = "adopter_id", type = IdType.AUTO)
    private Integer adopter_id;
    // 领养者编号
    @TableField(value = "`adopter_number`")
    private String adopter_number;
    // 领养者姓名
    @TableField(value = "`adopter_name`")
    private String adopter_name;
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
