package pt.tqs;


import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

public class StocksTest {
    IStockmarketService stockmarket = Mockito.mock(IStockmarketService.class);

    StocksPortfolio portfolio = new StocksPortfolio(stockmarket);

    @Test
    public void getTotalValue() {
        Stock googleStock = new Stock("Google", 50);
        Stock appleStock = new Stock("Apple", 100);

        portfolio.addStock(googleStock);
        portfolio.addStock(appleStock);


        when(stockmarket.lookUpPrice("Google")).thenReturn(100.0);
        when(stockmarket.lookUpPrice("Apple")).thenReturn(200.0);

        double result = portfolio.getTotalValue();

        assertEquals(25000.0, result);

        verify(stockmarket).lookUpPrice("Google");
        verify(stockmarket).lookUpPrice("Apple");

        
    }
}
