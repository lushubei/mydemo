package com.example.imakeyouth.dao;

import com.example.imakeyouth.model.Picture;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
  * 图片 Mapper 接口
 * </p>
 *
 * @author xiaobei
 * @since 2017-09-30
 */
@Repository
@Mapper
public interface PictureDAO extends BaseMapper<Picture> {

    @Select("select * from picture order by id desc")
    List<Picture> getPictureList();

    @Select("select * from picture where id = #{id}")
    Picture getPicture(@Param("id")Integer id);


    @Insert("insert into picture(url) value(#{url})")
    void addPicture(@Param("url")String url);

    @Update("update picture set url=#{url} where id = #{id}")
    void updatePicture(@Param("id")Integer id,
                    @Param("url")String title
    );

    @Delete("delete from picture where id=#{id}")
    void deletePicture(@Param("id") Integer id);
}