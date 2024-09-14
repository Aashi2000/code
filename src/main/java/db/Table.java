package db;

import columns.Column;

import java.util.*;

public class Table {
    private String name;
    private List<Column> columns;
    private List<Map<String, Object>> records;
    private boolean isDeleted;

    public Table(String name) {
        this.name = name;
        this.columns = new ArrayList<>();
        this.records = new ArrayList<>();
        this.isDeleted = false;
    }

    public void addColumn(Column column) {
        if (!isDeleted) {
            columns.add(column);
        } else {
            throw new IllegalStateException("Cannot modify a deleted table.");
        }    }

    public void insertRecord(Map<String, Object> record) {
        if (!isDeleted) {
            for (Column column : columns) {
                Object value = record.get(column.getName());
                if (!column.validate(value)) {
                    System.out.println("Validation failed for column: " + column + "with value" + value);
                    return;
                }
            }
            records.add(record);
        } else {
            throw new IllegalStateException("Cannot insert into a deleted table.");
        }
    }

    public List<Map<String, Object>> getAllRecords() {
        if (!isDeleted) {
            return new ArrayList<>(records);
        } else {
            throw new IllegalStateException("Cannot retrieve records from a deleted table.");
        }    }

    public List<Map<String, Object>> filterRecords(String columnName, Object value) {
        List<Map<String, Object>> filteredRecords = new ArrayList<>();
        for (Map<String, Object> record : records) {
            if (Objects.equals(record.get(columnName), value)) {
                filteredRecords.add(record);
            }
        }
        return filteredRecords;
    }

    public void deleteTable() {
        this.isDeleted = true;
        this.columns.clear();
        this.records.clear();
    }

    public boolean isDeleted() {
        return isDeleted;
    }
    public String getName() {
        return name;
    }
}