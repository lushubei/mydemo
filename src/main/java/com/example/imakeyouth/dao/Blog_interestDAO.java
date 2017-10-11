package com.example.imakeyouth.dao;

import com.example.imakeyouth.model.Blog_interest;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
  * 博客，兴趣关系表 Mapper 接口
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
@Repository
@Mapper
public interface Blog_interestDAO extends BaseMapper<Blog_interest> {

}