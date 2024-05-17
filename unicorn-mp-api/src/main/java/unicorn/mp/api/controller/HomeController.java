package unicorn.mp.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unicorn/home")
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "welcome to demo api!";
    }

}
