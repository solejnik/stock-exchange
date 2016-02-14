package pl.spring.demo.dao;

import java.util.List;

import pl.spring.demo.entity.StockEntity;

public interface StockDao extends Dao<StockEntity, Long> {

    List<StockEntity> findBookByDate(String date);
}
