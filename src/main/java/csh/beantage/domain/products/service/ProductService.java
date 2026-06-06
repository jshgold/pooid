package csh.beantage.domain.products.service;

import csh.beantage.domain.products.dto.ProductResponseDto.GetProductResponse;
import csh.beantage.domain.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repo;

    public List<GetProductResponse> getProducts() {
        return repo.findAll()
                .stream()
                .map(GetProductResponse::from)
                .toList();
    }



}
