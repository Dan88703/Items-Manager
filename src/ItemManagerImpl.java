import java.util.ArrayList;
import java.util.List;

public class ItemManagerImpl implements ItemManager {
    private List<Product> items = new ArrayList<>();;

    public static void main(String[] args) {

    }

    @Override
    public void addItem(String name, int quantity, String category) {
        Product product = new Product(name, quantity, category);
        items.add(product);
    }

    @Override
    public void removeItem(String name) {
        items.remove(findItem(name));
    }

    @Override
    public Product findItem(String name) {
        for (Product product : items) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> getAllItems() {
        return items;
    }

    public void showList(){
        if(items.isEmpty()){
            System.out.println("There are no items in this list");
            return;
        }

        for (int i = 0; i<items.size(); i++) {
            System.out.println((i+1) + ". ");
            items.get(i).showProductInfo();
        }
    }

    public void markAsPurchased(int index){
        Product product = items.get(index);
        if(items.contains(product)){
            product.setQuantity(product.getQuantity() + 1);
        }else{
            product.setQuantity(1);
        }
    }
}
