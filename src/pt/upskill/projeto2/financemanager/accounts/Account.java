package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.categories.Category;
import pt.upskill.projeto2.financemanager.date.Date;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public abstract class Account {
    private long id;
    private String currency;
    private String name;
    private Date startDate;
    private Date endDate;
    private String type;
    private ArrayList<StatementLine> statementLinesList = new ArrayList<>();
    private String additionalInfo = "";

    public Account(int id, String name) {
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
        setStartDateFromStatements(this);
        return startDate;
    }

    public Date getEndDate() {
        setEndDateFromStatements(this);
        return endDate;
    }

    public abstract double getInterestRate();

    public String getCurrency() {
        return currency;
    }

    public ArrayList<StatementLine> getStatementLinesList() {
        return statementLinesList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
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
                switch (count) {
                    case 0:
                    case 2:
                    case 3:
                    case 4:
                        count++;
                        continue;
                    case 1: //if reading second line
                        String[] tokens = line.replaceAll(" ", "").split(";");
                        if (line.contains("SavingsAccount")) {
                            newAccount = new SavingsAccount();
                        }
                        if (line.contains("DraftAccount")) {
                            newAccount = new DraftAccount();
                        }
                        newAccount.setId(Long.parseLong(tokens[1]));
                        newAccount.setCurrency(tokens[2]);
                        newAccount.setName(tokens[3]);
                        newAccount.setType(tokens[4]);
                        break;
                    default: //default == transaction lines in .csv file
                        String[] statementTokens = line.split(" ;");
                        if (!line.equals("")) {
                            hasStatementLines = true;
                            Date date = convertToDate(statementTokens[0]);
                            Date valueDate = convertToDate(statementTokens[1]);
                            String description = statementTokens[2];
                            double draft = Double.parseDouble(statementTokens[3]);
                            double credit = Double.parseDouble(statementTokens[4]);
                            double accountingBalance = Double.parseDouble(statementTokens[5]);
                            double availableBalance = Double.parseDouble(statementTokens[6]);
                            StatementLine statementLine = new StatementLine(date, valueDate, description, draft, credit, accountingBalance, availableBalance, null);
                            newAccount.addStatementLine(statementLine);
                        }
                }
                count++;
            }
            if (hasStatementLines) {
                setStartDateFromStatements(newAccount);
                setEndDateFromStatements(newAccount);
            }
            return newAccount;
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro nao encontrado");
        }
        return null;
    }

    private static Date convertToDate(String dateString) { //converts date in String to Date
        String[] tokens = dateString.split("-");
        int day = Integer.parseInt(tokens[0]);
        int month = Integer.parseInt(tokens[1]);
        int year = Integer.parseInt(tokens[2]);
        return new Date(day, month, year);
    }

    private static void setStartDateFromStatements(Account account) { //reads first Date in Account StatementLines and sets startDate
        if (account.getStatementLinesList().size() > 0) { //if account has statements
            account.setStartDate(account.getStatementLinesList().get(0).getDate()); //sets Account startDate to date in first statement
        }
    }

    private static void setEndDateFromStatements(Account account) { //reads last Date in Account StatementLines and sets endDate
        if (account.getStatementLinesList().size() > 0) { //if account has statements
            int lastIndex = account.getStatementLinesList().size() - 1;
            account.setEndDate(account.getStatementLinesList().get(lastIndex).getDate()); //sets Account endDate to date in last statement
        }
    }

    public String additionalInfo() {
        return additionalInfo;
    }

    public double currentBalance() {
        int lastIndex = statementLinesList.size() - 1;
        double balance = 0.0;
        if (lastIndex >= 0) {
            balance = balance + statementLinesList.get(lastIndex).getAccountingBalance();
            return balance;
        }
        return balance;
    }

    public double estimatedAverageBalance() {
        int nDays = 0;
        int totalDays = 0;
        double sum = 0;
        int listSize = statementLinesList.size();
        if (listSize > 1) { //if list contains entries
            if (statementLinesList.get(listSize - 1).getDate().getYear() == 2022) {
                for (int i = 0; i < statementLinesList.size() - 1; i++) {
                    nDays = statementLinesList.get(i).getDate().diffInDays(statementLinesList.get(i + 1).getDate());
                    sum += statementLinesList.get(i).getAvailableBalance() * nDays;
                    totalDays += nDays;
                }
                return sum / totalDays;
            } else {
                return statementLinesList.get(listSize-1).getAvailableBalance();
            }
        } else if (listSize == 1)
            return statementLinesList.get(listSize-1).getAvailableBalance();
        return 0.0;
    }

    public void addStatementLine(StatementLine statement) {
        statementLinesList.add(statement);
        Collections.sort(statementLinesList);
    }

    public void removeStatementLinesBefore(Date date) {
        ArrayList<StatementLine> newList = new ArrayList<>();
        for (StatementLine statementLine : statementLinesList){
            if (statementLine.getDate().after(date) || statementLine.getDate().equals(date))
                newList.add(statementLine);
        }
        statementLinesList = newList;

    }
    public double totalForMonth(int month, int year) {
        double total = 0.0;
        if (statementLinesList.size() > 0){ //if statementLinesList has statements
            for (StatementLine statement : statementLinesList){
                total = total + statement.getDraft();
            }
        }
        return total;
    }

    public double totalDraftsForCategorySince(Category category, Date date) {
        double total = 0.0;
            for (StatementLine statement : statementLinesList) {
                Category statementCategory = statement.getCategory();
                if (statementCategory != null && statementCategory.equals(category) && (statement.getDate().after(date) || statement.getDate().equals(date)))
                    total = total + statement.getDraft();
                }
        return total;
    }

    public void autoCategorizeStatements(List<Category> categories) {
        for (StatementLine statement : statementLinesList) {
            String statementDescription = statement.getDescription();
            for (Category category : categories) {
                if (category.hasTag(statementDescription)) {
                    statement.setCategory(category);
                }
            }
        }
    }
}
