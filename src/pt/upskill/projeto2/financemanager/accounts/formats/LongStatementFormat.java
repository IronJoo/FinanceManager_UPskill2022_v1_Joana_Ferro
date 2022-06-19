package pt.upskill.projeto2.financemanager.accounts.formats;

public class LongStatementFormat implements Format {
    @Override
    public String format(Object objectToFormat) {
        return null;
    }

    public String fields() {
        return "Date \tValue Date \tDescription \tDraft \tCredit \tAccounting balance \tAvailable balance "; //to do??
    }
}
