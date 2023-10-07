package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntPredicate;

public class Main {
    static class Fraction {
        int numerator;
        int denominator;

        Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public static int sumWithCondition(int[] array, IntPredicate condition) {
        int sum = 0;
        for (int num : array) {
            if (condition.test(num)) {
                sum += num;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        //Task1
        System.out.println("\nTask1");

        IntPredicate isLeapYear = year -> (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);

        int yearToCheck = 2024;
        boolean isLeap = isLeapYear.test(yearToCheck);
        System.out.println(yearToCheck + " рік є високосним: " + isLeap);


        BiFunction<LocalDate, LocalDate, Long> daysBetween = (date1, date2) -> ChronoUnit.DAYS.between(date1, date2);

        BiFunction<LocalDate, LocalDate, Long> sundaysBetween = (date1, date2) -> {
            LocalDate start = date1.isBefore(date2) ? date1 : date2;
            LocalDate end = date1.isBefore(date2) ? date2 : date1;
            return start.datesUntil(end, Period.ofDays(7))
                    .filter(date -> date.getDayOfWeek() == DayOfWeek.SUNDAY)
                    .count();
        };

        Function<LocalDate, String> dayOfWeek = date -> date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());

        LocalDate date1 = LocalDate.of(2023, 1, 1);
        LocalDate date2 = LocalDate.of(2023, 12, 31);
        long days = daysBetween.apply(date1, date2);
        System.out.println("Кількість днів між датами: " + days);

        long sundays = sundaysBetween.apply(date1, date2);
        System.out.println("Кількість повних неділь між датами: " + sundays);

        String day = dayOfWeek.apply(date1);
        System.out.println("День тижня для дати " + date1 + ": " + day);

        //Task2
        System.out.println("\nTask2");

        BinaryOperator<Fraction> addition = (frac1, frac2) -> {
            int newDenominator = frac1.denominator * frac2.denominator;
            int newNumerator = frac1.numerator * frac2.denominator + frac2.numerator * frac1.denominator;
            return new Fraction(newNumerator, newDenominator);
        };

        BinaryOperator<Fraction> subtraction = (frac1, frac2) -> {
            int newDenominator = frac1.denominator * frac2.denominator;
            int newNumerator = frac1.numerator * frac2.denominator - frac2.numerator * frac1.denominator;
            return new Fraction(newNumerator, newDenominator);
        };

        BinaryOperator<Fraction> multiplication = (frac1, frac2) -> {
            int newNumerator = frac1.numerator * frac2.numerator;
            int newDenominator = frac1.denominator * frac2.denominator;
            return new Fraction(newNumerator, newDenominator);
        };

        BinaryOperator<Fraction> division = (frac1, frac2) -> {
            int newNumerator = frac1.numerator * frac2.denominator;
            int newDenominator = frac1.denominator * frac2.numerator;
            return new Fraction(newNumerator, newDenominator);
        };

        Fraction frac1 = new Fraction(1, 2);
        Fraction frac2 = new Fraction(1, 3);

        Fraction sum = addition.apply(frac1, frac2);
        Fraction difference = subtraction.apply(frac1, frac2);
        Fraction product = multiplication.apply(frac1, frac2);
        Fraction quotient = division.apply(frac1, frac2);

        System.out.println("Сума: " + sum.numerator + "/" + sum.denominator);
        System.out.println("Різниця: " + difference.numerator + "/" + difference.denominator);
        System.out.println("Множення: " + product.numerator + "/" + product.denominator);
        System.out.println("Ділення: " + quotient.numerator + "/" + quotient.denominator);



        //Task3
        System.out.println("\nTask3");


        BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;

        BinaryOperator<Integer> min = (a, b) -> a < b ? a : b;

        int num1 = 10;
        int num2 = 5;
        int num3 = 8;
        int num4 = 12;

        int maximum = max.apply(max.apply(num1, num2), max.apply(num3, num4));
        int minimum = min.apply(min.apply(num1, num2), min.apply(num3, num4));

        System.out.println("Максимум: " + maximum);
        System.out.println("Мінімум: " + minimum);


        //Task4
        System.out.println("\nTask4");

        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int sumEqual5 = sumWithCondition(numbers, x -> x == 5);
        System.out.println("Сума чисел, які рівні 5: " + sumEqual5);

        int sumNotInRange = sumWithCondition(numbers, x -> x < 3 || x > 7);
        System.out.println("Сума чисел, які не знаходяться в діапазоні від 3 до 7: " + sumNotInRange);

        int sumPositive = sumWithCondition(numbers, x -> x > 0);
        System.out.println("Сума додатних чисел: " + sumPositive);

        int sumNegative = sumWithCondition(numbers, x -> x < 0);
        System.out.println("Сума від'ємних чисел: " + sumNegative);
    }
}