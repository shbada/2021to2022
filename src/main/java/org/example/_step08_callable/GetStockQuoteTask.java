package org.example._step08_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * return type Double
 */
class GetStockQuoteTask implements Callable<Double> {

    private String stockSymbol;

    public GetStockQuoteTask(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public Double call() {
        // Write some logic to fetch the stock price
        // for the given symbol and return it.
        return 0.0;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor =
                Executors.newFixedThreadPool(1);

        String symbol = "ABCD";
        GetStockQuoteTask task = new GetStockQuoteTask(symbol);
        Future<Double> future = executor.submit( task );
        Double price = future.get();
    }
}
