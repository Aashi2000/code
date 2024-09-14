package commands;

import db.Database;
import db.Table;

import java.util.Map;

public class InsertRecordCommand implements DatabaseCommand {
    private String tableName;
    private Map<String, Object> record;

    public InsertRecordCommand(String tableName, Map<String, Object> record) {
        this.tableName = tableName;
        this.record = record;
    }

    @Override
    public void execute() {
        Table table = Database.getInstance().getTable(tableName);
        table.insertRecord(record);
    }
}