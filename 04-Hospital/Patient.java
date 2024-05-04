import java.util.Scanner;
public class Patient extends Person {

    public Patient(String username, String password) {
        super(username, password);
    }

    public void ShowMenu (Scanner scanner){
        System.out.println("Patient menu");
        String response = scanner.nextLine();

    }
}
