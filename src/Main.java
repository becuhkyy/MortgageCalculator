import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final byte MONTHS_IN_YEAR = 12;
        final byte PERCENT = 100;

        Scanner scanner = new Scanner(System.in);

        //Principal
        System.out.print("Principal: ");
        int principal = scanner.nextInt();

        //Annual Interest Rate
        System.out.print("Annual Interest Rate: ");
        double annualInterestRate = scanner.nextDouble();

        //Monthly Interest Rate
        double monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;

        //Period
        System.out.print("Period (Years): ");
        byte years = scanner.nextByte();

        // Number of payments
        int numberOfMonthlyPayments = years * MONTHS_IN_YEAR;

        // Calculate monthly payment
        double monthlyPaymentAmount = principal
                * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments))
                / (Math.pow(1 + monthlyInterestRate, numberOfMonthlyPayments) - 1);

        // Format it as a currency
        String monthlyPaymentAmountFormatted = NumberFormat.getCurrencyInstance().format(monthlyPaymentAmount);
        // Print the result
        System.out.println(monthlyPaymentAmountFormatted);
    }
}