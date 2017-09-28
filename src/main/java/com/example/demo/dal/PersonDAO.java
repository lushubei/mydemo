package com.example.demo.dal;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lushubei on 17/9/23.
 */


@Repository
@Mapper
public interface PersonDAO {

    @Select("select * from person")
    List<Person> getPersonList();

    @Select("select * from person where id = #{id}")
    Person getPerson(@Param("id")int id);

    @Insert("insert into person(name,age,description) value(#{name},#{age},#{description})")
    void addPerson(@Param("name")String name,
                     @Param("age")int age,
                     @Param("description")String description
                     );

    @Update("update person set name=#{name},age=#{age},description=#{description} where id = #{id}")
    void updatePerson(@Param("id") int id,
                     @Param("name")String name,
                     @Param("age")int age,
                     @Param("description")String description
    );

    @Delete("delete from person where id=#{id}")
    void deletePerson(@Param("id") int id);

}
