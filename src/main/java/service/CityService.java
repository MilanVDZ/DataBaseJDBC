package service;

import data.CityDAO;
import data.CountryDAO;
import model.City;
import model.Country;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CityService {

    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final Scanner scanner = new Scanner(System.in);

    public CityService(){
        cityDAO = new CityDAO();
        countryDAO = new CountryDAO();
    }

    public void showAllCities() {
        List<City> cities = cityDAO.getAllCities();
        for (City city:cities)
            System.out.println(city);
    }

    public void showCityById() {
        System.out.println("Give me the ID of the city");
        int id = scanner.nextInt();
        City city = cityDAO.getCityById(id);
        System.out.println(city);

    }

    public void insertACity() {
        System.out.println("Give me the name of the new city");
        String name = scanner.next();
        System.out.println("Give me the id of the country");
        int countryId = scanner.nextInt();
        Country country = countryDAO.getCountryById(countryId);
        City city = new City(name, country);
        cityDAO.addCity(city);
    }

    public void updateACity() {
        showAllCities();
        System.out.println("Choose the ID of which city to change.");
        int originalId = scanner.nextInt();
        City city = cityDAO.getCityById(originalId);
        System.out.println("Type in the new name if you want to change it. Press '0' if you don't want to change anything.");
        String newName = scanner.next();
        if (!newName.equals("0"))city.setName(newName);
        System.out.println("Change the countryId to something new. '0' if you don't want to change it.");
        String countryId = scanner.next();
        if(!countryId.toUpperCase(Locale.ROOT).equals("0")) {
            Country country = countryDAO.getCountryById(Integer.parseInt(countryId));
            city.setCountry(country);
        }
        cityDAO.updateCity(city);
    }

    public void deleteACity() {
        showAllCities();
        System.out.println("Choose the ID of which city to delete.");
        int originalId = scanner.nextInt();
        City city = cityDAO.getCityById(originalId);
        cityDAO.deleteCity(city);
    }
}
