import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ItemManagerImplTest {

    private ItemManagerImpl manager;

    @BeforeEach
    void setup() {
        manager = new ItemManagerImpl();
    }

    @Test
    void testAddItem() {
        manager.addItem("Mleko", 2, "spożywcze");

        assertEquals(1, manager.getAllItems().size());
        assertEquals("Mleko", manager.getAllItems().get(0).getName());
    }

    @Test
    void testRemoveItem() {
        manager.addItem("Mleko", 2, "spożywcze");
        manager.addItem("Chleb", 1, "spożywcze");

        manager.removeItem("Mleko");

        assertEquals(1, manager.getAllItems().size());
        assertEquals("Chleb", manager.getAllItems().get(0).getName());
    }

    @Test
    void testFindItem() {
        manager.addItem("Mleko", 2, "spożywcze");

        Product found = manager.findItem("Mleko");

        assertNotNull(found);
        assertEquals("Mleko", found.getName());
    }

    @Test
    void testMarkAsPurchased() {
        manager.addItem("Mleko", 2, "spożywcze");

        manager.markAsPurchased(1);

        assertTrue(manager.getAllItems().get(0).isPurchased());
    }

    @Test
    void testShowList() {
        manager.addItem("Mleko", 2, "spożywcze");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        manager.showList();

        String output = out.toString();

        assertTrue(output.contains("1."));
        assertTrue(output.contains("Mleko"));
    }
}
