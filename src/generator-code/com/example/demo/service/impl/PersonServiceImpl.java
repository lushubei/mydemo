package com.example.demo.service.impl;

import com.example.demo.model.Person;
import com.example.demo.dao.PersonDAO;
import com.example.demo.service.IPersonService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-29
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonDAO, Person> implements IPersonService {
	
}
