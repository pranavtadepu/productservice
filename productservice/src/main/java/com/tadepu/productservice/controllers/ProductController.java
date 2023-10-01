package com.tadepu.productservice.controllers;


import com.tadepu.productservice.Exception.NotFoundException;
import com.tadepu.productservice.dtos.FakeStoreProductDto;
import com.tadepu.productservice.dtos.GenericProductDto;
import com.tadepu.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    @Value("${product-service.type}")
    String flag;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/")
    public List<GenericProductDto> getAllProducts(){

        return  productService.getAllProducts();
    }
//    //localhost:8080/products/123
//    @GetMapping("{id}")
//    public ResponseEntity<FakeStoreProductDto> getProductById(@PathVariable Long id){
//     return productService.getProductById(id);
//    }
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable Long id) throws NotFoundException {
        return productService.getProductById(id);
    }
    @PostMapping
    public GenericProductDto createProductById(@RequestBody GenericProductDto Product){
        return productService.createProduct(Product);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<GenericProductDto> deleteProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.deleteProductbyId(id),HttpStatus.OK);
    }
    @PutMapping("{id}")
    public GenericProductDto updateProductById(@PathVariable Long id, @RequestBody FakeStoreProductDto fakeStoreProductDto){
        return productService.updateProductById(id,fakeStoreProductDto);
    }
}
