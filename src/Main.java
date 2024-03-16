import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Principal
        System.out.print("Principal: ");
        int principal = scanner.nextInt();

        //Annual Interest Rate
        System.out.print("Annual Interest Rate: ");
        double annualInterestRate = scanner.nextDouble();

        //Monthly Interest Rate
        double monthlyInterestRate = (annualInterestRate / 100 ) / 12;

        //Period
        System.out.print("Period (Years): ");
        byte period = scanner.nextByte();

        // Number of payments
        int monthlyPayments = period * 12;

        // Calculate monthly payment
        double monthlyPaymentAmount = principal *
                (
                        (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, monthlyPayments))
                                /
                                (Math.pow(1 + monthlyInterestRate, monthlyPayments) - 1)
                );

        // Format it as a currency
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        // Print the result
        System.out.println(formatter.format(monthlyPaymentAmount));
    }
}