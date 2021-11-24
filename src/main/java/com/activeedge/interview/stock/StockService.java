package com.activeedge.interview.stock;

import com.activeedge.interview.stock.model.entity.Amount;
import com.activeedge.interview.stock.model.entity.Stock;
import com.activeedge.interview.stock.model.request.create.CreateAmountRequest;
import com.activeedge.interview.stock.model.request.create.CreateStockRequest;
import com.activeedge.interview.stock.model.request.update.UpdateStockRequest;
import com.activeedge.interview.stock.model.response.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class StockService {
    private final StockRepository stockRepository;

    public List<StockDto> getAllStocks() {
        return stockRepository.findAllStocks();
    }

    public StockDto getStock(Long stockId) {
        return stockRepository.findFirstByStockId(stockId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));
    }

    public String updateStock(Long stockId, UpdateStockRequest updateStockRequest) {
        var stock = getStockInRW(stockId);
        if (!Objects.isNull(updateStockRequest.getAmount())) stock.setAmount(new Amount(updateStockRequest.getAmount()));
        if (!Objects.isNull(updateStockRequest.getName())) stock.setName(updateStockRequest.getName());
        return "Updated";
    }

    public String deleteStock(Long stockId) {
        var stock = getStockInRW(stockId);
        stockRepository.deleteById(stock.getStockId());
        return "Deleted";
    }

    public String createStock(CreateStockRequest createStockRequest) {
        CreateAmountRequest amount = createStockRequest.getAmount();
        Stock newStock = new Stock(createStockRequest.getName(), new Amount(amount));
        stockRepository.save(newStock);
        return "Created";
    }

    private Stock getStockInRW(Long stockId) {
        return stockRepository.findById(stockId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock not found"));
    }
}
