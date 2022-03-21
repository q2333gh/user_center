package com.q2333.user_center.Mapper;

import com.q2333.user_center.model.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 白桃乌龙
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-03-20 10:55:28
* @Entity generator.domain.user
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




