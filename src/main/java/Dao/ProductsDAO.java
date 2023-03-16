package Dao;

import Model.Brands;
import Model.Products;
import Connection.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class ProductsDAO {

    public List<Products> getProducts(){
        List<Products> productsList = new ArrayList<>();
        try{
            Connection conn = MyConnection.getConnection();
            String sql = "SELECT * FROM `products`";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()){
               Products products = new Products();
               products.setId(rs.getInt("id"));
               products.setProduct_name(rs.getString("product_name"));
               products.setProduct_price(rs.getInt("product_price"));
               products.setProduct_color(rs.getString("product_color"));
               products.setBrand_id(rs.getInt("brand_id"));
               productsList.add(products);
            }
            rs.close();
            stm.close();
            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return productsList;
    }
    public void insert(Products products){
        try{
            Connection conn = MyConnection.getConnection();
            String sql = String.format
                    ("INSERT INTO `products` (`product_name`, `product_price`, `product_size`, `product_color`,`brand_id`) VALUES ('%s', '%d','%s','%s','%d') ",
                            products.getProduct_name(), products.getProduct_price(), products.getProduct_size(), products.getProduct_color(), products.getBrand_id());
            Statement stm = conn.createStatement();
            int rs =  stm.executeUpdate(sql);
            if(rs == 0){
                System.out.println("thêm thất bại");
            }
            stm.close();
            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public List<Products> update(Products products, int id){
        return null;
    }
    public void delete(int id){
        try{
            Connection conn = MyConnection.getConnection();
            String sql = "DELETE FROM `products` WHERE id = '" + id +"'";
            Statement stm = conn.createStatement();
            long rs = stm.executeUpdate(sql);
            if(rs == 0){
                System.out.println("không có sản phẩm nào có id = " + id);
            }else{
                System.out.println("xoa thanh cong");

            }
            stm.close();
            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }


}
