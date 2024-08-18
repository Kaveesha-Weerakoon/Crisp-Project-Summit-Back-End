package com.example.Crisp.controller;

import com.example.Crisp.dto.CartProductdto;
import com.example.Crisp.dto.Categorydto;
import com.example.Crisp.dto.Productdto;
import com.example.Crisp.model.CartProduct;
import com.example.Crisp.service.CartProductService;
import com.example.Crisp.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping(value="/api/v1/public/user/cartproduct")
@CrossOrigin("*")
public class CartProductController {

    @Autowired
    private CartProductService cartProductService;

    @GetMapping("/{id}")
    public ResponseEntity<CartProductdto> getCartProductByID(@PathVariable(value = "id") String id){
        return ResponseEntity.ok().body(cartProductService.getCartProductsByID(id));
    }

    @PostMapping
    public ResponseEntity<CartProductdto> addCartProduct(@RequestBody CartProductdto cartProduct) {
        System.out.println(cartProduct);
        CartProductdto Cartproduct2 = cartProductService.addCartProduct(cartProduct);
        return ResponseEntity.created(URI.create("")).body(Cartproduct2 );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CartProductdto> deleteCartProductById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(cartProductService.deleteCartProductByID(id));
    }
}
