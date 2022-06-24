package pt.upskill.projeto2.financemanager.accounts.formats;

import pt.upskill.projeto2.financemanager.accounts.Account;
import pt.upskill.projeto2.financemanager.accounts.StatementLine;
import pt.upskill.projeto2.financemanager.date.Date;

public class FileAccountFormat implements Format {
//    @Override
//    public String format(Account account) {
//        int i = 0;
//        String nl = System.getProperty("line.separator");
//        String finalString = "Account Info - " + new Date().toString() + nl
//                + "Account  ;" + account.getId() + " ; " + account.getCurrency() + "  ;"
//                + account.getName() + " ;" + SavingsAccount + " ;" + nl
//                + "Start Date ;" + account.getStartDate() + nl
//                + "End Date ;" + account.getEndDate() + nl
//                + "Date ;Value Date ;Description ;Draft ;Credit ;Accounting balance ;Available balance" + nl;
//
//                for (StatementLine statementLine : account.getStatementLinesList()){
//                    finalString +=
//                    i++;
//                }
                //+ account.getStatementLinesList().get(0).getDate() + " ;" + 31-10-2013 + " ;" + SUMMARY + " ;" + 0.0 + " ;" + 200.0 + " ;" + 2600.0 + " ;" + 2600.0 + nl
//                + "30-11-2013 ;30-11-2013 ;SUMMARY ;0.0 ;200.0 ;2800.0 ;2800.0" + nl
//                + "31-12-2013 ;31-12-2013 ;SUMMARY ;0.0 ;200.0 ;3000.0 ;3000.0" + nl
//                + "02-01-2014 ;02-01-2014 ;TRANSF ;0.0 ;300.0 ;3300.0 ;3300.0" + nl
//                + "02-01-2014 ;02-01-2014 ;TRANSF ;0.0 ;300.0 ;3600.0 ;3600.0" + nl
//                + "03-01-2014 ;03-01-2014 ;TRANSF ;0.0 ;300.0 ;3900.0 ;3900.0" + nl;
//                return "";
//    }

    @Override
    public String format(Object objectToFormat) {
        return null;
    }
}
