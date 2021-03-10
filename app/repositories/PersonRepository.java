package repositories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Guitar;
import models.Person;

import play.db.jpa.JPAApi;

import java.util.List;
import java.util.Optional;

@Singleton
public class PersonRepository {
    @Inject JPAApi jpaApi;

    public List<Person> findAll() {
        return jpaApi.em().createQuery("SELECT people FROM Person people", Person.class).getResultList();
    }

    public Optional<Person> findById(int id) {
        return jpaApi.em().createQuery("SELECT person FROM Person person WHERE id = :id", Person.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();
    }
    public Person add(Person person) {
        jpaApi.em().persist(person);
        return person;
    }

    public void update(Person person) {
        jpaApi.em().merge(person);
    }

    public void delete(int id) {
        jpaApi.em().createNativeQuery("DELETE FROM people WHERE id = :id")
                .setParameter("id", id).executeUpdate();
    }
}






