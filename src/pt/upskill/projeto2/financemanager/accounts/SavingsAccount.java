package pt.upskill.projeto2.financemanager.accounts;

import pt.upskill.projeto2.financemanager.categories.Category;

public class SavingsAccount extends Account{
    public static Category savingsCategory;

    public SavingsAccount(int id, String name) {
        super(id, name);
    }
}
