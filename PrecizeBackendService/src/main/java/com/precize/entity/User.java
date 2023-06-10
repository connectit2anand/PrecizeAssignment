package com.precize.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="user")
public class User {
	
	@Id
	@Column(name="name")
	@NotNull(message = "name cannot be null")
	private String name;
	
	@Column(name="address")
	@NotNull(message = "address cannot be null")
	private String address;
	
	@Column(name="city")
	@NotNull(message = "city cannot be null")
	private String city;
	
	@Column(name="country")
	@NotNull(message = "country cannot be null")
	private String country;
	
	@Length(min=6,max=6,message = "pincode should be of six digits")
	@Column(name="pin_code")
	private String pinCode;
	
	@Column(name="sat_score")
	@Max(100)
	@Min(0)
	private int satScore;
	
	@Column(name="result")
	private String result;
	

}
