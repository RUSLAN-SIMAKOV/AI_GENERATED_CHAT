package ruslan.simakov.aigeneratedchat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ruslan.simakov.aigeneratedchat.model.User;
import ruslan.simakov.aigeneratedchat.repository.UserRepository;

import java.util.List;

@SpringBootApplication
public class AiGeneratedChatApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(AiGeneratedChatApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User("id",
                "firstName",
                "lastName",
                1,
                "bio",
                "imageUrl");

        userRepository.save(user);
        List<User> all = userRepository.findAll();
        System.out.println(all);

    }
}
