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
//    @Override
//    public void autoCategorizeStatements(List<Category> categories) {
//        super.autoCategorizeStatements(categories);
//    }
//

    @Override
    public void addStatementLine(StatementLine statement) {
        statement.setCategory(savingsCategory);
        super.addStatementLine(statement);
    }
}
