package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class Invoice {

    //private final String invoiceNumber = "000000/00";
    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static String dateString = format.format(java.util.Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
    private int invoiceYear = YearMonth.now().getYear();
    private static final String FETCH_INVOICE_NUM_QUERY = "SELECT bill_number FROM invoice WHERE invoice_id=(SELECT seq FROM sqlite_sequence WHERE name='invoice')";
    private static final String INSERT_INVOICE_ITEM_QUERY = "INSERT into invoice (item_name, amount, total_price, bill_cost, bill_number, date, storno) VALUES (?,?,?,?,?,?,FALSE)";
    private static final String FETCH_LAST_FIVE_INVOICES_QUERY =
            "SELECT * from invoice " +
                  //  "WHERE date >= ? " +
                    "GROUP BY bill_number " ;
                   // "ORDER BY date DESC " +
                    //"LIMIT 5";
    public void generateInvoice() {
        try (Connection dbConnection = DatabaseConnection.getInstance().getConnection()) {
            dbConnection.setAutoCommit(false);
            String invoiceNr = fetchInvoiceNumber(dbConnection);
            insertInvoiceItems(dbConnection, invoiceNr);
            dbConnection.commit();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Duplicate key error occured");
        } catch (SQLException e) {
            System.out.println("Database error occured" + e.getMessage());
        }
    }

    private String fetchInvoiceNumber(Connection dbConnection) throws SQLException {
        int integerInvoiceNumber;
        try (PreparedStatement invoiceStmt = dbConnection.prepareStatement(FETCH_INVOICE_NUM_QUERY);
             ResultSet rs = invoiceStmt.executeQuery()) {
            String invoiceNr;
            if(rs.next()) {
                invoiceNr = rs.getString("bill_number");
                integerInvoiceNumber = Integer.parseInt(invoiceNr.split("/")[0]) + 1;
            } else {
                integerInvoiceNumber = 1; // if not found, initialize with the first number
            }
            return String.format("%06d/%d", integerInvoiceNumber, invoiceYear);
        }
    }

    private void insertInvoiceItems(Connection dbConnection, String invoiceNr) throws SQLException {
        for (Map.Entry<Item, Integer> entry : Cart.getInstance().getItemsInCart().entrySet()) {
            insertInvoiceItem(dbConnection, invoiceNr, entry);
        }
    }

    private static void insertInvoiceItem(Connection dbConnection, String invoiceNr, Map.Entry<Item, Integer> entry) throws SQLException {
        Item item = entry.getKey();
        double price = item.getPrice() * entry.getValue();

        try (PreparedStatement insertStmt = dbConnection.prepareStatement(INSERT_INVOICE_ITEM_QUERY)) {
            insertStmt.setString(1, item.getName());
            insertStmt.setDouble(2, (double)entry.getValue());
            insertStmt.setDouble(3, price);
            insertStmt.setDouble(4, Math.round(Cart.getInstance().getTotalCost() * 100.0) / 100.0);
            insertStmt.setString(5, invoiceNr);
            insertStmt.setString(6, dateString);
            insertStmt.executeUpdate();
        }
    }
    public List<String> getInvoices() {
        List<String> invoices = new ArrayList<>();
        //Date currentDate = Date.valueOf(LocalDate.now());

        try (Connection dbConnection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = dbConnection.prepareStatement(FETCH_LAST_FIVE_INVOICES_QUERY)) {

            //stmt.setString(1, String.valueOf(currentDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    StringBuilder invoice = new StringBuilder();
                    invoice.append("Invoice Number: ").append(rs.getString("bill_number")).append("\n");
                    invoice.append("Item Name: ").append(rs.getString("item_name")).append("\n");
                    invoice.append("Amount: ").append(rs.getDouble("amount")).append("\n");
                    invoice.append("Total Price: ").append(rs.getDouble("total_price")).append("\n");
                    invoice.append("Bill Cost: ").append(rs.getDouble("bill_cost")).append("\n");
                    invoice.append("Date: ").append(rs.getString("date")).append("\n");
                    invoice.append("-------------------------------\n");
                    invoices.add(invoice.toString());
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error occured" + e.getMessage());
        }

        return invoices;
    }
}