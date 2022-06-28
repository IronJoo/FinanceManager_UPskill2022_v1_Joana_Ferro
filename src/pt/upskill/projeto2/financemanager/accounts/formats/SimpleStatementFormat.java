package pt.upskill.projeto2.financemanager.accounts.formats;

import pt.upskill.projeto2.financemanager.accounts.Account;
import pt.upskill.projeto2.financemanager.accounts.StatementLine;

public class SimpleStatementFormat implements StatementLineFormat {

    @Override
    public String format(StatementLine objectToFormat) {
        String finalString = objectToFormat.getDate() + " \t" + objectToFormat.getDescription()
                + " \t" + objectToFormat.getDraft() + " \t" + objectToFormat.getCredit()
                + " \t" + objectToFormat.getAvailableBalance();
        return finalString;
    }

    @Override
    public String fields() {
        return "Date \tDescription \tDraft \tCredit \tAvailable balance ";
    }
}
