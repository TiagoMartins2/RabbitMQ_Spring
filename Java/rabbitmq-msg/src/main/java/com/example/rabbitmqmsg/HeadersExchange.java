package com.example.rabbitmqmsg;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class HeadersExchange {

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message for Mobile and Tv";

        Map<String, Object> headersMap = new HashMap<String, Object>();
        headersMap.put("item1", "mobile");
        headersMap.put("item2", "television");

        AMQP.BasicProperties br = new AMQP.BasicProperties();
        br = br.builder().headers(headersMap).build();

        channel.basicPublish("Headers-Exchange", "", br, message.getBytes());

        channel.close();
        connection.close();
    }
}
