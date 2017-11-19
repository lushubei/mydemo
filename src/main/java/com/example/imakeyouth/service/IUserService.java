package com.example.imakeyouth.service;

import com.example.imakeyouth.model.User;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
public interface IUserService extends IService<User> {
    void add(String username, String email, String mobileNo, long operatorId);

    User login(String username, String password,boolean isRememberWeekly, boolean isEncrypted);

    void setMyPassword(long userId, String oldPassword, String newPassword);

    void resetPassword(long userId, long operatorId);

    void updateBaseInfo(long userId, long operatorId, String email, String mobileNo);

    void delete(long userId, long operatorId);

    User queryBySessionId(String sessionId);

    User queryByUserName(String username);

    void setPasswordByValidateCode(long userId, String validateCode, String newPassword);

    User queryByUserNameAndPwd(String username, String password, boolean isEncrypted);
	
}
