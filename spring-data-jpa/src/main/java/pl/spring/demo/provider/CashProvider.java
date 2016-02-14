package pl.spring.demo.provider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import pl.spring.demo.entity.StockEntity;
import pl.spring.demo.wallet.Cash;
@Component
public class CashProvider {
	private ClassLoader classloader;
	private InputStream is;
	private String input;
	
	public CashProvider(){
		classloader = Thread.currentThread().getContextClassLoader();
		is = classloader.getResourceAsStream("cash.txt");
		try {
			input = IOUtils.toString(is, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<Cash> getStocks()  {
		List<Cash> cash = new ArrayList<Cash>();
		String[] cashInput = input.split("\n");
		for(String input:cashInput){
			String[] inputProperty = input.split(",");
			cash.add(new Cash(inputProperty[0], Double.parseDouble(inputProperty[1]),false));
		}
		return cash;
	}

}
