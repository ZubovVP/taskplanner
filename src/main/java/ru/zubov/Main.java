package ru.zubov;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ru.zubov.entity.User;
import ru.zubov.utils.HibernateUtil;


@Log4j2
public class Main {


    public static void main(String[] args) {
        log.info("Start Hibernate method!");

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        //

        //Запрос с несколькими параметрами
//        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
//        Root<User> root = criteriaQuery.from(User.class);
//        criteriaQuery.select(root);
//        Predicate p1 = criteriaBuilder.gt(root.get("id"), 20);
//        Predicate p2 = criteriaBuilder.lt(root.get("id"), 22);
//        criteriaQuery.select(root).where(criteriaBuilder.and(p1, p2));
//        Query query = session.createQuery(criteriaQuery);
//        List<User> users = query.getResultList();


        //Удаление записи по id
//        CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
//        Root<User> root = criteriaDelete.from(User.class);
//        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), 15));
//        session.createQuery(criteriaDelete).executeUpdate();


        //Обновление записи
        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> root = criteriaUpdate.from(User.class);

        criteriaUpdate.set("email", "123");
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), 15));
        session.createQuery(criteriaUpdate).executeUpdate();

        session.getTransaction().commit();
        session.close();
        HibernateUtil.close();
    }
}
