package com.example.imakeyouth.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.imakeyouth.common.Constants;
import com.example.imakeyouth.common.utils.MD5Utils;
import com.example.imakeyouth.exception.ApplicationException;
import com.example.imakeyouth.model.User;
import com.example.imakeyouth.dao.UserDAO;
import com.example.imakeyouth.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.imakeyouth.service.IValidate_codeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDAO, User> implements IUserService {

    @Autowired
    private IValidate_codeService validateCodeService;

    @Override
    public void add(String username, String email, String mobileNo, long operatorId) {

        // 用户名唯一性校验
        User checkUser=this.queryByUserName(username);
        if(checkUser!=null){
            throw new ApplicationException("用户名已存在！");
        }
        User user = new User();
//        user.setEmail(email);
//        user.setUsername(username);
//        user.setUserType(UserTypeEnum.site_admin.getCode());
//        user.setMobileNo(mobileNo);
//        user.setCreatorId(operatorId);
//        user.setUpdatorId(operatorId);
        user.setPassword(MD5Utils.MD5UserPwd(Constants.DEFAULT_PASSWORD, Constants.DEFAULT_PASSWORD_KEY));
        this.insert(user);
    }

    @Override
    public User login(String username, String password, boolean isRememberWeekly, boolean isEncrypted) {

        User user = this.queryByUserNameAndPwd(username, password,isEncrypted);
        if (user == null) {
            throw new ApplicationException("用户名或密码错误！");
        }
        // 更新sessionId
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
//        if (isRememberWeekly) {
//            cal.add(Calendar.MINUTE, Constants.SESSION_EXPIRED_MINUTES_WEEKLY);
//            user.setSessionExpiredMinutes(Constants.SESSION_EXPIRED_MINUTES_WEEKLY);
//        } else {
//            cal.add(Calendar.MINUTE, Constants.SESSION_EXPIRED_MINUTES);
//            user.setSessionExpiredMinutes(Constants.SESSION_EXPIRED_MINUTES);
//        }
//        user.setSessionId(UUID.randomUUID().toString());
//        user.setSessionExpiredDate(cal.getTime());
//        user.setCreatedDate(null);
//        user.setUpdatedDate(null);
//        user.setUpdatorId(null);
//        user.setCreatorId(null);
        this.updateById(user);
        return user;
    }

    @Override
    public void setMyPassword(long userId, String oldPassword, String newPassword) {
        // 验证旧密码是否正确
        User user = this.selectById(userId);
        if (user == null) {
            throw new ApplicationException("用户不存在");
        }
        if (!user.getPassword().equals(MD5Utils.MD5UserPwd(oldPassword, Constants.DEFAULT_PASSWORD_KEY))) {
            throw new ApplicationException("原密码验证失败！");
        }
        // 修改密码
        User model = new User();
//        model.setId(userId);
        model.setPassword(MD5Utils.MD5UserPwd(newPassword, Constants.DEFAULT_PASSWORD_KEY));
//        model.setUpdatorId(userId);
        this.updateById(model);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void setPasswordByValidateCode(long userId, String validateCode, String newPassword) {

//        ValidateCode mailHistory = validateCodeService.queryByValidateCodeAndUserId(userId, validateCode);
//        if (mailHistory == null) {
//            throw new ApplicationException("验证失败，请检查验证码！");
//        }
        User user = new User();
//        user.setId(userId);
        user.setPassword(MD5Utils.MD5UserPwd(newPassword, Constants.DEFAULT_PASSWORD_KEY));
//        user.setUpdatorId(0L);
        this.updateById(user);
        // 从当前时刻开始，该验证码失效
//        mailHistory.setExpiryDate(new Date());
//        validateCodeService.updateById(mailHistory);
    }

    @Override
    public void resetPassword(long userId, long operatorId) {
        User user = new User();
//        user.setId(userId);
        user.setPassword(MD5Utils.MD5UserPwd(Constants.DEFAULT_PASSWORD, Constants.DEFAULT_PASSWORD_KEY));
//        user.setUpdatorId(operatorId);
        this.updateById(user);
    }

    @Override
    public void updateBaseInfo(long userId, long operatorId, String email, String mobileNo) {
        User user = new User();
//        user.setId(userId);
//        user.setUpdatorId(operatorId);
//        user.setEmail(email);
//        user.setMobileNo(mobileNo);
        this.updateById(user);

    }

    @Override
    public void delete(long userId, long operatorId) {
        User user = new User();
//        user.setId(userId);
//        user.setUpdatorId(operatorId);
//        user.setIsDeleted(true);
        this.updateById(user);

    }

    @Override
    public User queryBySessionId(String sessionId) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        Date now = new Date();
        wrapper.eq("is_deleted", false);
        wrapper.eq("session_id", sessionId);
        wrapper.gt("session_expired_date", now);
        User user = this.selectOne(wrapper);
        if (user != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
//            cal.add(Calendar.MINUTE, user.getSessionExpiredMinutes());
//            user.setSessionExpiredDate(cal.getTime());
//            user.setCreatedDate(null);
//            user.setUpdatedDate(null);
//            user.setUpdatorId(null);
//            user.setCreatorId(null);
            this.updateById(user);
        }
        return user;
    }

    @Override
    public User queryByUserName(String username) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", false);
        wrapper.eq("username", username);
        return this.selectOne(wrapper);
    }

    @Override
    public User queryByUserNameAndPwd(String username, String password, boolean isEncrypted) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("is_deleted", false);
        wrapper.eq("username", username);
        if (isEncrypted == false) {
            wrapper.eq("password", MD5Utils.MD5UserPwd(password, Constants.DEFAULT_PASSWORD_KEY));
        }
        else{
            wrapper.eq("password", password);
        }
        return this.selectOne(wrapper);
    }


}
