package com.miracle.service.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by naonao on 18/4/20.
 */
@RestController
@RequestMapping("/miracle")
public class TestController {
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public String test() {
        return "miracle";
    }
}
