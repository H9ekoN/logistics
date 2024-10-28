import java.util.ArrayList;

public class Application {
    public int id;
    public int IdWarehouse;
    public int ColProduct;
    public int IdShop;
    public Application(int id, int IdWarehouse, int IdShop, int ColProduct){
        this.id = id;
        this.IdWarehouse = IdWarehouse;
        this.IdShop = IdShop;
        this.ColProduct = ColProduct;
    }

    @Override
    public String toString() {
        return String.format("ID Заявки: %s | ID Склада: %s | ID доставки: %s | Количество товара: %s",
                this.id, this.IdWarehouse, this.IdShop, this.ColProduct);
    }

}
