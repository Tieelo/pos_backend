package PoS_Java;

import javax.swing.*;
import java.util.Locale;
import java.util.Scanner;
import java.sql.*;
import java.io.File;
public class Test {
    private static String[] test = new String[]{"16 248.99", "10 25", "19 80"};
    public static void main(String[] args) {
        String item_name = "Wasser";
        double stock = 3.3;
        double price = 2.7;
        double totalPrice = 2.7;
        String invoiceNr = "test";

        //System.out.printf(Locale.US, "INSERT into invoice (item_name, amount, total_price, bill_cost, bill_number, storno)" +
          //      " VALUES ('%s', %2f, %2f, %2f, '%s', FALSE) \n", item_name, stock, price, totalPrice, invoiceNr);

        File folder = new File("BusinessData/answers/realdata/it/");
        System.out.println(folder.getAbsolutePath());
        //File folder = new File("C:\\dev\\repositorys\\divisionscout-spring\\src\\main\\resources\\BusinessData\\answers\\realdata\\it");
        //for (File file : folder.listFiles())
          //  System.out.println("BusinessData/answers/realdata/it/" + file.getName());
        /*File file = new File("test");
        File absoluteFile = file.getAbsoluteFile();
        File absoluteDirectory = absoluteFile.getParentFile();
        System.out.println(absoluteFile);
        System.out.println(absoluteDirectory);
    }*/
        /*//getGroups();
         *//* Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] words = str.split(" ");
        String[] test = words.clone();
        int length = words.length;

        System.out.println(words[1]);
        for (String word : words) {
            word = word.replaceAll(",", ".");
            System.out.println(word);*//*
        //update();
        update();
        }*/

/*    public static void update2(){
    Connection c = null;
    Statement stmt = null;

    try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:PoS_SQLite.sqlite");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        int itemsId = 16;
        double stock = 249.99;
        String sql = "UPDATE items set item_amount = " + stock + " where items_id ="+itemsId+";";
        stmt.executeUpdate(sql);
        c.commit();

        stmt.close();
        c.close();
    } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
    }
}*/
    }
/*public static void update(){
    Connection c = null;
    Statement stmt = null;

    try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:PoS_SQLite.sqlite");
        c.setAutoCommit(false);
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        String[] export;
        int itemsId;
        double stock;
        for (String i : test) {
            export = i.split(" ");
            itemsId = Integer.parseInt(export[0]);
            //export[1].replaceAll(",", ".");
            stock = Double.parseDouble(export[1]);
            System.out.println(itemsId + stock);
            String sql = "UPDATE items set item_amount = " + stock + " where items_id ="+itemsId+";";
            stmt.executeUpdate(sql);
            c.commit();
        }
        stmt.close();
        c.close();
    } catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
    }
}*/
}
