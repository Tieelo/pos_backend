package PoS_Java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PoS_GUI extends JFrame implements ActionListener {
    private JList<String> drinkList;
    private DefaultListModel<String> listModel;
    private JButton StornoButton;
    private JButton discountButton;
    private JButton SofortStornoButton;
    private JButton ArtikelmengeButton;
    private JButton[] groupButtons;
    private JLabel totalLabel;
    private double total;
    private Database database;


    //Problem: Kann nicht auf Datenbank zugreifen "NullPointerExecption"
    public class Database {
        private Connection conn;

        public Database() {
            //JDBC Laden
            try {
                // Datenbank Connection

                Connection conn = DriverManager.getConnection("jdbc:sqlite:sqllite.jar");
                System.out.println("Connection to SQLite database established.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public double getPrice(String item) {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT price FROM drinks WHERE name = ''" + item + "");
                if (rs.next()) {
                    return rs.getDouble("price");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
        }

        public List<String> getAlldrinks() {

            List<String> drinks = new ArrayList<>();
            Statement stmt = null;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT name from drinks");
                while (rs.next()){
                    System.out.println(rs.getString("name"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } return null;


            /*try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT name from drinks");
                while (rs.next()) {
                    drinks.add(rs.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return drinks;
*/
        }

        public String[] getdrinksinGroup(int group) {
            List<String> drinks = new ArrayList<>();
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT name FROM drinks WHERE group = ''" + group);
                while (rs.next()) {
                    drinks.add(rs.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return drinks.toArray(new String[0]);
        }
    }


    public PoS_GUI() {
        // Set up GUI
        setTitle("Point of Sale");
        setSize(620, 850);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setResizable(false);



        // Item list und Model
        listModel = new DefaultListModel<>();
        drinkList = new JList<>(listModel);
        JScrollPane listScrollPane = new JScrollPane(drinkList);

        // Buttons erstellen
        StornoButton = new JButton("Storno");
        discountButton = new JButton("Discount");
        SofortStornoButton = new JButton("Sofort-Storno");
        ArtikelmengeButton = new JButton("Artikelmenge");
        groupButtons = new JButton[8];
        for (int i = 0; i < 8; i++) {
            groupButtons[i] = new JButton("Gruppe " + (i + 1));
        }
        totalLabel = new JLabel("Total: 0.00€");

        // Komponenten hinzufügen
        add(listScrollPane, BorderLayout.WEST);
        add(totalLabel, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel();

        rightPanel.setLayout(new GridLayout(9, 1));
        for (JButton button : groupButtons) {
            rightPanel.add(button);
        }

        GridLayout myGridLayout = (GridLayout) rightPanel.getLayout();
        myGridLayout.setHgap(10);
        myGridLayout.setVgap(10);

        StornoButton.setPreferredSize(new Dimension(160,700));
        discountButton.setPreferredSize(new Dimension(160,700));
        SofortStornoButton.setPreferredSize(new Dimension(160,700));
        ArtikelmengeButton.setPreferredSize(new Dimension(160,700));

        for (int i = 0; i < groupButtons.length; i++){
            groupButtons[i].setPreferredSize(new Dimension(160,700));
        }

        rightPanel.add(StornoButton);
        rightPanel.add(discountButton);
        rightPanel.add(SofortStornoButton);
        rightPanel.add(ArtikelmengeButton);
        add(rightPanel);

        // Action-Listeners für die Buttons
        StornoButton.addActionListener(this);
        discountButton.addActionListener(this);
        SofortStornoButton.addActionListener(this);
        ArtikelmengeButton.addActionListener(this);
        for (JButton button : groupButtons) {
            button.addActionListener(this);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == StornoButton) {
            // Storno
            total = 0;
            totalLabel.setText("Total: $" + total);
            listModel.clear();
        } else if (e.getSource() == discountButton) {
            // Discount
            total *= 0.9;
            totalLabel.setText("Total: $" + total);

        } else if (e.getSource() == SofortStornoButton) {
            // Sofort-Storno für einzelnen Drink
            int selectedIndex = drinkList.getSelectedIndex();
            if (selectedIndex >= 0) {
                String item = listModel.getElementAt(selectedIndex);
                double price = database.getPrice(item);
                listModel.remove(selectedIndex);
                total -= price;
                totalLabel.setText("Total: $" + total);
            }
        } else if (e.getSource() == ArtikelmengeButton) {
            // Liste aller Drinks in Datenbank
            List<String> drinks = database.getAlldrinks();
            JList<String> drinkList = new JList<>(drinks.toArray(new String[0]));
            JScrollPane scrollPane = new JScrollPane(drinkList);
            JOptionPane.showMessageDialog(this, scrollPane, "All drinks", JOptionPane.PLAIN_MESSAGE);
        } else {
            // Drinks einer Gruppe zur Liste
            for (int i = 0; i < 8; i++) {
                if (e.getSource() == groupButtons[i]) {
                    String[] drinks = database.getdrinksinGroup(i + 1);
                    for (String drink : drinks) {
                        double price = database.getPrice(drink);
                        listModel.addElement(drink + " - $" + price);
                        total += price;
                    }
                    totalLabel.setText("Total: $" + total);
                    break;
                }
            }
        }
    }

    public static void connectToDb() {

        try {
            // Connect to the database

            Connection conn = DriverManager.getConnection("jdbc:sqlite:sqlite.jar");
            System.out.println("Connection to SQLite database established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {

        connectToDb();

        //new PoS_GUI();
    }
}

