package store.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.services.StoreService;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/basket")
    public ResponseEntity<Map<String,Double>> getBasketContent(){
        final Map<String, Double> basket = Collections.singletonMap("price", storeService.getBasketContent());
        return new ResponseEntity<>(basket, HttpStatus.OK);
    }

}