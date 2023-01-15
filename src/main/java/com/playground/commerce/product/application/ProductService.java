package com.playground.commerce.product.application;

import com.playground.commerce.product.application.dto.ProductDto;
import com.playground.commerce.product.application.dto.ProductSellDto;
import com.playground.commerce.product.domain.Product;
import com.playground.commerce.product.domain.ProductRepository;
import com.playground.commerce.product.domain.exception.NoExistProductException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findProducts() {
        final List<Product> products = productRepository.findAll();

        return mapToDtos(products);
    }

    private List<ProductDto> mapToDtos(final List<Product> products) {
        return products.stream()
                .map(it -> new ProductDto(it.getProductNumber(), it.getName(), it.getPrice(), it.getStock()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductDto findProductByProductNumber(final String productNumber) {
        final Product product = productRepository.findByProductNumber(productNumber)
                .orElseThrow(() -> new NoExistProductException(productNumber));

        return new ProductDto(product.getProductNumber(), product.getName(), product.getPrice(), product.getStock());
    }

    public void decreaseProductStock(final List<ProductSellDto> productSellDtos) {
        for (ProductSellDto productSellDto : productSellDtos) {
            final Product product = productRepository.findByProductNumberForUpdate(productSellDto.getProductNumber())
                    .orElseThrow(() -> new NoExistProductException(productSellDto.getProductNumber()));

            product.sell(productSellDto.getQuantity());
        }
    }
}
