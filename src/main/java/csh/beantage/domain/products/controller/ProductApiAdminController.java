package csh.beantage.domain.products.controller;

import csh.beantage.domain.products.dto.ProductRequestDto.*;
import csh.beantage.domain.products.dto.ProductResponseDto.ProductResponse;
import csh.beantage.domain.products.service.ProductService;
import csh.beantage.global.dto.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/admin/products")
@RestController
public class ProductApiAdminController {
    private final ProductService service;

    @GetMapping
    public ResponseEntity<ResponseDto<List<ProductResponse>>> getProducts() {
        return new ResponseEntity<>(
                new ResponseDto(
                        "200-1",
                        "조회 성공",
                        service.getProducts()
                ), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<ResponseDto<ProductResponse>> createProduct(@RequestBody CreateProductRequest request) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        "201-1",
                        "정상적으로 생성되었습니다",
                        service.createProduct(request)
                ), HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<String>> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return new ResponseEntity<>(
                new ResponseDto<>(
                        "204-1",
                        "정상삭제 되었습니다",
                        null
                ), HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto<ProductResponse>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody PatchProductRequest request) {
        return new ResponseEntity<>(
                new ResponseDto<>(
                        "200-1",
                        "수정이 성공했습니다",
                        service.updateProduct(id, request)
                ),
                HttpStatus.OK
        );
    }
}
