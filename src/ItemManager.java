import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface ItemManager {
    void addItem(String name, int quantity, String category) throws FileNotFoundException, SQLException, ParseException;
    void removeItem(String name);
    Product findItem(String name);
    List<Product> getAllItems();

}
