package com.example.imakeyouth.dao;

import com.example.imakeyouth.model.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import io.swagger.annotations.ApiImplicitParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
  * 用户表 Mapper 接口
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
@Repository
@Mapper
public interface UserDAO extends BaseMapper<User> {

    @Select("select * from user where id = #{id}")
    User getUser(@Param("id") Integer id);
}