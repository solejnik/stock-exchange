package pl.spring.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class ExchangeServiceImplTest {

	@Autowired
	private ExchangeService exchangeService  ;


	@Test
	public void testShouldFindBookByCriteriaTitleAuthorLibraryName() {
		//given
		for(int i =0;i<10;i++){
			Double changePlnToEur = exchangeService.changePlnToEur(100.0);
			System.out.println("11111111111111111111111111111111111111111111 "+changePlnToEur);
		}
	}

}