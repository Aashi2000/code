package columns;

public class IntColumn extends Column {
    private int minValue;

    public IntColumn(String name, boolean isRequired, int minValue) {
        super(name, isRequired);
        this.minValue = minValue;
    }

    @Override
    public boolean validate(Object value) {
        if (value == null) return !isRequired;
        if (!(value instanceof Integer)) return false;
        return (Integer) value >= minValue;
    }
}