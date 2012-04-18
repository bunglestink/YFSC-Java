package yfsc.infrastructure;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;    
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yfsc.entities.persistence.UserService;

@Service 
public class YfscUserDetailsService implements UserDetailsService {

	private UserService userService;
	
	public YfscUserDetailsService(UserService userService) {
		this.userService = userService;
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

		UserDetails userDetails = userService.getUserDetailsByUsername(username);
		
		if (userDetails == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return userDetails;
	}
}