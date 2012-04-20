package yfsc.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="Users")
public class User implements UserDetails {
	
	@Id
	@Column(name="Username")
	private String username;
	
	@Column(name="Password")
	private String Password;
	
	
	/*private String Email;*/
	
	
	@OneToMany(mappedBy="user")
	private List<Role> roles;

	
	
	
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
		return Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}

	/*public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}*/

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
