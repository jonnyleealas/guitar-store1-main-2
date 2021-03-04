package repositories;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Guitar;
import play.db.jpa.JPAApi;

import java.util.List;
import java.util.Optional;

@Singleton
public class GuitarRepository {
    @Inject JPAApi jpaApi;

    public List<Guitar> findAll() {
        return jpaApi.em().createQuery("SELECT guitars FROM Guitar guitars", Guitar.class).getResultList();
    }

    public Optional<Guitar> findById(int id) {
        return jpaApi.em().createQuery("SELECT guitar FROM Guitar guitar WHERE id = :id", Guitar.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();
    }

    public Guitar add(Guitar guitar) {
        jpaApi.em().persist(guitar);
         return guitar;
    }

    public void update(Guitar guitar) {
        jpaApi.em().merge(guitar);
    }

    public void delete(int id) {
        jpaApi.em().createNativeQuery("DELETE FROM guitars WHERE id = :id")
                .setParameter("id", id).executeUpdate();
    }
}






