package cn.atcat.controller;

import cn.atcat.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @GetMapping("list")
    public String list() {
        return "category/list";
    }
}
