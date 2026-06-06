package csh.beantage.domain.products.dto;

import csh.beantage.domain.products.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductResponseDto {

    public record ProductResponse(
            @NotNull
            Long id,
            @NotBlank
            String name,
            @NotNull
            Integer price,
            String imgUrl) {

        public static ProductResponse from (Product product) {
            return new ProductResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getImgUrl());
        }
    }

    public record ProductWithStockResponse(
            @NotNull
            Long id,
            @NotBlank
            String name,
            @NotNull
            Integer price,
            @NotNull
            Integer stock,
            String imgUrl) {

        public static ProductWithStockResponse from (Product product) {
            return new ProductWithStockResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getStock(),
                    product.getImgUrl());
        }
    }
}
