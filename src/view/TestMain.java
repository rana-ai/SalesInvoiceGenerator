package view;

import controller.FileOperations;
import model.InvoiceHeader;
import model.InvoiceLine;


import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class TestMain extends FileOperations {

    //read invoice
    public static void main(String[] args) throws FileNotFoundException {
        Scanner inputStream = new Scanner("resources/InvoiceHeader.csv");
        ArrayList<InvoiceHeader> invoices = readFile();
        for (InvoiceHeader invoice : invoices) {
            System.out.println(invoice.toString());
        }

        //write invoice
        writeFile(invoices);
        int invoicesCount = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("How are you , Welcome to our Sales Invoice Generator\n" +
                "Do you want to create new invoice?\n" + "" +
                "For (Yes), Press (1)\n" +
                "For (No), Press (2)");

        int number;
        while (scanner.hasNextLine()) {
            number = scanner.nextInt();
            if (number != 1 && number != 2) {
                System.out.println("Incorrect Number , Please insert the correct number\n" +
                        "Do you want to create new invoice?\n" + "" +
                        "For (Yes), Press (1)\n" +
                        "For (No), Press (2)");
            } else if (number == 2) {
                System.out.println("Thank You for using our application");
                break;
            } else {
                System.out.print("Ok, You want to create invoice.\n");
                System.out.print("Enter Customer Name :");
                String customerName = scanner.next();
                System.out.print("Enter Date :");
                String dateString = scanner.next();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date date = null;
                try {
                    date = sdf.parse(dateString);
                } catch (ParseException e) {
                    System.out.println("*************** Incorrect Date Format ************************");
                    System.out.println("Date need to be in the format : dd-MM-yyyy");
                    e.printStackTrace();
                    break;
                }
                invoicesCount++;
                InvoiceHeader header = new InvoiceHeader(invoicesCount, date, customerName);
                ArrayList<InvoiceLine> lines = new ArrayList<>();
                double invoicePrice = 0.0;
                while (scanner.hasNextLine()) {
                    System.out.print("Ok, Do you want to add item.\n");
                    System.out.print("For (Yes) , Press (1)\n");
                    System.out.println("For (No) , Press (2)");
                    number = scanner.nextInt();
                    if (number != 1 && number != 2){
                        System.out.println("Incorrect Number , Please insert the correct number");
                    }else if (number == 2){
                        System.out.print("Ok , You don't want to add other items\n" +
                                "Your Invoice with Name : " + customerName +
                                "\nCreated At : " + dateString +
                                "\nWith Total Price : " + invoicePrice);
                        break;
                    }else {
                        System.out.print("Ok, Enter Item Name :");
                        String itemName = scanner.next();
                        System.out.print("Enter Item Price :");
                        double itemPrice = scanner.nextDouble();
                        System.out.print("Enter Item Quantity :");
                        int itemQuantity = scanner.nextInt();
                        InvoiceLine line = new InvoiceLine(header, itemName , itemPrice , itemQuantity);
                        lines.add(line);
                        invoicePrice = invoicePrice + (itemPrice * itemQuantity);
                    }

                }

                header.setInvoiceLines(lines);
                ArrayList<InvoiceHeader> headers = new ArrayList<>();
                headers.add(header);
                writeFile(headers);
                break;
            }
        }





        }

    }



