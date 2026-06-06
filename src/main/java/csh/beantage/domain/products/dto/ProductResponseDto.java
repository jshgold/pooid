package csh.beantage.domain.products.dto;

import csh.beantage.domain.products.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


//"id": 1,            // id
//		"name" : "맛있는 원두", // 상품명
//		"price" : 30000,     // 가격
//		"stock" : 100,       // 재고 수량
//		"imgUrl" : "coffee1.jpg"

public class ProductResponseDto {

    public record ProductResponse(
            @NotNull
            Long id,
            @NotBlank
            String name,
            @NotNull
            Integer price,
            @NotNull
            Integer stock,
            String imgUrl) {

        public static ProductResponse from (Product product) {
            return new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStock(),
                    product.getImgUrl());
        }
    }
}
