/* Name: Mohamed Faizel
   Course: CNT 4714 - Fall 2021
   Assignment title: Project 1 - Event-driven Enterprise Simulation
   Date: Sunday September 12, 2021
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.Flow;

class NileDotCom implements ActionListener{
    // window constants
    private static final int WINDOW_WIDTH = 700; //pixels
    private static final int WINDOW_HEIGHT = 280; //pixels
    private static final int FIELD_WIDTH = 40;   //characters

    DecimalFormat df = new DecimalFormat("#.00");
    DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM/dd/yy, hh:mm:ss z");
    DateTimeFormatter dateID = DateTimeFormatter.ofPattern("ddMMyyyykkmm");
    private int itemNumber = 1;
    private int itemLimit = 0;
    private double currentTotal = 0.00;
    private double taxRate = 0.06;

    static String[] itemArray = new String[3];
    static double[] discount = new double[1];
    static String info;
    ArrayList<String> orderArray = new ArrayList<String>();


    private static final FlowLayout LAYOUT_STYLE = new FlowLayout();

    // window for GUI
    private JFrame window = new JFrame("NileDotCom - Fall 2021");

    // panel to hold text bars
    private JPanel textBars = new JPanel();
    // panel to hold buttons
    private JPanel actions = new JPanel();

    // user entry for amount of unique items
    private JLabel amountTag = new JLabel("Enter number of items in this order: ");
    private JTextField amountText = new JTextField(FIELD_WIDTH);
    // user entry for ID of item X
    private JLabel IDTag = new JLabel("Enter item ID for Item #" + itemNumber + ":");
    private JTextField IDText = new JTextField(FIELD_WIDTH);
    // user entry for quantity of item X
    private JLabel quantityTag = new JLabel("Enter quantity for Item #" + itemNumber + ":");
    private JTextField quantityText = new JTextField(FIELD_WIDTH);
    // result field of item X information
    private JLabel infoTag = new JLabel("Item #" + itemNumber + " info:");
    private JTextField infoText = new JTextField(FIELD_WIDTH);
    // result field of current order subtotal
    private JLabel subtotalTag = new JLabel("Order subtotal for " + (itemNumber - 1) + " item(s)");
    private JTextField subtotalText = new JTextField(FIELD_WIDTH);

    // buttons
    private JButton processOrder = new JButton("Process Item #" + itemNumber);
    private JButton confirmOrder = new JButton("Confirm Item #" + itemNumber);
    private JButton viewOrder = new JButton("View Order");
    private JButton finishOrder = new JButton("Finish Order");
    private JButton newOrder = new JButton("New Order");
    private JButton exit = new JButton("Exit");

    //NileDotCom():  constructor
    public NileDotCom() {
        //configure GUI
        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.getContentPane().setBackground(Color.BLACK);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        infoText.setEditable(false);
        subtotalText.setEditable(false);
        //chillText.setBackground(Color.WHITE);

        //register event listener
        processOrder.addActionListener(this);
        confirmOrder.addActionListener(this);
        exit.addActionListener(this);
        viewOrder.addActionListener(this);
        newOrder.addActionListener(this);
        finishOrder.addActionListener(this);
        //add components to the container
        Container c = window.getContentPane();
        c.setLayout(LAYOUT_STYLE);
        textBars.setLayout(new BoxLayout(textBars, BoxLayout.Y_AXIS));
        textBars.setBackground(Color.BLACK);
        actions.setLayout(new FlowLayout());
        actions.setBackground(Color.BLACK);

        amountTag.setForeground(Color.YELLOW);
        // amountTag.setFont(new Font("Serif", Font.BOLD, 14));

        IDTag.setForeground(Color.YELLOW);
        // IDTag.setFont(new Font("Serif", Font.BOLD, 14));

        quantityTag.setForeground(Color.YELLOW);
        // quantityTag.setFont(new Font("Serif", Font.BOLD, 14));

        infoTag.setForeground(Color.YELLOW);
        // infoTag.setFont(new Font("Serif", Font.BOLD, 14));

        subtotalTag.setForeground(Color.YELLOW);
        // subtotalTag.setFont(new Font("Serif", Font.BOLD, 14));

        textBars.add(amountTag);
        textBars.add(amountText);
        textBars.add(IDTag);
        textBars.add(IDText);
        textBars.add(quantityTag);
        textBars.add(quantityText);
        textBars.add(infoTag);
        textBars.add(infoText);
        textBars.add(subtotalTag);
        textBars.add(subtotalText);

        c.add(textBars);

        actions.add(processOrder);
        actions.add(confirmOrder);
        actions.add(viewOrder);
        actions.add(finishOrder);
        actions.add(newOrder);
        actions.add(exit);

        // disable buttons and fields until 1st order is entered
        confirmOrder.setEnabled(false);
        viewOrder.setEnabled(false);
        finishOrder.setEnabled(false);

        c.add(actions);

        //display GUI
        window.show();
    }

    //actionPerformed(): run button action event handler
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == processOrder)
        {
            amountText.setEditable(false);

            // receive number of unique items
            String itemAmount = amountText.getText();
            itemLimit = Integer.parseInt(itemAmount);
            // receive item ID and quantity
            String itemID = IDText.getText();
            String itemQuantity = quantityText.getText();

            // searches inventory.txt for item
            try {
                itemArray = inventorySearch(itemID, itemID.length());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            if (itemArray == null) {
                JOptionPane.showMessageDialog(null, "Item ID " + itemID + " is invalid");
            } else if (itemArray[2].equals("false")) {
                JOptionPane.showMessageDialog(null, "Item ID " + itemID + " is not in stock");
            } else {
                discount = discountPrice(itemArray[3], itemQuantity);
                info = itemArray[0] + " " + itemArray[1] + " $" + itemArray[3] + " " + itemQuantity + " " + discount[1] + "% $" + df.format(discount[0]);
                infoText.setText(info);

                // disable process order and enable confirm order buttons
                processOrder.setEnabled(false);
                confirmOrder.setEnabled(true);
                // update text label
                infoTag.setText("Item #" + itemNumber + " info:");
            }
        } else if (e.getSource() == confirmOrder) {
            currentTotal += discount[0];
            subtotalText.setText("$" + df.format(currentTotal));
            orderArray.add(info);

            JOptionPane.showMessageDialog(null, "Item # " + itemNumber + " accepted. Added to your cart.");
            itemNumber++;

            // checks if item confirmed is last item
            if (itemNumber > itemLimit) {
                // update text label
                subtotalTag.setText("Order subtotal for " + (itemNumber - 1) + " item(s)");
                // remove text labels and disable fields
                IDTag.setText(" ");
                quantityTag.setText(" ");
                IDText.setText("");
                quantityText.setText("");
                IDText.setEnabled(false);
                quantityText.setEnabled(false);

                processOrder.setText("Process Item");
                confirmOrder.setText("Confirm Item");
                processOrder.setEnabled(false);
                confirmOrder.setEnabled(false);
                viewOrder.setEnabled(true);
                finishOrder.setEnabled(true);
                newOrder.setEnabled(true);
            } else {
                // update text labels and button labels
                subtotalTag.setText("Order subtotal for " + (itemNumber - 1) + " item(s)");
                IDTag.setText("Enter item ID for Item #" + itemNumber + ":");
                quantityTag.setText("Enter quantity for Item #" + itemNumber + ":");
                IDText.setText("");
                quantityText.setText("");

                processOrder.setText("Process Item #" + itemNumber);
                confirmOrder.setText("Confirm Item #" + itemNumber);

                // update buttons
                processOrder.setEnabled(true);
                confirmOrder.setEnabled(false);
                viewOrder.setEnabled(true);
                finishOrder.setEnabled(true);
                newOrder.setEnabled(true);
            }
        } else if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == viewOrder) {
            int counter = 1;
            StringBuilder orderList = new StringBuilder();
            for (String s : orderArray) {
                String temp = counter + ") " + s;
                orderList.append(temp).append("\n");
                counter++;
            }
            JOptionPane.showMessageDialog(null, orderList.toString(),"Nile Dot Com - Current Shopping Cart Status", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (e.getSource() == finishOrder) {
            // get time of order placed and generate ID
            LocalDateTime temp = LocalDateTime.now();
            ZonedDateTime time = ZonedDateTime.now();
            String orderID = dateID.format(temp);
            String orderTime = dateTime.format(time);

            // writes orders to transactions.txt
            try {
                transactionWrite(orderID, orderTime);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            // creates invoice popup
            StringBuilder invoiceText = new StringBuilder();
            String date = "Date: " + orderTime;
            String amount = "Number of unique items: " + itemLimit;
            String legend = "Item # / ID / Title / Price / Qty / Disc % / Subtotal";
            invoiceText.append(date).append("\n");
            invoiceText.append(amount).append("\n");
            invoiceText.append(legend).append("\n\n");

            int counter = 1;
            for (String s : orderArray) {
                String number = counter + ". ";
                invoiceText.append(number).append(s).append("\n");
                counter++;
            }
            invoiceText.append("\n\n");

            double taxTotal = currentTotal * taxRate;
            String subtotal = "Order subtotal: $" + currentTotal;
            String tax = "Tax rate: " + taxRate + "%";
            String taxAmount = "Tax amount: $" + df.format(taxTotal);
            invoiceText.append(subtotal).append("\n");
            invoiceText.append(tax).append("\n");
            invoiceText.append(taxAmount).append("\n");

            double finalTotal = taxTotal + currentTotal;
            String orderTotal = "Order total: $" + df.format(finalTotal);
            invoiceText.append(orderTotal).append("\n\n");
            invoiceText.append("Thanks for shopping at Nile Dot Com!");

            JOptionPane.showMessageDialog(null, invoiceText.toString(), "Nile Dot Com - Final Invoice", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (e.getSource() == newOrder) {
            // reset all running variable
            itemNumber = 1;
            itemLimit = 0;
            currentTotal = 0.00;
            // clears list of all stored items
            orderArray.clear();

            // return gui to default appearance;
            processOrder.setEnabled(true);
            confirmOrder.setEnabled(false);
            viewOrder.setEnabled(false);
            finishOrder.setEnabled(false);

            IDTag.setText("Enter item ID for Item #" + itemNumber + ":");
            quantityTag.setText("Enter quantity for Item #" + itemNumber + ":");
            infoTag.setText("Item #" + itemNumber + " info:");
            subtotalTag.setText("Order subtotal for " + (itemNumber - 1) + " item(s)");

            amountText.setText("");
            amountText.setEnabled(true);
            amountText.setEditable(true);
            infoText.setText("");
            IDText.setEnabled(true);
            IDText.setEditable(true);
            subtotalText.setText("");
            quantityText.setEnabled(true);
            quantityText.setEditable(true);
        }
    }

    //main():  application entry point
    public static void main(String[] args) {
        NileDotCom gui = new NileDotCom();
    }

    public String[] inventorySearch(String id, int digit) throws IOException {
        FileReader fr = new FileReader("inventory.txt");
        BufferedReader br = new BufferedReader(fr);

        // read file line by line
        String line;
        String[] itemInfo;

        while ((line = br.readLine()) != null) {
            itemInfo = line.split("\\s*,\\s*");
            // checks if id of line matches
            if (id.equals(itemInfo[0]))
                return itemInfo;
        }

        return null;
    }

    public void transactionWrite(String ID, String time) throws IOException {
        FileWriter fw = new FileWriter("transactions.txt", true);

        for (String s : orderArray) {
            fw.write(ID + ", " + s + ", " + time + "\n");
            System.out.println(ID + ", " + s + ", " + time + "\n");
        }
        fw.flush();
        fw.close();
    }

    public double[] discountPrice(String price, String quantity) {
        double[] temp = new double[2];

        double newPrice = Double.parseDouble(price);
        int amount = Integer.parseInt(quantity);
        if (amount < 5) {
            temp[0] = newPrice * amount;
            temp[1] = 0;
        }
        else if (amount < 10) {
            temp[0] = newPrice * 0.9 * amount;
            temp[1] = 10;
        }
        else if (amount < 15) {
            temp[0] = newPrice * 0.85 * amount;
            temp[1] = 15;
        }
        else {
            temp[0] = newPrice * 0.8 * amount;
            temp[1] = 20;
        }

        return temp;
    }
}
