package org.example;

public class $2OopEx {
    public static void main(String[] args) {
        Calculator calculator = new AdvancedCalculator();

        double a = 6;
        double b = 3;

        performCalculations(a, b, calculator);

        /*
        b = 0;
        System.out.println("==========================");
        System.out.println("0으로 나누기 테스트");
        performCalculations(a, b, calculator);
         */
    }

    public static void performCalculations(double a, double b, Calculator calculator) {
        System.out.println("변수: " + a + ", " + b);
        System.out.println("더하기: " + calculator.add(a, b));
        System.out.println("빼기: " + calculator.subtract(a, b));
        System.out.println("곱하기: " + calculator.multiply(a, b));
        System.out.println("나누기: " + calculator.divide(a, b));

        if (calculator instanceof AdvancedOperations) {
            AdvancedOperations advancedCalculator = (AdvancedOperations) calculator;
            System.out.println(a + "의 " + b + "제곱: " + advancedCalculator.pow(a, b));
            System.out.println(a + "의 제곱근: " + advancedCalculator.sqrt(a));
        }
    }
}

// Abstraction
interface Calculator {
    double add(double a, double b);
    double subtract(double a, double b);
    double multiply(double a, double b);
    double divide(double a, double b);
}

interface AdvancedOperations {
    double pow(double base, double exponent);
    double sqrt(double value);
}

// Encapsulation
class SimpleCalculator implements Calculator {
    public double add(double a, double b) { return a + b; }
    public double subtract(double a, double b) { return a - b; }
    public double multiply(double a, double b) { return a * b; }
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return a / b;
    }
}

// Inheritance & Polymorphism
class AdvancedCalculator extends SimpleCalculator implements AdvancedOperations {
    public double pow(double base, double exponent) { return Math.pow(base, exponent); }
    public double sqrt(double value) { return Math.sqrt(value); }
}
