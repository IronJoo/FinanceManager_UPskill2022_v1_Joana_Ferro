package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.categories.Category;

import java.util.List;

public class SavingsAccount extends Account{
    public static Category savingsCategory = new Category("SAVINGS");

    public SavingsAccount(int id, String name) {
        super(id, name);
    }

    public SavingsAccount() {
        //super();
    }

    @Override
    public double getInterestRate() {
        return BanksConstants.savingsInterestRate();
    }

    @Override
    public void addStatementLine(StatementLine statement) {
        statement.setCategory(savingsCategory);
        super.addStatementLine(statement);
    }

    @Override
    public double estimatedAverageBalance() {
        int nDays = 0;
        int totalDays = 0;
        double sum = 0;
        int listSize = super.getStatementLinesList().size();
        if (listSize > 1) { //if list contains entries
            if (super.getStatementLinesList().get(listSize - 1).getDate().getYear() == 2022) {
                for (int i = 0; i < super.getStatementLinesList().size() - 1; i++) {
                    nDays = super.getStatementLinesList().get(i).getDate().diffInDays(super.getStatementLinesList().get(i + 1).getDate());
                    sum += super.getStatementLinesList().get(i).getAvailableBalance() * nDays;
                    totalDays += nDays;
                }
                return sum / totalDays;
            } else {
                return super.getStatementLinesList().get(listSize-1).getAvailableBalance();
            }
        } else if (listSize == 1)
            return super.getStatementLinesList().get(listSize-1).getAvailableBalance();
        return 0.0;
    }
}
