package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@javax.persistence.Table(name = "guitars")
@Entity
public class Guitar {

    public String name, make, model, description, color;

    @Id
    @Column(name = "id", nullable = false)
    public int id;

    @Column(name ="year_made")
    public int yearMade;

    public int getYearMade() { return yearMade; }

    public void setYearMade(int yearMade) { this.yearMade = yearMade; }

    public String getMake() { return make; }

    public void setMake(String make) { this.make = make;}

    public String getModel() { return model;}

    public void setModel(String model) {this.model = model;}

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
