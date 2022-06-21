package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.categories.Category;
import pt.upskill.projeto2.financemanager.date.Date;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Account {
    private long id;
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

    public long getId() {
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
            Account newAccount = new Account();
            int count = 0;
            //String dateLastUpdate = tokens[1];
            while (fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] tokens = line.replaceAll(" ", "").split(";");
                if (count == 1){ //if reading second line
                    if (line.contains("SavingsAccount")){
                        newAccount = new SavingsAccount();
                    }
                    if (line.contains("DraftAccount")){
                        newAccount = new DraftAccount();
                    }
                    newAccount.setId(Long.parseLong(tokens[1]));
                    newAccount.setName(tokens[3]);
                }
//                if (count == 2){
//                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//                    newAccount.setStartDate();
//                }
                count++;
            }
            return newAccount;
        }
        catch (FileNotFoundException e){
            System.out.println("Ficheiro n");;
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
