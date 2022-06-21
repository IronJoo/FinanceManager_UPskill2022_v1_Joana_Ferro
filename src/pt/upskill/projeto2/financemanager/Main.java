package pt.upskill.projeto2.financemanager;

import pt.upskill.projeto2.financemanager.accounts.Account;
import pt.upskill.projeto2.financemanager.accounts.StatementLine;
import pt.upskill.projeto2.financemanager.gui.PersonalFinanceManagerUserInterface;

import java.io.File;

/**
 * @author upSkill 2020
 * <p>
 * ...
 */

public class Main {

    public static void main(String[] args) {

        PersonalFinanceManager personalFinanceManager = new PersonalFinanceManager();
//        PersonalFinanceManagerUserInterface gui = new PersonalFinanceManagerUserInterface(
//                personalFinanceManager);
//        gui.execute();

//        Account testAccount = new Account(12345,"Teste");
//        Account testAccount2 = Account.newAccount(new File("account_info_test/1234567890989.csv"));
//        System.out.println(testAccount2.getClass());
//        System.out.println(testAccount2);
        Account testAccount3 = Account.newAccount(new File("account_info_test/1234567890989.csv"));
        System.out.println(testAccount3.getClass());
        System.out.println(testAccount3);
        for (StatementLine statement : testAccount3.getStatementLinesList())
            System.out.println(statement);

    }

}
