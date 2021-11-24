package com.activeedge.interview.stock;

import com.activeedge.interview.stock.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock, Long> {
    @Query(value = "SELECT * FROM users u  Where LOWER(u.email )= :email", nativeQuery = true)
    Optional<Stock> findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM users u  Where u.business_id = :business_id", nativeQuery = true)
    List<Stock> findUsersByBusinessId(@Param("business_id") UUID businessId);
}
