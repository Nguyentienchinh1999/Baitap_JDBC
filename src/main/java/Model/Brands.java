package Model;

import java.util.List;

public class Brands {
    private int id;
    private String brand_name;
    private String brand_address;
    private List<Products> productsList;

    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }

    public Brands(int id, String brand_name, String brand_address) {
        this.id = id;
        this.brand_name = brand_name;
        this.brand_address = brand_address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Brands() {
    }

    public int getId() {
        return id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_address() {
        return brand_address;
    }

    public void setBrand_address(String brand_address) {
        this.brand_address = brand_address;
    }

    @Override
    public String toString() {
        return "Brands{" +
                "id=" + id +
                ", brand_name='" + brand_name + '\'' +
                ", brand_address='" + brand_address + '\'' +
                ", productsList=" + productsList +
                '}';
    }
}
