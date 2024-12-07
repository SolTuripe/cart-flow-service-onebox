package cartFlowService.domain.models;

public class Item {

    private int    id;
    private String description;
    private double amount;

    public Item(int id, String description, double amount) {
        this.id          = id;
        this.description = description;
        this.amount      = amount;
    }

    public Item() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
