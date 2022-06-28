package pt.upskill.projeto2.financemanager.filters;

import pt.upskill.projeto2.financemanager.accounts.StatementLine;

public class NoCategorySelector implements Selector<StatementLine>{

    @Override
    public boolean isSelected(StatementLine item) { //Object is StatementLine
        if (item.getCategory() == null)
            return true;
        return false;
    }
}
