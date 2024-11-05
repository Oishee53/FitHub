class Equipment{
    private String equipmentID;
    private String name;
    private int quantity;
    private String condition;

    public Equipment(String equipmentID, String name, int quantity, String condition) {
        this.equipmentID = equipmentID;
        this.name = name;
        this.quantity = quantity;
        this.condition = condition;
    }


    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public String getCondition() {
        return condition;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}