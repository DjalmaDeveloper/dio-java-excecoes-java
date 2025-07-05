package br.com.dio.model;

import java.time.OffsetDateTime;
import java.util.Objects;

public class UserModel {

	private long id;
	private String name;
	private String email;
	private OffsetDateTime birthdate;
	
	public UserModel() {}

	public UserModel(long id, String name, String email, OffsetDateTime birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthdate = birthdate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public OffsetDateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(OffsetDateTime birthdate) {
		this.birthdate = birthdate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthdate, email, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserModel other = (UserModel) obj;
		return Objects.equals(birthdate, other.birthdate) &&
				Objects.equals(email, other.email) && id == other.id &&
				Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id +
				", name=" + name +
				", email=" + email +
				", birthdate=" + birthdate +
				"]";
	}
	
	
}
