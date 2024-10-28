import org.sqlite.JDBC;

import java.sql.*;
import java.util.*;

public class DbHandler {

    // Константа, в которой хранится адрес подключения
    private static final String CON_STR = "jdbc:sqlite:database.db";

    // Используем шаблон одиночка, чтобы не плодить множество
    // экземпляров класса DbHandler
    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    // Объект, в котором будет храниться соединение с БД
    private Connection connection;

    private DbHandler() throws SQLException {
        // Регистрируем драйвер, с которым будем работать
        // в нашем случае Sqlite
        DriverManager.registerDriver(new JDBC());
        // Выполняем подключение к базе данных
        this.connection = DriverManager.getConnection(CON_STR);
    }


    // для заявок -----------------------------------------------------------

    public List<Application> getAllApplication() {

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Application> applications = new ArrayList<Application>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT `id`, `IdWarehouse`, `IdShop`, `ColProduct` FROM Application");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                applications.add(new Application(resultSet.getInt("id"),
                        resultSet.getInt("IdWarehouse"),
                        resultSet.getInt("IdShop"),
                        resultSet.getInt("ColProduct")));
            }
            // Возвращаем наш список
            return applications;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    public void getApplication(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "SELECT FROM Application WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Добавление заявки в БД
    public void addApplication(Application application) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Application(`IdWarehouse`, `IdShop`, `ColProduct`) " +
                        "VALUES(?, ?, ?)")) {
            statement.setObject(1, application.IdWarehouse);
            statement.setObject(2, application.IdShop);
            statement.setObject(3, application.ColProduct);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление заявки по id
    public void deleteApplication(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Application WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // для складов -----------------------------------------------------------


    public List<Warehouse> getAllWarehouse() {

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Warehouse> warehouses = new ArrayList<Warehouse>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT `id`, `name`, `address`, `capacity` FROM Warehouse");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                warehouses.add(new Warehouse(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getInt("capacity")));
            }
            // Возвращаем наш список
            return warehouses;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }
    public void addWarehouse(Warehouse warehouse) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Warehouse(`id`, `name`, `address`, `capacity`) " +
                        "VALUES(?, ?, ?, ?)")) {
            statement.setObject(1, warehouse.id);
            statement.setObject(2, warehouse.name);
            statement.setObject(3, warehouse.address);
            statement.setObject(4, warehouse.capacity);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addProductToWarehouse(Warehouse warehouse){
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Warehouse(`Products`) " +
                        "VALUES(?)")) {
            statement.setObject(1, warehouse.products);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // для магазинчиков -----------------------------------------------------------

    public List<Shophouse> getAllShophouse() {

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Shophouse> Shophouses = new ArrayList<Shophouse>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT `id`, `name`, `address` FROM Shophouse");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                Shophouses.add(new Shophouse(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("address")));
            }
            // Возвращаем наш список
            return Shophouses;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    // Добавление магазина в БД
    public void addShopHouse(Shophouse shophouse) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Shophouse( `name`, `address`) " +
                        "VALUES(?, ?)")) {
            statement.setObject(1, shophouse.name);
            statement.setObject(2, shophouse.address);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление магазина по id
    public void deleteShopHouse(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Shophouse WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // для таблицы продуктов

    public List<Product> getAllProducts() {

        // Statement используется для того, чтобы выполнить sql-запрос
        try (Statement statement = this.connection.createStatement()) {
            // В данный список будем загружать наши продукты, полученные из БД
            List<Product> products = new ArrayList<Product>();
            // В resultSet будет храниться результат нашего запроса,
            // который выполняется командой statement.executeQuery()
            ResultSet resultSet = statement.executeQuery("SELECT `id`, `name`,`category`, `num`, `length`,`width`," +
                    "`height`,`weight`,`stack`,`warehouseId` FROM Products");
            // Проходимся по нашему resultSet и заносим данные в products
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("category"),
                        resultSet.getInt("num"),
                        resultSet.getDouble("length"),
                        resultSet.getDouble("width"),
                        resultSet.getDouble("height"),
                        resultSet.getDouble("weight"),
                        resultSet.getBoolean("stack"),
                        resultSet.getInt("warehouseId")));
            }
            // Возвращаем наш список
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            // Если произошла ошибка - возвращаем пустую коллекцию
            return Collections.emptyList();
        }
    }

    // Добавление продукта в БД
    public void addProduct(Product products) {
        // Создадим подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Products( `name`, `category`, `num`, `length`,`width`," +
                        "`height`,`weight`,`stack`,`warehouseId`) " +
                        "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            statement.setObject(1, products.name);
            statement.setObject(2,products.category);
            statement.setObject(3, products.num);
            statement.setObject(4, products.length);
            statement.setObject(5, products.width);
            statement.setObject(6, products.height);
            statement.setObject(7, products.weight);
            statement.setObject(8, products.stack);
            statement.setObject(9, products.warehouseId);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // изменение данных продукта
    public void replaceProduct(int id, String name,String category, int num, double length, double width, double height,
                               double weight, boolean stack, int warehouseId, String warehouseName){
        try (PreparedStatement statement = this.connection.prepareStatement("INSERT INTO Products(`id`, `name`, `category` `num`," +
                " `length`,`width`, `height`,`weight`,`stack`,`warehouseId`)" +
                " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } ;
    }

    // Удаление продукта по id
    public void deleteProduct(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM Products WHERE id = ?")) {
            statement.setObject(1, id);
            // Выполняем запрос
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}