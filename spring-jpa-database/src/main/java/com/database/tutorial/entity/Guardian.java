package com.database.tutorial.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable

/**
 * @AttributeOverrides - used to override the default column name and other properties for multiple fields.
 * Column will be created as part of main Entity table.
 * We can also use @Column annotation to override specific column property.
 */
@AttributeOverrides({ 
	@AttributeOverride(name = "name" , column = @Column(name = "guardian_name")) ,
	@AttributeOverride(name = "mobile" , column = @Column(name = "guardian_mobile")) ,
	@AttributeOverride(name = "emailId" , column = @Column(name = "guardian_email_address")) 
})
public class Guardian {

	private String name;
	private String mobile;
	private String email;
}
