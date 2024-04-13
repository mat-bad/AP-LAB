import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private List<Contact> contacts;

    public PhoneBook() {
        this.contacts = new ArrayList<>();
    }

    public boolean addContact(Contact contact) {
        for (Contact c : contacts) {
            if (c.getFirstName().equals(contact.getFirstName()) && c.getLastName().equals(contact.getLastName())) {
                return false;
            }
        }
        contacts.add(contact);
        return true;
    }

    public boolean deleteContact(String firstName, String lastName) {
        return contacts.removeIf(contact -> contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName));
    }

    public Contact findContact(String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName)) {
                return contact;
            }
        }
        return null;
    }

    public Contact[] findContacts(String group) {
        List<Contact> groupContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getGroup().equals(group)) {
                groupContacts.add(contact);
            }
        }
        if (groupContacts.isEmpty()) {
            return null;
        } else {
            return groupContacts.toArray(new Contact[0]);
        }
    }


    public void printContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
