public class Warehouse {
    public int id;
    public String name;
    public String address;
    public int capacity;
    public Product products;

    public Warehouse(int id, String name, String address, int capacity){
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    /* Функция для возможности добавления новых товаров на склад */

    /* Потом */

    /* Сеттеры для будущей возможности замены данных */

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setName(String name) {
        this.name = name;
    }


    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return String.format("ID: %s | Название склада: %s | Адрес: %s | Вместимость: %s",
                this.id, this.name, this.address, this.capacity);
    }
}
