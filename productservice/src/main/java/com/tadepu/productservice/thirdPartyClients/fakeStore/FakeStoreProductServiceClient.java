package com.tadepu.productservice.thirdPartyClients.fakeStore;

import com.tadepu.productservice.Exception.NotFoundException;
import com.tadepu.productservice.dtos.FakeStoreProductDto;
import com.tadepu.productservice.dtos.GenericProductDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class FakeStoreProductServiceClient {
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${}")
    public String specificProductRequesturl;
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String productRequestBaseUrl = "https://fakestoreapi.com/products";
    public void FakeStoreproxyProductService(RestTemplateBuilder restTemplateBuilder)
    {
        this.restTemplateBuilder=restTemplateBuilder;
    }


    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response=restTemplate.getForEntity(specificProductRequesturl, FakeStoreProductDto.class,id);
        FakeStoreProductDto fakeStoreProductDto= response.getBody();
        if(fakeStoreProductDto==null)

        {
            throw new NotFoundException("Product with id:"+id+ "doesn't exist");
        }

        return fakeStoreProductDto;
    }

    //CreateProduct Api
    public FakeStoreProductDto createProduct(GenericProductDto product)
    {
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(
                createProductRequestUrl, product, FakeStoreProductDto.class
        );
        return response.getBody();
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> listResponseEntity = restTemplate.getForEntity(productRequestBaseUrl,FakeStoreProductDto[].class);

        return Arrays.stream(listResponseEntity.getBody()).toList();
    }

    public FakeStoreProductDto deleteProductbyId(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> responseEntity = (ResponseEntity<FakeStoreProductDto>)restTemplate.execute(specificProductRequesturl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return  responseEntity.getBody();
    }

    public FakeStoreProductDto updateProductById(Long id, FakeStoreProductDto fakeStoreProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = restTemplate.execute(specificProductRequesturl, HttpMethod.PUT, requestCallback, responseExtractor,id);
        return fakeStoreProductDtoResponseEntity.getBody();

    }

}
