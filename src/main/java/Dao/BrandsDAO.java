package Dao;

import Model.Brands;
import Model.Products;
import Connection.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BrandsDAO {
    public List<Brands> getBrandsList(){
        List<Brands> brandsList = new ArrayList<>();
        try{
            Connection conn = MyConnection.getConnection();
            String sql = "SELECT * FROM `brands`";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()){
                Brands brands = new Brands();
                brands.setId(rs.getInt("id"));
                brands.setBrand_name(rs.getString("brand_name"));
                brands.setBrand_address(rs.getString("brand_address"));
                brandsList.add(brands);
            }
            rs.close();
            stm.close();
            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return brandsList;
    }

    public List<Brands> getCount(){
        List<Brands> brandList = new ArrayList<>();
        List<Products> productsList  =new ArrayList<>();
        try{
            Connection conn = MyConnection.getConnection();
            String sql = " SELECT b.brand_name, p.product_name\n " +
                    " FROM brands b\n" +
                    " inner join products p\n " +
                    " on b.id = p.brand_id ";
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()){
                Products products = new Products();
                Brands brands = new Brands();

                products.setProduct_name(rs.getString("p.product_name"));
                brands.setBrand_name(rs.getString("b.brand_name"));
                productsList.add(products);

                brands.setProductsList(productsList);
                brandList.add(brands);
            }

            rs.close();
            stm.close();
            conn.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return brandList;
    }
}
