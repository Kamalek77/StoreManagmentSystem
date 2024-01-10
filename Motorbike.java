public class Motorbike extends Vehicle {
    private String bikeType;
    private int engineSize;

    public Motorbike(String brand, String model, int year, double price, int quantity, String bikeType, int engineSize, String color) {
        super(brand, "Motorbike", model, year, price, quantity,color);
        this.bikeType = bikeType;
        this.engineSize = engineSize;
    }
    public Motorbike(){}

    public String getBikeType() {
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }
     @Override
    public String toString() {
        return "motorbike{" +
                "brand='" + getBrand() + "'," +
                "vehicleType='" + getVehicleType() + "'," +
                "color="+getColor()+","+
                "model='" + getModel() + "'," +
                "year=" + getYear() + "," +
                "price=" + getPrice() + "," +
                "quantity=" + getQuantity() + "," +
                "bikeType='" + bikeType + "'," +
                "engineSize=" + engineSize +
                '}';
    }
}
