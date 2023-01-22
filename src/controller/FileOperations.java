package controller;

import model.InvoiceHeader;
import model.InvoiceLine;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FileOperations {

    public static ArrayList<InvoiceHeader> readFile() {
        ArrayList<InvoiceHeader> invoices = new ArrayList<>();
        String invoiceHeaderFilePath = "resources/InvoiceHeader.csv";
        String invoiceLineFilePath = "resources/InvoiceLine.csv";


        String line;

        try(BufferedReader reader = new BufferedReader(new FileReader(invoiceHeaderFilePath))){

            while ((line = reader.readLine()) != null) {
                InvoiceHeader invoice;
                String[] row = line.split(",");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = dateFormat.parse(row[1]);
                invoice = new InvoiceHeader(Integer.parseInt(row[0]), date, row[2]);
                ArrayList<InvoiceLine> invoiceItems = getItemsForInvoice(invoice, invoiceLineFilePath);
                invoice.setInvoiceLines(invoiceItems);
                invoices.add(invoice);


            }
        } catch (FileNotFoundException ex) {
            System.out.println("**************** File is not found with this path **********************");
            ex.printStackTrace();
        } catch (IOException | ParseException ex) {
            System.out.println("******************* IO/Parse Exception ***********************");
            ex.printStackTrace();
        }

        return invoices;
    }

    public static void writeFile(ArrayList<InvoiceHeader> invoices) {

        String invoiceHeaderFilePath = "resources/InvoiceHeader.csv";
        String invoiceLineFilePath = "resources/InvoiceLine.csv";
        PrintWriter headerWriter;
        PrintWriter lineWriter;

        try {
            headerWriter = getFileWriter(invoiceHeaderFilePath);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            for (InvoiceHeader invoice : invoices) {
                String formattedDate = dateFormat.format(invoice.getDate());
                headerWriter.println(invoice.getInvoiceNum() + "," + formattedDate + "," + invoice.getCustomerName());
            }

            headerWriter.flush();

            lineWriter = getFileWriter(invoiceLineFilePath);
            ArrayList<InvoiceLine> invoiceItems;

            for (InvoiceHeader invoice : invoices) {
                invoiceItems = invoice.getInvoiceLines();
                for (InvoiceLine item : invoiceItems) {
                    lineWriter.println(invoice.getInvoiceNum() + "," + item.getItemName() + ","
                            + item.getItemPrice() + "," + item.getCount());

                }

            }

            lineWriter.flush();

        } catch (IOException ex) {
            System.out.println("******************* IO Exception ***********************");
            ex.printStackTrace();
        }

    }

    // Helper Method to organize the code in a good way ( Retrieve items for each invoice)
    private static ArrayList<InvoiceLine> getItemsForInvoice(InvoiceHeader invoice, String filePath) {
        ArrayList<InvoiceLine> invoiceItems = new ArrayList<>();

        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while ((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                int num = Integer.parseInt(row[0]);
                if (num == invoice.getInvoiceNum()) {
                    invoiceItems.add(new InvoiceLine(invoice, row[1],
                            Double.parseDouble(row[2]), Integer.parseInt(row[3])));
                }

            }
        } catch (FileNotFoundException ex) {
            System.out.println("**************** File is not found with this path **********************");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("******************* IO Exception ***********************");
            ex.printStackTrace();
        }

        return invoiceItems;
    }

    // Helper Method to organize the code in a good way ( Retrieve writer for a specific file path)
    private static PrintWriter getFileWriter(String filePath) throws IOException {
        return new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
    }




}



