package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.categories.Category;
import pt.upskill.projeto2.financemanager.date.Date;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Account {
    private long id;
    private String currency;
    private String name;
    private Date startDate;
    private Date endDate;
    private double interestRate;
    private ArrayList<StatementLine> statementLinesList = new ArrayList<>();

    public Account(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Account() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        setDatesFromStatements(this);
        return startDate;
    }

    public Date getEndDate() {
        setDatesFromStatements(this);
        return endDate;
    }
    public abstract double getInterestRate(); //fix to be abstract

    public String getCurrency() {
        return currency;
    }

    public ArrayList<StatementLine> getStatementLinesList() {
        return statementLinesList;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Account newAccount(File file) {
        try {
            Scanner fileScanner = new Scanner(file);
            Account newAccount = null;
            boolean hasStatementLines = false;
            int count = 0;
            //String dateLastUpdate = tokens[1];
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] tokens = line.replaceAll(" ", "").split(";");
                switch (count) {
                    case 0:
                    case 2:
                    case 3:
                    case 4:
                        count++;
                        continue;
                    case 1: //if reading second line
                        if (line.contains("SavingsAccount")) {
                            newAccount = new SavingsAccount();
                        }
                        if (line.contains("DraftAccount")) {
                            newAccount = new DraftAccount();
                        }
                        newAccount.setId(Long.parseLong(tokens[1]));
                        newAccount.setCurrency(tokens[2]);
                        newAccount.setName(tokens[3]);
                        break;
//                    case 2:
//                        newAccount.setStartDate(convertToDate(tokens[1]));
//                        break;
//                    case 3:
//                        newAccount.setEndDate(convertToDate(tokens[1]));
//                        break;
                    default: //default == transaction lines in .csv file
                        if (!line.equals("")) {
                            hasStatementLines = true;
                            Date date = convertToDate(tokens[0]);
                            Date valueDate = convertToDate(tokens[1]);
                            String description = tokens[2];
                            double draft = Double.parseDouble(tokens[3]);
                            double credit = Double.parseDouble(tokens[4]);
                            double accountingBalance = Double.parseDouble(tokens[5]);
                            double availableBalance = Double.parseDouble(tokens[6]);
                            StatementLine statementLine = new StatementLine(date, valueDate, description, draft, credit, accountingBalance, availableBalance, null);
                            newAccount.addStatementLine(statementLine);
                        }
                }
                count++;
            }
            if (hasStatementLines)
                setDatesFromStatements(newAccount);
            return newAccount;
        }
        catch (FileNotFoundException e){
            System.out.println("Ficheiro nao encontrado");;
        }
        return null;
    }
    private static Date convertToDate(String dateString){ //converts date in String to Date
        String[] tokens = dateString.split("-");
        int day = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int year = Integer.parseInt(tokens[2]);
        return new Date(day, month, year);
    }
    private static void setDatesFromStatements(Account account){ //reads first and last Date in Account StatementLines and sets start and end Dates accordingly
        if (account.getStatementLinesList().size() > 0){ //if account has statements
            account.setStartDate(account.getStatementLinesList().get(0).getDate()); //sets Account startDate to date in first statement
            int lastIndex = account.getStatementLinesList().size() - 1;
            account.setEndDate(account.getStatementLinesList().get(lastIndex).getDate()); //sets Account endDate to date in last statement
        }
    }

    public String additionalInfo() {
        return "";
    }

    public double currentBalance() {
        int lastIndex = statementLinesList.size() - 1;
        double balance = 0.0;
        if (lastIndex >= 0) {
            balance = balance + statementLinesList.get(lastIndex).getAccountingBalance();
            return balance;
        }
        return 0.0;
    }

    public double estimatedAverageBalance() {
        int lastIndex = statementLinesList.size() - 1;
        double balance = 0.0;
        if (lastIndex >= 0) {
            balance = balance + statementLinesList.get(lastIndex).getAccountingBalance(); //TO DO: possibly using wrong getter
            return balance;
        }
        return 0.0;
    }

    public void addStatementLine(StatementLine statement) {
        statementLinesList.add(statement);
    }

    public void removeStatementLinesBefore(Date date) {
    }

    public double totalDraftsForCategorySince(Category category, Date date) {
        return 0;
    }

    public double totalForMonth(int month, int year) {
        double total = 0;
        if (statementLinesList.size() > 0){ //if statementLinesList has statements
            for (StatementLine statement : statementLinesList){
                total = total + statement.getDraft();
            }
            return total;
        }
        return 0.0;
    }

    public void autoCategorizeStatements(List<Category> categories) {
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", interestRate=" + interestRate +
                '}';
    }
}
