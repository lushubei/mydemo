package com.example.imakeyouth.web;

import com.example.demo.vo.resp.GatewayResp;
import com.example.imakeyouth.dao.BlogDAO;
import com.example.imakeyouth.dao.UserDAO;
import com.example.imakeyouth.model.Blog;
import com.example.imakeyouth.vo.req.BlogReq;
import com.example.imakeyouth.vo.resp.BlogResp;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 博客 前端控制器
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */

@RestController
@Api("博客管理相关API")
@RequestMapping("//blog")
public class BlogController {

    @Autowired
    BlogDAO dao;

    @Autowired
    UserDAO userDAO;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ApiOperation(value="获取所有列表", notes="查询所有博客信息")
    public GatewayResp<List<BlogResp>> getBlogList(){

        List<Blog> blogList = dao.getBlogList();
        List<BlogResp> blogResqList = new ArrayList<BlogResp>();

        for(Blog blog:blogList){
            dao.updateBlogPageView(blog.getId());
            BlogResp blogResp = new BlogResp();
            blogResp.setId(blog.getId());
            blogResp.setAuthor_id(blog.getAuthor_id());
            blogResp.setAuthor_name("xiaobei");
            blogResp.setContent(blog.getContent());
            blogResp.setPage_view(blog.getPage_view());
            blogResp.setPicture(blog.getPicture());
            blogResp.setTitle(blog.getTitle());
            blogResp.setUpdate_time(blog.getUpdate_time().toString());
            blogResqList.add(blogResp);
        }

        GatewayResp<List<BlogResp>> resp = new GatewayResp<>();

        resp.setData(blogResqList);
        return resp;
    }

    @ApiOperation(value="查询博客信息", notes="查询某博客信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "博客ID",required = true,dataType = "Integer")
    )
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public GatewayResp<BlogResp> getBlog(@PathVariable Integer id){

        dao.updateBlogPageView(id);

        GatewayResp<BlogResp> resp = new GatewayResp<>();

        Blog blog = dao.getBlog(id);
        BlogResp blogResp = new BlogResp();
        blogResp.setId(blog.getId());
        blogResp.setAuthor_id(blog.getAuthor_id());
        System.out.println(blog.getAuthor_id());

        String name = userDAO.getUser(blog.getAuthor_id()).getUser_name();
        blogResp.setAuthor_name(name);

        blogResp.setContent(blog.getContent());
        blogResp.setPage_view(blog.getPage_view());
        blogResp.setPicture(blog.getPicture());
        blogResp.setTitle(blog.getTitle());
        blogResp.setUpdate_time(blog.getUpdate_time().toString());

        resp.setData(blogResp);

        return resp;
    }

    @ApiOperation(value="写博客", notes="写博客")
    @ApiImplicitParam(name = "blog_req",value = "博客信息",required = true,dataType = "Blog")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public GatewayResp<List<Blog>> addBlog(@RequestBody BlogReq blog_req){

        Date date = new Date();
        System.out.println(date.getTime());
        Date create_time = new Date(date.getTime());
        Date update_time = create_time;
        Integer author_id = 1;
        dao.addBlog(blog_req.getTitle(),blog_req.getPicture(),blog_req.getContent(),author_id,create_time,update_time);

        GatewayResp<List<Blog>> resp = new GatewayResp<>();
        resp.setData(dao.getBlogList());

        return resp;
    }

    @ApiOperation(value="修改博客", notes="修改博客")
    @ApiImplicitParam(name = "blog_req",value = "博客信息",required = true,dataType = "Blog")
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public GatewayResp<BlogResp> updatePerson(@RequestBody BlogReq blog_req){
        Date date = new Date();
        System.out.println(date.getTime());
        Date now_time = new Date(date.getTime());
        Date update_time = now_time;
        Integer author_id = 1;
        dao.updateBlog(blog_req.getId(),blog_req.getTitle(),blog_req.getPicture(),blog_req.getContent(),author_id,update_time);

        GatewayResp<BlogResp> resp = new GatewayResp<>();

        Blog blog = dao.getBlog(blog_req.getId());
        BlogResp blogResp = new BlogResp();
        blogResp.setId(blog.getId());
        blogResp.setAuthor_id(blog.getAuthor_id());

        String name = userDAO.getUser(blog.getAuthor_id()).getUser_name();
        blogResp.setAuthor_name(name);

        blogResp.setContent(blog.getContent());
        blogResp.setPage_view(blog.getPage_view());
        blogResp.setPicture(blog.getPicture());
        blogResp.setTitle(blog.getTitle());
        blogResp.setUpdate_time(blog.getUpdate_time().toString());

        resp.setData(blogResp);
        return resp;
    }

    @ApiOperation(value="删除博客", notes="根据博客ID删除博客")
    @ApiImplicitParam(name = "id",value = "博客ID",required = true,dataType = "Integer")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public GatewayResp<List<BlogResp>> deleteBlog(@PathVariable Integer id){
        dao.deleteBlog(id);

        List<Blog> blogList = dao.getBlogList();
        List<BlogResp> blogResqList = new ArrayList<BlogResp>();

        for(Blog blog:blogList){
            dao.updateBlogPageView(blog.getId());
            BlogResp blogResp = new BlogResp();
            blogResp.setId(blog.getId());
            blogResp.setAuthor_id(blog.getAuthor_id());
            blogResp.setAuthor_name("xiaobei");
            blogResp.setContent(blog.getContent());
            blogResp.setPage_view(blog.getPage_view());
            blogResp.setPicture(blog.getPicture());
            blogResp.setTitle(blog.getTitle());
            blogResp.setUpdate_time(blog.getUpdate_time().toString());
            blogResqList.add(blogResp);
        }


        GatewayResp<List<BlogResp>> resp = new GatewayResp<>();

        resp.setData(blogResqList);

        return resp;
    }

}