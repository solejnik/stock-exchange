package pl.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.spring.demo.entity.ExchangeEntity;
@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeEntity, Long> {

	@Query("select exchange.value from ExchangeEntity exchange where exchange.description like :description")
	public Double getExchangeProperty(@Param("description") String description);

}
