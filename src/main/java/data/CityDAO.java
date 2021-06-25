package data;

import model.City;
import model.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class CityDAO {

    private final EntityManagerFactory emf;

    public CityDAO() {
        emf = EMFactory.getEMF();
    }

    public City getCityById(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(City.class, id);
    }

    public List<City> getAllCities() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select c From City c");
        List<City> citiesList = query.getResultList();
        return citiesList;
    }

    public void addCity(City city) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(city);
        em.getTransaction().commit();
    }

    public void updateCity(City city)  {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(city);
        em.getTransaction().commit();
    }

    public void deleteCity(City city) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(City.class, city.getId()));
        em.getTransaction().commit();
    }
}
