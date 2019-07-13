package myapp.dell.example.android.canteenmanagementapp;

public class Model {
    String id,items,price;
    public Model() {

    }

    public Model(String id, String items, String price) {
        this.id = id;
        this.items = items;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
