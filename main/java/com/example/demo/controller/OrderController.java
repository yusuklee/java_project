package com.example.demo.controller;

import com.example.demo.dto.OrderForm;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("주문")
    public ResponseEntity<?> order(@RequestBody OrderForm orderForm){
        Long orderId=orderService.order(orderForm);


        return ResponseEntity.ok(orderId);
    }

    @GetMapping("주문내역")
    public List<Long> showOrders(){
        return orderService.showOrders();
    }

    @DeleteMapping("order/{orderId}")
    public Long delete(@PathVariable Long orderId){
       return orderService.cancel(orderId);
    }
}
