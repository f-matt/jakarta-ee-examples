package com.github.fmatt;

import java.util.Objects;

public class MyEntity {
	
	private Integer id;
	
	private String description;
	
	public MyEntity(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public MyEntity() {
		
	}
	
	@Override
	public String toString() {
		return "MyEntity [id:" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyEntity other = (MyEntity) obj;
		return Objects.equals(id, other.id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
