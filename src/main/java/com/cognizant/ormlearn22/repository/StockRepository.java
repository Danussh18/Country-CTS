package com.cognizant.ormlearn22.repository;

import com.cognizant.ormlearn22.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    List<Stock> findByCodeAndDateBetween(String code, Date dateBefore, Date dateAfter);
    List<Stock> findByCodeAndCloseGreaterThan(String code, java.math.BigDecimal close);
    List<Stock> findTop3ByOrderByVolumeAsc();
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
    List<Stock> findAllByCode(String code);
}
