package store.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class HelloWorldController {

    @GetMapping
    public ResponseEntity<Map<String, String>> sayHello(){
        final Map<String, String> msg = Collections.singletonMap("msg", "hello world");
        return new ResponseEntity<Map<String,String>>(msg, HttpStatus.OK);
    }

}
