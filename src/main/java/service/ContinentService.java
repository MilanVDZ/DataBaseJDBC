package service;

import data.ContinentDAO;
import model.Continent;
import java.util.List;
import java.util.Scanner;

public class ContinentService {

    private final ContinentDAO continentDAO;
    private final Scanner scanner = new Scanner(System.in);

    public ContinentService(){
        continentDAO = new ContinentDAO();
    }

    public void showAllContinents() {
        List<Continent> continents = continentDAO.getAllContinents();
        for (Continent continent:continents)
            System.out.println(continent);
    }

    public void showContinentById() {
        System.out.println("Give me the ID of the continent");
        int id = scanner.nextInt();
        Continent continent = continentDAO.getContinentById(id);
        System.out.println(continent);
    }

    public void insertAContinent() {
        System.out.println("Give me the name of the new continent");
        String name = scanner.next();
        Continent continent = new Continent(name);
        continentDAO.addContinent(continent);
    }

    public void updateAContinent() {
        showAllContinents();
        System.out.println("Choose the ID of which continent to change.");
        int originalId = scanner.nextInt();
        Continent continent = continentDAO.getContinentById(originalId);
        System.out.println("Type in the new ID if you want to change it. Press '0' if you don't want to change anything.");
        int newId = scanner.nextInt();
        if(newId!= 0)continent.setId(newId);
        System.out.println("Type in the new name if you want to change it. Press '0' if you don't want to change anything.");
        String newName = scanner.next();
        if (!newName.equals("0"))continent.setName(newName);
        continentDAO.updateContinent(continent);
    }

    public void deleteAContinent() {
        showAllContinents();
        System.out.println("Choose the ID of which continent to delete.");
        int originalId = scanner.nextInt();
        Continent continent = continentDAO.getContinentById(originalId);
        continentDAO.deleteContinent(continent);
    }
}
