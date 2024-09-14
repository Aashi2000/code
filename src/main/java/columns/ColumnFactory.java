package columns;

public class ColumnFactory {
    public static Column createColumn(String name, String type, boolean isRequired, Object constraint) {

        if (type.equalsIgnoreCase("int")) {
            int minValue = constraint instanceof Integer ? (Integer) constraint : 1024;
            return new IntColumn(name, isRequired, minValue);
        } else if (type.equalsIgnoreCase("string")) {
            int maxLength = constraint instanceof Integer ? (Integer) constraint : 20;
            return new StringColumn(name, isRequired, maxLength);
        }
        throw new IllegalArgumentException("Unsupported column type: " + type);
    }
}