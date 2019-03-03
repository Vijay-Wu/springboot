package pers.vergil.restfulcrud.controller;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class hello {
//    @RequestMapping({"/","index.html"})
//    public String index(){
//        return "index";
//    }
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }
    @RequestMapping("/success")
    public String success(Map<String, Object> map){
        map.put("hello","你好");
        return "success";
    }
}
