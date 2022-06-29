package pt.upskill.projeto2.financemanager.accounts.formats;

import pt.upskill.projeto2.financemanager.accounts.Account;
import pt.upskill.projeto2.financemanager.accounts.StatementLine;
import pt.upskill.projeto2.financemanager.date.Date;

public class FileAccountFormat implements Format {
    @Override
    public String format(Object objectToFormat) {
        Account account = (Account) objectToFormat;
        String nl = System.getProperty("line.separator");
        String finalString = "Account Info - " + new Date().toString() + nl
                + "Account  ;" + account.getId() + " ; " + account.getCurrency() + "  ;"
                + account.getName() + " ;" + account.getType() + " ;" + nl
                + "Start Date ;" + account.getStartDate() + nl
                + "End Date ;" + account.getEndDate() + nl
                + "Date ;Value Date ;Description ;Draft ;Credit ;Accounting balance ;Available balance" + nl;

                for (StatementLine statementLine : account.getStatementLinesList()){
                    finalString += statementLine.getDate() + " ;" + statementLine.getValueDate()
                            + " ;" + statementLine.getDescription() + " ;" + statementLine.getDraft()
                            + " ;" + statementLine.getCredit() + " ;" + statementLine.getAccountingBalance()
                            + " ;" + statementLine.getAvailableBalance() + nl;
                }
                return finalString;
    }
}
