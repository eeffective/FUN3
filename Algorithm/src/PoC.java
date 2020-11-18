
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PoC {

    public static void main(String[] args) {
        String[] countryArray = {"France","Italy", "the Netherlands", "Germany", "Spain"};

        System.out.println(randomizeArray(Arrays.asList(countryArray), countryArray.length));
    }

    public static List<String> randomizeArray(List<String> array, int arrayLength) {
        Random rnd = new Random();

        for (int i = arrayLength - 1; i > 0; i--) {
            int j = rnd.nextInt(i+1);

            String temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
        }

        return array;
    }
}
