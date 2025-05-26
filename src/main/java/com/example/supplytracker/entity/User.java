package com.example.supplytracker.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.supplytracker.enums.Role;

@Entity
@Data
@Table(name="USERS")
@NoArgsConstructor // to create constructor with no parameters
@AllArgsConstructor // to create constructor with the below parameters

public class User
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; //@Id and @GeneratedValue will help me generate a primary key id
	
	private String name;
	
	@Column(unique=true) // it causes the email to be unique for each user
	private String email; 

	private String password;
	
	@Enumerated(EnumType.STRING) // maps the roles present in enums to this as a string 
		private Role role;
}
