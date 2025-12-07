import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    void testConstructor() {
        Product p = new Product("Mleko", 2, "spożywcze");

        assertEquals("Mleko", p.getName());
        assertEquals(2, p.getQuantity());
        assertEquals("spożywcze", p.getCategory());
        assertFalse(p.isPurchased());
    }

    @Test
    void testMarkAsPurchased() {
        Product p = new Product("Chleb", 1, "spożywcze");

        p.setPurchased(true);

        assertTrue(p.isPurchased());
    }

    @Test
    void testShowProductInfo() {
        Product p = new Product("Masło", 1, "spożywcze");

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));

        p.showProductInfo();

        String text = out.toString();

        assertTrue(text.contains("Masło"));
        assertTrue(text.contains("1"));
    }
}
