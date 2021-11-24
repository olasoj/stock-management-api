package com.activeedge.interview.stock;

import com.activeedge.interview.stock.model.request.CreateStockRequest;
import com.activeedge.interview.stock.model.request.UpdateStockRequest;
import com.activeedge.interview.util.model.response.ResponseModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/team")
@AllArgsConstructor
public class StockController {
    private static final String MESSAGE = "message";
    private final StockService stockService;

    @GetMapping(value = "")
    public void teamMembers(
            HttpServletRequest request, HttpServletResponse response) {
        ResponseModel.responseSuccess(request, response, HttpStatus.OK, "team_members",
                stockService.getAllStocks());
    }

    @PostMapping(value = "/create")
    public void teamMember(@Valid @RequestBody CreateStockRequest createStockRequest,
                           HttpServletRequest request, HttpServletResponse response) {
        ResponseModel.responseSuccess(request, response, HttpStatus.CREATED, MESSAGE,
                stockService.createStock(createStockRequest));
    }

    @PutMapping(value = "/{stockId}")
    public void teamMember(@Valid @RequestBody UpdateStockRequest updateStockRequest,
                           @PathVariable(value = "stockId") Long stockId,
                           HttpServletRequest request, HttpServletResponse response) {
        ResponseModel.responseSuccess(request, response, HttpStatus.OK, MESSAGE,
                stockService.updateStock(stockId, updateStockRequest));
    }

    @DeleteMapping(value = "/{stockId}")
    public void teamMember(@PathVariable(value = "stockId") Long stockId,
                           HttpServletRequest request, HttpServletResponse response) {
        ResponseModel.responseSuccess(request, response, HttpStatus.OK, MESSAGE,
                stockService.deleteStock(stockId));
    }
}
