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
}
