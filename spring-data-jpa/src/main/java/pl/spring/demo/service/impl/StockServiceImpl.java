package pl.spring.demo.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.entity.StockEntity;
import pl.spring.demo.mapper.StockMapper;
import pl.spring.demo.provider.StocksProvider;
import pl.spring.demo.repository.StockRepository;
import pl.spring.demo.service.StockService;
import pl.spring.demo.to.StockTo;

@Service
@Transactional(readOnly = true)
public class StockServiceImpl implements StockService {

	public StockServiceImpl() {
	}

	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private StocksProvider stocksProvider;

	@Override
	public List<StockTo> findAllStocks() {
		return StockMapper.map2To(stockRepository.findAll());
	}

	@Override
	public List<StockTo> findStockByName(String name) {
		return StockMapper.map2To(stockRepository.findStockByName(name));
	}

	@Override
	public List<StockTo> findStockByDate(String date) {
		return StockMapper.map2To(stockRepository.findStockByDate(java.sql.Date.valueOf(date)));
	}

	@Override
	@Transactional(readOnly = false)
	public StockTo saveStock(StockTo stock) {
		StockEntity entity = StockMapper.map(stock);
		entity = stockRepository.save(entity);
		return StockMapper.map(entity);
	}

	@Override
	@Transactional(readOnly = false)
	public void initStockService() {
		List<StockEntity> stocks = stocksProvider.getStocks();
		for (StockEntity stock : stocks) {
			saveStock(StockMapper.map(stock));
		}
	}

	@Override
	public Double getAvgOfStockBetweenDates(String stockName, String date) {
		Double stockList = stockRepository.countStockAvgByNameBetweenTwoDates(stockName, java.sql.Date.valueOf(date));
		return stockList;
	}

	@Override
	public Double getMinOfStockBetweenDates(String stockName, String date) {
		Double stockList = stockRepository.countStockMinByNameBetweenTwoDates(stockName, java.sql.Date.valueOf(date));
		return stockList;
	}

	@Override
	public Double getMaxOfStockBetweenDates(String stockName, String date) {
		Double stockList = stockRepository.countStockMaxByNameBetweenTwoDates(stockName, java.sql.Date.valueOf(date));
		return stockList;
	}
	@Override
	public Integer countStocksByNameBetweenTwoDates(String stockName, String date) {
		return stockRepository.countStocksByNameBetweenTwoDates(stockName, java.sql.Date.valueOf(date));
	}
	
	@Override
	public Double countStocksAvgToDateAndDaysAmount(String stockName, String date, Integer days){
		List<StockEntity> stocksByNameTillDate = stockRepository.getStocksByNameTillDates(stockName,java.sql.Date.valueOf(date),new PageRequest(0, days, Direction.DESC, "date"));
		Integer countDays = 0;
		Double sum = 0.0;
		Double avg = 0.0;
		for(StockEntity stock:stocksByNameTillDate){
			countDays++;
			sum+=stock.getPrice();
			if(countDays>=days){
				break;
			}
		}
		avg = sum/countDays;
		return avg;
	}
	
	@Override
	public List<Date> getAllDates(){
		return stockRepository.getAllDates();
	}
}
