package pt.upskill.projeto2.financemanager.filters;

import pt.upskill.projeto2.financemanager.date.Date;

public class BeforeDateSelector implements Selector{
    private Date date;
    public BeforeDateSelector(Date date) {
        this.date = date;
    }

    @Override
    public boolean isSelected(Object item) {
        return false;
    }
}
