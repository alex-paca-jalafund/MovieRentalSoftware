package pricingTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movie.errorHanlding.priceException.MaxDaysPriceException;
import org.movie.errorHanlding.priceException.MinDaysPriceException;
import org.movie.pricing.Price;
import org.movie.pricing.concretes.PriceContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceContextTest {

    private Price mockPrice;

    @BeforeEach
    void setUp() {
        mockPrice = mock(Price.class);
    }

    @Test
    void testCalculatePrice_ValidDays_ReturnsPrice() throws MinDaysPriceException, MaxDaysPriceException {
        when(mockPrice.calculatePrice(5)).thenReturn(50.0);

        PriceContext context = new PriceContext(mockPrice);

        double result = context.calculatePrice(5);

        assertEquals(50.0, result);
        verify(mockPrice).calculatePrice(5);
    }

    @Test
    void testCalculatePrice_DaysLessThanOne_ThrowsMinDaysPriceException() {
        PriceContext context = new PriceContext(mockPrice);

        assertThrows(MinDaysPriceException.class, () -> context.calculatePrice(0));
    }

    @Test
    void testCalculatePrice_DaysGreaterThanThirty_ThrowsMaxDaysPriceException() {
        PriceContext context = new PriceContext(mockPrice);

        assertThrows(MaxDaysPriceException.class, () -> context.calculatePrice(40));
    }

    @Test
    void testSetPriceStrategy_ChangesStrategy() throws MinDaysPriceException, MaxDaysPriceException {
        Price newStrategy = mock(Price.class);
        when(newStrategy.calculatePrice(3)).thenReturn(30.0);

        PriceContext context = new PriceContext(mockPrice);
        context.setPriceStrategy(newStrategy);

        double result = context.calculatePrice(3);

        assertEquals(30.0, result);
    }
}

