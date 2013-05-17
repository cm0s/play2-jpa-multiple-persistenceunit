package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User extends Model{

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public User(String name) {
        this.name = name;
    }
}
