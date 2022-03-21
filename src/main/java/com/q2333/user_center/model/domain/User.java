package com.q2333.user_center.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
//mybatisXG,自动生成mysql里面的字段
/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户的主键ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**     * 用户的昵称
     */
    private String username;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户头像

     */
    private String avatorURL;

    /**
     * 性别

     */
    private Integer gender;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 电话号码

     */
    private String phone;

    /**
     * 邮箱

     */
    private String email;

    /**
     * 0:正常
1:异常

     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间

     */
    private Date updateTime;

    /**
     * 是否标记删除
     */
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}