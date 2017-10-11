package com.example.imakeyouth.dao;

import com.example.imakeyouth.model.User_activity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
  * 用户，虚拟项目关系表 Mapper 接口
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
@Repository
@Mapper
public interface User_activityDAO extends BaseMapper<User_activity> {

}