package com.activeedge.interview.stock;

import com.activeedge.interview.stock.model.request.create.CreateAmountRequest;
import com.activeedge.interview.stock.model.request.create.CreateStockRequest;
import com.activeedge.interview.stock.model.request.update.UpdateStockRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
class StockControllerTest {

    private MockMvc mvc;

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;
    private JacksonTester<CreateStockRequest> createStockRequestJacksonTester;
    private JacksonTester<UpdateStockRequest> updateStockRequestJacksonTester;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(stockController)
                .build();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAllStocks() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get("/stocks")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(stockService, times(1)).getAllStocks();
    }

    @Test
    void createStock() throws Exception {
        var createStockRequest = new CreateStockRequest("Stock", new CreateAmountRequest(new BigDecimal(20)));
        JsonContent<CreateStockRequest> write = createStockRequestJacksonTester.write(createStockRequest);
        MockHttpServletResponse response = mvc.perform(
                post("/stocks").
                        contentType(MediaType.APPLICATION_JSON).content(
                                write.getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        verify(stockService, times(1)).createStock(any());
    }

    @Test
    void getStock() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        get("/stocks/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(stockService, times(1)).getStock(any());
    }

    @Test
    void updateStock() throws Exception {
        var updateStockRequest = new UpdateStockRequest("Stock", new CreateAmountRequest(new BigDecimal(20)));
        JsonContent<UpdateStockRequest> write = updateStockRequestJacksonTester.write(updateStockRequest);
        MockHttpServletResponse response = mvc.perform(
                put("/stocks/1").
                        contentType(MediaType.APPLICATION_JSON).content(
                                write.getJson())).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(stockService, times(1)).updateStock(any(), any());
    }

    @Test
    void deleteStock() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                        delete("/stocks/1")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(stockService, times(1)).deleteStock(any());
    }
}