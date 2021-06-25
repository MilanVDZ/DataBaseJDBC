package data;

import model.Country;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class CountryDAO {

    private final EntityManagerFactory emf;

    public CountryDAO() {
        emf = EMFactory.getEMF();
    }

    public Country getCountryById(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Country.class, id);
    }

    public List<Country> getAllCountries() {
        EntityManager em = emf.createEntityManager();
//        return em.createQuery("FROM Country", Country.class).getResultList();
        Query query = em.createQuery("Select c From Country c");
        List<Country> countryList = query.getResultList();
        return countryList;
    }

    public void addCountry(Country country) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(country);
        em.getTransaction().commit();
    }

    public void updateCountry(Country country)  {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(country);
        em.getTransaction().commit();
    }

    public void deleteCountry(Country country) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Country.class, country.getId()));
        em.getTransaction().commit();
    }
}
