/**
 * Spring JPA by default have CRUD Repository for all Entity type.
 * We just need to extend CrudRepository interface and specialize with Entity type and Primary key.
 * Spring JPA has all implementation for the Crud Repository. We can access the same through Autowired
 */
package org.spring.tutorials;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TutorialRepository extends CrudRepository<Tutorial, Long> {

	List<Tutorial> findByPublished(boolean published);
	List<Tutorial> findByTitleContaining(String title);

}
