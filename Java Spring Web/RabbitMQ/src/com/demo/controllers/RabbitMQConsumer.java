package com.demo.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@EnableRabbit
public class RabbitMQConsumer {

	/*@RabbitListener(queues = "Mobile")
	public void getMessage(Person p){
		System.out.println(p.getName());
	}*/
	
	@RabbitListener(queues = "Mobile")
	public void getMessage(byte[] message) throws IOException, ClassNotFoundException{
		ByteArrayInputStream bis = new ByteArrayInputStream(message);
		ObjectInput in = new ObjectInputStream(bis);
		Person p = (Person) in.readObject();
		in.close();
		bis.close();
		System.out.println(p.getName());
	}
}
