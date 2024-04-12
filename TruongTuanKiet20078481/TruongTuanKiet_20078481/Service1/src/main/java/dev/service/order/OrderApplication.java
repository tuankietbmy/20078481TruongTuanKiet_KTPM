package dev.yonk.order;

import com.github.javafaker.Faker;
import dev.yonk.order.models.Order;
import dev.yonk.order.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class OrderApplication implements CommandLineRunner {

    private final OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        List<Order> orderList = new ArrayList<>();
        for ( int i=1; i<4; i++) {
            Order order = new Order();
            order.setOrderDate(faker.date().past(10, java.util.concurrent.TimeUnit.DAYS).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());
            order.setStatus(faker.lorem().word());
            order.setShippingAddress(faker.address().fullAddress());
            order.setUserId(1L);
            orderList.add(order);
        }
        orderRepository.saveAll(orderList);
    }
}
