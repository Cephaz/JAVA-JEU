package Entity;

public class RandomNum {
    public static int usingMathClass(int max, int min) {
        double randomDouble = Math.random();
        randomDouble = randomDouble * max + min;
        int randomInt = (int) randomDouble;
        return randomInt;
    }
}