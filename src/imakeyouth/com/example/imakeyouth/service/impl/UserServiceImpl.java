package com.example.imakeyouth.service.impl;

import com.example.imakeyouth.model.User;
import com.example.imakeyouth.dao.UserDAO;
import com.example.imakeyouth.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
	
}
