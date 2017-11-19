package com.example.imakeyouth.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.imakeyouth.model.Validate_code;
import com.example.imakeyouth.dao.Validate_codeDAO;
import com.example.imakeyouth.service.IValidate_codeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 验证码发送记录 服务实现类
 * </p>
 *
 * @author xiaobei
 * @since 2017-11-18
 */
@Service
public class Validate_codeServiceImpl extends ServiceImpl<Validate_codeDAO, Validate_code> implements IValidate_codeService {
    @Override
    public Validate_code queryByValidateCodeAndUserId(long userId,String validateCode){

        EntityWrapper<Validate_code> wrapper=new EntityWrapper<>();
        wrapper.eq("validate_code",validateCode);
        wrapper.eq("to_user_id",userId);
        wrapper.gt("expiry_date",new Date());

        return this.selectOne(wrapper);
    }
}
