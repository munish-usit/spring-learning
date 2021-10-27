package com.database.tutorial.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.database.tutorial.entity.Address;
import com.database.tutorial.entity.Guardian;
import com.database.tutorial.entity.Student;

@SpringBootTest
public class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	public void saveStudent() {
		Student student = Student.builder()
				.emailId("rahul1@gmail.com")
				.firstName("Rahul1")
				.lastName("Sharma")
				//.guardianName("Nikhil")
				//.guardianEmail("nikhil@gmail.com")
				//.guardianMobile("9999999999")
				.build();

		studentRepository.save(student);
	}
	

    @Test
    public void saveStudentWithGuardian() {

        Guardian guardian = Guardian.builder()
                .email("nikhil@gmail.com")
                .name("Nikhil")
                .mobile("9999956324")
                .build();

        Student student = Student.builder()
                .firstName("Rahul2")
                .emailId("rahul2@gmail.com")
                .lastName("Rahul")
                .guardian(guardian)
                .build();

        studentRepository.save(student);

    }
    
    @Test
    public void saveStudentWithAddress() {

    	Address homeAddress = Address.builder().street("delhi").city("delhi").pinCode("110094").build();
    	Address officeAddress = Address.builder().street("noida").city("noida").pinCode("201306").build();
    	
    	Student student = Student.builder()
    			.firstName("Rahul3")
    			.emailId("rahul3@gmail.com")
    			.lastName("Sharma")
    			.homeAddress(homeAddress)
    			.officeAddress(officeAddress)
    			.build();

    	studentRepository.save(student);

    }
    
    
    @Test
    public void saveStudentWithTopics() {

    	Student student = Student.builder()
    			.firstName("Rahul4")
    			.emailId("rahul4@gmail.com")
    			.lastName("Sharma")
    			.topics(Arrays.asList("History","Science"))
    			.build();

    	studentRepository.save(student);

    }
    
    @Test
    public void printAllStudent() {
        List<Student> studentList =  studentRepository.findAll();
        System.out.println("studentList = " + studentList);
    }
    
    @Test
    public void printStudentByFirstName() {
        
        List<Student> students =
                studentRepository.findByFirstName("Rahul1");

        System.out.println("students = " + students);
    }
    
    @Test
    public void printStudentByFirstNameContaining() {

        List<Student> students =
                studentRepository.findByFirstNameContaining("Rahul");

        System.out.println("students = " + students);
    }
    
    @Test
    public void printStudentBasedOnGuardianName(){
        List<Student> students =
                studentRepository.findByGuardianName("Nikhil");
        System.out.println("students = " + students);
    }
    
    @Test
    public void printgetStudentByEmailAddress() {
        Student student =
                studentRepository.getStudentByEmailAddress(
                        "rahul3@gmail.com"
                );

        System.out.println("student = " + student);
    }
    
    
    @Test
    public void printgetStudentFirstNameByEmailAddress() {
        String firstName =
                studentRepository.getStudentFirstNameByEmailAddress(
                        "rahul1@gmail.com"
                );
        System.out.println("firstName = " + firstName);
    }
    
    @Test
    public void printgetStudentByEmailAddressNative(){
        Student student =
                studentRepository.getStudentByEmailAddressNative(
                        "rahul2@gmail.com"
                );

        System.out.println("student = " + student);
    }
    
  
    @Test
    public void printgetStudentByEmailAddressNativeNamedParam() {
        Student student =
                studentRepository.getStudentByEmailAddressNativeNamedParam(
                        "rahul3@gmail.com"
                );

        System.out.println("student = " + student);
    }
  
    
    
}
