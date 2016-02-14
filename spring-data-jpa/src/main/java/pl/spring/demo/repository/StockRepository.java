package pl.spring.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.spring.demo.entity.StockEntity;
@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {


    @Query("select stock from StockEntity stock where upper(stock.name) like concat(upper(:name), '%')")
    public List<StockEntity> findStockByName(@Param("name") String name);
    
    @Query("select stock from StockEntity stock where stock.date = :date")
    public List<StockEntity> findStockByDate(@Param("date") Date date);
    
    @Query("select round(avg(stock.price),2) from StockEntity stock where ((stock.name like :name) and (stock.date >= :date))")
    public Double countStockAvgByNameBetweenTwoDates(@Param("name") String name,@Param("date") Date date);
    
    @Query("select min(stock.price) from StockEntity stock where ((stock.name like :name) and (stock.date >= :date))")
    public Double countStockMinByNameBetweenTwoDates(@Param("name") String name,@Param("date") Date date);
    
    @Query("select max(stock.price) from StockEntity stock where ((stock.name like :name) and (stock.date >= :date))")
    public Double countStockMaxByNameBetweenTwoDates(@Param("name") String name,@Param("date") Date date);
    
    @Query("select count(stock) from StockEntity stock where ((stock.name like :name) and (stock.date <= :date))")
    public Integer countStocksByNameBetweenTwoDates(@Param("name") String name,@Param("date") Date date);
    
    @Query("select stock from StockEntity stock where ((stock.name like :name) and (stock.date <= :date))")
    public List<StockEntity> getStocksByNameTillDates(@Param("name") String name,@Param("date") Date date,Pageable pageable);

    @Query("select distinct stock.date from StockEntity stock order by stock.date")
    public List<Date> getAllDates();
    
}
