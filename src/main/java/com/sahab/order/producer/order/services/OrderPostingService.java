package com.sahab.order.producer.order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sahab.order.producer.order.model.OrderDetails;

@Service
public class OrderPostingService {
	 private KafkaTemplate<String, String> kafkaTemplate;
	 @Value("${order-in-topic}")
		private String orderInTopic;

	  @Autowired
	  OrderPostingService(KafkaTemplate<String, String> kafkaTemplate) {
	    this.kafkaTemplate = kafkaTemplate;
	  }

	 public void postOrder(OrderDetails orderDetails) {
	    kafkaTemplate.send(orderInTopic, orderDetails.toString());
	  }
	  
	 /* void sendMessageWithCallback(String message) {
		    ListenableFuture<SendResult<String, String>> future = 
		      kafkaTemplate.send(topic1, message);
		  
		    future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
		      @Override
		      public void onSuccess(SendResult<String, String> result) {
		        LOG.info("Message [{}] delivered with offset {}",
		          message,
		          result.getRecordMetadata().offset());
		      }
		  
		      @Override
		      public void onFailure(Throwable ex) {
		        LOG.warn("Unable to deliver message [{}]. {}", 
		          message,
		          ex.getMessage());
		      }
		    });
		  }*/
}
