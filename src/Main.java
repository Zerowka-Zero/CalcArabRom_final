import java.util.Scanner;

public class Main {

    public static void main(String[] args) {   // Метод main собственной персоной
        System.out.print("Input: ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        System.out.println("Output: " + calc(input));
    }

    public static String calc(String input) {   // метод calc инициализирует и принимает значения от всех методов
        String[] inArr = input.split(" ");
        String y = initialMethod(inArr);
        if (y.equals("inArab")) {
            return calcArab(inArr);
        }
        if (y.equals("inRom")) {
            return calcRom(inArr);
        }
        return null;
    }

    static String initialMethod(String[] inArr) { // стартовый метод
        String[] signArr = {"+", "-", "/", "*"};

        if (inArr.length != 3) {
            throw new IndexOutOfBoundsException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        for (String i : signArr) {
            if (inArr[1].equals(i)) {
                break;
            }
            if (i.equals("*")) { // Найти решение получше, хотя ошибку должен перехватить default в методе calcRom или calcArab
                throw new IndexOutOfBoundsException("т.к. формат математической операции не удовлетворяет - один оператор (+, -, /, *)");
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
        throw new IllegalArgumentException("throws Exception //т.к. программа принимает числа от 0 до 10 включительно");
    }

    static int[] checkerRom(String[] inArr) { // прокси метод calcRom для перевода римских цифр в арабские
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
            if (out1 == 0 || out2 == 0) {
                throw new IllegalArgumentException("throws Exception //т.к. используются одновременно разные системы исчисления");
            }
            return new int[]{out1, out2};

        } else {
            throw new IllegalArgumentException("throws Exception //т.к. программа принимает числа от 0 до 10 включительно");
        }
    }

    static String calcRom(String[] inArr) {  // метод римских цифр
        int[] rm = checkerRom(inArr);
        int res;
        int pi1 = rm[0];
        int pi2 = rm[1];

        switch (inArr[1]) {
            case "+" -> res = pi1 + pi2;
            case "-" -> res = pi1 - pi2;
            case "*" -> res = pi1 * pi2;
            case "/" -> {
                if (pi1 == 0 || pi2 == 0) {
                    throw new IndexOutOfBoundsException("На ноль делить нельзя");
                }
                res = (int) Math.round ((double) pi1 / (double) pi2); // говнокод
            }
            default -> throw new IndexOutOfBoundsException("Ошибка по итогам складывания чисел роман");
        }
        if (res <= 0) {
            throw new IllegalArgumentException("throws Exception //т.к. в римской системе нет нуля или отрицательных чисел");
        }
        for (RomanNam j : RomanNam.values()) {
            if ((j.ordinal() + 1) == res) {
                return String.valueOf(j);
            }
        }

        throw new IndexOutOfBoundsException("Ошибка конвертации");
    }

    private static String calcArab(String[] inArr) { // метод арабских цифр
        int res;

        switch (inArr[1]) {
            case "+" -> res = Integer.parseInt(inArr[0]) + Integer.parseInt(inArr[2]);
            case "-" -> res = Integer.parseInt(inArr[0]) - Integer.parseInt(inArr[2]);
            case "*" -> res = Integer.parseInt(inArr[0]) * Integer.parseInt(inArr[2]);
            case "/" -> res = (int) Math.round( (double) Integer.parseInt(inArr[0]) / (double) Integer.parseInt(inArr[2])); // дабл говнокод
            default -> throw new IndexOutOfBoundsException("Ошибка по итогам складывания чисел арабик");
        }

        return String.valueOf(res);
    }
}