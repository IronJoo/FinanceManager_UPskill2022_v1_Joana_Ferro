package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.categories.Category;
import pt.upskill.projeto2.financemanager.date.Date;

import java.io.File;
import java.util.List;

public class Account {
    private int id;
    private String name;
    private Date startDate;
    private Date endDate;

    public Account(int id, String name){
        this.id = id;
        this.name = name;
    }
    public Account(Date date, Date valueDate, String description, double draft, double credit, double accountingBalance, double availableBalance, Category category) {

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
    public void setName(String other) {
    }

    public static Account newAccount(File file) {
        Account newAccount = new Account();
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

    public double getInterestRate() {
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
    }

    public void autoCategorizeStatements(List<Category> categories) {
    }
}
