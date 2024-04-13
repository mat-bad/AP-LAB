import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            String[] parts = input.split(" ");
            switch (parts[0]) {
                case "contacts":
                    if (parts[1].equals("-a")) {
                        // Add contact
                        System.out.println("Please enter contact's group: ");
                        String group = scanner.nextLine().trim();
                        System.out.println("Please enter contact's email: ");
                        String email = scanner.nextLine().trim();
                        System.out.println("Please enter contact's country code: ");
                        String countryCode = scanner.nextLine().trim();
                        System.out.println("Please enter contact's phone number: ");
                        String number = scanner.nextLine().trim();

                        // Validate phone number input
                        if (!isValidPhoneNumber(countryCode, number)) {
                            System.out.println("Error: Invalid phone number format.");
                            break;
                        }

                        System.out.println("Please enter contact's zip code: ");
                        String zipCode = scanner.nextLine().trim();
                        System.out.println("Please enter contact's country: ");
                        String country = scanner.nextLine().trim();
                        System.out.println("Please enter contact's city: ");
                        String city = scanner.nextLine().trim();

                        Contact contact = new Contact(
                                group.isEmpty() ? "-" : group,
                                email.isEmpty() ? "-" : email,
                                parts[2],
                                parts[3],
                                new PhoneNumber(countryCode.isEmpty() ? "-" : countryCode, number.isEmpty() ? "-" : number),
                                new Address(zipCode.isEmpty() ? "-" : zipCode, country.isEmpty() ? "-" : country, city.isEmpty() ? "-" : city)
                        );

                        if (phoneBook.addContact(contact)) {
                            System.out.println("Contact saved!");
                        } else {
                            System.out.println("Contact already exists.");
                        }
                    } else if (parts[1].equals("-r")) {
                        // Remove contact
                        String firstName = parts[2];
                        String lastName = parts[3];
                        Contact contact = phoneBook.findContact(firstName, lastName);
                        if (contact != null) {
                            System.out.println("Are you sure you want to delete " + firstName + " " + lastName + "? (yes/no)");
                            String confirmation = scanner.nextLine().trim();
                            if (confirmation.equalsIgnoreCase("yes")) {
                                if (phoneBook.deleteContact(firstName, lastName)) {
                                    System.out.println("Ok");
                                } else {
                                    System.out.println("An error occurred.");
                                }
                            }
                        } else {
                            System.out.println("Not found");
                        }
                    }
                    break;
                case "show":
                    if (parts.length == 1) {
                        int cnt = 1;
                        for (Contact contact : phoneBook.getContacts()) {
                            System.out.println(cnt + "- " + contact.getFirstName() + " " + contact.getLastName());
                            cnt++;
                        }
                    } else if (parts[1].equals("-g")) {
                        // Show contacts by group
                        Contact[] groupContacts = phoneBook.findContacts(parts[2]);
                        if (groupContacts == null) {
                            System.out.println("No contacts found in this group.");
                        } else {
                            for (Contact c : groupContacts) {
                                System.out.println(c);
                            }
                        }
                    } else if (parts[1].equals("-c")) {
                        // Show contact by name
                        Contact c = phoneBook.findContact(parts[2], parts[3]);
                        if (c != null) {
                            System.out.println(c);
                        } else {
                            System.out.println("Contact not found.");
                        }
                    } else {
                        // Show all contacts
                        phoneBook.printContacts();
                    }
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
        }
    }
    private static boolean isValidPhoneNumber(String countryCode, String number) {
        String fullNumber = countryCode + number;
        return fullNumber.matches("\\+\\d{12}");
    }
}
