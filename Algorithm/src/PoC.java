import utility.TimeStamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PoC {

    public static void main(String[] args) {
        List<String> list = generateList(150000000);
        randomizeArray(list, list.size());
    }

    public static List<String> randomizeArray(List<String> array, int arrayLength) {
        Random rnd = new Random();
        TimeStamp ts = new TimeStamp();

        ts.init();
        ts.setBegin("Start loop");
        for (int i = arrayLength - 1; i > 0; i--) {
            int j = rnd.nextInt(i + 1);

            String temp = array.get(i);
            array.set(i, array.get(j));
            array.set(j, temp);
        }
        ts.setEnd("End loop");
        System.out.println(ts.toString());

        return array;
    }

    public static List<String> generateList(int length){
        List<String> result = new ArrayList<>();

        for(int i = 0; i < length; i++){
            result.add("generatedString");
        }

        return result;
    }
}
