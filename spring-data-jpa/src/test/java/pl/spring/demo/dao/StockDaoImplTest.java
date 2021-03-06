package pl.spring.demo.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.StockEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class StockDaoImplTest {

    @Autowired
    private StockDao stockDao;

    @Test
    public void testShouldFindBookById() {
        // given
        final String date = "2013-02-20";
        // when
       List<StockEntity> findStockByDate = stockDao.findStockByDate(date);
        // then
        assertNotNull(findStockByDate);
    }
}