package com.example.demo.controller;

import com.example.demo.dal.Person;
import com.example.demo.dal.PersonDAO;
import com.example.demo.vo.req.PersonReq;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import com.example.demo.vo.resp.GatewayResp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.json.JSONArray;

import com.alibaba.fastjson.JSON;

/**
 * Created by lushubei on 17/9/23.
 */


@RestController
@EnableAutoConfiguration
@ResponseBody
@RequestMapping(value = "/p")
public class PersonController {
    @Autowired
    PersonDAO dao;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public GatewayResp<List<Person>> getPersonList(){

        List<Person> personList = dao.getPersonList();
        GatewayResp<List<Person>> resp = new GatewayResp<>();
        resp.setData(personList);
        return resp;

    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public GatewayResp<Person> getPerson(@PathVariable int id){

        GatewayResp<Person> resp = new GatewayResp<>();

        resp.setData(dao.getPerson(id));

        return resp;

    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public GatewayResp<List<Person>> addPerson(@RequestBody PersonReq person_req){
        System.out.println(person_req);
        dao.addPerson(person_req.getName(),person_req.getAge(),person_req.getDescription());

        GatewayResp<List<Person>> resp = new GatewayResp<>();
        resp.setData(dao.getPersonList());

        return resp;
    }

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public GatewayResp<Person> updatePerson(@RequestBody Person person){
        dao.updatePerson(person.getId(),person.getName(),person.getAge(),person.getDescription());

        GatewayResp<Person> resp = new GatewayResp<>();
        resp.setData(dao.getPerson(person.getId()));

        return resp;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public GatewayResp<List<Person>> deletePerson(@PathVariable int id){
        dao.deletePerson(id);

        GatewayResp<List<Person>> resp = new GatewayResp<>();
        resp.setData(dao.getPersonList());

        return resp;
    }

}
