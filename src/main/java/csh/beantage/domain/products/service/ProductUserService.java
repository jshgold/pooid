package csh.beantage.domain.products.service;

import csh.beantage.domain.products.dto.ProductResponseDto.ProductResponse;
import csh.beantage.domain.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class ProductUserService {
    private final ProductRepository repo;

    public List<ProductResponse> getProducts() {
        List<ProductResponse> list = repo.findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
        log.info("[ProductUserService][getProducts] {}", list);
        return list;
    }
}
