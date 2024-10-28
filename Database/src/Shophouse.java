public class Shophouse {
    public int id;
    public String name;
    public String address;

    public Shophouse(int id, String name, String address){
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public String toString() {
        return String.format("ID: %s | Название магазина: %s | Адрес: %s",
                this.id, this.name, this.address);
    }
}
