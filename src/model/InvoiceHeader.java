package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {

    //members
    private int invoiceNum;
    private Date date;
    private String customerName;
    private ArrayList<InvoiceLine> invoiceLines;

    //Constructors
    public InvoiceHeader(int invoiceNum, Date date, String customerName) {
        this.invoiceNum = invoiceNum;
        this.date = date;
        this.customerName = customerName;
    }

    //setter and getter Methods
    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    //toString method
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String invoiceDate = dateFormat.format(date);
        String lines = "";
        for (InvoiceLine line : invoiceLines){
            lines = lines + line;

        }
        return "InvoiceNum" + invoiceNum + "\n" + '{' + "\n" + "InvoiceDate "
                + '(' + invoiceDate + ')' + ", " + customerName + "\n" + lines + '}';


    }
}
