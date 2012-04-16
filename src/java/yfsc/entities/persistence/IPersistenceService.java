package yfsc.entities.persistence;

import java.util.List;

public interface IPersistenceService<T>  {
    
    public List<T> list();
    public T get(int id);
    public void saveOrUpdate(T object);
    public void delete(T object);
}
