package pl.spring.demo.provider;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import pl.spring.demo.entity.StockEntity;
@Component
public class DatesProvider {
	private ClassLoader classloader;
	private InputStream is;
	private String input;
	
	public DatesProvider(){
		classloader = Thread.currentThread().getContextClassLoader();
		is = classloader.getResourceAsStream("dane.csv");
		try {
			input = IOUtils.toString(is, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public List<Date> getDates()  {
		List<Date> dates = new ArrayList<Date>();
		String[] datesInput = input.split("\n");
		for (String data : datesInput) {
			String[] dateSplited = data.split(",");
			String newDate = dateSplited[1];
			newDate = newDate.substring(0, 4)+"-"+newDate.substring(4,6)+"-"+newDate.substring(6);
			Date dateDate = java.sql.Date.valueOf(newDate);
			if(!dates.contains(dateDate)){
				dates.add(dateDate);
			}
		}
		Collections.sort(dates);
		return dates;
	}

}
