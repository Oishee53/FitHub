public class Equipment {
    private String equipmentID;
    private String name;
    private int quantity;
    private String condition;
    private double cost;

    public Equipment(String equipmentID, String name, int quantity, String condition, double cost) {
        this.equipmentID = equipmentID;
        this.name = name;
        this.quantity = quantity;
        this.condition = condition;
        this.cost = cost;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}

