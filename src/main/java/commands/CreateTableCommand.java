package commands;

import db.Database;

public class CreateTableCommand implements DatabaseCommand {
    private String tableName;

    public CreateTableCommand(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void execute() {
        Database.getInstance().createTable(tableName);
    }
}
