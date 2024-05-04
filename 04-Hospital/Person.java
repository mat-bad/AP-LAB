import java.util.Scanner;
abstract public class Person {
    protected String username;
    protected int money;
    protected Hospital hospital;
    protected String password;

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    abstract public void ShowMenu (Scanner scanner);

}