package calculator;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    // Определяем словарь римских цифр
    private static final Map<String, Integer> ROMAN_NUMERALS = new HashMap<String, Integer>() {{
        put("I", 1);
        put("II", 2);
        put("III", 3);
        put("IV", 4);
        put("V", 5);
        put("VI", 6);
        put("VII", 7);
        put("VIII", 8);
        put("IX", 9);
        put("X", 10);
    }};

    /*
     * Метод для преобразования римских цифр в арабские
     * Принимает строку с римской цифрой в качестве параметра и возвращает целое число арабской системы счисления
     */
    private static int romanToArabic(String input) {
        int result = 0;
        int prevValue = 0;
        for (int i = input.length() - 1; i >= 0; i--) {
            int currentValue = ROMAN_NUMERALS.get(String.valueOf(input.charAt(i)));
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
            prevValue = currentValue;
        }
        return result;
    }

    /*
     * Метод для преобразования арабских цифр в римские
     * Принимает целое число арабской системы счисления в качестве параметра и возвращает строку с римской цифрой
     */
    private static String arabicToRoman(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("Invalid number: " + number);
        }
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] tens = {"", "X", "XX"};
        if (number <= 10) {
            return ones[number];
        } else {
            return tens[number / 10] + ones[number % 10];
        }
    }

    /*
     * Метод для проверки, является ли переданная строка римской цифрой
     * Принимает строку в качестве параметра и возвращает true, если строка является римской цифрой, и false - в противном случае
     */
    private static boolean isRomanNumeral(String input) {
        return ROMAN_NUMERALS.containsKey(input);
    }

    /*
     * Метод для преобразования переданной строки с цифрой в число арабской или римской системы счисления
     * Принимает строку с цифрой в качестве параметра и возвращает число арабской или римской системы счисления
     * В случае, если строка не является ни арабской, ни римской цифрой, метод генерирует IllegalArgumentException
     */
    private static int parseNumber(String input) throws IllegalArgumentException {
        if (isRomanNumeral(input)) {
            return romanToArabic(input);
        } else {
            int num = 0;
            try {
                num = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format: " + input);
            }
            return num;
        }
    }

    public static String calc(String expression) {
        // Разбиваем выражение на три части, разделенные пробелом
        String[] parts = expression.split(" ");
        // Если количество частей не равно 3, выбрасываем исключение
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid expression: " + expression);
        }

        if (isRomanNumeral(parts[0]) != isRomanNumeral(parts[2])) {
            throw new IllegalArgumentException("Expression contains numbers of different formats: " + expression);
        }

        // Получаем значения левой и правой частей выражения
        int left = parseNumber(parts[0]);
        int right = parseNumber(parts[2]);

        // Выполняем операцию в зависимости от знака между левой и правой частями выражения
        int result = switch (parts[1]) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> left / right;
            default -> throw new IllegalArgumentException("Invalid operator: " + parts[1]);
        };

        // Если обе части выражения записаны римскими цифрами, то результат преобразуем в римскую запись
        if (isRomanNumeral(parts[0]) && isRomanNumeral(parts[2])) {
            return arabicToRoman(result);
        } else {
            return Integer.toString(result);
        }
    }
}
