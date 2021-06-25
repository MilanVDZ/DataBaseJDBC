package data;

import model.Continent;
import model.Country;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class ContinentDAO {

    private final EntityManagerFactory emf;

    public ContinentDAO() {
        emf = EMFactory.getEMF();
    }

    public Continent getContinentById(int id) {
       EntityManager em = emf.createEntityManager();
       return em.find(Continent.class, id);
    }

    public List<Continent> getAllContinents() {
        EntityManager em = emf.createEntityManager();
//        return em.createQuery("FROM Continents", Continent.class).getResultList();
        Query query = em.createQuery("Select c From Continent c");
        List<Continent> continentList = query.getResultList();
        return continentList;
    }

    public Continent getContinentByCountry(Country country) {
        EntityManager em = emf.createEntityManager();
        return em.find(Continent.class, country);
    }

    public void addContinent(Continent continent) {
       EntityManager em = emf.createEntityManager();
       em.getTransaction().begin();
       em.persist(continent);
       em.getTransaction().commit();
    }

    public void updateContinent(Continent continent)  {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(continent);
        em.getTransaction().commit();
    }

    public void deleteContinent(Continent continent) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Continent.class, continent.getId()));
        em.getTransaction().commit();
    }
}
