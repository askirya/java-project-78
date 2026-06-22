package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private boolean required;
    private Integer sizeof;

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        sizeof = size;
        return this;
    }

    @Override
    public boolean isValid(Map<?, ?> value) {
        if (value == null) {
            return !required;
        }

        if (sizeof != null && value.size() != sizeof) {
            return false;
        }

        return true;
    }
}