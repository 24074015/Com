
package com.mycompany.mavenproject13;

import java.util.Scanner;

abstract class Expense {
    public double amount;

    public Expense(double amount) {
        this.amount=amount;
    }
    public double Amount() {
        return amount;
}
    public abstract String ExpenseType();
}
class GroceriesExpense extends Expense {
    public GroceriesExpense(double amount) {
        super(amount);
    }
   
    public String ExpenseType() {
        return "Groceries";
    }}

class WaterAndLightsExpense extends Expense {
    public WaterAndLightsExpense(double amount) {
        super(amount);
    }

    public String ExpenseType() {
        return "Water and Lights";
    }
}
class TravelCostExpense extends Expense {
    public TravelCostExpense(double amount) {
        super(amount);
    }

    public String ExpenseType() {
        return "Travel Costs";
    }}

class CellPhoneExpense extends Expense {
    public CellPhoneExpense(double amount) {
        super(amount);
    }
   
    public String ExpenseType() {
        return "Cell Phone";
    }}

class OtherExpense extends Expense {
    public OtherExpense(double amount) {
        super(amount);
    }
  
    public String ExpenseType() {
        return "Other";
    }}

abstract class Accommodation {
    public abstract double MonthlyPayment();
}
class RentAccommodation extends Accommodation {
    public double rentAmount;

    public RentAccommodation(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public double MonthlyPayment() {
        return rentAmount;
    }}

class HomeLoanAccommodation extends Accommodation {
    public double propertyPrice;
    public double deposit;
    public double interestRate;
    public int repaymentMonths;

    public HomeLoanAccommodation(double propertyPrice, double deposit, double interestRate, int repaymentMonths) {
        this.propertyPrice = propertyPrice;
        this.deposit = deposit;
        this.interestRate = interestRate;
        this.repaymentMonths = repaymentMonths;
    }
 public double MonthlyPayment() {
        double loanAmount = propertyPrice - deposit;
        double monthlyInterestRate = interestRate / 100 / 12;
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -repaymentMonths));
    }}

public class Mavenproject13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        System.out.print("Enter your gross monthly income: ");
        double grossIncome = scanner.nextDouble();

        System.out.print("Enter monthly tax: ");
        double tax = scanner.nextDouble();

        Expense[] expenses = new Expense[5];

        System.out.print("Enter monthly groceries expense: ");
        expenses[0] = new GroceriesExpense(scanner.nextDouble());

        System.out.print("Enter monthly water and lights expense: ");
        expenses[1] = new WaterAndLightsExpense(scanner.nextDouble());

        System.out.print("Enter monthly travel cost: ");
        expenses[2] = new TravelCostExpense(scanner.nextDouble());

        System.out.print("Enter  monthly cell phone and telephone expense: ");
        expenses[3] = new CellPhoneExpense(scanner.nextDouble());

        System.out.print("Enter  monthly other expenses: ");
        expenses[4] = new OtherExpense(scanner.nextDouble());

        Accommodation accommo;
        System.out.print("Do you want to rent or buy a property? (rent/buy): ");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("rent")) {
            System.out.print("Enter the monthly rent amount: ");
            double rent = scanner.nextDouble();
            accommo = new RentAccommodation(rent);
        } else if (choice.equalsIgnoreCase("buy")) {
            System.out.print("Enter the purchase price of the property: ");
            double propertyPrice = scanner.nextDouble();

            System.out.print("Enter the total deposit: ");
            double deposit = scanner.nextDouble();

            System.out.print("Enter the interest rate (percentage): ");
            double interestRate = scanner.nextDouble();

            System.out.print("Enter the number of months to repay (between 240 and 360): ");
            int months = scanner.nextInt();

            accommo = new HomeLoanAccommodation(propertyPrice, deposit, interestRate, months);

            double monthlyRepayment = accommo.MonthlyPayment();
            if (monthlyRepayment > grossIncome / 3) {
                System.out.println("Warning: Your monthly home loan repayment exceeds one-third of your gross income");
            }
        } else {
            System.out.println("Invalid option. Exiting.");
            return;
        }
        double totalExpenses = tax;
        for (Expense exp : expenses) {
            totalExpenses += exp.Amount();
        }
        totalExpenses += accommo.MonthlyPayment();

        double availableMoney = grossIncome - totalExpenses;
        System.out.println(" available monthly money after all deductions: " + availableMoney);

    }}
