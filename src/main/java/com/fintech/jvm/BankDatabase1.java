package com.fintech.jvm;



public class BankDatabase1 {

        int bobBalance = 5000;
        int aliceBalance = 1000;

        public void transfer(int amount) {
            System.out.println("--- BEGIN TRANSACTION ---");

            // We save this in case we need to hit "Undo"
            int originalBobBalance = bobBalance;

            try {
                // STEP 1: Deduct the amount from Bob
                bobBalance -= amount;

                // SIMULATING A SERVER CRASH
                if (amount > 3000) {
                    throw new Exception("SERVER CRASH! Power lost before Alice gets paid.");
                }

                // STEP 2: Add the amount to Alice
                aliceBalance += amount;

                System.out.println("COMMIT: Transfer Successful.");

            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());

                // STEP 3: ROLLBACK!
                // How do you restore Bob's money so it isn't lost?
                bobBalance = originalBobBalance;

                System.out.println("ROLLBACK: Transaction aborted. Balances restored.");
            }

            System.out.println("Final Balances -> Bob: " + bobBalance + ", Alice: " + aliceBalance);

        }
        public static void main(String[] args){
            BankDatabase1 bd = new BankDatabase1();
            //2000 is not greater than 3000 so it won't crash
            bd.transfer(2000);

    }
}
