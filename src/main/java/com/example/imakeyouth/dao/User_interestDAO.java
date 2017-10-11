package com.example.imakeyouth.dao;

import com.example.imakeyouth.model.User_interest;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
  * 用户兴趣表 Mapper 接口
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
@Repository
@Mapper
public interface User_interestDAO extends BaseMapper<User_interest> {

}