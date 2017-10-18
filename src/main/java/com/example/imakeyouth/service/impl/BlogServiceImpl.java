package com.example.imakeyouth.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.example.imakeyouth.model.Blog;
import com.example.imakeyouth.dao.BlogDAO;
import com.example.imakeyouth.service.IBlogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客 服务实现类
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogDAO, Blog> implements IBlogService {

//    @Autowired
//    BlogDAO blogDAO;
//
//    public Page<Blog> selectBlogList(Page<Blog> page, Integer state){
//        page.setRecords(blogDAO.selectBlogList(page,state));
//        return page;
//    }
}
