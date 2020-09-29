package com.sahab.order.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.sahab.order.common.model.OrderDetails;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

@Configuration
public class OrderProducerConfig {

	@Value("${bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put( 
		            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
		            KafkaAvroSerializer.class); 
		props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://127.0.0.1:8081");
		return props;
	}

	@Bean
	public ProducerFactory<String, OrderDetails> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	  public KafkaTemplate<String, OrderDetails> kafkaTemplate() {
		  KafkaTemplate<String, OrderDetails> kafkaTemplate= new KafkaTemplate<>(producerFactory());
	    		
				/*  kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
	        @Override
	        public void onSuccess(
	          ProducerRecord<String, String> producerRecord, 
	          RecordMetadata recordMetadata) {
	          
	          LOG.info("acknowledgement from ProducerListener message: {} offset:  {}",
	            producerRecord.value(),
	            recordMetadata.offset());
	        }
	      });*/
		  return kafkaTemplate;
	  }
	
	/*@Bean
	  public RoutingKafkaTemplate routingTemplate(GenericApplicationContext context) {
	    Map<String, Object> props = new HashMap<>();
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, 
	      bootstrapServers);
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	      StringSerializer.class);
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	      ByteArraySerializer.class);
	    DefaultKafkaProducerFactory<Object, Object> bytesPF = 
	      new DefaultKafkaProducerFactory<>(props);
	    context.registerBean(DefaultKafkaProducerFactory.class, "bytesPF", bytesPF);

	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, 
	      StringSerializer.class);
	    DefaultKafkaProducerFactory<Object, Object> stringPF = 
	      new DefaultKafkaProducerFactory<>(props);

	    Map<Pattern, ProducerFactory<Object, Object>> map = new LinkedHashMap<>();
	    map.put(Pattern.compile(".*-bytes"), bytesPF);
	    map.put(Pattern.compile("reflectoring-.*"), stringPF);
	    return new RoutingKafkaTemplate(map);
	  }*/

}
