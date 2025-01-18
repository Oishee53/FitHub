public class Account {
    private static double balance;
    public Account(double balance) {
        this.balance = balance;
    }
    public static void memberPayment(double amount) {
        if (amount > 0) {
            balance += amount;
            WriteToFile.BalanceFile(balance);
        }
    }
    public static void trainerPaid(String trainerID,double salary) {
        if (salary > 0 && balance >= salary) {
            balance -= salary;
            WriteToFile.BalanceFile(balance);
            System.out.println(trainerID + " paid successfully!");
            WriteToFile.TrainerAccount(trainerID,salary);
        } else {
            System.out.println("Insufficient funds for trainer payment.");
        }
    }

    public static void equipmentPurchased(double price,int quantity) {
        if (price > 0 && balance >= price*quantity) {
            balance -= price*quantity;
            WriteToFile.BalanceFile(balance);
        } else {
            System.out.println("Insufficient funds for equipment purchase.");
        }
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
