package com.activeedge.interview.stock;

import com.activeedge.interview.stock.model.request.create.CreateStockRequest;
import com.activeedge.interview.stock.model.request.update.UpdateStockRequest;
import com.activeedge.interview.util.model.response.ResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/stocks")
@AllArgsConstructor
public class StockController {
    private static final String MESSAGE = "message";
    private final StockService stockService;

    @GetMapping(path = "")
    public void getAllStocks(HttpServletRequest request, HttpServletResponse response) {
        ResponseModel.responseWriter(request, response, HttpStatus.OK,
                stockService.getAllStocks());
    }

    @PostMapping(path = "")
    public void createStock(@Valid @RequestBody CreateStockRequest createStockRequest,
                            HttpServletRequest request, HttpServletResponse response) {
        ResponseModel.responseWriter(request, response, HttpStatus.CREATED,
                stockService.createStock(createStockRequest));
    }

    @GetMapping(path = "/{stockId}")
    public void getStock(@PathVariable(value = "stockId") Long stockId, HttpServletRequest request, HttpServletResponse response) {
        ResponseModel.responseWriter(request, response, HttpStatus.OK,
                stockService.getStock(stockId));
    }

    @PutMapping(value = "/{stockId}")
    public void updateStock(@Valid @RequestBody UpdateStockRequest updateStockRequest, @PathVariable(value = "stockId") Long stockId,
                            HttpServletRequest request, HttpServletResponse response) {
        ResponseModel.responseWriter(request, response, HttpStatus.OK,
                stockService.updateStock(stockId, updateStockRequest));
    }

    @DeleteMapping(value = "/{stockId}")
    public void deleteStock(@PathVariable(value = "stockId") Long stockId, HttpServletRequest request, HttpServletResponse response) {
        ResponseModel.responseWriter(request, response, HttpStatus.OK,
                stockService.deleteStock(stockId));
    }
}
