package tarea2;
/* 
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class consumerkfk extends Thread  {
    private static final String TOPIC = "nombre_del_topico";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String GROUP_ID = "grupo_de_consumidores"; // Reemplaza con un identificador de grupo válido

    private static class KafkaConsumerThread implements Runnable {
        private final KafkaConsumer<String, String> consumer;

        public KafkaConsumerThread() {
            Properties props = new Properties();
            properties.put("bootstrap.servers", "localhost:9092");
            properties.put("group.id", "my_consumer_group");
            properties.put("key.deserializer", StringDeserializer.class.getName());
            properties.put("value.deserializer", StringDeserializer.class.getName());

            consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Collections.singletonList(TOPIC));
        }

        @Override
        public void run() {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        String message = record.value();
                        System.out.println("Received message: " + message);
                        // Agrega aquí la lógica adicional para procesar el mensaje recibido de Kafka
                    }
                }
            } finally {
                consumer.close();
            }
        }
    }

    public static void main(String[] args) {
        Thread consumerThread = new Thread(new KafkaConsumerThread());
        consumerThread.start();
    }

}

  // Configuración del consumidor de Kafka
  Properties properties = new Properties();
  properties.put("bootstrap.servers", "localhost:9092");
  properties.put("group.id", "my_consumer_group");
  properties.put("key.deserializer", StringDeserializer.class.getName());
  properties.put("value.deserializer", StringDeserializer.class.getName());

  // Crear el consumidor de Kafka
  KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

  // Suscribirse a la cola de Kafka
  consumer.subscribe(Collections.singletonList("channel"));

  try {
      // Recibir y procesar los mensajes de Kafka
      while (true) {
          ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
          for (ConsumerRecord<String, String> record : records) {
              String key = record.key();
              String value = record.value();
              System.out.println("Received message: Key = " + key + ", Value = " + value);
              // Agrega aquí la lógica adicional para procesar el mensaje recibido de Kafka
          }
      }
  } catch (Exception e) {
      e.printStackTrace();
  } finally {
      // Cerrar el consumidor de Kafka al finalizar
      consumer.close();
  }
}*/
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class consumerkfk {

    public static void main(String[] args) {
        // Configuración del consumidor de Kafka
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", "my_consumer_group");
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());

        // Crear el consumidor de Kafka
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Suscribirse a la cola de Kafka
        consumer.subscribe(Collections.singletonList("channel"));

        try {
            // Recibir y procesar los mensajes de Kafka
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    String key = record.key();
                    String value = record.value();
                    System.out.println("Received message: Key = " + key + ", Value = " + value);
                    // Agrega aquí la lógica adicional para procesar el mensaje recibido de Kafka
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el consumidor de Kafka al finalizar
            consumer.close();
        }
    }
}