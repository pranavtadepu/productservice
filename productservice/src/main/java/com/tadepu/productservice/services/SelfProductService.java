package com.tadepu.productservice.services;

import com.tadepu.productservice.dtos.FakeStoreProductDto;
import com.tadepu.productservice.dtos.GenericProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    @Override
    public GenericProductDto getProductById(Long Id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        return null;
    }

    @Override
    public GenericProductDto deleteProductbyId(Long id) {
        return null;
    }

    @Override
    public GenericProductDto updateProductById(Long id, FakeStoreProductDto fakeStoreProductDto) {
        return null;
    }
}
