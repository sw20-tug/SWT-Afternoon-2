package com.example.demo.controller;

import com.example.demo.model.MainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MainController {

    @Autowired
    private MainModel model;

    @RequestMapping(value="/sayHello", method = RequestMethod.GET)
    public String sayHello()
    {
        model.setName("Hello Hotels");
        return model.toString();
    }


}
