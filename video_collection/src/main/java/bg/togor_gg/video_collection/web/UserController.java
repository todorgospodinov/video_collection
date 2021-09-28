package bg.togor_gg.video_collection.web;

import bg.togor_gg.video_collection.model.binding.UserRegistrationBindingModel;
import bg.togor_gg.video_collection.model.service.UserRegistrationServiceModel;
import bg.togor_gg.video_collection.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    public UserController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/register")
public String registerAndLoginUser(UserRegistrationBindingModel registrationBindingModel){
UserRegistrationServiceModel userServiceModel = modelMapper.map(registrationBindingModel, UserRegistrationServiceModel.class);
    //ToDO
        userService.registerAndLoginUser(userServiceModel);
        return "redirect:/home";
    }
    @PostMapping("/login-error")
    public String failedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter
            .SPRING_SECURITY_FORM_USERNAME_KEY)
                                      String username,
                              RedirectAttributes attributes) {
        attributes.addFlashAttribute ("bad_credentials", true);
        attributes.addFlashAttribute ("username", username);

        return "redirect:/users/login";
    }

}
