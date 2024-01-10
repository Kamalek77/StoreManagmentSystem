import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class Mongo implements dataHandeling {
    private final com.mongodb.client.MongoClient mongoClient;
    private final MongoDatabase database;
    private final MongoCollection<Document> collection;

    public Mongo() {
        // Establishing connection to MongoDB
        String connectionString = "mongodb://localhost:27017";
        MongoClientURI uri = new MongoClientURI(connectionString);
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase("Vehicle");
        collection = database.getCollection("bson");
    }
    
    @Override
    public void WriteFromArrayList(List<Vehicle> vehicles) {
        List<Document> documents = new ArrayList<>();

        for (Vehicle vehicle : vehicles) {
            Document document = new Document("brand", vehicle.getBrand())
                    .append("vehicleType", vehicle.getVehicleType())
                    .append("model", vehicle.getModel())
                    .append("color", vehicle.getColor())
                    .append("year", vehicle.getYear())
                    .append("price", vehicle.getPrice())
                    .append("quantity", vehicle.getQuantity());

            if (vehicle instanceof Car) {
                document.append("carType", ((Car) vehicle).getCartype())
                        .append("energyType", ((Car) vehicle).getFuelType());
            } else if (vehicle instanceof Motorbike) {
                document.append("bikeType", ((Motorbike) vehicle).getBikeType())
                        .append("engineSize", ((Motorbike) vehicle).getEngineSize());
            } else if (vehicle instanceof Truck) {
                document.append("payloadCapacity", ((Truck) vehicle).getPayloadCapacity())
                        .append("engineType", ((Truck) vehicle).getEngineType());
            }

            documents.add(document);
        }

        collection.insertMany(documents);
    }
    @Override
    public ArrayList<Vehicle> Write2ArrayList() {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        for (Document document : collection.find()) {
            String vehicleType = document.getString("vehicleType");

            if (vehicleType.equals("Car")) {
                Car car = new Car();
                car.setBrand(document.getString("brand"));
                car.setVehicleType(vehicleType);
                car.setModel(document.getString("model"));
                car.setColor(document.getString("color"));
                car.setYear(document.getInteger("year"));
                car.setPrice(document.getDouble("price"));
                car.setQuantity(document.getInteger("quantity"));
                car.setCartype(document.getString("carType"));
                car.setFuelType(document.getString("energyType"));
                vehicles.add(car);
            } else if (vehicleType.equals("Motorbike")) {
                Motorbike motorbike = new Motorbike();
                motorbike.setBrand(document.getString("brand"));
                motorbike.setVehicleType(vehicleType);
                motorbike.setModel(document.getString("model"));
                motorbike.setColor(document.getString("color"));
                motorbike.setYear(document.getInteger("year"));
                motorbike.setPrice(document.getDouble("price"));
                motorbike.setQuantity(document.getInteger("quantity"));
                motorbike.setBikeType(document.getString("bikeType"));
                motorbike.setEngineSize(document.getInteger("engineSize"));
                vehicles.add(motorbike);
            } else if (vehicleType.equals("Truck")) {
                Truck truck = new Truck();
                truck.setBrand(document.getString("brand"));
                truck.setVehicleType(vehicleType);
                truck.setModel(document.getString("model"));
                truck.setColor(document.getString("color"));
                truck.setYear(document.getInteger("year"));
                truck.setPrice(document.getDouble("price"));
                truck.setQuantity(document.getInteger("quantity"));
                truck.setPayloadCapacity(document.getInteger("payloadCapacity"));
                truck.setEngineType(document.getString("engineType"));
                vehicles.add(truck);
            }
        }

        return vehicles;
    }

    public void closeConnection() {
        mongoClient.close();
    }
    public void emptyDatabase() {
        database.drop();
    }
}