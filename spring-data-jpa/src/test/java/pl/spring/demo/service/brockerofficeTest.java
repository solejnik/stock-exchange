package pl.spring.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.brockeroffice.BrockerOffice;
import pl.spring.demo.provider.CashProvider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class brockerofficeTest {

	@Autowired
	private BrockerOffice brockeroffice;
	@Autowired
	private CashProvider cashProvider;

	@Test
	public void testShouldFindBookByCriteriaTitleAuthorLibraryName() {
		brockeroffice.initBrockerOffice();
		brockeroffice.play();
	}

}