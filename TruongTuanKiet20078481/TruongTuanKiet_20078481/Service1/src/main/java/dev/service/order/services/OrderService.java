package dev.yonk.order.services;

import dev.yonk.order.dto.OrderDto;
import dev.yonk.order.dto.ResponseDto;
import dev.yonk.order.dto.UserDto;
import dev.yonk.order.mappers.OrderMapper;
import dev.yonk.order.models.Order;
import dev.yonk.order.repositories.OrderRepository;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestTemplate restTemplate;

    @Retry(name = "retryApi")
    public List<ResponseDto> getAllOrder (){
        List<ResponseDto> responseDtos = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            OrderDto orderDto = OrderMapper.MapToDto(order);
            UserDto userDto = restTemplate.getForObject("http://localhost:8081/user/" + order.getUserId(), UserDto.class);
            ResponseDto responseDto = new ResponseDto(orderDto, userDto);
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    public ResponseDto getOrderById (Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        OrderDto orderDto = OrderMapper.MapToDto(order);
        UserDto userDto = restTemplate.getForObject("http://localhost:8081/user/" + order.getUserId(), UserDto.class);
        return new ResponseDto(orderDto, userDto);
    }

    public Order addNewOrder (Order order) {
        return orderRepository.save(order);
    }

    public boolean deleteOrder (Long id) {
        Optional<Order> orderOtp = orderRepository.findById(id);
        if(orderOtp.isPresent()) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}

