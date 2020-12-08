package com.sapient.demo.forexservice.testcases;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.sapient.demo.forexservice.models.ForexModel;
import com.sapient.demo.forexservice.repositories.ForexRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ForexDataRepositoryTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private ForexRepository forexRepository;
	
	
	@Test
	public void findByFromAndToTest() {
	    // given
	    ForexModel model = new ForexModel(1L, "DEN", "INR", BigDecimal.valueOf(50));
	    entityManager.persist(model);
	    entityManager.flush();
	 
	    // when
	    ForexModel found = forexRepository.findByFromAndTo("DEN", "INR");
	 
	    assertEquals(found.getId(),model.getId());
	}
}
