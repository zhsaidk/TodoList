package zhsaidk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApplicationRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationRunner.class);
        System.out.println(context);

        /* todo 13012025 2:51  userService->findById Вызывает циклический зависимость, нужно починить (with Projections) */
    }
}
