package frequentRenterPointsTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.movie.errorHanlding.RenterPointsException.RenterPointsCustomerException;
import org.movie.frequentRenterPoints.RenterPoints;
import org.movie.frequentRenterPoints.concretes.RenterPointsContext;
import org.movie.rental.Customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RenterPointsContextTest {

    private RenterPoints mockPoints;
    private Customer mockCustomer;

    @BeforeEach
    void setUp() {
        mockPoints = mock(RenterPoints.class);
        mockCustomer = mock(Customer.class);
    }

    @Test
    void testCalculatePoints_ValidCustomer_ReturnsPoints() throws RenterPointsCustomerException {
        when(mockPoints.calculatePoints(mockCustomer)).thenReturn(5);
        RenterPointsContext context = new RenterPointsContext(mockPoints);

        int result = context.calculatePoints(mockCustomer);

        assertEquals(5, result);
        verify(mockPoints).calculatePoints(mockCustomer);
    }

    @Test
    void testCalculatePoints_NullCustomer_ThrowsException() {
        RenterPointsContext context = new RenterPointsContext(mockPoints);

        assertThrows(RenterPointsCustomerException.class, () -> context.calculatePoints(null));
    }

    @Test
    void testSetRenterPointsStrategy_ChangesStrategy() throws RenterPointsCustomerException {
        RenterPoints newStrategy = mock(RenterPoints.class);
        when(newStrategy.calculatePoints(mockCustomer)).thenReturn(10);

        RenterPointsContext context = new RenterPointsContext(mockPoints);
        context.setRenterPointsStrategy(newStrategy);

        int result = context.calculatePoints(mockCustomer);

        assertEquals(10, result);
    }
}

