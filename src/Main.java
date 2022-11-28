import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Input: ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println("Output: " + calc(input));
    }

    public static String calc(String input) {
        String[] inArr = input.split(" ");
        String y = initialMethod(inArr);
        if (y.equals("inArab")){
            return calcArab(inArr);
        }
        if (y.equals("inRom")){
            return calcRom(inArr);
        }
        return y;
    }

    static String initialMethod(String[] inArr) {
        String[] signArr = {"+", "-", "/", "*"};


        if (inArr.length != 3) {
            throw new IndexOutOfBoundsException("/т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        for (String i : signArr) {
            if (inArr[1].equals(i)) {
                break;
            }
            if (i.equals("*")) { // Найти решение получше!!
                throw new IndexOutOfBoundsException("/т.к. формат математической операции не удовлетворяет - один оператор (+, -, /, *)");
            }
        }

        try {
            if (Integer.parseInt(inArr[0]) <= 10 && Integer.parseInt(inArr[2]) <= 10) {
                if (Integer.parseInt(inArr[0]) > 0 && Integer.parseInt(inArr[2]) > 0) {
                    return "inArab";
                }
            }
        } catch (NumberFormatException e) {
            return "inRom";
        }
        throw new IllegalArgumentException("throws Exception //т.к. алькулятор должен принимать на вход числа от 1 до 10 включительно, не более.");
    }

    private static String[] checkerRom(String[] inArr){
        String[] romanArr = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int calcNumberRom = 0, count = 0, out1 = 0, out2 = 0;

        for (String j : romanArr) {
            count += 1;
            if (inArr[0].equals(j)) {
                calcNumberRom += 1;
                out1 += count;
            }
            if (inArr[2].equals(j)) {
                calcNumberRom += 1;
                out2 += count;
            }
        }
        if (calcNumberRom == 2) {
            inArr[0] = String.valueOf(out1);
            inArr[2] = String.valueOf(out2);
            return inArr;

        } else {
            throw new IllegalArgumentException("throws Exception //т.к. калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.");
        }
    }

    private static String calcRom(String[] inArr){
        String[] rm = checkerRom(inArr);
        int res = 0;

        for (String j : rm){
            System.out.print(j);
        }
        System.out.println("");
        int pi1 = Integer.parseInt(rm[0]);
        int pi2 = Integer.parseInt(rm[0]);

        switch (inArr[1]) {
            case "+" -> {
                res = pi1 + pi2;
            }
            case "-" -> {
                res = pi1 - pi2;
            }
            case "*" -> {
                res = pi1 * pi2;
            }
            case "/" -> {
                if (pi1 == 0 || pi2 == 0) {
                    throw new IndexOutOfBoundsException("На ноль делить нельзя");
                }
                res = pi1 / pi2;
            }
            default -> {
                throw new IndexOutOfBoundsException("Ошибка по итогам складывания чисел роман");
            }
        }
        for (RomanNam j : RomanNam.values()){
            if((j.ordinal() + 1) == res){
                return String.valueOf(j);

            }
        }

        throw new IndexOutOfBoundsException("Ошибка конвертации");
    }

    private static String calcArab(String[] inArr) {
        int res = 0;
        switch (inArr[1]) {
            case "+" -> {
                res = Integer.parseInt(inArr[0]) + Integer.parseInt(inArr[2]);
            }
            case "-" -> {
                res = Integer.parseInt(inArr[0]) - Integer.parseInt(inArr[2]);
            }
            case "*" -> {
                res = Integer.parseInt(inArr[0]) * Integer.parseInt(inArr[2]);
            }
            case "/" -> {
                res = Integer.parseInt(inArr[0]) / Integer.parseInt(inArr[2]);
            }
            default -> throw new IndexOutOfBoundsException("Ошибка по итогам складывания чисел арабик");
        }
        return String.valueOf(res);
    }
}
