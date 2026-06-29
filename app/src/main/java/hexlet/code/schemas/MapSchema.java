package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        addCheck("required", value -> value != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> value == null || value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        addCheck("shape", value -> value == null || isShapeValid(value, schemas));
        return this;
    }

    private boolean isShapeValid(Map<?, ?> value, Map<String, ? extends BaseSchema<?>> schemas) {
        for (var entry : schemas.entrySet()) {
            if (!isValidBySchema(entry.getValue(), value.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private <T> boolean isValidBySchema(BaseSchema<T> schema, Object value) {
        return schema.isValid((T) value);
    }
}