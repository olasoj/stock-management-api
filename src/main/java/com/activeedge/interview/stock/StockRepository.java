package com.activeedge.interview.stock;

import com.activeedge.interview.stock.model.entity.Stock;
import com.activeedge.interview.stock.model.response.StockDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query(value = "SELECT * FROM stock  LIMIT 100", nativeQuery = true)
    List<StockDto> findAllAsStockDto();

    Optional<StockDto> findFirstById(@Param("id") Long id);
}
