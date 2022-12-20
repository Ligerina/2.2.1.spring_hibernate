package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public List<Car> listCars() {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String carModel, int carSeries) {
      System.out.println("1");
      System.out.println("2");
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car as c where c.model = :carM and c.series = :carS");
      query.setParameter("carM",carModel);
      query.setParameter("carS",carSeries);
      System.out.println("НАЙДЕНА МАШИНА С ID: " + query.getResultList().get(0).getId());

      TypedQuery<User> query1 = sessionFactory.getCurrentSession().createQuery("from User as u where u.car = :id");
      query1.setParameter("id",query.getResultList().get(0));
      System.out.println("НАЙДЕН ЧЕЛОВЕК С МАШИНОЙ: " + query1.getResultList().get(0).getFirstName());
      return query1.getResultList().get(0);
   }

}
