import java.util.HashMap;
import java.util.List;

public interface ItemManager {
    void addItem(String name, int quantity, String category);
    void removeItem(String name);
    Product findItem(String name);
    List<Product> getAllItems();

}
