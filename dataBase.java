import java.io.IOException;
import java.util.*;
import java.time.Year;
import java.util.stream.Collectors;

public class dataBase 
{ 
    private final int choice; //t1-JackSon(.JSON)\n\t2-MongoDb
    private static ArrayList<Vehicle> vehiclesList;
    Json json=new Json();
    int currentYear = Year.now().getValue();
    Mongo mongo=new Mongo();
 
    public dataBase(int choice)
    {
        this.choice=choice;
        System.out.println("Loading...");
       if(choice==1)
        {
           vehiclesList=json.Write2ArrayList();
        }
       else if(choice==2)
       {
           vehiclesList=mongo.Write2ArrayList();
       }
    }
           
   
        
        public void AddNewVehicule() 
        {
            String type = "";
            Vehicle toBeAdded = null;
            Scanner scan=new Scanner(System.in);
            System.out.print("Enter Brand : ");
            String Brand = scan.nextLine();
            Brand = Brand.toLowerCase();
            System.out.print("Enter model : ");
            String model = scan.nextLine();
            model=model.toLowerCase();
            System.out.print("Enter Color : ");
            String Color = scan.nextLine();
            Color=Color.toLowerCase();
            //////////////////////////////////////////////////////////
            
            int year = 0;
            boolean isValidInput = false;
            while (!isValidInput || (year<1900 || year>(currentYear+1)))
            {
                    System.out.print("Enter year of release : ");
                    String input = scan.nextLine();
                try {
                     year = Integer.parseInt(input);
                      isValidInput = true;
                     } catch (NumberFormatException e) {
                   System.out.println("Invalid input: please enter a valid number.");
                    }
             }
            
            ///////////////////////////////////////////
              isValidInput = false;
              double price=0.00;
                while (!isValidInput || price<=0)
            {
                  System.out.print("Enter a price in U$D number: ");
                    String input = scan.nextLine();
                try {
                     price = Double.parseDouble(input);
                      isValidInput = true;
                     } catch (NumberFormatException e) {
                   System.out.println("Invalid input: please enter a valid price.");
                    }
             }
            ////////////////////////////////////////////
     
            int qty = 0;
            isValidInput = false;
            while (!isValidInput || qty<=0)
            {
                  System.out.print("Enter the qty: ");
                    String input = scan.nextLine();
                try {
                     qty = Integer.parseInt(input);
                      isValidInput = true;
                     } catch (NumberFormatException e) {
                   System.out.println("Invalid input: please enter a valid number.");
                    }
             }
            ////////////////////////////////////////////////////////////////////////////////////////
           
           while (!type.equals("1") && !type.equals("2") && !type.equals("3")) 
           {
                System.out.print("\nChoose Vehicle Type: \n1-Car\n2-Truck\n3-MotorBike\nOption: ");
                type = scan.nextLine();
                if (!type.equals("1") && !type.equals("2") && !type.equals("3")) 
                {
                System.out.println("Invalid option. Please choose 1, 2, or 3.");
                }
           }
           ////////////////////////////////////////////////////////////////////////////////////////////////
            
           
            switch(type)
            {
                case"1"://car
                {
                    System.out.print("Enter car type : ");
                    String cartype=scan.nextLine();
                    cartype=cartype.toLowerCase();
                    
                    System.out.print("Enter Energy type : ");
                    String EnergyType=scan.nextLine(); 
                    EnergyType=EnergyType.toLowerCase();
                    toBeAdded = new Car(Brand,model,year,price,qty,cartype,EnergyType,Color);
                    System.out.println(toBeAdded.toString());
                    break;
                }
                
                case"2"://truck
                {
                    int capacity = 0;

                        while (capacity < 100) 
                       {
                                 System.out.print("Enter load Capacity (in kg, must be at least 100): ");
                                     String capacityStr = scan.nextLine();
    
                            try {
                                capacity = Integer.parseInt(capacityStr);
                                } catch (NumberFormatException e) 
                                        {
                                   System.out.println("Invalid input. Please enter a valid integer.");
                                        continue;
                                        }
    
                                                  if (capacity < 100) {
                                                                            System.out.println("Capacity must be at least 100.");
                                                                       }
                        }
                    
                    
                    
                    System.out.print("Enter Engine type : ");
                    String EngineType=scan.nextLine(); 
                    EngineType=EngineType.toLowerCase();
                    
                    toBeAdded = new Truck(Brand,model,year,price,qty,capacity,EngineType,Color);
                    System.out.println(toBeAdded.toString());
                    break;
                }
                case"3"://motorbike
                 {
                        int Size = 0;

                        while (Size <= 0) 
                       {
                                 System.out.print("Enter Engine Size: ");
                                     String capacityStr = scan.nextLine();
    
                            try {
                                Size = Integer.parseInt(capacityStr);
                                } catch (NumberFormatException e) 
                                        {
                                   System.out.println("Invalid input. Please enter a valid integer.");
                                        continue;
                                        }
    
                                                  if (Size <= 0) {
                                                                             System.out.println("Invalid input. Please enter a valid Size.");
                                                  }
                        }
                    System.out.print("Enter BIketype : ");
                    String biketype=scan.nextLine(); 
                    biketype=biketype.toLowerCase();
                    
                    toBeAdded = new Motorbike(Brand,model,year,price,qty,biketype,Size,Color);
                    System.out.println(toBeAdded.toString());
                    break;     
                 }
                default:
                {
                    break;
                }
            }
           vehiclesList.add(toBeAdded);
    }
        
        public void saveModifiction() throws IOException
        {
            if (!vehiclesList.isEmpty())
            {
            switch(choice)
            {
                case 1://Json
                {
                                json.WriteFromArrayList(vehiclesList);                
                                break;
                }
                case 2://mongo
                {
                    mongo.emptyDatabase();
                    mongo.WriteFromArrayList(vehiclesList);
                    mongo.closeConnection();
                    break;
                }
                default:
                {
                    System.out.println("Error occured while saveModifiction()");
                }
                              
            }
            }
            
        }
        
        private boolean isWeakMatch(String str1, String str2) {
             if (str1.length() < 3 || str2.length() < 3) {
                 return false;
            }
        for (int i = 0; i <= str1.length() - 3; i++) {
        String sub = str1.substring(i, i + 3);
        if (str2.contains(sub)) {
            return true;
        }
        }
         return false;
            }

        
        public Vehicle Search4 (String Searching,boolean returnValue)
        {
            int count=0;
            if (vehiclesList.isEmpty())
            {
                System.out.println("The dataBase is still empty");
                return null;
            }
            HashSet<String> wordsSet = new HashSet<>();
            String searching=Searching.toLowerCase();
            String[] words = searching.split("\\s+");
            for (String word : words)
                {
                if (!word.isEmpty()) 
                {
                    wordsSet.add(word);
                }       
                }
            List <Vehicle> search=null;
            ArrayList<Vehicle>Matched=new ArrayList<>();
            for (String Searching4 : wordsSet)
            {
            search= vehiclesList.stream()
                .filter(    v -> 
                   (v instanceof Car && ((Car) v).getCartype().equalsIgnoreCase(Searching4))
                || (v instanceof Car && ((Car) v).getFuelType().equalsIgnoreCase(Searching4))
                || (v instanceof Truck && ((Truck) v).getEngineType().equalsIgnoreCase(Searching4))
                || (v instanceof Truck && Integer.toString(((Truck) v).getPayloadCapacity()).equalsIgnoreCase(Searching4))          
                || (v instanceof Motorbike && ((Motorbike) v).getBikeType().equalsIgnoreCase(Searching4))
                || (v instanceof Motorbike && Integer.toString(((Motorbike) v).getEngineSize()).equalsIgnoreCase(Searching4))
                || v.getBrand().equalsIgnoreCase(Searching4)
                || v.getVehicleType().equalsIgnoreCase(Searching4)
                || v.getModel().equalsIgnoreCase(Searching4)
                || v.getColor().equalsIgnoreCase(Searching4)
                || Integer.toString(v.getYear()).equalsIgnoreCase(Searching4)      
                || Double.toString(v.getPrice()).equalsIgnoreCase(Searching4))
                  .collect(Collectors.toList());
            }
            System.out.println("Perfect match : ");
   
               if (!search.isEmpty()) 
               {
                 for (Vehicle result : search)
                 {
                         count++;
                         System.out.println(count+"- "+result.toString());
                         Matched.add(result);
                 }
               }    else {  System.out.println("Not found : ");}
               search=null;
               System.out.println ( "Close match : ");
               
               for (String searching4 : wordsSet) {
         search = vehiclesList.stream()
        .       filter(v ->
            (v instanceof Car && ((Car) v).getCartype().toLowerCase().contains(searching4.toLowerCase()))
            || (v instanceof Car && ((Car) v).getFuelType().toLowerCase().contains(searching4.toLowerCase()))
            || (v instanceof Truck && ((Truck) v).getEngineType().toLowerCase().contains(searching4.toLowerCase()))
            || (v instanceof Truck && Integer.toString(((Truck) v).getPayloadCapacity()).contains(searching4.toLowerCase()))
            || (v instanceof Motorbike && ((Motorbike) v).getBikeType().toLowerCase().contains(searching4.toLowerCase()))
            || (v instanceof Motorbike && Integer.toString(((Motorbike) v).getEngineSize()).contains(searching4.toLowerCase()))
            || v.getBrand().toLowerCase().contains(searching4.toLowerCase())
            || v.getVehicleType().toLowerCase().contains(searching4.toLowerCase())
            || v.getModel().toLowerCase().contains(searching4.toLowerCase())
            || v.getColor().toLowerCase().contains(searching4.toLowerCase())
            || Integer.toString(v.getYear()).contains(searching4.toLowerCase())
            || Double.toString(v.getPrice()).contains(searching4.toLowerCase()))
                  .filter(filteredVehicle -> !Matched.contains(filteredVehicle)) 
        .collect(Collectors.toList());
}

               if (!search.isEmpty()) 
               {
                 for (Vehicle result : search)
                 {
                         count++;
                         System.out.println(count+"- "+result.toString());
                         Matched.add(result);
                 }
               }    else {  System.out.println("Not found : ");}
               
               System.out.println("weak match : ");
               
               for (String searching4 : wordsSet) {
    search = vehiclesList.stream()
        .filter(v ->
               (v instanceof Car && isWeakMatch(((Car) v).getCartype().toLowerCase(),(searching4.toLowerCase())))      
            || (v instanceof Car && isWeakMatch(((Car) v).getFuelType().toLowerCase(),(searching4.toLowerCase())))
            || (v instanceof Truck && isWeakMatch(((Truck) v).getEngineType().toLowerCase(),(searching4.toLowerCase())))
            || (v instanceof Truck && isWeakMatch(Integer.toString(((Truck) v).getPayloadCapacity()),(searching4.toLowerCase())))
            || (v instanceof Motorbike && ((Motorbike) v).getBikeType().toLowerCase().contains(searching4.toLowerCase()))
            || (v instanceof Motorbike && Integer.toString(((Motorbike) v).getEngineSize()).contains(searching4.toLowerCase()))
            || isWeakMatch(v.getBrand().toLowerCase(), searching4.toLowerCase())
            || isWeakMatch(v.getModel().toLowerCase(), searching4.toLowerCase())
            || v.getVehicleType().toLowerCase().contains(searching4.toLowerCase())
            || isWeakMatch(v.getColor().toLowerCase(), searching4.toLowerCase())
            || isWeakMatch(Integer.toString(v.getYear()), searching4.toLowerCase())
            || isWeakMatch(Double.toString(v.getPrice()).toLowerCase(), searching4.toLowerCase())   
            )   .filter(filteredVehicle -> !Matched.contains(filteredVehicle)) 
            .collect(Collectors.toList());
            }
               
               if (!search.isEmpty()) 
               {
                 for (Vehicle result : search)
                 {
                         count++;
                         System.out.println(count+"- "+result.toString());
                         Matched.add(result);
                         
                 }
               }    else {  System.out.println("Not found : ");}
               
               
               if (returnValue)
               {
                   if (count==0  || Matched.isEmpty())
                   {
                       System.out.println("No result to be returned");
                       return null;
                   }
                   System.out.println("Enter the option Number : ");
                   Scanner scan=new Scanner(System.in);
                   boolean enteredValid=false;
                   int Choice =-1;
                   String choiceString;
                   while(!enteredValid)
                   {
                         choiceString=scan.nextLine();
                        try {
                        Choice = Integer.parseInt(choiceString);
                        } catch (NumberFormatException e) 
                        {
                            System.out.println("Invalid choice. Please enter a valid integer.");
                            enteredValid=false;
                        }
                        if ( Choice>count || Choice<=0)
                        {
                            enteredValid=false;
                            System.out.println("Invalid choice. Please enter a valid option number."+count);
                        }
                        else
                        {
                            enteredValid=true;
                            
                        }
                    }
                   
                   return Matched.get(Choice-1);
               }
               
        return null;}
        
        public void DeleteVehicles(Vehicle v)
        {
            if (v==null)
            {
                System.out.println("Can't be edited!!");
                return;
            }
            int ind=vehiclesList.indexOf(v);
            
            Vehicle vDeleted=vehiclesList.remove(ind);
            System.out.println("Succssefully deleted "+vDeleted.toString());
        }
        
        public void EditVehicles(Vehicle v)
        {
            if (v==null)
            {
                System.out.println("Can't be edited!!");
                return;
            }
            int ind=vehiclesList.indexOf(v);
            Scanner scan=new Scanner(System.in);
            String option="0";
            Vehicle toBeAdded=v;
            while(true)
            {
                System.out.print("What would you like to edit :\n\tA-Exit Without Saving\n\t0-Exit editing\n\t1-Brand \n\t2-model\n\t3-color\n\t4-year of release\n\t"
                        + "5-price\n\t6-quantity\n\t");
                if (toBeAdded instanceof Car)
                {
                    System.out.print("7-Car Type\n\t8-EnergyType\n");
                }
                else if (toBeAdded instanceof Truck)
                {
                    System.out.print("7-EngineType \n\t8-PayloadCapacity\n");
                }
                else if (toBeAdded instanceof Motorbike)
                {
                    System.out.print("7-Bike Type\n\t8-EngineSize\n");
                }
                
                option= scan.nextLine();
                  
                switch (option)
                {
                    case "0":
                            {
                               vehiclesList.set(ind, toBeAdded);
                               System.out.println("Succesfully edited"); 
                               return;
                            }
                    case "A":
                              {
                                      return;
                               }
                    case "1":
                    {
                        System.out.print("New Brand : ");
                        toBeAdded.setBrand(scan.nextLine());
                        break;
                    }
                    case "2":
                    {
                        System.out.print("New model : ");
                        toBeAdded.setModel(scan.nextLine());
                        break;
                    }
                    case "3":
                    {
                        System.out.print("New color : ");
                        toBeAdded.setColor(scan.nextLine());
                        break;
                    }
                    case "4":
                    {
                       
                            int year = 0;
                            boolean isValidInput = false;
                             while (!isValidInput || (year<1900 || year>(currentYear+1)))
                                {
                                     System.out.print("Enter New year of release : ");
                                     String input = scan.nextLine();
                                    try {
                                         year = Integer.parseInt(input);
                                         isValidInput = true;
                                        } catch (NumberFormatException e) {
                                        System.out.println("Invalid input: please enter a valid number.");
                                                                }
                                 }
                     toBeAdded.setYear(year);
                      break;
                    }
                    case "5":
                    {
                                    boolean isValidInput = false;
                                    double price=0.00;
                                    while (!isValidInput || price<=0)
                                    {
                                         System.out.print("Enter new price in U$D number: ");
                                         String input = scan.nextLine();
                                            try {
                                                  price = Double.parseDouble(input);
                                                     isValidInput = true;
                                                } catch (NumberFormatException e) {
                                                  System.out.println("Invalid input: please enter a valid price.");
                                                                                    }
                                    }
                           toBeAdded.setPrice(price);
                           break;
                    }
                    case "6":
                    {
                                        int qty = 0;
                                       boolean  isValidInput = false;
                                        while (!isValidInput || qty<=0)
                                         {
                                              System.out.print("Enter the new qty: ");
                                              String input = scan.nextLine();
                                            try {
                                                qty = Integer.parseInt(input);
                                                isValidInput = true;
                                                } catch (NumberFormatException e) {
                                                               System.out.println("Invalid input: please enter a valid number.");
                                                                                  }
                                          }
                       toBeAdded.setQuantity(qty);
                       break;
                    }
                    case"7":
                    {
                        if ((toBeAdded.getVehicleType()).equalsIgnoreCase("car")|| toBeAdded instanceof Car)
                        {
                            System.out.println("Enter new Cartype   :");
                            ((Car) toBeAdded).setCartype(scan.nextLine());
                        }
                        else if ((toBeAdded.getVehicleType()).equalsIgnoreCase("truck")|| toBeAdded instanceof Truck)
                        {
                            System.out.println("Enter new Enginetype    :");
                            ((Truck) toBeAdded).setEngineType(scan.nextLine());
                        }
                        else if ((toBeAdded.getVehicleType()).equalsIgnoreCase("motorbike")|| toBeAdded instanceof Motorbike)
                        {
                            System.out.println("Enter new Biketype  :");
                            ((Motorbike) toBeAdded).setBikeType(scan.nextLine());
                        }
                        break;
                    }
                    case"8":
                    {
                        if ((toBeAdded.getVehicleType()).equalsIgnoreCase("car")|| toBeAdded instanceof Car)
                        {
                            System.out.println("Enter new EnergyType    :");
                            ((Car) toBeAdded).setFuelType(scan.nextLine());
                        }
                        else if ((toBeAdded.getVehicleType()).equalsIgnoreCase("truck")|| toBeAdded instanceof Truck)
                        {
                            System.out.println("Enter new payloadCapacity   :");
                            int capacity = 0;

                        while (capacity < 100) 
                       {
                                 System.out.print("Enter load Capacity (in kg, must be at least 100): ");
                                     String capacityStr = scan.nextLine();
    
                            try {
                                capacity = Integer.parseInt(capacityStr);
                                } catch (NumberFormatException e) 
                                        {
                                   System.out.println("Invalid input. Please enter a valid integer.");
                                        continue;
                                        }
    
                                                  if (capacity < 100) {
                                                                            System.out.println("Capacity must be at least 100.");
                                                                       }
                        }
                         ((Truck) toBeAdded).setPayloadCapacity(capacity);
                        }
                        else if ((toBeAdded.getVehicleType()).equalsIgnoreCase("motorbike")|| toBeAdded instanceof Motorbike)
                        {
                            System.out.println("Enter new EngineSize    :");
                                         int Size = 0;

                                          while (Size <= 0) 
                                 {
                                 System.out.print("Enter Engine Size: ");
                                     String capacityStr = scan.nextLine();
    
                                 try {
                                Size = Integer.parseInt(capacityStr);
                                } catch (NumberFormatException e) 
                                        {
                                   System.out.println("Invalid input. Please enter a valid integer.");
                                        continue;
                                        }
    
                                                  if (Size <= 0) {
                                                                             System.out.println("Invalid input. Please enter a valid Size.");
                                                  }
                            }
                                     
                               ((Motorbike) toBeAdded).setEngineSize(Size);
                        }
                        break;
                    }
                            
                    default:
                    {
                        System.out.println("kindly enter a valid input");
                        break;
                    }
                }
            }
            
                
        }      
}