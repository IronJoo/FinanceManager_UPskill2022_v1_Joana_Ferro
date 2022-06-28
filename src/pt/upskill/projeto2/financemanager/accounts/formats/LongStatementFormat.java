package pt.upskill.projeto2.financemanager.accounts.formats;

import pt.upskill.projeto2.financemanager.accounts.StatementLine;

public class LongStatementFormat implements StatementLineFormat {

    public String fields() {
        return "Date \tValue Date \tDescription \tDraft \tCredit \tAccounting balance \tAvailable balance "; //to do??
    }

    @Override
    public String format(StatementLine objectToFormat) {
        String finalString = objectToFormat.getDate() + " \t" + objectToFormat.getValueDate()
                + " \t" + objectToFormat.getDescription() + " \t" + objectToFormat.getDraft()
                + " \t" + objectToFormat.getCredit() + " \t" + objectToFormat.getAccountingBalance()
                + " \t" + objectToFormat.getAvailableBalance();
        return finalString;
    }
}
