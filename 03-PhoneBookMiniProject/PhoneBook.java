import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

class Address {
    String zipCode, country, city;

    public Address(String zipCode, String country, String city) {
        this.zipCode = zipCode;
        this.country = country;
        this.city = city;
    }

    public String toString() {
        return zipCode + country + city;
    }
}

class PhoneNumber {
    String countryCode, number;

    public PhoneNumber(String country, String number) {
        this.countryCode = countryCode;
        this.number = number;
    }

    public String toString() {
        return countryCode + number;
    }
}

class Contact {
    String group, email, firstName, lastName;
    PhoneNumber phoneNumber;
    Address address;

    public Contact(String group, String email, String firstName, String lastName, PhoneNumber phoneNumber, Address address) {
        this.group = group;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}

public class PhoneBook {
    
    ArrayList<Contact> contacts;
    int contactCount;

    public boolean contactExists(Contact contact) {
        return contacts.contains(contact);
    }

    boolean addContact(Contact contact) {
        if(contactExists(contact)) return false;
        contacts.add(contact);
        return true;
    }

    boolean deleteContact(String firstName, String lastName)

    Contact[] findContact(String inputStr)

    Contact[] findContacts(String searchType)

    void printContacts()

    parser

    public static void main(String[] args) {
        return;
    }

}
