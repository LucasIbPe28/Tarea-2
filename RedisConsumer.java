package com.tarea2;
import com.rabbitmq.client.*;
import redis.clients.jedis.Jedis;
import java.io.IOException;
import java.util.*;

public class RedisConsumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel canal = connection.createChannel();
        canal.queueDeclare("celular", true, false, false, null);
        Consumer consumer = new DefaultConsumer(canal) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String mensaje = new String(body, "UTF-8");
                Jedis redisconsumer = new Jedis("localhost", 6379);
                redisconsumer.rpush("dispositivos", mensaje);
                List<String>datosRabbit;
                datosRabbit = redisconsumer.lrange("dispositivos", 0, -1);
                for(int i=0;i<datosRabbit.size();i++){
                    System.out.println(datosRabbit.get(i));
                }
                redisconsumer.close();
            }
        };
        canal.basicConsume("celular", true, consumer);
    }
}