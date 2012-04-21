package yfsc.entities.persistence;

import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import yfsc.entities.Role;
import yfsc.entities.User;

@Service
public class UserService implements IPersistenceService<User> {

    private SessionFactory sessionFactory;
	private SecurityContext securityContext;
	
	private String rolePrefix;
	private String userRolename;
	private String adminRolename;
	
	
	public UserService(SessionFactory sessionFactory, SecurityContext securityContext) {
		this.sessionFactory = sessionFactory;
		this.securityContext = securityContext;
	}
	
	
    
    @Override
    public List<User> list() {
        Query query = sessionFactory.getCurrentSession().createQuery("from User r order by r.startDate desc");
        return Collections.checkedList(query.list(), User.class);
    }
    
	@Deprecated
    @Override
    public User get(int id) {
        throw new NotImplementedException();
    }
	public User get(String username) {
		return (User)sessionFactory.getCurrentSession().get(User.class, username);
	}
    
    @Override
    public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
    }
    
    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
	
	public boolean isUsernameAvailable(String username) {
		return sessionFactory.getCurrentSession().get(User.class, username) == null;
	}
	
	
	public void createUserAccount(User user) throws Exception {
		if (!isUsernameAvailable(user.getUsername())) {
			throw new Exception("Username not avaialable.");
		}
		
		saveOrUpdate(user);
		grantRole(user, userRolename);
	}
	
	public void grantRole(User user, String rolename) {
		Role role = new Role();
		role.setUser(user);
		role.setRolename(rolename);
		sessionFactory.getCurrentSession().saveOrUpdate(role);
	}
	
	
	
	public void loginUser(User user) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		securityContext.setAuthentication(authentication);
	}
	
	
	
	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}
	public void setUserRolename(String rolename) {
		this.userRolename = rolename;
	}
	public void setAdminRolename(String rolename) {
		this.adminRolename = rolename;
	}
}
