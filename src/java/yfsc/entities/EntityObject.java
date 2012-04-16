package yfsc.entities;

import java.io.Serializable;
import javax.persistence.*;

@MappedSuperclass
public abstract class EntityObject implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}