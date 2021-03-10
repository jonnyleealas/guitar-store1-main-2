package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@javax.persistence.Table(name = "people")
@Entity
public class Person {

    public String name;

    @Id
    @Column(name = "id", nullable = false)
    public int id;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }


}
