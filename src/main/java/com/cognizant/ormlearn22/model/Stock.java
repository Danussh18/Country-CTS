package com.cognizant.ormlearn22.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="stock")
    public class Stock {

        @Id
        @Column(name = "st_id")
        private int id;

        @Column(name = "st_code")
        private String code;

        @Column(name = "st_date")
        private Date date;

        @Column(name = "st_open")
        private BigDecimal open;

        @Column(name = "st_close")
        private BigDecimal close;

        @Column(name = "st_volume")
        private BigDecimal volume;

        @Override
        public String toString() {
            return "Stock [id=" + id + ", code=" + code + ", date=" + date + ", open=" + open + ", close=" + close
                    + ", volume=" + volume + "]";
        }
    }
