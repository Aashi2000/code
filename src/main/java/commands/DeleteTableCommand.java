package commands;

import db.Database;

public class DeleteTableCommand implements DatabaseCommand {
    private String tableName;

    public DeleteTableCommand(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public void execute() {
        Database.getInstance().deleteTable(tableName);
    }
}