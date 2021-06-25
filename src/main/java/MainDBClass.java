
import service.CityService;
import service.ContinentService;
import service.CountryService;

import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class MainDBClass {
    private static int choiceOne= 9;
    private static int choiceTwo= 9;
    private static boolean continueThis= true;

    public static void main(String[] args) {

        try {
            while (continueThis){
                getChoice();
                choices();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getChoice(){
        Scanner scanner = new Scanner(System.in);

        while (choiceOne==9) {
            System.out.println("What do you want to look at? \n1: Countries \n2: Continents \n3: Cities \n0: End");
            choiceOne = scanner.nextInt();
            if(choiceOne==0)break;
            if(choiceOne<1||choiceOne>3){
                choiceOne = 9;
                System.out.println("Invalid choice.");
            }else {
                while (choiceTwo==9) {
                    System.out.println("What do you want to look at? \n1: See All \n2: See One \n3: Add One \n4: Edit One \n5: Delete One\n0: End");
                    choiceTwo = scanner.nextInt();
                    if (choiceTwo==0)break;
                    if(choiceTwo<1||choiceTwo>5){
                        choiceTwo = 9;
                        System.out.println("Invalid choice.");
                    }
                }
            }
        }

    }

    private static void choices() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        CountryService countryService = new CountryService();
        ContinentService continentService = new ContinentService();
        CityService cityService = new CityService();
        if (choiceTwo!=0)
        if (choiceOne==1){
            switch (choiceTwo){
                case 1:countryService.showAllCountries();break;
                case 2:countryService.showCountryByIdWithoutCheck();break;
                case 3:countryService.addCountryWithoutCheck();break;
                case 4:countryService.updateCountryWithoutCheck();break;
                case 5:countryService.deleteACountry();break;
            }
            System.out.println("We did a country thing!");
        } else if (choiceOne==2){
            switch (choiceTwo){
                case 1:continentService.showAllContinents();break;
                case 2:continentService.showContinentById();break;
                case 3:continentService.insertAContinent();break;
                case 4:continentService.updateAContinent();break;
                case 5:continentService.deleteAContinent();break;
            }
            System.out.println("We did a continent thing!");
        }
        else if (choiceOne==3){
            switch (choiceTwo){
                case 1:cityService.showAllCities();break;
                case 2:cityService.showCityById();break;
                case 3:cityService.insertACity();break;
                case 4:cityService.updateACity();break;
                case 5:cityService.deleteACity();break;
            }
            System.out.println("We did a city thing!");
        }
            choiceOne =9;
            choiceTwo =9;
            boolean goodAnswer;
            do {
                System.out.println("Do you want to Try again? Y/N");
                String answer = scanner.next();
                if (answer.toUpperCase(Locale.ROOT).equals("N")){
                    System.out.println("Bye!");
                    continueThis = false;
                    break;
                }
                if (!answer.toUpperCase(Locale.ROOT).equals("Y")) {
                    goodAnswer = false;
                    System.out.println(answer+ " is not a good answer.");
                }
                else goodAnswer = true;
            }while (!goodAnswer);
    }
}
