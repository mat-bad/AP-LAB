import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Hospital {
    private static HashMap <String, ArrayList<Reserve>> reservations = new HashMap<>();
    private static HashMap <String,Person> persons = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    private static int runMenu(String[] args, String description) {
        System.out.println(description);
        for(int i=0; i < args.length; i++) {
            System.out.println("" + (i+1) + "->" + args[i]);
        }
        
        int result = scanner.nextInt();
        while(result <= 0 || result > args.length) {
            System.out.println("invalid option, please try again");
            result = scanner.nextInt();
        }
        return result;
    }

    private static int runMenu(String[] args) {
        return runMenu(args, "choose your option:");
    }

    private static Person authenticate(String username, String password) {
        if(!persons.containsKey(username)) {
            return null;
        }
        Person user = persons.get(username);
        if(!user.password.equals(password)) {
            return null;
        }
        return user;
    }

    private static void signupDoctor(String username, String password) {
        persons.put(username, new Doctor(username, password));
    }

    private static void signupPatient(String username, String password) {
        persons.put(username, new Patient(username, password));
    }

    private static void hospitalMenu() {
        System.out.println("welcome to the hospital:");
        Person user = null;
        while(true) {
            if(user == null) {
                int option = runMenu(new String[]{"login", "register"});
                if(option == 1) {
                    System.out.println("enter your code melli");
                    String username = scanner.nextLine();
                    System.out.println("enter your password:");
                    String password = scanner.nextLine();
                    user = authenticate(username, password);
                    if(user == null) {
                        System.out.println("invalid credentials");
                    } else {
                        System.out.println("Hello " + user.username);
                    }
                } else {
                    int op2 = runMenu(new String[]{"patient", "doctor"});
                    String username = scanner.nextLine();
                    String password = scanner.nextLine();
                    if(op2 == 1) signupPatient(username, password);
                    else if(op2 == 2) signupDoctor(username, password);
                }
                
            } else {
                user.ShowMenu(scanner);
            }
        }
    }

    public static void main(String[] args) {
        hospitalMenu();
    }
}
