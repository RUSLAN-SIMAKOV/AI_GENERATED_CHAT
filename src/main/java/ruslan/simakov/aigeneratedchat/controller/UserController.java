package ruslan.simakov.aigeneratedchat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ruslan.simakov.aigeneratedchat.model.User;
import ruslan.simakov.aigeneratedchat.service.UserService;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/user/random")
    public User getRandomUser() {
        return userService.getRandomUser();
    }
}
