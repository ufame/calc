package tests;

import calculator.Calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testArabicAddition() {
        String expression = "4 + 5";
        String result = Calculator.calc(expression);
        Assertions.assertEquals("9", result);
    }

    @Test
    public void testArabicSubtraction() {
        String expression = "15 - 3";
        String result = Calculator.calc(expression);
        Assertions.assertEquals("12", result);
    }

    @Test
    public void testArabicMultiplication() {
        String expression = "6 * 7";
        String result = Calculator.calc(expression);
        Assertions.assertEquals("42", result);
    }

    @Test
    public void testArabicDivision() {
        String expression = "24 / 6";
        String result = Calculator.calc(expression);
        Assertions.assertEquals("4", result);
    }

    @Test
    public void testRomanAddition() {
        String expression = "II + III";
        String result = Calculator.calc(expression);
        Assertions.assertEquals("V", result);
    }

    @Test
    public void testRomanSubtraction() {
        String expression = "IX - V";
        String result = Calculator.calc(expression);
        Assertions.assertEquals("IV", result);
    }

    @Test
    public void testRomanMultiplication() {
        String expression = "IV * VI";
        String result = Calculator.calc(expression);
        Assertions.assertEquals("XXIV", result);
    }

    @Test
    public void testRomanDivision() {
        String expression = "X / V";
        String result = Calculator.calc(expression);
        Assertions.assertEquals("II", result);
    }

    @Test
    public void testInvalidExpression() {
        String expression = "2 * 2 * 2";
        Assertions.assertThrows(IllegalArgumentException.class, () -> Calculator.calc(expression));
    }

    @Test
    public void testNumbersOfDifferentFormats() {
        String expression = "IV + 3";
        Assertions.assertThrows(IllegalArgumentException.class, () -> Calculator.calc(expression));
    }

    @Test
    public void testInvalidRomanNumeral() {
        String expression = "XVIIII + III";
        Assertions.assertThrows(IllegalArgumentException.class, () -> Calculator.calc(expression));
    }

    @Test
    public void testInvalidOperator() {
        String expression = "4 % 3";
        Assertions.assertThrows(IllegalArgumentException.class, () -> Calculator.calc(expression));
    }
}