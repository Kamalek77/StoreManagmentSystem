public class Car extends Vehicle {
    
    private String Cartype;
    private String EnergyType; 

    public Car(String brand, String model, int year, double price, int quantity, String Cartype, String EnergyType,String Color) 
    {
        super(brand, "Car", model, year, price, quantity,Color);
        this.Cartype=Cartype;
        this.EnergyType = EnergyType;
    }
    public Car() {
       
    }

    public String getCartype() {
        return Cartype;
    }

    public void setCartype(String cartype) {
        Cartype = cartype;
    }

    public String getFuelType() {
        return EnergyType;
    }

    public void setFuelType(String EnergyType) {
        this.EnergyType = EnergyType;
    }
    
    @Override
     public String toString() {
        return "car{" +
                "brand='" + getBrand() + "'," +
                "vehicleType='" + getVehicleType() + "'," +
                "color="+getColor()+","+
                "model='" + getModel() + "'," +
                "year=" + getYear() + "," +
                "price=" + getPrice() + "," +
                "quantity=" + getQuantity() + "," +
                "Cartype=" + Cartype + "'," +
                "EnergyType=" + EnergyType  +
                '}';
     }
}