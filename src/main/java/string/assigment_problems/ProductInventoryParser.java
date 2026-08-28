package string.assigment_problems;

public class ProductInventoryParser {

    public static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");

        if (fields.length != 3) {
            System.out.println("Invalid Record");
        } else {
            String productName = fields[0];
            String sku = fields[1];
            String quantity = fields[2];
            System.out.println("Product: " + productName + " | SKU: " + sku + " | Qty: " + quantity);
        }
    }

    public static void main(String[] args) {
        String csvLine = "Wireless Mouse,WM-2201,150";
        parseInventoryRecord(csvLine);
    }
}
