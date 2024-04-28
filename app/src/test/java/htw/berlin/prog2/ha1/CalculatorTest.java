package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {

        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    //TODO hier weitere Tests erstellen

    // Teilaufgabe 1_23/04_true

    @Test
    @DisplayName("should prozentanzeige  ")
    void testProzent() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(4);
        calc.pressUnaryOperationKey("%");

        String expected = "0.04";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    // Teilaufgabe 1_23/04_true
    @Test
    @DisplayName("Subtraction of two positive numbers with decimal point result")
    void testMultipleSuptraktionDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(9);
        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(6);
        calc.pressEqualsKey();

        String expected = "86";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    // Teilaufgabe 2.1_24/04_false Zeile 129

    @Test
    @DisplayName("Test1 ")
    void test1() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDigitKey(6);
        calc.pressEqualsKey();

        String expected = "16";
        String actual = calc.readScreen();

        System.out.println(actual);
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Test2 ")
    void test2() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(3);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(4);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();

        String expected = "4";
        String actual = calc.readScreen();

      //  System.out.println(actual);

        assertEquals(expected, actual);
    }

    // den Kompleten code umschreiben 27.04.2024
    @Test
    @DisplayName("Test3")
    void Test3() {
        Calculator calc = new Calculator();

     //   calc.pressDigitKey(1);
       // calc.pressDotKey();
        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("x");
        calc.pressDigitKey(3);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(2);
      //  calc.pressUnaryOperationKey("%");
        calc.pressEqualsKey();

        String expected = "7.5";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

}

