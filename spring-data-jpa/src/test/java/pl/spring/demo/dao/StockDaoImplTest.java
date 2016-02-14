package pl.spring.demo.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.StockEntity;
import pl.spring.demo.repository.StockRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class StockDaoImplTest {

    @Autowired
    private StockRepository stockDao;

    @Test
    public void testShouldFindBookById() {
        // given
        final long stockId = 1;
        // when
        List<StockEntity> findStockByDate = stockDao.findStockByDate(java.sql.Date.valueOf("2014-02-20"));
        // then
    }


}
