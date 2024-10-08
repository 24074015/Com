
package com.mycompany.mavenproject14;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Consumer;


class Expense {
    private double amount;
    private String type;

  
    public Expense(double amount, String type) {
        this.amount = amount;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }} 

class Accommodation {
   private double rentAmount;
   private double propertyPrice;
   private double deposit;
   private double interestRate;
   private int repaymentMonths;
    private boolean isRent;

   
    public Accommodation(double rentAmount) {
        this.rentAmount = rentAmount;
        this.isRent = true;
    }

    
    public Accommodation(double propertyPrice, double deposit, double interestRate, int repaymentMonths) {
        this.propertyPrice = propertyPrice;
        this.deposit = deposit;
        this.interestRate = interestRate;
        this.repaymentMonths = repaymentMonths;
        this.isRent = false;
    }

   
    public double getMonthlyPayment() {
        if (isRent) {
            return rentAmount;
        } else {
            double loanAmount = propertyPrice - deposit;
            double monthlyInterestRate = interestRate / 100 / 12;
            return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -repaymentMonths));
        }}}
    

class Vehicle {
    public String model;
     public double purchasePrice;

    public double deposit;
    public double interestRate;
    public double insurancePremium;

    public Vehicle(String model, double purchasePrice, double deposit, double interestRate, double insurancePremium) {
        this.model = model;
        this.purchasePrice = purchasePrice;
        this.deposit = deposit;
        this.interestRate = interestRate;
        this.insurancePremium = insurancePremium;
    }

    public double getMonthlyCost() {
        double loanAmount = purchasePrice - deposit;
        double monthlyInterestRate = interestRate / 100 / 12;
        double loanRepayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -60)); // 5 years repayment
        return loanRepayment + insurancePremium;
    }

    public String getModel() {
        return model;
    }}

public class Mavenproject14 {
    
    public  static Consumer<Double> expenseWarning = totalExpenses -> System.out.println(
            "Warning: Your total expenses exceed 75% of your income. They are " + totalExpenses + ".");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>(); 

       
        System.out.print("Enter your gross monthly income: ");
        double grossIncome = scanner.nextDouble();

        System.out.print("Enter estimated monthly tax: ");
        double tax = scanner.nextDouble();
        expenses.add(new Expense(tax, "Tax"));

       
        System.out.print("Enter estimated monthly groceries expense: ");
        expenses.add(new Expense(scanner.nextDouble(), "Groceries"));

        System.out.print("Enter estimated monthly water and lights expense: ");
        expenses.add(new Expense(scanner.nextDouble(), "Water and Lights"));

        System.out.print("Enter estimated monthly travel cost: ");
        expenses.add(new Expense(scanner.nextDouble(), "Travel Costs"));

        System.out.print("Enter estimated monthly cell phone and telephone expense: ");
        expenses.add(new Expense(scanner.nextDouble(), "Cell Phone"));

        System.out.print("Enter estimated monthly other expenses: ");
        expenses.add(new Expense(scanner.nextDouble(), "Other"));

        
        Accommodation accommodation;
        System.out.print("Do you want to rent or buy a property? (rent/buy): ");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("rent")) {
            System.out.print("Enter the monthly rent amount: ");
            accommodation = new Accommodation(scanner.nextDouble());
        } else if (choice.equalsIgnoreCase("buy")) {
            System.out.print("Enter the purchase price of the property: ");
            double propertyPrice = scanner.nextDouble();

            System.out.print("Enter the total deposit: ");
            double deposit = scanner.nextDouble();

            System.out.print("Enter the interest rate (percentage): ");
            double interestRate = scanner.nextDouble();

            System.out.print("Enter the number of months to repay (between 240 and 360): ");
            int months = scanner.nextInt();

            accommodation = new Accommodation(propertyPrice, deposit, interestRate, months);

            double monthlyRepayment = accommodation.getMonthlyPayment();
            if (monthlyRepayment > grossIncome / 3) {
                System.out.println("Warning: Your monthly home loan repayment exceeds 75% of your gross income.");
            }
        } else {
            System.out.println("Invalid option Exiting.");
            return;
        }
        expenses.add(new Expense(accommodation.getMonthlyPayment(), "Accommodation"));

        
        System.out.print("Do you want to buy a vehicle? (yes/no): ");
        String vehicleChoice = scanner.next();

        if (vehicleChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter the model: ");
            scanner.nextLine(); 
            String model = scanner.nextLine();

            System.out.print("Enter the purchase price of the vehicle: ");
            double vehiclePrice = scanner.nextDouble();

            System.out.print("Enter the total deposit for the vehicle: ");
            double vehicleDeposit = scanner.nextDouble();

            System.out.print("Enter the interest rate for the vehicle loan: ");
            double vehicleInterestRate = scanner.nextDouble();

            System.out.print("Enter the estimated insurance: ");
            double insurancePremium = scanner.nextDouble();

            Vehicle vehicle = new Vehicle(model, vehiclePrice, vehicleDeposit, vehicleInterestRate, insurancePremium);
            double monthlyVehicleCost = vehicle.getMonthlyCost();
            expenses.add(new Expense(monthlyVehicleCost, "Vehicle (" + model + ")"));
        }

        
        double totalExpenses = 0;
        for (Expense exp : expenses) {
            totalExpenses += exp.getAmount();
        }

        
        if (totalExpenses > 0.75 * grossIncome) {
            expenseWarning.accept(totalExpenses); 
        }

        
        Collections.sort(expenses, Comparator.comparingDouble(Expense::getAmount).reversed());
        System.out.println("Expenses in descending order:");
        for (Expense exp : expenses) {
            System.out.println(exp.getType() + ": " + exp.getAmount());
        }

        double availableMoney = grossIncome - totalExpenses;
        System.out.println("Available monthly money after all deductions: " + availableMoney);

        
    }}


