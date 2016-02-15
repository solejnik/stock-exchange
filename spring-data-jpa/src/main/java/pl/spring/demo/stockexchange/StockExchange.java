package pl.spring.demo.stockexchange;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.service.StockService;
import pl.spring.demo.to.StockTo;
@Component
public class StockExchange {
	@Autowired
	private StockService stockService;
	@Resource(name="initDates")
	private List<Date> dates;
	@Resource(name="initDateIterator")
	private Iterator<Date> datesIterator;
	
	public StockExchange() {
	}
	
	public List<StockTo> getStocks(){
		return stockService.findStockByDate(datesIterator.next().toString());
	}

	public List<Date> getDates() {
		return dates;
	}
	
	
}
