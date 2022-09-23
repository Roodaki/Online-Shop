public class Product {
    private String name;
    private double price;
    private double discount;

    public Product(String name, double price) {
        setName(name);
        setPrice(price);
        setDiscount(0); // Default
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price - price * discount / 100;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }

}
