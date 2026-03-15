import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMProject extends JFrame implements ActionListener {

    double balance = 5000;
    String pin = "2527";

    JTextField pinField, amountField;
    JButton loginBtn, balanceBtn, depositBtn, withdrawBtn, exitBtn;

    JPanel loginPanel, atmPanel;

    ATMProject() {

        setTitle("ATM Interface");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // LOGIN PANEL
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3,1));

        loginPanel.add(new JLabel("Enter ATM PIN:"));

        pinField = new JTextField();
        loginPanel.add(pinField);

        loginBtn = new JButton("Login");
        loginPanel.add(loginBtn);

        add(loginPanel);

        loginBtn.addActionListener(this);

        setVisible(true);
    }

    public void createATMPanel() {

        atmPanel = new JPanel();
        atmPanel.setLayout(new GridLayout(6,1));

        atmPanel.add(new JLabel("Enter Amount"));

        amountField = new JTextField();
        atmPanel.add(amountField);

        balanceBtn = new JButton("Check Balance");
        depositBtn = new JButton("Deposit");
        withdrawBtn = new JButton("Withdraw");
        exitBtn = new JButton("Exit");

        atmPanel.add(balanceBtn);
        atmPanel.add(depositBtn);
        atmPanel.add(withdrawBtn);
        atmPanel.add(exitBtn);

        balanceBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        withdrawBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        setContentPane(atmPanel);
        revalidate();
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==loginBtn) {

            if(pinField.getText().equals(pin)) {
                createATMPanel();
            }
            else {
                JOptionPane.showMessageDialog(this,"Wrong PIN");
            }
        }

        if(e.getSource()==balanceBtn) {
            JOptionPane.showMessageDialog(this,"Balance: ₹"+balance);
        }

        if(e.getSource()==depositBtn) {
            double amount = Double.parseDouble(amountField.getText());
            balance += amount;
            JOptionPane.showMessageDialog(this,"Amount Deposited");
        }

        if(e.getSource()==withdrawBtn) {

            double amount = Double.parseDouble(amountField.getText());

            if(amount > balance) {
                JOptionPane.showMessageDialog(this,"Insufficient Balance");
            }
            else {
                balance -= amount;
                JOptionPane.showMessageDialog(this,"Please collect cash");
            }
        }

        if(e.getSource()==exitBtn) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new ATMProject();
    }
}