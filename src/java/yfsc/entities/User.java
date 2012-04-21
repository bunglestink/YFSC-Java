package yfsc.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="Users")
public class User implements UserDetails {
	
	public User() {
		this.roles = new LinkedList<Role>();
		this.registrations = new LinkedList<AnnualRegistration>();
	}
	
	@Id
	@NotEmpty
	@Size(min=3, max=100)
	@Column(name="Username")
	private String username;
	
	@NotEmpty
	@Size(min=6, max=100)
	@Column(name="Password")
	private String password;
	
	
	@NotEmpty
	@Column(name="Email")
	private String email;
	
	
	@OneToMany(mappedBy="user")
	private List<Role> roles;

	@OneToMany(mappedBy="user")
	private List<AnnualRegistration> registrations;
	
	
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		return authorities;
	}
	
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String Password) {
		this.password = Password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String Email) {
		this.email = Email;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public List<AnnualRegistration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(List<AnnualRegistration> registrations) {
		this.registrations = registrations;
	}
}
