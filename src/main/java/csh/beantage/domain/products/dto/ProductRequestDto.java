package csh.beantage.domain.products.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ProductRequestDto {
    public record CreateProductRequest(
            @NotBlank(message = "상품명은 필수입니다")
            String name,
            @NotNull
            @Min(value = 0, message = "최소 수량은 0입니다")
            Integer stock,
            @NotNull
            @Min(value = 0, message = "최소 가격은 0원입니다")
            Integer price,
            String imageUrl) {}

    public record PatchProductRequest(
            @Pattern(regexp = "\\+S\\+")
            String name,
            @Min(value = 0, message = "최소 수량은 0입니다")
            Integer stock,
            @Min(value = 0, message = "최소 금액은 0입니다")
            Integer price,
            String imageUrl
    ){}
}
