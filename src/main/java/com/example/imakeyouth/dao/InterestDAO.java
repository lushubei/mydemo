package com.example.imakeyouth.dao;

import com.example.imakeyouth.model.Interest;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
  * 兴趣点 Mapper 接口
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
@Repository
@Mapper
public interface InterestDAO extends BaseMapper<Interest> {

}