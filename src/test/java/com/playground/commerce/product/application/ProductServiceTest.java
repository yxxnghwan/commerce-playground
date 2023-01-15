package com.playground.commerce.product.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.playground.commerce.ServiceTest;
import com.playground.commerce.product.domain.exception.SoldOutException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductServiceTest extends ServiceTest {

    @Test
    @DisplayName("상품 목록을 조회한다.")
    void findProducts() {
        final var 상품_목록 = 상품_목록_조회();

        assertAll(
                () -> 리스트_사이즈_검증(상품_목록, 3),
                () -> 목록에_상품이_존재함(상품_목록, 아노락_상품번호, 후리스_상품번호, 롱패딩_상품번호)
        );
    }

    @Nested
    class SellTest {

        @Test
        @DisplayName("상품이 팔리면 재고가 감소한다.")
        void sell() {
            final String 상품_번호 = "999999";
            상품_등록(상품_번호, "무스탕", 1_000_000L, 100L);

            상품이_팔림(상품_번호, 50);

            final var 무스탕 = 상품_조회(상품_번호);
            assertThat(무스탕.getStock()).isEqualTo(50L);
        }

        @ParameterizedTest
        @ValueSource(ints = {1, 2})
        @DisplayName("동시에 여러 스레드에서 상품이 팔려도 정상적으로 동작한다.")
        void sellConcurrency(final int soldOutExceptionCount) throws InterruptedException {
            final String 상품_번호 = "999999";
            final long stockCount = 10L;
            독립된_실행((status) -> {
                상품_등록(상품_번호, "무스탕", 1_000_000L, stockCount);
                return null;
            });

            final int multiThreadCount = (int) stockCount + soldOutExceptionCount;
            final AtomicInteger checkSoldOutExceptionCount = new AtomicInteger();
            final CountDownLatch countDownLatch = new CountDownLatch(multiThreadCount);
            final ExecutorService executorService = Executors.newFixedThreadPool(multiThreadCount);

            for (int i = 0; i < multiThreadCount; i++) {
                executorService.submit(() -> {
                    try {
                        별도_스레드에서_상품이_팔림(상품_번호, 1);
                    } catch (final SoldOutException e) {
                        checkSoldOutExceptionCount.incrementAndGet();
                    }
                    countDownLatch.countDown();
                });
            }
            countDownLatch.await();

            assertThat(checkSoldOutExceptionCount.get()).isEqualTo(soldOutExceptionCount);
        }

        private void 별도_스레드에서_상품이_팔림(final String productNumber, final int quantity) {
            독립된_실행((status) -> {
                상품이_팔림(productNumber, quantity);
                return null;
            });
        }
    }
}
