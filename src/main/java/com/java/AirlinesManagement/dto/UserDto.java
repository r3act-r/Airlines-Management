package com.java.AirlinesManagement.dto;

import com.java.AirlinesManagement.entity.UserEntity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {
	
	private int id;
	
	@NotNull(message="User name can't be null")
	private String name;
	
	@NotNull(message="Email Id can't be null")
	private String email;
	
	@NotNull(message="Password is not present")
	@Size(min=6, max= 20 , message="Password length should be of minimum 6 digit")
	private String password;
	
	@NotNull(message="Role can't be null")
	private String role;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ "]";
	}

	public UserEntity userDtotoEntity(UserDto userDto)
	{
		UserEntity user = new UserEntity();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		
		return user;
	}
	
}
