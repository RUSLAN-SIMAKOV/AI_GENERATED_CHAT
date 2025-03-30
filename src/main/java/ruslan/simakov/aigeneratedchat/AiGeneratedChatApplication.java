package ruslan.simakov.aigeneratedchat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiGeneratedChatApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AiGeneratedChatApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Initialize hook");
    }
}
