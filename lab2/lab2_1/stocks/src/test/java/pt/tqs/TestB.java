package pt.tqs;

import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TestB{
    @Mock
    IStockmarketService stockmarket;

    @InjectMocks
    StocksPortfolio portfolio;

    @Test
    public void getTotalValue() {
        Stock amazonStock = new Stock("Amazon", 10);
        Stock microsoftStock = new Stock("Microsoft", 20);

        portfolio.addStock(amazonStock);
        portfolio.addStock(microsoftStock);

        when(stockmarket.lookUpPrice("Amazon")).thenReturn(300.0);
        when(stockmarket.lookUpPrice("Microsoft")).thenReturn(400.0);

        double result = portfolio.getTotalValue();

        assertEquals(11000.0, result);

        verify(stockmarket).lookUpPrice("Amazon");
        verify(stockmarket).lookUpPrice("Microsoft");

    }
}