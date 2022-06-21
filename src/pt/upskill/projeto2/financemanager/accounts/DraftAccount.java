package pt.upskill.projeto2.financemanager.accounts;

public class DraftAccount extends Account {

    public DraftAccount(int id, String name) {
        super(id, name);
    }

    public DraftAccount() {
    }

    @Override
    public double getInterestRate() {
        return BanksConstants.normalInterestRate();
    }
}
