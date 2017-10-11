package com.example.imakeyouth.dao;

import com.example.demo.dal.Person;
import com.example.imakeyouth.model.Blog;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <p>
  * 博客 Mapper 接口
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
@Repository
@Mapper
public interface BlogDAO extends BaseMapper<Blog> {


    @Select("select * from blog order by update_time desc")
    List<Blog> getBlogList();

    @Select("select * from blog where id = #{id}")
    Blog getBlog(@Param("id")Integer id);


    @Insert("insert into blog(title,picture,content,author_id,create_time,update_time) " +
            "value(#{title},#{picture},#{content},#{author_id},#{create_time},#{update_time})")
    void addBlog(@Param("title")String title,
                 @Param("picture")String picture,
                 @Param("content")String content,
                 @Param("author_id") Integer author_id,
                 @Param("create_time") Date create_time,
                 @Param("update_time") Date update_time
    );

    @Update("update blog set title=#{title},picture=#{picture},content=#{content}," +
            "update_time=#{update_time} where id = #{id}")
    void updateBlog(@Param("id")Integer id,
                    @Param("title")String title,
                    @Param("picture")String picture,
                    @Param("content")String content,
                    @Param("author_id") Integer author_id,
                    @Param("update_time") Date update_time
    );

    @Delete("delete from blog where id=#{id}")
    void deleteBlog(@Param("id") Integer id);

    @Update("update blog set page_view = page_view + 1 where id = #{id}")
    void updateBlogPageView(@Param("id")Integer id
    );

}