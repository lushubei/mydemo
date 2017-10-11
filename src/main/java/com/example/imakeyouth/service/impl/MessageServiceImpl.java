package com.example.imakeyouth.service.impl;

import com.example.imakeyouth.model.Message;
import com.example.imakeyouth.dao.MessageDAO;
import com.example.imakeyouth.service.IMessageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 留言表 服务实现类
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageDAO, Message> implements IMessageService {
	
}
