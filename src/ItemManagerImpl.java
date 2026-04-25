import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ItemManagerImpl implements ItemManager {

    private List<Product> items = new ArrayList<>();

    public static void main(String[] args) {

        ItemManagerImpl manager = new ItemManagerImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
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
                            System.out.print("Podaj numer produktu: ");
                            int index = scanner.nextInt() - 1;
                            scanner.nextLine();

                            manager.markAsPurchased(index);
                            System.out.println("Produkt oznaczony!");
                        }
                        break;

                    case 4:
                        manager.showList();

                        if (!manager.getAllItems().isEmpty()) {
                            System.out.print("Podaj numer produktu do usunięcia: ");
                            int removeIndex = scanner.nextInt() - 1;
                            scanner.nextLine();

                            Product p = manager.getAllItems().get(removeIndex);
                            manager.removeItem(p.getName());

                            System.out.println("Produkt usunięty!");
                        }
                        break;

                    case 5:
                        System.out.println("Do widzenia!");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Nieprawidłowa opcja!");
                }

            } catch (InputMismatchException e) {
                System.out.println("Błąd: wpisz liczbę!");
                scanner.nextLine();

            } catch (FileNotFoundException e) {
                System.out.println("Błąd pliku: " + e.getMessage());

            } catch (SQLException e) {
                System.out.println("Błąd bazy danych: " + e.getMessage());

            } catch (ParseException e) {
                System.out.println("Błąd parsowania danych: " + e.getMessage());

            } catch (Exception e) {
                System.out.println("Inny błąd: " + e.getMessage());
            }
        }
    }

    @Override
    public void addItem(String name, int quantity, String category)
            throws FileNotFoundException, SQLException, ParseException {

        if (name.equalsIgnoreCase("plik")) {
            throw new FileNotFoundException("Nie znaleziono pliku produktu.");
        }

        if (name.equalsIgnoreCase("sql")) {
            throw new SQLException("Brak połączenia z bazą.");
        }

        if (name.equalsIgnoreCase("data")) {
            throw new ParseException("Zły format danych.", 0);
        }

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

    public void showList() {
        if (items.isEmpty()) {
            System.out.println("Lista jest pusta.");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ".");
            items.get(i).showProductInfo();
        }
    }

    public void markAsPurchased(int index) {
        Product product = items.get(index);
        product.setQuantity(product.getQuantity() + 1);
    }
}