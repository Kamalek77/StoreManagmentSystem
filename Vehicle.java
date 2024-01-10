public abstract class Vehicle
{
    private String brand; 
    private String vehicleType;
    private String model;
    private String color;
    private int year;
    private double price;
    private int quantity;

    public Vehicle(String brand, String vehicleType, String model, int year, double price, int quantity, String color) {
        this.brand = brand;
        this.vehicleType = vehicleType;
        this.model = model;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.color=color;
    }
    public Vehicle() {
       
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "Vehicle{\n" +
                "brand='" + brand + "',\n" +
                "vehicleType='" + vehicleType + "',\n" +
                "model='" + model + "',\n" +
                "year=" + year + ",\n" +
                "price=" + price + ",\n" +
                "quantity=" + quantity + "\n" +
                '}';
    }
}