import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String fileName = "countries.csv";
        Map<String, Country> countriesMap = null;
        try {
            countriesMap = createCountriesMapFromFile(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Plik " + fileName + " nie istnieje!");
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj kod kraju, o którym chcesz zobaczyć informacje:");
        String answerCode = sc.nextLine();

        System.out.println(countriesMap.get(answerCode));
    }

    private static Map<String, Country> createCountriesMapFromFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);

        Map<String, Country> countriesMap = new HashMap<>();

        while (sc.hasNextLine()){
            String countryInfos = sc.nextLine();
            Country country = createCountry(countryInfos);
            countriesMap.put(country.getCode(),country);
        }
        return countriesMap;
    }

    private static Country createCountry(String countryInfos) {
        String[] countryInfosArray = countryInfos.split(";");
        String code = countryInfosArray[0];
        String name = countryInfosArray[1];
        Integer populationInt = Integer.valueOf(countryInfosArray[2]);
        int population = populationInt;

        return new Country(code, name, population);
    }
}
