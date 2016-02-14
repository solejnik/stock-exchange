package pl.spring.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import pl.spring.demo.service.StockService;

@Component
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private StockService stockService;

	@Override
	public void onApplicationEvent(final ContextRefreshedEvent event) {
		stockService.initStockService();
	}
}