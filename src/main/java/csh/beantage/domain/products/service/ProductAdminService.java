package csh.beantage.domain.products.service;

import csh.beantage.domain.products.dto.ProductRequestDto.*;
import csh.beantage.domain.products.dto.ProductResponseDto.*;
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
public class ProductAdminService {
    private final ProductRepository repo;

    public List<ProductWithStockResponse> getProducts() {
        return repo.findAll()
                .stream()
                .map(ProductWithStockResponse::from)
                .toList();
    }

    public ProductWithStockResponse createProduct(CreateProductRequest request) {
        Product product = Product.create(
                request.name(),
                request.price(),
                request.stock(),
                request.imgUrl()
        );
        repo.save(product);
        return ProductWithStockResponse.from(product);
    }


    public void deleteProduct(Long id) {
        Product product = repo.findById(id).orElseThrow(NoSuchElementException::new);
        product.delete();
    }

    public ProductWithStockResponse updateProduct(Long id, PatchProductRequest request) {
        Product product = repo.findById(id).orElseThrow(NoSuchElementException::new);
        product.update(request);
        return ProductWithStockResponse.from(product);
    }
}
