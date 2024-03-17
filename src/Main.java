import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    final static byte MONTHS_IN_YEAR = 12;
    final static byte PERCENT = 100;
    final static int MIN_PRINCIPAL = 1000;
    final static int MAX_PRINCIPAL = 1_000_000;
    final static float MIN_ANNUAL_INTEREST_RATE = 0.00F;
    final static float MAX_ANNUAL_INTEREST_RATE = 30.00F;
    final static byte MIN_YEARS = 1;
    final static byte MAX_YEARS = 30;

    public static void main(String[] args) {
        int principal = (int) readNumber("Principal (BGN 1K - BGN 10M): ", MIN_PRINCIPAL, MAX_PRINCIPAL);
        byte years = (byte) readNumber("Period (Years): ", MIN_YEARS, MAX_YEARS);
        float annualInterestRate = (float) readNumber("Annual Interest Rate (0.00 - 30.00%): ", MIN_ANNUAL_INTEREST_RATE, MAX_ANNUAL_INTEREST_RATE);

        printMortgage(principal, annualInterestRate, years);

        printPaymentSchedule(years, principal, annualInterestRate);
    }

    private static void printMortgage(int principal, float annualInterestRate, byte years) {
        double mortgage = calculateMortgage(principal, annualInterestRate, years);
        String monthlyPaymentAmountFormatted = NumberFormat.getCurrencyInstance().format(mortgage);
        System.out.println("MORTGAGE: ");
        System.out.println("----------");
        System.out.println("Monthly Payments: " + monthlyPaymentAmountFormatted);
    }

    private static void printPaymentSchedule(byte years, int principal, float annualInterestRate) {
        System.out.println();
        System.out.println("BALANCE: ");
        System.out.println("----------");
        for (short month = 1; month <= years * MONTHS_IN_YEAR; month++) {
            double balance = calculateBalance(principal, annualInterestRate, years, month);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
        }
    }

    public static double calculateMortgage(
            int principal,
            float annualInterestRate,
            byte years
    ){
        short numberOfMonthlyPayments = (short)(years * MONTHS_IN_YEAR);
        double monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;

        // Calculate and return mortgage
        return principal
                * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments) - 1);
    }

    public static double calculateBalance(
            int principal,
            float annualInterestRate,
            byte years,
            short numberOfPaymentsMade
    ){
        short numberOfMonthlyPayments = (short)(years * MONTHS_IN_YEAR);
        double monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;

        // Calculate and return remaining balance
        return principal
                * (Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments) - Math.pow(1 + monthlyInterestRate, numberOfPaymentsMade))
                / (Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments) - 1);
    }

    public static double readNumber(String prompt, double minVal, double maxVal) {
        Scanner scanner = new Scanner(System.in);
        double value;

        while (true) {
            System.out.print(prompt);
            value = scanner.nextDouble();
            if (value >= minVal && value <= maxVal )
                return value;
            System.out.println("Please enter a value between " + minVal + " and " + maxVal);
        }
    }

}