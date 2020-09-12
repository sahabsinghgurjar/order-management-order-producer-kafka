package com.sahab.order.producer.order.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sahab.order.producer.order.model.OrderDetails;
import com.sahab.order.producer.order.services.OrderPostingService;

@RestController
@RequestMapping("/order")
public class OrderPostingResource {

	@Autowired
	private OrderPostingService orderPostingService;

	@PostMapping
	public ResponseEntity<String> registerUser(@RequestBody OrderDetails orderDetails) {
			orderPostingService.postOrder(orderDetails);

		return ResponseEntity.ok("Odrer successfully placed.");
	}

}
