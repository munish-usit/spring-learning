/**
 * Table in database is created when we successfully run the spring boot application
 * @Entity -- to map class with table. Separate table will be created for this class. It will be persisted in database.
 * @Entity(name="user_details") -- it change the entity name in java  and the created table name in database
 * @Table(name="user_details") -- it doesn't change the entity name, just change the created table name in database
 * @Id -- it specify the primary key in a table.
 * @GeneratedValue -- it automatically generate the id based on generation strategies. It works for Integer,Long data type
 * @Transient -- it specify jpa or hibernate to not save the value of this field in database. 
 * The other approach could be to create static or transient variable on java side.
 * @Temporal(TemporalType.DATE) -- to specify date format like Date or Time or Timestamp
 * @Embeddable -- any custom data type like Address which itself is not an individual entity, but we want to include in another entity like UserDetails.
 * Attributes of embeddable class will get inserted as separate columns in the entity table.
 * @Embedded -- optional, but we can tell hibernate that this data type is embedded.
 * @AttributeOverrides({
		@AttributeOverride(name="zipcode",column=@Column(name="home_zipcode")),
		@AttributeOverride(name="city",column=@Column(name="home_city")),
		@AttributeOverride(name="country",column=@Column(name="home_country"))
	})
 * @AttributeOverride -- used to override the existing column definition of @Embeddable class in @Embedded class
 * @ElementCollection -- used to store list/set/map of any custom data type. Like Set<InterestAreas>.
 * RDBMS doesn't support storing arrray/list/map in any column. Column is singular value.
 * JPA/Hibernate will automatically create additional table and store the mapping like user_id,interest_id,interest_name
 * Table name by default is <EntityName>_<EmbeddableClassName>. We can configure table name and columns name using @JoinTable
 * @JoinTable(name="user_schools",joinColumns=@JoinColumn(name="user_id")) -- we can rename table and join column name.
 * @GenericGenerator(name="hilo-gen",strategy="hilo") -- hibernate annotation
 * @CollectionId(columns = {@Column(name="id")}, generator = "hilo-gen", type = @Type(type="long")) -- hibernate annotation
 * This is used to generate unique id for the table similar to @Id
 * 
 * Relationships
 * @OneToOne(cascade = {CascadeType.ALL}) -- it maps one entity to another entity and creates a one to one relationship.
 * To create one-one relationship, jpa/hibernate automatically create one column also known as join column or foreign key in the parent table.
 * @JoinColumn(name = "school_id") -- it used to change the join column or foreign key column name.
 */

package com.tutorials.database.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity(name="user_details")
public class UserDetails {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="user_name")
	private String userName;
	
	@Temporal(TemporalType.DATE)
	private Date creationDate = new Date();
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="zipcode",column=@Column(name="home_zipcode")),
		@AttributeOverride(name="city",column=@Column(name="home_city")),
		@AttributeOverride(name="country",column=@Column(name="home_country"))
	})
	private Address homeAddress;
	
	@Embedded
	private Address officeAddress;
	
	@ElementCollection
	private Set<InterestAreas> interestAreas;
	
	/**
	@ElementCollection
	@JoinTable(name="user_schools",joinColumns=@JoinColumn(name="user_id"))
	@GenericGenerator(name="hilo-gen",strategy="hilo")
	@CollectionId(columns = {@Column(name="id")}, generator = "hilo-gen", type = @Type(type="long"))
	private Collection<School> schools;
	**/
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "school_id")
	private School school;
	
	@OneToMany(cascade = {CascadeType.ALL})
	@JoinTable(name="user_vehicles",
				joinColumns=@JoinColumn(name="user_id"),
				inverseJoinColumns=@JoinColumn(name="vehicle_id")
				)
	private Collection<Vehicle> vehicles;
	
	@OneToMany(cascade = {CascadeType.ALL},mappedBy="user")
	private Collection<Account> accounts;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	private Collection<Article> articles;
	

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	
	public Set<InterestAreas> getInterestAreas() {
		return interestAreas;
	}
	public void setInterestAreas(Set<InterestAreas> interestAreas) {
		this.interestAreas = interestAreas;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}
	
	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	public Collection<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}
	
	public Collection<Article> getArticles() {
		return articles;
	}
	public void setArticles(Collection<Article> articles) {
		this.articles = articles;
	}
	
	
	/**
	public Collection<School> getSchools() {
		return schools;
	}
	public void setSchools(Collection<School> schools) {
		this.schools = schools;
	}
	**/
	
	
	
	
	
	
	
}
