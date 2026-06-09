package csh.beantage.domain.products.controller;

import csh.beantage.domain.products.dto.ProductResponseDto.ProductResponse;
import csh.beantage.domain.products.service.ProductUserService;
import csh.beantage.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/products")
@RestController
public class ProductApiUserController {
    private final ProductUserService service;

    @GetMapping
    public ResponseEntity<ResponseDto<List<ProductResponse>>> getProducts() {
        log.info("Run getProducts*********************************");
        ResponseDto rsData = new ResponseDto("200-1","조회 성공", service.getProducts());
        return new ResponseEntity<>(rsData, HttpStatus.OK);
    }
}
