package pl.spring.demo.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import pl.spring.demo.dao.StockDao;
import pl.spring.demo.entity.StockEntity;

public class StockDaoImpl extends AbstractDao<StockEntity, Long> implements StockDao {

	@Override
	public List<StockEntity> findBookByDate(String date) {
		TypedQuery<StockEntity> query = entityManager.createQuery(
	               "select stock from StockEntity stock where upper(stock.date) like concat(upper(:date), '%')", StockEntity.class);
	       query.setParameter("date", date);
	       return query.getResultList();
		}



}
