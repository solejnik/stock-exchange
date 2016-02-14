package pl.spring.demo.configuration;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import pl.spring.demo.provider.CashProvider;
import pl.spring.demo.provider.DatesProvider;
import pl.spring.demo.wallet.Cash;

@Configuration
public class AppConfig {
	@Autowired
	private CashProvider cashProvider;
	@Autowired
	private DatesProvider datesProvider ;
   
	@Bean
	@Scope("prototype")
    public List<Cash> initCash() {
        return cashProvider.getStocks();
    }
    
    @Bean
    public List<Date> initDates(){
    	return datesProvider.getDates();
    }
    
    @Bean
    public Iterator<Date> initDateIterator(){
    	return initDates().iterator();
    }
}
  