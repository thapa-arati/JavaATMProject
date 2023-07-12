import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMProject extends JFrame implements ActionListener {

    private JLabel balanceLabel;
    private JButton checkBalanceButton;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton insertPINButton;
    private JButton exitButton;

    private double balance;
    private boolean isPINInserted;

    public ATMProject() {
        setTitle("ATM Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        insertPINButton = new JButton("Insert PIN");
        balanceLabel = new JLabel("Balance: $0.00");
        checkBalanceButton = new JButton("Check Balance");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        exitButton = new JButton("Exit");

        checkBalanceButton.addActionListener(this);
        depositButton.addActionListener(this);
        withdrawButton.addActionListener(this);
        insertPINButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(balanceLabel);
        add(insertPINButton);
        add(checkBalanceButton);
        add(depositButton);
        add(withdrawButton);
        add(exitButton);

        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkBalanceButton) {
            if (isPINInserted) {
                JOptionPane.showMessageDialog(this, "Your balance is $" + balance);
            } else {
                JOptionPane.showMessageDialog(this, "Please insert your PIN first.");
            }
        } else if (e.getSource() == depositButton) {
            if (isPINInserted) {
                String amountStr = JOptionPane.showInputDialog(this, "Enter the amount to deposit:");
                double amount = Double.parseDouble(amountStr);
                balance += amount;
                JOptionPane.showMessageDialog(this, "$" + amount + " deposited successfully.");
                balanceLabel.setText("Balance: $" + balance);
            } else {
                JOptionPane.showMessageDialog(this, "Please insert your PIN first.");
            }
        } else if (e.getSource() == withdrawButton) {
            if (isPINInserted) {
                String amountStr = JOptionPane.showInputDialog(this, "Enter the amount to withdraw:");
                double amount = Double.parseDouble(amountStr);
                if (amount < 20) {
                    JOptionPane.showMessageDialog(this, "Amount should be greater or equal to $20");
                } else {
                    if (balance >= amount) {
                        balance -= amount;
                        JOptionPane.showMessageDialog(this, "$" + amount + " withdrawn successfully.");
                        balanceLabel.setText("Balance: $" + balance);
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient funds.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please insert your PIN first.");
            }
        } else if (e.getSource() == insertPINButton) {
            String pin = JOptionPane.showInputDialog(this, "Please insert your 4-digit PIN:");
            if (pin != null && pin.length() == 4 && pin.equals("1234")) {
                isPINInserted = true;
                JOptionPane.showMessageDialog(this, "PIN inserted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid PIN. Please try again.");
            }
        } else if (e.getSource() == exitButton) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?");
            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ATMProject();
            }
        });
    }
}
