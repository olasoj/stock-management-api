package com.activeedge.interview.stock;

import com.activeedge.interview.stock.model.entity.Stock;
import com.activeedge.interview.stock.model.request.create.CreateAmountRequest;
import com.activeedge.interview.stock.model.request.create.CreateStockRequest;
import com.activeedge.interview.stock.model.request.update.UpdateStockRequest;
import com.activeedge.interview.stock.model.response.StockDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StockServiceTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockService stockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllStocks() {
        StockDto stockDto = getStockDto();
        when(stockRepository.findAllStocks()).thenReturn(List.of(stockDto));
        List<StockDto> allStocks = stockService.getAllStocks();
        assertEquals(List.of(stockDto), allStocks);
    }

    @Test
    void getStock() {
        StockDto stockDto = getStockDto();
        when(stockRepository.findFirstByStockId(1L)).thenReturn(Optional.of(stockDto));
        StockDto allStocks = stockService.getStock(1L);
        assertEquals(stockDto, allStocks);
    }

    @Test
    void updateStock() {
        var updateStockRequest = new UpdateStockRequest("Stock", new CreateAmountRequest(new BigDecimal(20)));
        when(stockRepository.findById(1L)).thenReturn(Optional.of(new Stock()));
        stockService.updateStock(1L, updateStockRequest);
        verify(stockRepository, times(1)).findById(any());
    }

    @Test
    void deleteStock() {
        when(stockRepository.findById(1L)).thenReturn(Optional.of(new Stock()));
        stockService.deleteStock(1L);
        verify(stockRepository, times(1)).findById(any());
        verify(stockRepository, times(1)).deleteById(any());
    }

    @Test
    void createStock() {
        var createStockRequest = new CreateStockRequest("Stock", new CreateAmountRequest(new BigDecimal(20)));

        when(stockRepository.findById(1L)).thenReturn(Optional.of(new Stock()));
        stockService.createStock(createStockRequest);
        verify(stockRepository, times(1)).save(any());
    }

    private StockDto getStockDto() {
        return new StockDto() {
            @Override
            public Long getStockId() {
                return 1L;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public AmountDto getAmount() {
                return null;
            }

            @Override
            public LocalDateTime getCreationDate() {
                return null;
            }

            @Override
            public LocalDateTime getModifiedDate() {
                return null;
            }
        };
    }
}