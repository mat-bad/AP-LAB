import java.util.HashMap;
import java.util.Scanner;
public class Doctor extends Person{
    private HashMap<String,Boolean> reservedDate = new HashMap<>();

    public Doctor(String username, String password) {
        super(username, password);
    }

    public void ShowMenu (Scanner scanner){
        System.out.println("Doctor menu");
        String response = scanner.nextLine();

    }
}
