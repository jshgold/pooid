package csh.beantage.domain.products.service;

import csh.beantage.domain.products.dto.ProductRequestDto.*;
import csh.beantage.domain.products.dto.ProductResponseDto;
import csh.beantage.domain.products.dto.ProductResponseDto.ProductResponse;
import csh.beantage.domain.products.entity.Product;
import csh.beantage.domain.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repo;

    public List<ProductResponse> getProducts() {
        return repo.findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    public ProductResponse createProduct(CreateProductRequest request) {
        Product product = Product.create(
                request.name(),
                request.price(),
                request.stock(),
                request.imgUrl()
        );
        repo.save(product);
        return ProductResponse.from(product);
    }


    public void deleteProduct(Long id) {
        Product product = repo.findById(id).orElseThrow(NoSuchElementException::new);
        product.delete();
    }

    public ProductResponse updateProduct(Long id, PatchProductRequest request) {
        Product product = repo.findById(id).orElseThrow(NoSuchElementException::new);
        product.update(request);
        return ProductResponse.from(product);
    }
}
