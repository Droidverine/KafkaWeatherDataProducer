package com.example.producer.Weatherdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class WeatherdataApplication implements CommandLineRunner {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Random random = new Random();

    public WeatherdataApplication(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherdataApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Weather producer started...");
    }

    @Scheduled(fixedRate = 5000)
    public void sendRandomWeatherData() {
        String sensorId = "sensor" + (random.nextInt(3) + 1);
        double temperature = 15 + (35 - 15) * random.nextDouble();
        double humidity = 30 + (70 - 30) * random.nextDouble();
        String timestamp = Instant.now().toString();

        String message = String.format(
            "{\"sensorId\":\"%s\",\"temperature\":%.2f,\"humidity\":%.2f,\"timestamp\":\"%s\"}",
            sensorId, temperature, humidity, timestamp
        );

        kafkaTemplate.send("weather-metrics", sensorId, message);
        System.out.println("Sent: " + message);
    }
}

