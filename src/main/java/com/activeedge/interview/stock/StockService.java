package com.activeedge.interview.stock;

import com.activeedge.interview.stock.model.request.CreateStockRequest;
import com.activeedge.interview.stock.model.request.UpdateStockRequest;
import com.activeedge.interview.stock.model.response.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public Set<StockDto> getAllStocks() {
        return null;
    }

    public StockDto getStock() {
        return null;
    }

    public String updateStock(Long stockId, UpdateStockRequest updateStockRequest) {


        return null;
    }

    public String deleteStock(Long stockId) {
        return null;
    }

    public String createStock(CreateStockRequest createStockRequest) {
        return null;
    }
}
