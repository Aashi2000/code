package db;

import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Database instance;
    private Map<String, Table> tables;

    private Database() {
        this.tables = new HashMap<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void createTable(String name) {
        tables.put(name, new Table(name));
    }

    public Table getTable(String name) {
        return tables.get(name);
    }

    public void deleteTable(String name) {
        Table table = tables.get(name);
        if (table != null) {
            table.deleteTable();
            tables.remove(name);
            System.out.println("Table '" + name + "' has been deleted.");
        } else {
            System.out.println("Table '" + name + "' does not exist.");
        }
    }

    public boolean isTableDeleted(String name) {
        Table table = tables.get(name);
        if (table == null) {
            System.out.println("Table '" + name + "' does not exist in the database.");
            return true; // Consider non-existent tables as deleted
        }
        boolean deleted = table.isDeleted();
        System.out.println("Table '" + name + "' " + (deleted ? "is" : "is not") + " deleted.");
        return deleted;
    }
}
