package yfsc.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Roles")
public class Role extends EntityObject {
	
	@Column(name="Role")
	private String rolename;
	
	@ManyToOne
	@JoinColumn(name="Username")
	private User user;

	
	
	
	
	
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="Username")
	public String getUsername() {
		return user.getUsername();
	}
}
