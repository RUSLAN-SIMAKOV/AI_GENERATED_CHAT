package ruslan.simakov.aigeneratedchat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ruslan.simakov.aigeneratedchat.model.User;
import ruslan.simakov.aigeneratedchat.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User getRandomUser() {
        List<User> users = userRepository.findAll();
        return users.get((int) (Math.random() * users.size()));
    }
}
