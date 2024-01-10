import java.io.IOException;
import java.util.Scanner;

public class StoreManagementSystem
{
    public static void main (String[] args) throws IOException
    {
       //System.out.println("hi");
       Scanner p = new Scanner(System.in);
          
           System.out.println("\t Store Management  System \n ---------------------------------------------------------\n");
            String choice="H";
            while(!choice.equals("1") && !choice.equals("2") )
            {
                System.out.print("Choose Your dataSource:\n\t1-JackSon\n\t2-MongoDb\n\nChoice (1 or 2): ");
                choice = p.nextLine();
                if (!choice.equals("1") && !choice.equals("2") )
                    {
                        System.out.println("\n Kindly enter a valid value!\n\n");
                    }
            }
          
            //////////////////////////////////////////////
            dataBase data;
            int num=0;
            try {
                num = Integer.parseInt(choice);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
                }
            data= new dataBase(num);
        
           
            ////////////////////////////////////////////////////////////
            while(true)
            {
            choice="H";
            while(!choice.equals("1") &&!choice.equals("0") && !choice.equals("2") &&!choice.equals("3") &&!choice.equals("4") &&!choice.equals("5"))
            {
                System.out.print("Welcome back!! \n Would you like to :\n\t1-Add a new vehicle\n\t2-Search for an existing vehicle\n\t3-Update an existing vehicle\n\t4-Delete an existing item\n\t5-Exit without saving \n\t0-Exit and Save changes\n\nChoice (0,1,2,3,4 or 5): ");
                choice = p.nextLine();
                if (!choice.equals("0") &&!choice.equals("1") && !choice.equals("2") &&!choice.equals("3") &&!choice.equals("4") &&!choice.equals("5"))
                    {
                        System.out.println("\n Kindly enter a valid value!\n\n");
                    }
            }
            
            switch(choice)
            {
                case "0":
                {
                         data.saveModifiction();
                         return;
                }
                case"1":
                {
                    String i="y";
                    while ( i.equalsIgnoreCase("y"))
                    {
                        data.AddNewVehicule();
                        System.out.print("\n add more [Y/N} : ");//
                        i=p.nextLine();
                    }
                    break;
                }
                case "2":
                {
                        System.out.print("What are you looking for : ");
                        String searching=p.nextLine();
                        searching = searching.toLowerCase();
                        data.Search4(searching,false);
                        break;
                }
                case "3":
                {
                    System.out.print("What are you looking for  editing: ");
                    String searching=p.nextLine();
                    searching = searching.toLowerCase();
                    Vehicle v =data.Search4(searching, true);
                    System.out.print("Are you sure you want to edit\n"+v.toString()+"\n[Y/N] : ");
                    String answer;
                    while(true)
                    {
                        answer = p.nextLine().trim().toUpperCase();
                          if (answer.equals("Y")) {
                                     data.EditVehicles(v);
                                             break;
                                            } else if (answer.equals("N")) {
                                         System.out.println("edit canceled.");
                                              break;
                                                } else {
                                            System.out.println("Invalid input. Please enter 'Y' or 'N':");
                                                        }
                    }
                    break;
                }
                case "4":
                {
                    System.out.print("What are you looking for  deleting: ");
                    String searching=p.nextLine();
                    searching = searching.toLowerCase();
                    Vehicle v =data.Search4(searching, true);
                    System.out.println("Are you sure you want to delete [Y/N} : "+v.toString());
                    String answer;
                    while(true)
                    {
                        answer = p.nextLine().trim().toUpperCase();
                          if (answer.equals("Y")) {
                                     data.DeleteVehicles(v);
                                             break;
                                            } else if (answer.equals("N")) {
                                         System.out.println("Deletion canceled.");
                                              break;
                                                } else {
                                            System.out.println("Invalid input. Please enter 'Y' or 'N':");
                                                        }
                    }
                    break;
                }
                case"5":
                {
                    return;
                }
                
                default:
                {
                    break;
                }
            }
            if (choice.equals("5"))
            {
                break;
            }
        }
    }
}