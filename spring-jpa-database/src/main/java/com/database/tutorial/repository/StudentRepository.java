package com.database.tutorial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.database.tutorial.entity.Student;

/**
 * https://www.baeldung.com/spring-data-derived-queries
 * Query Methods
 * We can define our methods based on Query DSL convention, and Spring JPA will automatically provide the implementation.
 * Spring Data JPA framework inspect the contract, and automatically build the interface implementation.
 * Spring Data JPA supports find, read, query, count and get. 
 * So, for example, we could have done queryByName and Spring Data would behave the same.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	/**
	 * Query Methods
	 * We can define custom Query Methods, and internally Spring JPA will resolve that to Native SQL query to fetch results.
	 */
	public List<Student> findByFirstName(String firstName);
	
	public List<Student> findByFirstNameContaining(String firstName);
	
    public List<Student> findByLastNameNotNull();
    
    public List<Student> findByGuardianName(String guardianName);

    public Student findByFirstNameAndLastName(String firstName,String lastName);
    
	/**
	 * We can also write custom query.
	 * JPQL = JPA Query is Java based and use Entity and Attribute name to identtify table and columns.
	 * Native Query is SQL based query.
	 */
    
    //JPQL
    @Query("select s from Student s where s.emailId = ?1")
    public Student getStudentByEmailAddress(String emailId);
    
    
    //JPQL
    @Query("select s.firstName from Student s where s.emailId = ?1")
    public String getStudentFirstNameByEmailAddress(String emailId);
    

    //Native Query
    @Query(
    	value = "select * from student_details where email_address = ?1",
    	nativeQuery = true
    )
    public Student getStudentByEmailAddressNative(String emailId);
    
    //Native Named Param Query
    @Query(
            value = "SELECT * FROM student_details s where s.email_address = :emailId",
            nativeQuery = true
    )
    public Student getStudentByEmailAddressNativeNamedParam( @Param("emailId") String emailId);
  
}
