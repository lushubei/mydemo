package com.example.imakeyouth.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.vo.resp.GatewayResp;
import com.example.imakeyouth.dao.BlogDAO;
import com.example.imakeyouth.dao.UserDAO;
import com.example.imakeyouth.model.Blog;
import com.example.imakeyouth.service.IBlogService;
import com.example.imakeyouth.service.IUserService;
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
    private BlogDAO dao;

    @Autowired
    private IBlogService iBlogService;

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ApiOperation(value="获取所有列表", notes="查询所有博客信息")
    public GatewayResp<List<BlogResp>> getBlogList(@RequestParam(value = "page", required = false) Integer page){

        EntityWrapper<Blog> ew = new EntityWrapper<>();

        GatewayResp<List<BlogResp>> resp = new GatewayResp<>();

        /**
         * page: 第几页
         * pageSize: 每页几行
         */
        System.out.println("page: "+ page);
        if(null == page)
        {
            page = 1;
        }
        Integer pageSize = 10;

        Page<Blog> pageRet = new Page<>(page, pageSize);
        Page<Blog> blogPage = iBlogService.selectPage(pageRet,ew);

        if(null != blogPage.getRecords()){
            List<BlogResp> blogResqList = new ArrayList<BlogResp>();
            blogResqList = this.makeResp(blogPage.getRecords());
            resp.setData(blogResqList);
        }

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

        Blog blog = iBlogService.selectById(id);

        BlogResp blogResp = new BlogResp();
        blogResp.setId(blog.getId());
        blogResp.setAuthor_id(blog.getAuthor_id());
        System.out.println(blog.getAuthor_id());


        if(iUserService.selectById(blog.getAuthor_id()) != null)
        {
            blogResp.setAuthor_name(iUserService.selectById(blog.getAuthor_id()).getUser_name());
        }else{
            blogResp.setAuthor_name("无名氏");
        }

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
        Date create_time = new Date(date.getTime());
        Date update_time = create_time;
        Integer author_id = 1;

        Blog b = new Blog();
        b.setTitle(blog_req.getTitle());
        b.setPicture(blog_req.getPicture());
        b.setContent(blog_req.getContent());
        b.setAuthor_id(author_id);
        b.setCreate_time(create_time);
        b.setUpdate_time(update_time);

        iBlogService.insert(b);
        GatewayResp<List<Blog>> resp = new GatewayResp<>();

        EntityWrapper<Blog> ew = new EntityWrapper<>();
        List<Blog> blogList = iBlogService.selectList(ew);
        resp.setData(blogList);

        return resp;
    }

    @ApiOperation(value="修改博客", notes="修改博客")
    @ApiImplicitParam(name = "blog_req",value = "博客信息",required = true,dataType = "Blog")
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public GatewayResp<BlogResp> updatePerson(@RequestBody BlogReq blog_req){
        Date date = new Date();
        Date now_time = new Date(date.getTime());
        Date update_time = now_time;
        {
            Blog blog = new Blog();
            blog.setId(blog_req.getId());
            blog.setTitle(blog_req.getTitle());
            blog.setPicture(blog_req.getPicture());
            blog.setContent(blog_req.getContent());
            blog.setAuthor_id(blog_req.getAuthor_id());
            blog.setUpdate_time(update_time);
            iBlogService.updateById(blog);
        }

        GatewayResp<BlogResp> resp = new GatewayResp<>();

        Blog blog = iBlogService.selectById(blog_req.getId());
        BlogResp blogResp = new BlogResp();
        blogResp.setId(blog.getId());
        blogResp.setAuthor_id(blog.getAuthor_id());

        if(iUserService.selectById(blog.getAuthor_id()) != null)
        {
            blogResp.setAuthor_name(iUserService.selectById(blog.getAuthor_id()).getUser_name());
        }else{
            blogResp.setAuthor_name("无名氏");
        }

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

        Boolean ret = iBlogService.deleteById(id);

        EntityWrapper<Blog> ew = new EntityWrapper<>();
        List<Blog> blogList = iBlogService.selectList(ew);

        List<BlogResp> blogResqList = this.makeResp(blogList);

        GatewayResp<List<BlogResp>> resp = new GatewayResp<>();

        resp.setData(blogResqList);

        return resp;
    }


    @RequestMapping(value = "/search",method = RequestMethod.GET)
    @ApiOperation(value="搜索博客", notes="搜索博客信息")
    public GatewayResp<List<BlogResp>> searchBlogList(@RequestParam(value = "searchMessage") String searchMessage){

        List<Blog> blogList = new ArrayList<Blog>();

        if(!searchMessage.isEmpty()){
            EntityWrapper<Blog> ew = new EntityWrapper<Blog>();
            ew.setEntity(new Blog());
            ew.like("title",searchMessage).or().like("content",searchMessage);

            blogList = iBlogService.selectList(ew);

        }else{
            EntityWrapper<Blog> ew = new EntityWrapper<>();
            blogList = iBlogService.selectList(ew);
        }

        List<BlogResp> blogResqList = new ArrayList<BlogResp>();

        blogResqList = this.makeResp(blogList);

        GatewayResp<List<BlogResp>> resp = new GatewayResp<>();

        resp.setData(blogResqList);
        return resp;
    }

    private List<BlogResp> makeResp(List<Blog> blogList){

        List<BlogResp> blogResqList = new ArrayList<>();

        for(Blog blog:blogList){

            dao.updateBlogPageView(blog.getId());
            BlogResp blogResp = new BlogResp();
            blogResp.setId(blog.getId());
            blogResp.setAuthor_id(blog.getAuthor_id());
            if(iUserService.selectById(blog.getAuthor_id()) != null)
            {
                blogResp.setAuthor_name(iUserService.selectById(blog.getAuthor_id()).getUser_name());
            }else{
                blogResp.setAuthor_name("无名氏");
            }
            blogResp.setContent(blog.getContent());
            blogResp.setPage_view(blog.getPage_view());
            blogResp.setPicture(blog.getPicture());
            blogResp.setTitle(blog.getTitle());
            blogResp.setUpdate_time(blog.getUpdate_time().toString());
            blogResqList.add(blogResp);
        }
        return blogResqList;
    }
}