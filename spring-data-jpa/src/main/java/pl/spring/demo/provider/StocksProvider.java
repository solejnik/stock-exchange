package pl.spring.demo.provider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.spring.demo.entity.StockEntity;
@Component
public class StocksProvider {
	private ClassLoader classloader;
	private InputStream is;
	private String input;
	
	public StocksProvider(){
		classloader = Thread.currentThread().getContextClassLoader();
		is = classloader.getResourceAsStream("dane.csv");
		try {
			input = IOUtils.toString(is, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<StockEntity> getStocks()  {
		List<StockEntity> stocks = new ArrayList<StockEntity>();
		String[] stocksInput = input.split("\n");
		for (String stock : stocksInput) {
			String[] stockSplited = stock.split(",");
			String stockName = stockSplited[0];
			String stockPrice = stockSplited[2];
			String stockDate = stockSplited[1];
			stockDate = stockDate.substring(0, 4)+"-"+stockDate.substring(4,6)+"-"+stockDate.substring(6);
			StockEntity stockEntity = new StockEntity(stockName,Double.parseDouble(stockPrice),java.sql.Date.valueOf(stockDate));
			stocks.add(stockEntity);
		}
		return stocks;
	}

}
