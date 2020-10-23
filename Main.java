//package converter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            int sRadix = scanner.nextInt();
            String input = scanner.next();
            int tRadix = scanner.nextInt();
            if(sRadix < 1 || sRadix > 36 || tRadix < 1 || tRadix > 36) {
                System.out.println("Error");
                System.exit(0);
            }
        if (input.contains(".")) {
                String[] parts = input.split("\\.");
                String decimalConverted = convertDecimalPart(sRadix, tRadix, parts[0]);
                String decOfFractional = Double.toString(convertFractPartToDec(sRadix, parts[1]));
                double number = Double.parseDouble(decOfFractional);
                String decFractToTradix = convertDecFractToTradix(tRadix, number);
                System.out.println(decimalConverted + "." + decFractToTradix);
        }
        else {
                System.out.println(convertDecimalPart(sRadix, tRadix, input));
            }
        } catch (Exception e){
            System.out.println("Error");
        }
    }

    public static String convertDecimalPart(int sRadix, int tRadix, String num){
        int numInDecimal;
        if (sRadix == 1) {
            numInDecimal = num.split("").length;
        } else {
                numInDecimal = Integer.parseInt(num, sRadix);
        }
        StringBuilder result = new StringBuilder();
        if (tRadix == 1){
            result.append("1".repeat(Math.max(0, numInDecimal)));
        }
        else {
            result = new StringBuilder(Integer.toString(numInDecimal, tRadix));
        }
        return result.toString();
    }

    public static double convertFractPartToDec(int sRadix, String num) {
        double decimalOfFractional = 0;
        String[] segments = num.split("");
        for (int i = 0; i < num.length(); i++) {
            int value = Integer.parseInt(segments[i], sRadix);
            decimalOfFractional+= value * 1.0 / Math.pow(sRadix, i+1);
        }
        return decimalOfFractional;
    }

    public static String convertDecFractToTradix(int tRadix, double number) {
        String value = Double.toString(number);
        StringBuilder result = new StringBuilder();
        double multiplier = number;
        for (int i = 0; i < value.length(); i++) {
            String parts = Double.toString(multiplier * tRadix);
            String[] temp =  parts.split("\\.");
            multiplier = Double.parseDouble("0." + temp[1]);
            result.append(Integer.toString(Integer.parseInt(temp[0]), tRadix));
        }
        result.setLength(5);
        return result.toString();
    }
}
