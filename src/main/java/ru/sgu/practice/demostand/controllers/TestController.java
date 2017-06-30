package ru.sgu.practice.demostand.controllers;

/**
 * Created by admin on 30.06.2017.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TestController {

    @RequestMapping("/index")
    public String index() {
        return "Hello world test2 app";
    }
}
