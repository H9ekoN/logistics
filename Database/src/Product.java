public class Product {
    public int id;
    public String name;
    public double length;
    public double width;
    public double weight;
    public int num;
    public double height;
    public boolean stack;
    public int warehouseId;
    public String category;

    public Product(int id, String name,String category, int num, double length, double width, double height,
                   double weight, boolean stack, int warehouseId){
        this.id = id;
        this.name = name;
        this.num = num;
        this.length = length;
        this.width = width;
        this.weight = weight;
        this.height = height;
        this.stack = stack;
        this.warehouseId = warehouseId;
        this.category = category;
    }

    /* Сеттеры для будущей возможности замены данных */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isStack() {
        return stack;
    }

    public void setStack(boolean stack) {
        this.stack = stack;
    }
    @Override
    public String toString() {
        return String.format("ID: %s | Товар: %s | Категория: %s | Количество: %s | Длина: %s | Ширина: %s | Высота: %s | Вес: %s | Штабелирование: %s | ID Склада: %s",
                this.id, this.name,this.category,this.num, this.length, this.width, this.height,this.weight,  this.stack, this.warehouseId);
    }
}