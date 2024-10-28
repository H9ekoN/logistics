import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var url = "jdbc:sqlite:database.db";
        var sql = "CREATE TABLE IF NOT EXISTS Application (" +
                " id INTEGER PRIMARY KEY," +
                " IdWarehouse INTEGER NOT NULL," +
                " IdShop INTEGER NOT NULL," +
                " ColProduct INTEGER NOT NULL" + ");";
        try (var conn = DriverManager.getConnection(url); var stmt = conn.createStatement()) {
            // создание новой таблицы
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        DbHandler dbHandler = null;
        try {
            dbHandler = DbHandler.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbHandler.addApplication(new Application(2, 3, 2, 1));
        //dbHandler.addProduct(new Product(1,"РЫБА ПО АКЦИИ", "РЫБЫ", 9, 0.15,0.5, 0.7, 5, true, 1));
        List<Product> products = dbHandler.getAllProducts();
        for (Product products2 : products) {
            System.out.println(products2.toString());
        }
        List<Application> applications = dbHandler.getAllApplication();
        for (Application application : applications) {
            System.out.println(application.toString());
        }

        List<Shophouse> shophouses = dbHandler.getAllShophouse();
        for (Shophouse shophouse : shophouses) {
            System.out.println(shophouse.toString());
        }
        List<Warehouse> warehouses = dbHandler.getAllWarehouse();
        for (Warehouse warehouse : warehouses) {
            System.out.println(warehouse.toString());
        }


        // Создаем экземпляр по работе с БД
        // Добавляем запись
        //dbHandler.addProduct(new Product("Музей", 200, "Развлечения"));
        // Получаем все записи и выводим их на консоль
        // Удаление записи с id = 8
        //dbHandler.deleteProduct(8);

    }
    }
    /*
        var url = "jdbc:sqlite:database.db";
        var sql = "CREATE TABLE IF NOT EXISTS Warehouse (" +
                " id INTEGER PRIMARY KEY," +
                " name text NOT NULL," +
                " address text NOT NULL," +
                "capacity INTEGER NOT NULL" + ");";
        try (var conn = DriverManager.getConnection(url); var stmt = conn.createStatement()) {
            // создание новой таблицы
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        DbHandler dbHandler = null;
        try {
            dbHandler = DbHandler.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbHandler.addWarehouse(new Warehouse(1,"DomMoy", "Borshstreet", 4));
        List<Warehouse> warehouses = dbHandler.getAllWarehouse();
        for (Warehouse warehouses2 : warehouses) {
            System.out.println(warehouses2.toString());
        }


    }
    */



/*
        try {
            // Создаем экземпляр по работе с БД
            DbHandler dbHandler = DbHandler.getInstance();
            // Добавляем запись
            //dbHandler.addProduct(new Product("Музей", 200, "Развлечения"));
            // Получаем все записи и выводим их на консоль
            List<Product> products = dbHandler.getAllProducts();
            for (Product product : products) {
                System.out.println(product.toString());
            }
            // Удаление записи с id = 8
            //dbHandler.deleteProduct(8);
        } catch (SQLException e) {
            e.printStackTrace();
        }
 */