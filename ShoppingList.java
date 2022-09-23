import java.util.ArrayList;

public class ShoppingList {
    public static int numCreatedIDs;

    private int ID;
    private int purchasedProductsCount;
    private double totalPrice;
    private ShippingStatus shippingStatus;
    private ArrayList<Product> purchasedProductsArrayList;

    static {
        numCreatedIDs = 1;
    }

    public ShoppingList(ArrayList<Product> enteredPurchasedProductsArrayList) {
        setPurchasedProductsArrayList(enteredPurchasedProductsArrayList);
        setID(numCreatedIDs++);
        setPurchasedProductsCount(purchasedProductsArrayList.size());
        setTotalPrice(calculateTotalPrice());
        setShippingStatus(ShippingStatus.PROCESSING); // Default = PROCESSING (Waiting fot Seller to Set)
        setPurchasedProductsArrayList(enteredPurchasedProductsArrayList);
    }

    public String getPurchasedProductsData() {

        String allPurchasedProductsData = "";

        int counterProducts = 1;
        for (Product product : getPurchasedProductsArrayList()) {
            allPurchasedProductsData += counterProducts + ".\nProduct Name: " + product.getName() + "\nProduct Price: "
                    + product.getPrice() + "\nProduct Discount: " + product.getDiscount() + "\n";
            counterProducts++;
        }

        return allPurchasedProductsData;
    }

    private double calculateTotalPrice() {
        double totalPrice = 0;

        for (Product product : getPurchasedProductsArrayList())
            totalPrice += product.getPrice();

        return totalPrice;
    }

    public void setID(int iD) {
        ID = iD;
    }

    public int getID() {
        return ID;
    }

    public void setPurchasedProductsCount(int purchasedProductsCount) {
        this.purchasedProductsCount = purchasedProductsCount;
    }

    public int getPurchasedProductsCount() {
        return purchasedProductsCount;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setPurchasedProductsArrayList(ArrayList<Product> purchasedProductsArrayList) {
        this.purchasedProductsArrayList = purchasedProductsArrayList;
    }

    public ArrayList<Product> getPurchasedProductsArrayList() {
        return purchasedProductsArrayList;
    }

}