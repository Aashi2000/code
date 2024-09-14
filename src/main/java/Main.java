import commands.*;
import db.Database;
import db.Table;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create a table
        Database db = Database.getInstance();

        DatabaseCommand createTableCommand = new CreateTableCommand("users");
        createTableCommand.execute();

        // Add columns to the table
        DatabaseCommand addIdColumn = new AddColumnCommand("users", "id", "int", true, 1024);
        DatabaseCommand addNameColumn = new AddColumnCommand("users", "name", "string", true, 20);
        addIdColumn.execute();
        addNameColumn.execute();

        Map<String, Object> recordWithId = new HashMap<>();
        recordWithId.put("name", "Alie");
        recordWithId.put("id", 1030);

        DatabaseCommand insertRecordWithId = new InsertRecordCommand("users", recordWithId);
        insertRecordWithId.execute();

//        Map<String, Object> recordWithoutId = new HashMap<>();
//        recordWithoutId.put("name", "Alice");
//        DatabaseCommand insertRecordWithoutId = new InsertRecordCommand("users", recordWithoutId);
//        insertRecordWithoutId.execute();

        // Insert records
        Map<String, Object> record1 = new HashMap<>();
        record1.put("id", 1027);
        record1.put("name", "Aashi");
        DatabaseCommand insertRecord1 = new InsertRecordCommand("users", record1);
        insertRecord1.execute();

        Map<String, Object> record2 = new HashMap<>();
        record2.put("id", 1028);
        record2.put("name", "Bo777777777777777777777777777b");
        DatabaseCommand insertRecord2 = new InsertRecordCommand("users", record2);
        insertRecord2.execute();

        // Print all records
        Table usersTable = Database.getInstance().getTable("users");
        System.out.println("All records:");
        System.out.println(usersTable.getAllRecords());

        // Filter records
        System.out.println("Filtered records (name = Aashi):");
        System.out.println(usersTable.filterRecords("name", "Alice"));

        // Delete table
        DatabaseCommand deleteTableCommand = new DeleteTableCommand("users");
        deleteTableCommand.execute();
//
//
//        db.deleteTable("users");
//
//        // Check if the table is deleted (it should be now)
//        db.isTableDeleted("users");
    }
}