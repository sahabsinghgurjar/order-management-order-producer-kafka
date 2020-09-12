package com.sahab.order.producer.order.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahab.order.producer.order.model.OrderUserEntity;

@Repository
public interface OrderUserRepo extends JpaRepository<OrderUserEntity, String>{
	
	List<OrderUserEntity> findAll();

}
