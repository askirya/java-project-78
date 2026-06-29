package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        addCheck("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", value -> value == null || value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value -> value == null || value.contains(substring));
        return this;
    }
}