package commands;

import columns.ColumnFactory;
import db.Table;

import db.Database; // Added import for Database
import columns.Column; // Added import for Column


public class AddColumnCommand implements DatabaseCommand {
    private String tableName;
    private String columnName;
    private String columnType;
    private boolean isRequired;
    private Object constraint;

    public AddColumnCommand(String tableName, String columnName, String columnType, boolean isRequired, Object constraint) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.columnType = columnType;
        this.isRequired = isRequired;
        this.constraint = constraint;
    }

    @Override
    public void execute() {
        Table table = Database.getInstance().getTable(tableName);
        Column column = ColumnFactory.createColumn(columnName, columnType, isRequired, constraint);
        if (column.isRequired()) {
            System.out.println("The column " + columnName + " is required.");
        }
        table.addColumn(column);
    }
}
