package model;


public class InvoiceLine {
    //members
    private InvoiceHeader invoice;
    private String itemName;
    private double itemPrice;
    private int count;
    private int total;

    //Constructor
    public InvoiceLine(InvoiceHeader invoice, String itemName, double itemPrice, int count) {
        this.invoice = invoice;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;

    }


    //setter and getter methods
    public InvoiceHeader getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceHeader invoice) {
        this.invoice = invoice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



    @Override
    public String toString() {
        return itemName + ", " + itemPrice + " LE, " + count +"\n";
    }

}
