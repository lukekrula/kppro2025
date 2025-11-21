package cz.uhk.kppro;

import cz.uhk.kppro.model.User;
import cz.uhk.kppro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KpproApplication {

    private UserService userService;

    @Autowired
    public KpproApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(KpproApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        return args -> {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("heslo");
            admin.setRole("ADMIN");

            User user = new User();
            user.setUsername("user");
            user.setPassword("heslo");
            user.setRole("USER");

            userService.save(user);
            userService.save(admin);
        };
    }

}
