package pl.spring.demo.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import pl.spring.demo.dao.StockDao;
import pl.spring.demo.entity.StockEntity;

public class StockDaoImpl extends AbstractDao<StockEntity, Long> implements StockDao {

	@Override
	public List<StockEntity> findStockByDate(String date) {
		TypedQuery<StockEntity> query = entityManager.createQuery(
	               "select stock from StockEntity stock where stock.date like :date", StockEntity.class);
	       query.setParameter("date", java.sql.Date.valueOf(date));
	       return query.getResultList();
		}



}
