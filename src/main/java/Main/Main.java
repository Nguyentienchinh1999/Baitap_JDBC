package Main;

import Dao.BrandsDAO;
import Dao.ProductsDAO;
import Model.Brands;
import Model.Products;

import javax.swing.*;
import java.util.*;

public class Main {
    public static ProductsDAO productsDAO = new ProductsDAO();
    public static BrandsDAO brandsDAO = new BrandsDAO();
    public static List<Products> productsList = new ArrayList<>();
    public static List<Brands> brandsList = new ArrayList<>();

    public static List<Products> getProductsList = productsDAO.getProducts();
    public static List<Brands> getBrandsList = brandsDAO.getBrandsList();
    private static void mainMenu() {
        System.out.println("--- QUẢN LÝ SẢN PHẨM ---");
        System.out.println("1. DANH SÁCH SẢN PHẨM");
        System.out.println("2. Thêm Sản PHẩm");
        System.out.println("3. Xoa Sản Phẩm Theo Mã");
        System.out.println("4. Cập nhật thông tin Sản PHẩm");
        System.out.println("5. Lấy ra thông tin sau:");
        System.out.println("6. Lấy ra top 5 sản phẩm có giá cao nhất và in ra thông tin");
        System.out.println("7. Danh sách hãng sản xuất");
        System.out.println("8. Thêm hãng sản xuất");
        System.out.println("9. Xóa hãng sản xuất theo mã");
    }

    private static void option1() {
        productsList = productsDAO.getProducts();
        String leftAlignFormat = "| %-5d | %-13s | %-12d | %-7s  | %n";
        System.out.format("+-------+---------------+--------------+----------+%n");
        System.out.format("| STT   | Tên sản phẩm  | Giá sản phẩm | Màu sắc  |%n");
        System.out.format("+-------+---------------+--------------+----------+%n");
        for (int i = 0; i < productsList.size(); i++) {
            System.out.format(leftAlignFormat, productsList.get(i).getId(), productsList.get(i).getProduct_name(), productsList.get(i).getProduct_price(), productsList.get(i).getProduct_color());
        }
        System.out.format("+-------+---------------+--------------+----------+%n");
    }

    private static void option2(Scanner in) {
        Products products = new Products();

        System.out.println("Nhập thông tin sản phẩm: ");

        System.out.print("nhập vào tên sản phẩm: ");
        String productName = in.nextLine();
        boolean flag2= false;
        if(productName.equals(" ")){
            flag2 = true;
        }
        if(!flag2){
            System.out.print("tên sp không đc để trống");
            return;
        }
        products.setProduct_name(productName);

        System.out.print("nhập giá: ");
        int product_price = in.nextInt();

        if(product_price <= 0){
            System.out.print("giá sản phẩm phải dương");
            return;
        }
        products.setProduct_price(product_price);

        System.out.print("nhập size: ");
        String product_size = in.next();
        boolean flag1= false;
        if(product_size.equalsIgnoreCase("s") || product_size.equalsIgnoreCase("l") || product_size.equalsIgnoreCase("xl")){
            flag1 = true;
        }
        if(!flag1){
            System.out.print("cần nhập đúng size");
            return;
        }
        products.setProduct_size(product_size);

        System.out.print("nhập màu sắc: ");
        products.setProduct_color(in.next());

        System.out.print("nhập hãng");
        brandsList = brandsDAO.getBrandsList();
        int brand_id = in.nextInt();
        boolean flag = false;
        for(int i = 0; i < brandsList.size(); i++){
            if(brandsList.get(i).getId() == brand_id){
                flag = true;
            }
        }
        if(!flag){
            System.out.print("không có brand id trùng khớp: ");
            return;
        }
        products.setBrand_id(brand_id);

        productsDAO.insert(products);
        System.out.print("thêm thành công");
    }

    private static void option3(Scanner in) {
        System.out.println("nhập vào id cần xóa: ");

    }

    private static void option5(){
       Map<String, Integer> productsMap = new HashMap<>();
       productsMap = productsDAO.getCount();
        System.out.println(productsMap);
    }
    private static void option6(){
        productsDAO.getProducts().stream().sorted((o1, o2) -> {
            if(o1.getProduct_price() - o2.getProduct_price() < 0){
                return 1;
            }else if(o1.getProduct_price() - o2.getProduct_price() > 0){
                return -1;
            }return 0;
        }).limit(5).forEach(System.out::println);
    }
    public static void main(String[] args) {
        int option = -1;
        Scanner in = new Scanner(System.in);
        do {
            mainMenu();
            System.out.print("Nhập lựa chọn: ");
            try {
                option = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập sai định dạng!");
                continue;
            }
            if (option < 1 || option > 9) {
                System.out.println("Lựa chọn không hợp lệ");
                continue;
            }
            // Xu ly cac TH thoa man
            switch (option) {
                case 1:
                    option1();
                    break;
                case 2:
                    option2(in);
                    break;
                case 3:
                    break;
                case 5:
                    option5();
                    break;
                case 6:
                    option6();
                    break;
                case 7:
                    break;
            }

        }
        while (option != 9);
        in.close();
    }
}
