package csh.beantage.domain.products.entity;

import csh.beantage.domain.products.dto.ProductResponseDto.*;
import csh.beantage.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Product extends BaseEntity {
    String name;
    int price;
    int stock;
    String imgUrl;


}
