package com.jimmy.controller;

import com.jimmy.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiongyang
 * @date 2020/3/30 21:34
 * @Description:
 */

@RequestMapping("/task")
@RestController
public class TastController {

    @Autowired
    TaskService taskService;

    @RequestMapping("/taskOne")
    public void taskOne(){
        taskService.testTask();
    }
}
