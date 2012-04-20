package yfsc.entities.persistence;

import java.util.Collections;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import yfsc.entities.User;

@Service
public class UserService implements IPersistenceService<User> {

    SessionFactory sessionFactory;
	private String rolePrefix;
    
    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
	
	
	
	
	
	public String getRolePrefix() {
		return rolePrefix;
	}

	public void setRolePrefix(String rolePrefix) {
		this.rolePrefix = rolePrefix;
	}
}
