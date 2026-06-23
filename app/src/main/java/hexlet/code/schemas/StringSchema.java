package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private boolean required;
    private Integer minLength;
    private String contains;

    public StringSchema() {
        addCheck(value -> !required || value != null && !value.isEmpty());
        addCheck(value -> value == null || minLength == null || value.length() >= minLength);
        addCheck(value -> value == null || contains == null || value.contains(contains));
    }

    public StringSchema required() {
        required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        contains = substring;
        return this;
    }

}