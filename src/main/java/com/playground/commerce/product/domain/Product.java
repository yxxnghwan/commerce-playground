package com.playground.commerce.product.domain;

import com.playground.commerce.product.domain.exception.InvalidStockException;
import com.playground.commerce.product.domain.exception.SoldOutException;
import com.playground.commerce.product.domain.vo.ProductPrice;
import com.playground.commerce.product.domain.vo.Stock;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productNumber;
    private String name;

    @Embedded
    private ProductPrice productPrice;

    @Embedded
    private Stock stock;

    protected Product() {
    }

    public Product(final String productNumber, final String name, final Long price, final Long stock) {
        this.productNumber = productNumber;
        this.name = name;
        this.productPrice = new ProductPrice(price);
        this.stock = new Stock(stock);
    }

    public void sell(final long amount) {
        this.stock = decreaseStock(amount);
    }

    private Stock decreaseStock(final long amount) {
        try {
            return stock.decrease(amount);
        } catch (final InvalidStockException e) {
            throw new SoldOutException(stock.getValue(), amount);
        }
    }

    public Long getId() {
        return id;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return productPrice.getValue();
    }

    public Long getStock() {
        return stock.getValue();
    }
}
