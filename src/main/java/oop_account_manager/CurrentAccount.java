package oop_account_manager;

public class CurrentAccount extends BankAccount{
    private double overdraftLimit;
    public CurrentAccount(String accountId, double balance, double overdraftLimit){
        super(accountId,balance);
        this.overdraftLimit = overdraftLimit;
    }
    @Override
    public void withdraw(double amount){
        if (getBalance() + overdraftLimit >= amount){
            setBalance(getBalance() - amount);
            System.out.println("successful");
        }else{
            System.out.println("Declined: Exceeds overdraft limit.");
        }
    }

}
