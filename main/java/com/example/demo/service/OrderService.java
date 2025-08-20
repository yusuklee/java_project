package com.example.demo.service;

import com.example.demo.dto.OrderForm;
import com.example.demo.dto.OrderLine;
import com.example.demo.entity.*;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public Long order(OrderForm orderForm) {
        List<OrderItem> orderItems = new ArrayList<>();
        List<OrderLine> orderLines = orderForm.getLines();
        for(OrderLine orderLine: orderLines){
            Long id = orderLine.getItemId();
            Item item = itemRepository.findById(id).orElse(null);
            OrderItem temp = OrderItem.createOrderItem(
                item,item.getPrice()*orderLine.getCount(),
                orderLine.getCount()
            );
            orderItems.add(temp);
        }
        OrderItem[] array= orderItems.toArray(new OrderItem[0]); //orderItems배열만들고
        Long memberId = orderForm.getMemberId();
        Member member=memberRepository.findById(memberId).orElse(null); //멤버 찾앗고
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY); // 딜리버리 다만들엇고

        Order order = Order.createOrder(member,delivery,array);
        orderRepository.save(order);

        return order.getId();


    }

    public List<Long> showOrders() {
     return orderRepository.findAll().stream().map(order -> order.getId()).toList();

    }

    public Long cancel(Long orderId) {

        Order order=orderRepository.findById(orderId).orElseThrow(()->new IllegalStateException("주문을 찾지 못하였습니다."));
        order.cancel(order);
        orderRepository.deleteById(orderId);
        return orderId;
    }
}
