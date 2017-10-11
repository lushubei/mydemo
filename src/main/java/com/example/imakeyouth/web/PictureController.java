package com.example.imakeyouth.web;

import com.example.demo.vo.resp.GatewayResp;
import com.example.imakeyouth.dao.PictureDAO;
import com.example.imakeyouth.model.Picture;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 图片 前端控制器
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
@Controller
@RequestMapping("/picture")
@Api("图片相关API")
public class PictureController {
    @Autowired
    PictureDAO dao;



    @ApiOperation(value="查询图片信息", notes="查询某图片信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "图片ID",required = true,dataType = "Integer")
    )
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public GatewayResp<Picture> getPicture(@PathVariable Integer id){


        GatewayResp<Picture> resp = new GatewayResp<>();

        Picture picture = dao.getPicture(id);

        resp.setData(picture);

        return resp;
    }

    @ApiOperation(value="上传图片", notes="上传图片")
    @ApiImplicitParam(name = "picture_req",value = "图片信息",required = true,dataType = "PictureReq")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public GatewayResp<List<String>> addPicture(HttpServletRequest request) throws IOException {
        GatewayResp<List<String>> resp = new GatewayResp<>();
        List<String> list = new ArrayList();

        System.out.println(request);

        // 保存
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        System.out.println(files);

        MultipartFile file = null;
        BufferedOutputStream stream = null;
        Date date = new Date();
        String uploadFilePath = "./src/web-static/images/upload/";
        for (int i=0 ;i < files.size();i++) {
            file = files.get(i);

            if(!file.isEmpty()){
                byte[] byteBuff = file.getBytes();
                String fileName = file.getOriginalFilename();
                String extName = fileName.substring(fileName.lastIndexOf("."));
                String newFileName =  date.getTime() + extName;
                String saveFileName = uploadFilePath + newFileName;

                System.out.println(saveFileName);

                stream = new BufferedOutputStream(new FileOutputStream(new File(saveFileName)));
                stream.write(byteBuff);
                stream.close();

                String file_url = "images/upload/" + newFileName;

                //save to DB
                dao.addPicture(file_url);

                resp.setMsg("上传成功");

                list.add(file_url);

            } else {
                resp.setMsg("You failed to upload " + i
                        + " because the file was empty.");
                return resp;
            }

        }

        resp.setData(list);
        System.out.println("返回成功：" + list);
        return resp;
    }


//    @ApiOperation(value="删除图片", notes="根据图片ID删除图片")
//    @ApiImplicitParam(name = "id",value = "博客ID",required = true,dataType = "Integer")
//    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
//    public GatewayResp<List<BlogResp>> deleteBlog(@PathVariable Integer id){
//        dao.deleteBlog(id);
//
//        List<Blog> blogList = dao.getBlogList();
//        List<BlogResp> blogResqList = new ArrayList<BlogResp>();
//
//        for(Blog blog:blogList){
//            dao.updateBlogPageView(blog.getId());
//            BlogResp blogResp = new BlogResp();
//            blogResp.setId(blog.getId());
//            blogResp.setAuthor_id(blog.getAuthor_id());
//            blogResp.setAuthor_name("xiaobei");
//            blogResp.setContent(blog.getContent());
//            blogResp.setPage_view(blog.getPage_view());
//            blogResp.setPicture(blog.getPicture());
//            blogResp.setTitle(blog.getTitle());
//            blogResp.setUpdate_time(blog.getUpdate_time().toString());
//            blogResqList.add(blogResp);
//        }
//
//
//        GatewayResp<List<BlogResp>> resp = new GatewayResp<>();
//
//        resp.setData(blogResqList);
//
//        return resp;
//    }

}
