import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

class Ticket {
    String name, trainName, classType, date, from, to;
    int trainNo;

    Ticket(String name, int trainNo, String trainName, String classType, String date, String from, String to) {
        this.name = name;
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.classType = classType;
        this.date = date;
        this.from = from;
        this.to = to;
    }
}

public class OnlineReservationGUI {

    static HashMap<Integer, Ticket> database = new HashMap<>();
    static int pnrCounter = 1000;

    public static void main(String[] args) {
        loginFrame();
    }

    static void loginFrame() {

        JFrame frame = new JFrame("Login");
        frame.setSize(500,300);
        frame.setLayout(new GridLayout(3,2));

        JLabel userLabel = new JLabel("Login ID:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("Login");

        frame.add(userLabel);
        frame.add(userField);
        frame.add(passLabel);
        frame.add(passField);
        frame.add(new JLabel());
        frame.add(loginBtn);

        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());

            if(user.equals("preethi") && pass.equals("2527")) {
                frame.dispose();
                mainMenu();
            } else {
                JOptionPane.showMessageDialog(frame,"Invalid Login");
            }
        });

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static void mainMenu() {

        JFrame frame = new JFrame("Online Reservation System");
        frame.setSize(300,200);
        frame.setLayout(new GridLayout(3,1));

        JButton reserveBtn = new JButton("Reserve Ticket");
        JButton cancelBtn = new JButton("Cancel Ticket");
        JButton exitBtn = new JButton("Exit");

        frame.add(reserveBtn);
        frame.add(cancelBtn);
        frame.add(exitBtn);

        reserveBtn.addActionListener(e -> reservationForm());
        cancelBtn.addActionListener(e -> cancellationForm());
        exitBtn.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    static void reservationForm() {

        JFrame frame = new JFrame("Reservation Form");
        frame.setSize(400,400);
        frame.setLayout(new GridLayout(8,2));

        JTextField name = new JTextField();
        JTextField trainNo = new JTextField();
        JTextField trainName = new JTextField();
        JTextField classType = new JTextField();
        JTextField date = new JTextField();
        JTextField from = new JTextField();
        JTextField to = new JTextField();

        JButton submit = new JButton("Book Ticket");

        frame.add(new JLabel("Name")); frame.add(name);
        frame.add(new JLabel("Train No")); frame.add(trainNo);
        frame.add(new JLabel("Train Name")); frame.add(trainName);
        frame.add(new JLabel("Class Type")); frame.add(classType);
        frame.add(new JLabel("Date")); frame.add(date);
        frame.add(new JLabel("From")); frame.add(from);
        frame.add(new JLabel("To")); frame.add(to);
        frame.add(new JLabel()); frame.add(submit);

        submit.addActionListener(e -> {

            int pnr = pnrCounter++;

            Ticket t = new Ticket(
                    name.getText(),
                    Integer.parseInt(trainNo.getText()),
                    trainName.getText(),
                    classType.getText(),
                    date.getText(),
                    from.getText(),
                    to.getText()
            );

            database.put(pnr,t);

            JOptionPane.showMessageDialog(frame,"Reservation Successful!\nPNR: "+pnr);
        });

        frame.setVisible(true);
    }

    static void cancellationForm() {

        JFrame frame = new JFrame("Cancel Ticket");
        frame.setSize(300,200);
        frame.setLayout(new GridLayout(3,2));

        JTextField pnrField = new JTextField();
        JButton searchBtn = new JButton("Search");
        JButton cancelBtn = new JButton("Cancel Ticket");

        frame.add(new JLabel("Enter PNR"));
        frame.add(pnrField);
        frame.add(searchBtn);
        frame.add(cancelBtn);

        searchBtn.addActionListener(e -> {

            int pnr = Integer.parseInt(pnrField.getText());

            if(database.containsKey(pnr)) {

                Ticket t = database.get(pnr);

                JOptionPane.showMessageDialog(frame,
                        "Name: "+t.name+
                        "\nTrain: "+t.trainName+
                        "\nFrom: "+t.from+
                        "\nTo: "+t.to);

            } else {
                JOptionPane.showMessageDialog(frame,"PNR Not Found");
            }
        });

        cancelBtn.addActionListener(e -> {

            int pnr = Integer.parseInt(pnrField.getText());

            if(database.containsKey(pnr)) {
                database.remove(pnr);
                JOptionPane.showMessageDialog(frame,"Ticket Cancelled");
            } else {
                JOptionPane.showMessageDialog(frame,"Invalid PNR");
            }
        });

        frame.setVisible(true);
    }
}