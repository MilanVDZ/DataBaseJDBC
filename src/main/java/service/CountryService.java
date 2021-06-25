package service;

import data.ContinentDAO;
import data.CountryDAO;
import model.Continent;
import model.Country;

import java.util.Locale;
import java.util.Scanner;

public class CountryService {

    private final CountryDAO countryDAO;
    private final ContinentDAO continentDAO;

    public CountryService() {
        countryDAO = new CountryDAO();
        continentDAO = new ContinentDAO();
    }

    public void showAllCountries() {
        countryDAO.getAllCountries().forEach(System.out::println);
    }

    public void showCountryById() {
        Scanner scanner = new Scanner(System.in);
       boolean rightInput;
        int id= 0;
       do {
           System.out.println("Insert Id");
           try {
               id = Integer.parseInt(scanner.next());
               scanner.nextLine();
               rightInput= true;
           } catch (NumberFormatException e) {
                rightInput =false;
               System.out.println("Id is not correct");
           }
       }while (!rightInput);

        Country country = countryDAO.getCountryById(id);
        if (country!= null) System.out.println(country);
        else System.out.println("This Id does not have a country");
    }

    public void  addCountry() {
        Scanner scanner = new Scanner(System.in);
        continentDAO.getAllContinents().forEach(System.out::println);
        System.out.println("Which one of these continents do you want to use? Type in a number.");
        boolean exists = false;
        int continentId =0;
        while (!exists) {
           continentId = makeACorrectId();
           if (continentDAO.getContinentById(continentId)!=null)
               exists = true;
           else System.out.println("This continent doesn't exist.");
        }
        System.out.println("Insert countryName");
        String countryName = scanner.next();
        Country country = new Country(countryName, continentDAO.getContinentById(continentId));
        countryDAO.addCountry(country);
        System.out.println("Country was made");
    }

    public void updateCountry() {
        countryDAO.getAllCountries().forEach(System.out::println);
        System.out.println("Which one do you want to edit? Select number");
        boolean exist = false;
        int currentId = 0;
        while (!exist){
            currentId = makeACorrectId();
            if (countryDAO.getCountryById(currentId)!= null) exist = true;
            else System.out.println("Country doesn't exist");
        }
        Scanner scanner = new Scanner(System.in);
        Country country = countryDAO.getCountryById(currentId);
        String answer;
        System.out.println("Do you want to change the name? NA for nothing");
        answer = scanner.next();
        if(!answer.toUpperCase(Locale.ROOT).equals("NA")){
            System.out.println("What do you want to change it to?");
            String name = scanner.next();
            country.setName(name);
        }
        System.out.println("Do you want to change the Continent? NA for nothing");
        answer = scanner.next();
        if(!answer.toUpperCase(Locale.ROOT).equals("NA")){
            System.out.println("What do you want to change it to?");
            int id = scanner.nextInt();
            country.setContinent(continentDAO.getContinentById(id));
        }

        countryDAO.updateCountry(country);
        System.out.println("Country updated");
    }

    public void deleteACountry() {
        Scanner scanner = new Scanner(System.in);
        countryDAO.getAllCountries().forEach(System.out::println);
        System.out.println("Give id of country you want to delete:");
        int countryId = giveExistingCountryId();
        System.out.println("Are you sure you want to delete this country:");
        System.out.println(countryDAO.getCountryById(countryId));
        System.out.println("Y/N");
        String answer = scanner.next();
        if (answer.toUpperCase(Locale.ROOT).equals("Y")){
            countryDAO.deleteCountry(countryDAO.getCountryById(countryId));
            System.out.println("country has been deleted");
        }else System.out.println("Country has not been deleted");
    }

    private int giveExistingCountryId() {
        boolean exist = false;
        int currentId= 0;
        while (!exist){
            currentId = makeACorrectId();
            if (countryDAO.getCountryById(currentId)!= null) exist = true;
            else System.out.println("Country doesn't exist");
        }
        return currentId;
    }

    private int makeACorrectId(){
        Scanner scanner = new Scanner(System.in);
        boolean rightInput;
        int id= 0;
        do {
            System.out.println("Insert Id");
            try {
                id = Integer.parseInt(scanner.next());
                scanner.nextLine();
                rightInput= true;
            } catch (NumberFormatException e) {
                rightInput =false;
                System.out.println("Id is not correct");
            }
        }while (!rightInput);
        return id;
    }

    public void showCountryByIdWithoutCheck() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Give an id of a country");
        int id = scanner.nextInt();
        Country country = countryDAO.getCountryById(id);
        if(country!=null) System.out.println(country);
        else System.out.println("Country doens't exist");
    }

    public void  addCountryWithoutCheck() {
        Scanner scanner = new Scanner(System.in);
        continentDAO.getAllContinents().forEach(System.out::println);
        System.out.println("Which one of these continents do you want to use? Type in a number.");
        int continentId = scanner.nextInt();
        System.out.println("Give me the name of the country");
        String name = scanner.next();
        Country country = new Country(name, continentDAO.getContinentById(continentId));
        countryDAO.addCountry(country);
        System.out.println("Country has been added");
    }

    public void updateCountryWithoutCheck() {
        Scanner scanner = new Scanner(System.in);
        countryDAO.getAllCountries().forEach(System.out::println);
        System.out.println("Which one do you want to edit? Select number");
        int currentId = scanner.nextInt();
        Country country = countryDAO.getCountryById(currentId);
        String answer;
        System.out.println("Change the CountryName to something new. NA if you don't want to change it.");
        answer = scanner.next();
        if (!answer.toUpperCase(Locale.ROOT).equals("NA")){
            Continent continent = continentDAO.getContinentById(Integer.parseInt(answer));
            country.setContinent(continent);
        }
        countryDAO.updateCountry(country);
        System.out.println("Country has been updated");
    }
}
