package pt.upskill.projeto2.financemanager.filters;

import pt.upskill.projeto2.financemanager.accounts.StatementLine;

public class NoCategorySelector implements Selector{

    @Override
    public boolean isSelected(Object item) { //Object is StatementLine?
        return false; //to do
    }
}
