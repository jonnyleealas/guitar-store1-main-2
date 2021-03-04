package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@javax.persistence.Table(name = "guitars")
@Entity
public class Guitar {

    public String make, model, description, color;

    @Id
    @Column(name = "id", nullable = false)
    public int id;

    @Column(name ="year_made")
    public int yearMade;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }


}
