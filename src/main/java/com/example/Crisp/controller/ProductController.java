package com.example.Crisp.controller;

import com.example.Crisp.dto.Productdto;
import com.example.Crisp.exception.imagenotfound.ImageNotFound;
import com.example.Crisp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;

import static com.example.Crisp.constant.Constant.ITEM_DIRECTORY;
import static org.springframework.util.MimeTypeUtils.IMAGE_GIF_VALUE;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping(value="/api/v1/public/products")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<Page<Productdto>> getAllProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "10") int size) {
        System.out.println("Sd");
        return ResponseEntity.ok().body(productService.getAllProducts(page, size));
    }

    @PostMapping
    public ResponseEntity<Productdto> addCategory(@Valid @RequestBody Productdto product) {
        System.out.print("Sd");
        Productdto addedProduct = productService.addProduct(product);
        return ResponseEntity.created(URI.create("")).body(addedProduct);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Productdto> getProduct(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PutMapping("/photo")
    public ResponseEntity<String> uploadPhoto(@RequestParam("id") String id, @RequestParam MultipartFile file){
        return ResponseEntity.ok().body(productService.uploadPhoto(id,file));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Page<Productdto>>  getProductsByCategory(@RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "size", defaultValue = "10") int size,
                                                           @PathVariable (value = "id") String id) {
        return ResponseEntity.ok().body(productService.getProductsByCategory(page,size,id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Page<Productdto>> getProductsByName(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @PathVariable(value = "name") String name) {
        System.out.println("Sd");
        return ResponseEntity.ok().body(productService.getProductsByName(page, size, name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Productdto> deleteProductById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(productService.deleteProductByID(id));
    }

    @GetMapping(value="/photo/{filename}",produces = {IMAGE_PNG_VALUE, IMAGE_GIF_VALUE})
    public byte[] getphoto(@PathVariable("filename") String filename) throws IOException {

        try{
            return Files.readAllBytes(Paths.get(ITEM_DIRECTORY + filename));
        } catch (Exception exception) {
            throw new ImageNotFound();
        }
    }
}
