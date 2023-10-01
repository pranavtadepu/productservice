package com.tadepu.productservice.services;

import com.tadepu.productservice.Exception.NotFoundException;
import com.tadepu.productservice.dtos.FakeStoreProductDto;
import com.tadepu.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long Id) throws NotFoundException;

    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();

    GenericProductDto deleteProductbyId(Long id);

    GenericProductDto updateProductById(Long id, FakeStoreProductDto fakeStoreProductDto);
}
