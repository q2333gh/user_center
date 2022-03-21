package com.q2333.user_center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.q2333.user_center.model.domain.User;
import com.q2333.user_center.service.UserService;
import com.q2333.user_center.Mapper.UserMapper;
import lombok.extern.slf4j.XSlf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
 * 用户服务实现类
 *
 * @author q2333
 */


@Service
@XSlf4j //监控日志
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Resource
    private  UserMapper userMapper;
    // 2.加密密码,SALT盐。混淆密码
    private static final String SALT = "yupi";
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1.校验非空
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return -1;
        }
        if (userAccount.length() < 4) {
            return -1;
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8){
            return -1;
        }
        // 账户不能包含特殊字符
        String validPattern = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        // 账户不能重复,查询数据库的操作放到靠后,节约资源
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = UserMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }
        // 2.加密密码
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }
        return user.getId();
    }
    @Override
    public User doLogin(String userAccount, String userPassword) {
        // 1.校验非空.账号，密码长度
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        if (userAccount.length() < 4) {
            return null;
        }
        if (userPassword.length() < 8 ){
            return null;
        }
        // 账户不能包含特殊字符
        String validPattern = "\\pP|\\pS|\\s+";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }
        //加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询数据库，用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        //用户不存在
        if (user == null){
            log.info("user login failed,userAccount cant match userPassword");
            return null;
        }
        //3.记录用户的登陆态

        return null;
    }
}




