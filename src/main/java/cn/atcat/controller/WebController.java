package cn.atcat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WebController {

    @GetMapping
    public String hello(){
        return "欢迎你的访问···，前端请访问10.22.111.160:5173";
    }
}
