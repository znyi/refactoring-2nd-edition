import java.util.ArrayList;

public class Invoice {

    private String customer;
    private ArrayList<Performance> performances;

    public Invoice(String customer, ArrayList<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }

    public String getCustomer() {
        return customer;
    }

    public ArrayList<Performance> getPerformances() {
        return performances;
    }

}
