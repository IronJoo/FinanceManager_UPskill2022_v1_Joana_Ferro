package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.categories.Category;
import pt.upskill.projeto2.financemanager.date.Date;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Account {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private double interestRate;

    public Account(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setName(String other) {
    }

    public static Account newAccount(File file) {
        try {
            Scanner fileScanner = new Scanner(file);
            Account newAccount = new Account();
            String line = fileScanner.nextLine();
            //String[] tokens = line.split(" - "); //save date of last update
            //String dateLastUpdate = tokens[1];
            while (fileScanner.hasNextLine()){
                if (line.contains("SavingsAccount")){
                    newAccount = new SavingsAccount();
                }
                if (line.contains("DraftAccount")){
                    newAccount = new DraftAccount();
                }
            }
            return newAccount;
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
    public String additionalInfo() {
        return "";
    }

    public double currentBalance() {
        return 0;
    }

    public double estimatedAverageBalance() {
        return 0;
    }

    public void addStatementLine(StatementLine description) {
    }

    public void removeStatementLinesBefore(Date date) {
    }

    public double totalDraftsForCategorySince(Category category, Date date) {
        return 0;
    }

    public double totalForMonth(int month, int year) {
        return 0;
    }

    public void autoCategorizeStatements(List<Category> categories) {
    }
}
