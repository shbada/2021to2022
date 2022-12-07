package com.example.stock_project.service;

import com.example.stock_project.domain.Stock;
import com.example.stock_project.repository.PessimisticStockRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class PessimisticLockStockService implements StockBusinessInterface {

    private final PessimisticStockRepository stockRepository;

    public PessimisticLockStockService(final PessimisticStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(final Long id, final Long quantity) {
        // get stock
        Stock stock = stockRepository.getByProductId(id);

        // decrease stock
        stock.decrease(quantity);
    }
}
