package dev.yonk.order.controllers;

import dev.yonk.order.dto.ResponseDto;
import dev.yonk.order.models.Order;
import dev.yonk.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    public ResponseEntity<List<ResponseDto>> getAllOrder () {
        return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getOrderById (@PathVariable Long id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addNewOrder (@RequestBody Order order) {
        return new ResponseEntity<>(orderService.addNewOrder(order), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Order> deleteOrder (@PathVariable Long id) {
        boolean check = orderService.deleteOrder(id);
        if(check) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
