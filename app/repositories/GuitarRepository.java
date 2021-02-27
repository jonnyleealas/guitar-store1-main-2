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
        System.out.println("im in add"+ guitar.id);
        jpaApi.em().persist(guitar);
         return guitar;
    }

    public void update(Guitar guitar) {

        System.out.println("Im in update");
        jpaApi.em().merge(guitar);
    }

    public void delete(int id) {
        System.out.println("inside delete");
        jpaApi.em().createNativeQuery("DELETE FROM guitars WHERE id = :id")
                .setParameter("id", id).executeUpdate();
    }



}


//    public void delete(Guitar guitar) {
//        jpaApi.em().remove(guitar);
//    }




//    public void add(Guitar guitar) {
//        if (!findById(guitar.getId()).isPresent()) {
//            guitar.setId(++guitarId);
//            guitarList.add(guitar);
//        }
//    }

//    public void update(Guitar guitar) {
//        System.out.println("in update repo");
//        Optional<Guitar> guitarOptional = findById(guitar.getId());
//        if (guitarOptional.isPresent()) {
//            Guitar guitarToUpdate = guitarOptional.get();
//
//            guitarToUpdate.setDescription(guitar.getDescription());
//            guitarToUpdate.setName(guitar.getName());
//        }
//    }

