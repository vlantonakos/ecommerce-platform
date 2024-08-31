package com.example.orderservice.service;

import com.example.common.event.OrderCreatedEvent;
import com.example.orderservice.model.Order;
import com.example.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public Mono<Order> createOrder(Order order) {
        return orderRepository.save(order)
                .doOnSuccess(savedOrder -> {
                    OrderCreatedEvent event = new OrderCreatedEvent(savedOrder.getId(), savedOrder.getUserId());
                    kafkaTemplate.send("order-created", event);
                });
    }

    public Mono<Order> getOrder(String id) {
        return orderRepository.findById(id);
    }

    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}