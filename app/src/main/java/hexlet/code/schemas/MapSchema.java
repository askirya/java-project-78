package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private boolean required;
    private Integer sizeof;
    private Map<String, ? extends BaseSchema<?>> shape;

    public MapSchema() {
        addCheck(value -> !required || value != null);
        addCheck(value -> value == null || sizeof == null || value.size() == sizeof);
        addCheck(this::isShapeValid);
    }

    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        sizeof = size;
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        shape = schemas;
        return this;
    }

    private boolean isShapeValid(Map<?, ?> value) {
        if (value == null || shape == null) {
            return true;
        }

        for (Map.Entry<String, ? extends BaseSchema<?>> entry : shape.entrySet()) {
            if (!entry.getValue().isValidObject(value.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }
}