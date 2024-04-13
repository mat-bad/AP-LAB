public class Contact {
    private String group;
    private String email;
    private String firstName;
    private String lastName;
    private PhoneNumber phoneNumber;
    private Address address;

    public Contact(String group, String email, String firstName, String lastName, PhoneNumber phoneNumber, Address address) {
        this.group = group;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Contact {\n" +
                "group: " + (group.isEmpty() ? "-" : group) + "\n" +
                "email: " + (email.isEmpty() ? "-" : email) + "\n" +
                "firstName: " + (firstName.isEmpty() ? "-" : firstName) + "\n" +
                "lastName: " + (lastName.isEmpty() ? "-" : lastName) + "\n" +
                "phoneNumber: " + phoneNumber + "\n" +
                "address: " + address + "\n" +
                '}';
    }
}
