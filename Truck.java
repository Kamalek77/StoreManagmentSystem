public class Truck extends Vehicle {
    private String engineType;
    private int payloadCapacity; 
    
    public Truck(String brand,  String model, int year, double price, int quantity, int payloadCapacity, String engineType,String color) {
        super(brand, "Truck", model, year, price, quantity,color);
        this.payloadCapacity = payloadCapacity;
        this.engineType = engineType;
    }
    public Truck(){}

    public int getPayloadCapacity() {
        return payloadCapacity;
    }

    public void setPayloadCapacity(int payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
     public String toString() 
     {
            return "Truck{  " +
                "brand='" + getBrand() + "'," +
                "vehicleType='" + getVehicleType() + "'," +
                "color="+getColor()+","+
                "model='" + getModel() + "'," +
                "year=" + getYear() + "," +
                "price=" + getPrice() + "," +
                "quantity=" + getQuantity() + "," +
                "Capacity ='" + payloadCapacity + "'," +
                "engineType=" + engineType +
                '}';
     }
}
