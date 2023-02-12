package com.example.rabbitmqmsg;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Pulisher {

	public static void main(String[] args) throws IOException, TimeoutException {

		ConnectionFactory factory = new ConnectionFactory();

		Connection connection = factory.newConnection();


		Channel channel = connection.createChannel();

		//we can publish the message to queue with the queue
		String message = "este teste pdoerá ser um sucesso";
		channel.basicPublish("", "Queue-1", null, message.getBytes());

		channel.close();
		connection.close();
	}

}
