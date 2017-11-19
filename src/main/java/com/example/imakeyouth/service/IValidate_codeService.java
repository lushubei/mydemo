package com.example.imakeyouth.service;

import com.example.imakeyouth.model.Validate_code;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 验证码发送记录 服务类
 * </p>
 *
 * @author xiaobei
 * @since 2017-11-18
 */
public interface IValidate_codeService extends IService<Validate_code> {
    Validate_code queryByValidateCodeAndUserId(long userId,String validateCode);
}
