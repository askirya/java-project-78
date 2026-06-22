package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class MapSchemaTest {

    @Test
    void testMapSchemaDefaultValueIsNullValid() {
        var validator = new Validator();
        var schema = validator.map();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
    }

    @Test
    void testMapSchemaRequiredRejectsNull() {
        var validator = new Validator();
        var schema = validator.map().required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
    }

    @Test
    void testMapSchemaRequiredAllowsEmptyMap() {
        var validator = new Validator();
        var schema = validator.map().required();

        HashMap<String, String> data = new HashMap<>();
        assertThat(schema.isValid(data)).isTrue();
    }

    @Test
    void testMapSchemaSizeof() {
        var validator = new Validator();
        var schema = validator.map().sizeof(2);

        HashMap<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertThat(schema.isValid(data)).isFalse();

        data.put("key2", "value2");

        assertThat(schema.isValid(data)).isTrue();
    }

    @Test
    void testMapSchemaCombinedRules() {
        var validator = new Validator();
        var schema = validator.map().required().sizeof(2);

        assertThat(schema.isValid(null)).isFalse();

        HashMap<String, String> data1 = new HashMap<>();
        data1.put("key1", "value1");
        assertThat(schema.isValid(data1)).isFalse();

        HashMap<String, String> data2 = new HashMap<>();
        data2.put("key1", "value1");
        data2.put("key2", "value2");
        assertThat(schema.isValid(data2)).isTrue();

        HashMap<String, String> data3 = new HashMap<>();
        data3.put("key1", "value1");
        data3.put("key2", "value2");
        data3.put("key3", "value3");
        assertThat(schema.isValid(data3)).isFalse();
    }

    @Test
    void testMapSchemaSizeofWithEmptyMap() {
        var validator = new Validator();
        var schema = validator.map().sizeof(0);

        assertThat(schema.isValid(new HashMap<>())).isTrue();

        HashMap<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.isValid(data)).isFalse();
    }
}