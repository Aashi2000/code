package columns;

public class StringColumn extends Column {
    private int maxLength;

    public StringColumn(String name, boolean isRequired, int maxLength) {
        super(name, isRequired);
        this.maxLength = maxLength;
    }

    @Override
    public boolean validate(Object value) {
        if (value == null) return !isRequired;
        if (!(value instanceof String)) return false;
        return ((String) value).length() <= maxLength;
    }
}
