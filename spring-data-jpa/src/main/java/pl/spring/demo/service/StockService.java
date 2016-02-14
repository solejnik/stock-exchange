package pl.spring.demo.service;

import java.util.Date;
import java.util.List;

import pl.spring.demo.entity.StockEntity;
import pl.spring.demo.to.StockTo;

public interface StockService {


	List<StockTo> findAllStocks();

	StockTo saveStock(StockTo stock);

	void initStockService();

	List<StockTo> findStockByDate(String date);

	List<StockTo> findStockByName(String name);

	Double getAvgOfStockBetweenDates(String stockName, String date);

	Double getMinOfStockBetweenDates(String stockName, String date);

	Double getMaxOfStockBetweenDates(String stockName, String date);

	Integer countStocksByNameBetweenTwoDates(String stockName, String date);

	Double countStocksAvgToDateAndDaysAmount(String stockName, String date, Integer days);

	List<Date> getAllDates();

}
