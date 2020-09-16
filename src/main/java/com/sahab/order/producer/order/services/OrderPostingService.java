package com.sahab.order.producer.order.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.sahab.order.common.model.OrderDetails;

@Service
public class OrderPostingService {
	 private KafkaTemplate<String, OrderDetails> kafkaTemplate;
	 @Value("${order-in-topic}")
		private String orderInTopic;

	  @Autowired
	  OrderPostingService(KafkaTemplate<String, OrderDetails> kafkaTemplate) {
	    this.kafkaTemplate = kafkaTemplate;
	  }

	 public void postOrder(OrderDetails orderDetails) {
	    kafkaTemplate.send(orderInTopic, orderDetails);
	  }
	  
/* void sendMessageWithCallbackFeature(String message) {
		    ListenableFuture<SendResult<String, String>> listenableFuture = 
		      kafkaTemplate.send(orderInTopic, message);
		  
		    listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
		      @Override
		      public void onSuccess(SendResult<String, String> result) {
		       System.out.println("Message "+ message+" delivered with offset "+result.getRecordMetadata().offset());
		      }
		  
		      @Override
		      public void onFailure(Throwable ex) {
		      System.out.println("Unable to deliver message "+
		          message);
		          ex.printStackTrace();
		      }
		    });
		  }*/
}
