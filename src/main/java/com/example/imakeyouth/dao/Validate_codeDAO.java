package com.example.imakeyouth.dao;

import com.example.imakeyouth.model.Validate_code;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
  * 验证码发送记录 Mapper 接口
 * </p>
 *
 * @author xiaobei
 * @since 2017-11-18
 */
@Repository
@Mapper
public interface Validate_codeDAO extends BaseMapper<Validate_code> {

}