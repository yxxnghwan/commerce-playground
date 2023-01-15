package com.playground.commerce;

import static org.assertj.core.api.Assertions.assertThat;

import com.playground.commerce.config.DatabaseCleaner;
import com.playground.commerce.config.DatabaseCleanerConfig;
import com.playground.commerce.order.application.OrderService;
import com.playground.commerce.order.application.dto.OrderItemDto;
import com.playground.commerce.order.application.dto.OrderResultDto;
import com.playground.commerce.product.application.ProductService;
import com.playground.commerce.product.application.dto.ProductDto;
import com.playground.commerce.product.application.dto.ProductSellDto;
import com.playground.commerce.product.domain.Product;
import com.playground.commerce.product.domain.ProductRepository;
import java.util.List;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootTest
@Transactional
@Import({DatabaseCleanerConfig.class})
public class ServiceTest {

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected ProductService productService;
    @Autowired
    protected OrderService orderService;
    protected String 아노락_상품번호;
    protected String 후리스_상품번호;
    protected String 롱패딩_상품번호;
    @Autowired
    private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
    void setUp() {
        transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        아노락_상품번호 = productRepository.save(new Product("1", "아노락", 1_000L, 10L)).getProductNumber();
        후리스_상품번호 = productRepository.save(new Product("2", "후리스", 5_000L, 100L)).getProductNumber();
        롱패딩_상품번호 = productRepository.save(new Product("3", "롱패딩", 10_000L, 30L)).getProductNumber();
    }

    @AfterEach
    void tearDown() {
        databaseCleaner.execute();
    }

    protected List<ProductDto> 상품_목록_조회() {
        return productService.findProducts();
    }

    protected ProductDto 상품_조회(final String productNumber) {
        return productService.findProductByProductNumber(productNumber);
    }

    protected void 상품_등록(final String productNumber, final String name, final Long price, final Long stock) {
        productRepository.save(new Product(productNumber, name, price, stock));
    }

    protected void 상품이_팔림(final String productNumber, final long quantity) {
        productService.decreaseProductStock(List.of(new ProductSellDto(productNumber, quantity)));
    }

    protected OrderResultDto 주문_정보로_주문을_요청한다(final OrderItemDto... orderItemDtos) {
        return orderService.order(List.of(orderItemDtos));
    }

    protected OrderItemDto 주문_정보(final String productNumber,
                                 final String productName,
                                 final Long quantity,
                                 final Long itemPricePerPiece) {
        return new OrderItemDto(productNumber, productName, quantity, itemPricePerPiece);
    }

    protected <T> T 독립된_실행(final TransactionCallback<T> callback) {
        return transactionTemplate.execute(callback);
    }

    protected Assert 리스트_사이즈_검증(final List list, final int size) {
        return assertThat(list).hasSize(size);
    }

    protected Assert 목록에_상품이_존재함(final List products, final String... productNumbers) {
        return assertThat(products).extracting("productNumber")
                .contains(productNumbers);
    }
}
