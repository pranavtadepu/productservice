package com.tadepu.productservice.services;

import com.tadepu.productservice.Exception.NotFoundException;
import com.tadepu.productservice.dtos.FakeStoreProductDto;
import com.tadepu.productservice.dtos.GenericProductDto;
import com.tadepu.productservice.thirdPartyClients.fakeStore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Primary
@Service("fakeStoreProductService")
public class FakeStoreproxyProductService implements ProductService {
    FakeStoreProductServiceClient fakeStoreProductServiceClient;

    public FakeStoreproxyProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient) {
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return fakeStoreToGeneric(fakeStoreProductServiceClient.getProductById(id));
    }

    public GenericProductDto createProduct(GenericProductDto product) {
        return fakeStoreToGeneric(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        List<GenericProductDto> genericProductDtos = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto: fakeStoreProductServiceClient.getAllProducts()){
            genericProductDtos.add(fakeStoreToGeneric(fakeStoreProductDto));
        }
        return genericProductDtos;
    }

    @Override
    public GenericProductDto deleteProductbyId(Long id) {
        return fakeStoreToGeneric((fakeStoreProductServiceClient.deleteProductbyId(id)));
    }

    @Override
    public GenericProductDto updateProductById(Long id, FakeStoreProductDto fakeStoreProductDto) {
        return fakeStoreToGeneric(fakeStoreProductServiceClient.updateProductById(id, fakeStoreProductDto));
    }

    private GenericProductDto fakeStoreToGeneric(FakeStoreProductDto fakeStoreProductDto){
        GenericProductDto product= new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setTitle(fakeStoreProductDto.getDescription());
        product.setDescription(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }
}
