package com.cognizant.ormlearn22.service;

import com.cognizant.ormlearn22.Application;
import com.cognizant.ormlearn22.model.Stock;
import com.cognizant.ormlearn22.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public List<Stock> getStocks() {
        LOGGER.info("----------------GETTING ALL STOCKS-------------");
        return stockRepository.findAll();
    }

    public  List<Stock> getByCode(String code){
        LOGGER.info("----------------GETTING ALL STOCKS BY CODE, {}-------------",code);
        return stockRepository.findAllByCode(code);
    }

    @Transactional
    public List<Stock> getByCodeAndDate(String fb, Date date, Date valueOf) {
        LOGGER.info("----------------GETTING ALL STOCKS BY CODE, {} AND DATE BETWEEN {}, {}-------------",fb,date,valueOf);
        return stockRepository.findByCodeAndDateBetween(fb, date, valueOf);
    }

    @Transactional
    public List<Stock> getByCodeAndStockPrice() {
        LOGGER.info("----------------GETTING ALL STOCKS BY CODE and STOCK PRICE-------------");
        return stockRepository.findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal(1000.00)); // ???
    }

    @Transactional
    public List<Stock> getTop3ByVolume() {
        LOGGER.info("----------------GETTING ALL STOCKS BY TOP3 VOLUME-------------");
        return stockRepository.findTop3ByOrderByVolumeAsc();
    }

    @Transactional
    public List<Stock> getTop3ByCode() {
        LOGGER.info("----------------GETTING ALL STOCKS BY TOP3 HIGH CLOSE BY ASC-------------");
        return stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
    }
}
