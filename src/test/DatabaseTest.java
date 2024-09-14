import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

class DatabaseTest {

    private Database database;

    @BeforeEach
    void setUp() {
        // Initialize the database before each test
        database = Database.getInstance();
    }

    @Test
    void testCreateTable() {
        // Create a new table
        database.createTable("users");

        // Verify that the table is created
        assertNotNull(database.getTable("users"), "Table 'users' should exist.");
    }

    @Test
    void testAddColumn() {
        // Create a new table and add columns
        database.createTable("users");
        Table usersTable = database.getTable("users");

        usersTable.addColumn(new IntColumn("id", true, 1));
        usersTable.addColumn(new StringColumn("name", true, 20));

        // Verify columns are added
        assertEquals(2, usersTable.getAllRecords().size(), "Table should have 2 columns.");
    }

    @Test
    void testInsertValidRecord() {
        // Create table, add columns, and insert a valid record
        database.createTable("users");
        Table usersTable = database.getTable("users");
        usersTable.addColumn(new IntColumn("id", true, 1));
        usersTable.addColumn(new StringColumn("name", true, 20));

        Map<String, Object> record = new HashMap<>();
        record.put("id", 1);
        record.put("name", "Alice");

        boolean inserted = usersTable.insertRecord(record);

        // Assert the record is inserted successfully
        assertTrue(inserted, "Record should be inserted successfully.");
        assertEquals(1, usersTable.getAllRecords().size(), "There should be one record in the table.");
    }

    @Test
    void testInsertInvalidRecord() {
        // Create table, add columns, and attempt to insert an invalid record
        database.createTable("users");
        Table usersTable = database.getTable("users");
        usersTable.addColumn(new IntColumn("id", true, 1));
        usersTable.addColumn(new StringColumn("name", true, 20));

        Map<String, Object> invalidRecord = new HashMap<>();
        invalidRecord.put("id", 0);  // Invalid, because id must be >= 1
        invalidRecord.put("name", "Bob");

        boolean inserted = usersTable.insertRecord(invalidRecord);

        // Assert that the invalid record was not inserted
        assertFalse(inserted, "Record should not be inserted due to invalid ID.");
        assertEquals(0, usersTable.getAllRecords().size(), "There should be no records in the table.");
    }

    @Test
    void testFilterRecords() {
        // Create table, add columns, insert records, and filter by name
        database.createTable("users");
        Table usersTable = database.getTable("users");
        usersTable.addColumn(new IntColumn("id", true, 1));
        usersTable.addColumn(new StringColumn("name", true, 20));

        Map<String, Object> record1 = new HashMap<>();
        record1.put("id", 1);
        record1.put("name", "Alice");
        usersTable.insertRecord(record1);

        Map<String, Object> record2 = new HashMap<>();
        record2.put("id", 2);
        record2.put("name", "Bob");
        usersTable.insertRecord(record2);

        Map<String, Object> record3 = new HashMap<>();
        record3.put("id", 3);
        record3.put("name", "Ash");
        usersTable.insertRecord(record3);
        // Filter records where name is "Alice"
        List<Map<String, Object>> filteredRecords = usersTable.filterRecords("name", "Alice");

        // Assert that only one record is returned
        assertEquals(1, filteredRecords.size(), "There should be one record with the name 'Alice'.");
        assertEquals("Alice", filteredRecords.get(0).get("name"), "The record should have the name 'Alice'.");
    }

    @Test
    void testDeleteTable() {
        // Create a table and delete it
        database.createTable("users");
        database.deleteTable("users");

        // Verify that the table is deleted
        assertNull(database.getTable("users"), "Table 'users' should be deleted.");
    }
}
