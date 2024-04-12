package dev.yonk.user;

import com.github.javafaker.Faker;
import dev.yonk.user.models.User;
import dev.yonk.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class UserApplication implements CommandLineRunner {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        List<Long> orderIds = new ArrayList<>();
        orderIds.add(1L);
        orderIds.add(2L);
        orderIds.add(3L);

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
//        user.setOrderIds(orderIds);

        userRepository.save(user);
    }
}
