package com.example.RabbitMQ;

import jdk.jfr.Registered;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @GetMapping("/test/{name}")
    public String testAPI(@PathVariable("name") String name){
        return ("Success");
    }

}
