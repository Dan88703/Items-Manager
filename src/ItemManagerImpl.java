import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class ItemManagerImpl implements ItemManager {
    private List<Product> items = new ArrayList<>();;

    public static void main(String[] args) {
            ItemManagerImpl manager = new ItemManagerImpl();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== Lista zakupów ===");
                System.out.println("1. Dodaj produkt");
                System.out.println("2. Pokaż listę zakupów");
                System.out.println("3. Oznacz jako kupione");
                System.out.println("4. Usuń produkt");
                System.out.println("5. Wyjście");
                System.out.print("Wybierz opcję: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Nazwa produktu: ");
                        String name = scanner.nextLine();

                        System.out.print("Ilość: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Kategoria: ");
                        String category = scanner.nextLine();

                        manager.addItem(name, quantity, category);
                        System.out.println("Produkt dodany!");
                        break;

                    case 2:
                        manager.showList();
                        break;

                    case 3:
                        manager.showList();
                        if (!manager.getAllItems().isEmpty()) {
                            System.out.print("Podaj numer produktu do oznaczenia jako kupiony: ");
                            int index = scanner.nextInt() - 1; // użytkownik podaje od 1
                            if (index >= 0 && index < manager.getAllItems().size()) {
                                manager.markAsPurchased(index);
                                System.out.println("Oznaczono jako kupione!");
                            } else {
                                System.out.println("Nieprawidłowy numer!");
                            }
                        }
                        break;

                    case 4:
                        manager.showList();
                        if (!manager.getAllItems().isEmpty()) {
                            System.out.print("Podaj numer produktu do usunięcia: ");
                            int removeIndex = scanner.nextInt() - 1;
                            scanner.nextLine();
                            if (removeIndex >= 0 && removeIndex < manager.getAllItems().size()) {
                                Product toRemove = manager.getAllItems().get(removeIndex);
                                manager.removeItem(toRemove.getName());
                                System.out.println("Produkt usunięty!");
                            } else {
                                System.out.println("Nieprawidłowy numer!");
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Do widzenia!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Nieprawidłowa opcja, spróbuj ponownie.");
                }

        }
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
