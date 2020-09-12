package com.sahab.order.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
//@PropertySource("classpath:secrets.properties")
public class OrdermanagementProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdermanagementProducerApplication.class, args);
	}

}
	