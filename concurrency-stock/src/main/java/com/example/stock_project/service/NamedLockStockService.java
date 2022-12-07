package com.example.stock_project.service;

import com.example.stock_project.domain.Stock;
import com.example.stock_project.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NamedLockStockService implements StockBusinessInterface{

    private final StockRepository stockRepository;

    public NamedLockStockService(final StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // 부모의 트랜잭션과 별도로 실행되어야한다.
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public synchronized void decrease(final Long id, final Long quantity) {
        final Stock stock = stockRepository.getByProductId(id);
        stock.decrease(quantity);
        stockRepository.saveAndFlush(stock);
    }
}
