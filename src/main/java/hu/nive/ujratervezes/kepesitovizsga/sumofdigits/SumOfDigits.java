package hu.nive.ujratervezes.kepesitovizsga.sumofdigits;

import java.util.Random;

public class SumOfDigits {
    public int getSumOfDigits(Random random) {

        int result = random.nextInt();
        result = Math.abs(result);
        while (result > 9) {
            String str = Integer.toString(result);
            result = 0;
            for (char item: str.toCharArray()) {
                result += (item - 48);
            }
        }
        return result;
    }
}
