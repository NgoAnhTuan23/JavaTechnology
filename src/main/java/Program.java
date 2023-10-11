import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {



        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        // Khởi tạo PhoneDAO và ManufactureDAO

        PhoneDAO phoneDAO = new PhoneDAO(sessionFactory);
        ManufactureDAO manufactureDAO = new ManufactureDAO(sessionFactory);

        // Thêm một số dữ liệu vào cơ sở dữ liệu
        Manufacture manufacturer1 = new Manufacture();
        manufacturer1.setName("Manufacturer 1");
        manufacturer1.setLocation("Location 1");
        manufacturer1.setEmployee(150);

        Phone phone1 = new Phone();
        phone1.setName("Phone 1");
        phone1.setPrice(500);
        phone1.setColor("Red");
        phone1.setCountry("Country 1");
        phone1.setQuantity(10);
        phone1.setManufacture(manufacturer1);

        // Thêm dữ liệu vào cơ sở dữ liệu
        manufactureDAO.add(manufacturer1);
        phoneDAO.add(phone1);

        // Lấy thông tin của một điện thoại theo ID
        Phone retrievedPhone = phoneDAO.get(2);
        System.out.println("Retrieved Phone by ID : " + retrievedPhone.getName());

        // Lấy thông tin của một nhà sản xuất theo ID
        Manufacture retrievedManufacturer = manufactureDAO.get(1);
        System.out.println("Retrieved Manufacturer: " + retrievedManufacturer.getName());

        System.out.println("==============================================");
        // Lấy danh sách tất cả điện thoại và nhà sản xuất
        List<Phone> allPhones = phoneDAO.getAll();
        List<Manufacture> allManufacturers = manufactureDAO.getAll();
        System.out.println("==========  All Phones (only name): ==========");
        for (Phone phone : allPhones) {
            int i = 1;
            System.out.print(i + ". ");
            System.out.println(phone.getName());
            ++i;
        }

        System.out.println("==============================================");
        System.out.println("======== All Manufacturers (only name): ======");
        for (Manufacture manufacturer : allManufacturers) {
            int i = 1;
            System.out.print(i + ". ");
            System.out.println(manufacturer.getName());
            ++i;
        }

        System.out.println("==============================================");
        // Cập nhật thông tin của một điện thoại
        retrievedPhone.setColor("Blue");
        phoneDAO.update(retrievedPhone);

        // Xóa một điện thoại
        phoneDAO.remove(retrievedPhone);

        // Xóa một nhà sản xuất
        manufactureDAO.remove(retrievedManufacturer);

        System.out.println("==== All Phones after remove (only name): ====");
        for (Phone phone : allPhones) {
            int i = 1;
            System.out.print(i + ". ");
            System.out.println(phone.getName());
            ++i;
        }

        System.out.println("=================================================");
        System.out.println("== All Manufacturers after remove (only name): ==");
        for (Manufacture manufacturer : allManufacturers) {
            int i = 1;
            System.out.print(i + ". ");
            System.out.println(manufacturer.getName());
            ++i;
        }

        // Đóng SessionFactory khi bạn đã hoàn thành
        HibernateUtil.sessionFactory.close();
    }
}
