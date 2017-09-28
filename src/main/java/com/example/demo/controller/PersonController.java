package com.example.demo.controller;

import com.example.demo.dal.Person;
import com.example.demo.dal.PersonDAO;
import com.example.demo.vo.req.PersonReq;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.vo.resp.GatewayResp;

import java.util.List;

/**
 * Created by lushubei on 17/9/23.
 */


@RestController
@RequestMapping(value = "/p")
@Api("用户管理相关API")
public class PersonController {
    @Autowired
    PersonDAO dao;


    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ApiOperation(value="获取用户列表", notes="查询所有用户信息")
    public GatewayResp<List<Person>> getPersonList(){

        List<Person> personList = dao.getPersonList();
        GatewayResp<List<Person>> resp = new GatewayResp<>();
        resp.setData(personList);
        return resp;

    }
    @ApiOperation(value="查询用户信息", notes="查询某用户信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "String")
    )
    @ApiResponses({
            @ApiResponse(code=400,message="请求参数没填好"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public GatewayResp<Person> getPerson(@PathVariable int id){

        GatewayResp<Person> resp = new GatewayResp<>();

        resp.setData(dao.getPerson(id));

        return resp;
    }

    @ApiOperation(value="添加用户", notes="根据Person添加用户")
    @ApiImplicitParam(name = "person_req",value = "用户信息",required = true,dataType = "Person")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public GatewayResp<List<Person>> addPerson(@RequestBody PersonReq person_req){

        dao.addPerson(person_req.getName(),person_req.getAge(),person_req.getDescription());

        GatewayResp<List<Person>> resp = new GatewayResp<>();
        resp.setData(dao.getPersonList());

        return resp;
    }

    @ApiOperation(value="修改用户", notes="根据Person修改用户")
    @ApiImplicitParam(name = "person",value = "用户信息",required = true,dataType = "Person")
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public GatewayResp<Person> updatePerson(@RequestBody Person person){
        dao.updatePerson(person.getId(),person.getName(),person.getAge(),person.getDescription());

        GatewayResp<Person> resp = new GatewayResp<>();
        resp.setData(dao.getPerson(person.getId()));

        return resp;
    }

    @ApiOperation(value="删除用户", notes="根据用户ID删除用户")
    @ApiImplicitParam(name = "id",value = "用户ID",required = true,dataType = "int")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public GatewayResp<List<Person>> deletePerson(@PathVariable int id){
        dao.deletePerson(id);

        GatewayResp<List<Person>> resp = new GatewayResp<>();
        resp.setData(dao.getPersonList());

        return resp;
    }

}
