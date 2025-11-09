package com.india.railway.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "Customer")
public class Customer_Redis {

	@Id
	@Indexed
	private Long id; // "indexed" for faster retrieval,
						// @Id for marking this field as the key
	private String name;
	private long phone;
	private String email;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getPhone()=" + getPhone() + ", getEmail()=" + getEmail()
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
