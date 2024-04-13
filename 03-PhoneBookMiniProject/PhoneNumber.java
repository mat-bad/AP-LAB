public class PhoneNumber {
    private String countryCode;
    private String number;

    public PhoneNumber(String countryCode, String number) {
        if ((countryCode + number).length() - 1 != 12) {
            System.out.println("Error: The total length of the country code and phone number must be 12 characters.");
            this.countryCode = "";
            this.number = "";
            return;
        }
        this.countryCode = countryCode;
        this.number = number;
    }

    @Override
    public String toString() {
        return "(" + countryCode + ") " + number;
    }
}
