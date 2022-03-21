package com.q2333.user_center.service;

import com.q2333.user_center.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 白桃乌龙
* @description 针对表【user】的数据库操作Service
* @createDate 2022-03-20 10:55:28
 *
 * >>>userService:业务接口,,
 *
 * 定义需求,出入输出什么.impl真正编写具体逻辑,实现方法代码等
*/

public interface UserService extends IService<User> {
    /**
     * userRegister 方法:用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);
}
