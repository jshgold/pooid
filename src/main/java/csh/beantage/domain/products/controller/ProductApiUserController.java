package csh.beantage.domain.products.controller;

import csh.beantage.domain.products.dto.ProductResponseDto.GetProductResponse;
import csh.beantage.domain.products.service.ProductService;
import csh.beantage.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductApiUserController {
    private final ProductService service;

    @GetMapping
    public ResponseEntity<ResponseDto<List<GetProductResponse>>> getProducts() {
        ResponseDto rsData = new ResponseDto("200-1","조회 성공", service.getProducts());
        return new ResponseEntity<>(rsData, HttpStatus.OK);
    }
}
