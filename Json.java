import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
 
public class Json implements dataHandeling{
    
      private static final String filePath="Data.Json";
       
     @Override
    public void WriteFromArrayList(List<Vehicle> vehicles) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), vehicles);
         System.out.println("Succesfully saved");
    } catch (IOException e) {
        System.out.println("An error occurred while writing the Vehicles to JSON file: " + e.getMessage());
        e.printStackTrace();
    }
}
  ////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public ArrayList<Vehicle> Write2ArrayList(){
    ObjectMapper objectMapper = new ObjectMapper();
    ArrayList<Vehicle> vehicles=new ArrayList<>();
    File jsonFile = new File(filePath);
try {
            // Read the JSON data from the file into a JsonNode
            JsonNode rootNode = objectMapper.readTree(jsonFile);
            
            // Check if the root node is an array
            if (rootNode.isArray()) {
                // Iterate through each JSON node in the array
                for (JsonNode vehicleNode : rootNode) {
                    // Extract the vehicleType attribute from the JSON node
                    String vehicleType = vehicleNode.get("vehicleType").asText();
                    
                    // Dynamically obtain the Class object for the specified vehicleType
                    Class<?> vehicleClass = Class.forName(vehicleType);
                    
                    // Deserialize the JSON node into a Vehicle object of the corresponding type
                    Vehicle vehicle = objectMapper.readValue(vehicleNode.toString(), (Class<Vehicle>) vehicleClass);
                    
                    // Add the deserialized Vehicle object to the ArrayList
                    vehicles.add(vehicle);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return vehicles;
    }
}
