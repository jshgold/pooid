package csh.beantage.domain.products.entity;

import csh.beantage.domain.products.dto.ProductRequestDto.*;
import csh.beantage.domain.products.dto.ProductResponseDto.*;
import csh.beantage.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)  // JPA 스펙 충족, 외부 직접 생성 차단
@Getter
@Entity
@SQLRestriction("deleted_at IS NULL")
public class Product extends BaseEntity {
    private String name;
    private Integer price;
    private Integer stock;
    private String imgUrl;
    private LocalDateTime deletedAt ;

    private Product (String name, Integer price, Integer stock, String imgUrl) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.imgUrl = imgUrl;
    }

    public static Product create(String name, Integer price, Integer stock, String imgUrl) {
        return new Product(name, price, stock, imgUrl);
    }

    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void update(PatchProductRequest request) {
        if(name!=null) this.name = request.name();
        if(price!=null) this.price = request.price();
        if(stock!=null) this.stock = request.stock();
        if(imgUrl!=null) this.imgUrl = request.imgUrl();
    }
}
