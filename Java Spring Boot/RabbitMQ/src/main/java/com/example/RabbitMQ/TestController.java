package com.example.RabbitMQ;

import jdk.jfr.Registered;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    //will send the message to the queue
    @Autowired
    RabbitTemplate rabbitTemplate;

    /*
    @GetMapping("/test/{name}")
    public String testAPI(@PathVariable("name") String name){

        Person p = new Person(1L, name);

        //only pass serialize objects soo we need to implement Serializable interface in the object class
        rabbitTemplate.convertAndSend("Mobile", p);

        //direct exchange
        rabbitTemplate.convertAndSend("Direct-Exchange", "mobile", p);

        //fanout exchange
        rabbitTemplate.convertAndSend("Fanout-Exchange", "", p);

        //topic exchange
        rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", p);

        return ("Success");
    }

     */

    @GetMapping("/test/{name}")
    public String testAPI(@PathVariable("name") String name) throws IOException {

        Person p = new Person(1L, name);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(p);
        out.flush();
        out.close();

        byte[] byteMessage = bos.toByteArray();
        bos.close();

        Message message = MessageBuilder.withBody(byteMessage).setHeader("item1", "mobile")
                .setHeader("item2", "television").build();

        rabbitTemplate.send("Headers-Exchange", "", message);

        return "Success";
    }

    @GetMapping("/defaultExchange/{name}")
    public String defaultExchange (@PathVariable String name){

        Person p = new Person(1L, name);
        rabbitTemplate.convertAndSend("Mobile", p);

        return "Success";
    }
}
