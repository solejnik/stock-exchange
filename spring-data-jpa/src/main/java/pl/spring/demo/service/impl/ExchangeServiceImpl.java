package pl.spring.demo.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.repository.ExchangeRepository;
import pl.spring.demo.service.ExchangeService;


@Service
@Transactional(readOnly = true)
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;
    @Override
    public Double changePlnToEur(Double pln){
    	Random random = new Random();
    	Double COURSE_FROM = exchangeRepository.getExchangeProperty("COURSE_FROM");
    	Double COURSE_TO = exchangeRepository.getExchangeProperty("COURSE_TO");
    	Double BUY_MULTIPLIER = exchangeRepository.getExchangeProperty("BUY_MULTIPLIER");
    	Double amount = COURSE_FROM + (COURSE_TO - COURSE_FROM) * random.nextDouble() * BUY_MULTIPLIER;
    	return roundTwoDigits(pln/amount);
    }
    
    @Override
    public Double changeEurToPln(Double eur){
    	Random random = new Random();
    	Double COURSE_FROM = exchangeRepository.getExchangeProperty("COURSE_FROM");
    	Double COURSE_TO = exchangeRepository.getExchangeProperty("COURSE_TO");
    	Double SELL_MULTIPLIER = exchangeRepository.getExchangeProperty("SELL_MULTIPLIER");
    	Double amount = COURSE_FROM + (COURSE_TO - COURSE_FROM) * random.nextDouble() * SELL_MULTIPLIER;
    	return roundTwoDigits(eur*amount);
    }
    
    private Double roundTwoDigits(Double number) {
		return (double) ((double) Math.round(number * 100) / 100);
	}

   
}
