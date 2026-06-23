package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MapSchemaTest {

    @Test
    void testMapSchemaAllowsNullByDefault() {
        var validator = new Validator();
        var schema = validator.map();

        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    void testMapSchemaRequiredRejectsNull() {
        var validator = new Validator();
        var schema = validator.map().required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
    }

    @Test
    void testMapSchemaSizeof() {
        var validator = new Validator();
        var schema = validator.map().sizeof(2);

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertThat(schema.isValid(data)).isFalse();

        data.put("key2", "value2");

        assertThat(schema.isValid(data)).isTrue();
    }

    @Test
    void testShapeValidatesFirstNameAndLastName() {
        var validator = new Validator();
        var schema = validator.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", "Smith");

        assertThat(schema.isValid(human)).isTrue();
    }

    @Test
    void testShapeRejectsNullLastName() {
        var validator = new Validator();
        var schema = validator.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", null);

        assertThat(schema.isValid(human)).isFalse();
    }

    @Test
    void testShapeRejectsLastNameTooShort() {
        var validator = new Validator();
        var schema = validator.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "Anna");
        human.put("lastName", "B");

        assertThat(schema.isValid(human)).isFalse();
    }

    @Test
    void testShapeRequiresConfiguredKeysWhenTheirSchemasAreRequired() {
        var validator = new Validator();
        var schema = validator.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");

        assertThat(schema.isValid(human)).isFalse();
    }

    @Test
    void testShapeWithCombinedRules() {
        var validator = new Validator();
        var schema = validator.map().required().sizeof(2);

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required().minLength(3));
        schemas.put("age", validator.number().required().positive());

        schema.shape(schemas);

        Map<String, Object> validPerson = new HashMap<>();
        validPerson.put("firstName", "John");
        validPerson.put("age", 25);

        assertThat(schema.isValid(validPerson)).isTrue();

        Map<String, Object> shortNamePerson = new HashMap<>();
        shortNamePerson.put("firstName", "Jo");
        shortNamePerson.put("age", 25);

        assertThat(schema.isValid(shortNamePerson)).isFalse();

        Map<String, Object> negativeAgePerson = new HashMap<>();
        negativeAgePerson.put("firstName", "John");
        negativeAgePerson.put("age", -5);

        assertThat(schema.isValid(negativeAgePerson)).isFalse();
    }

    @Test
    void testShapeAllowsExtraKeys() {
        var validator = new Validator();
        var schema = validator.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());

        schema.shape(schemas);

        Map<String, String> person = new HashMap<>();
        person.put("firstName", "John");
        person.put("extra", "value");

        assertThat(schema.isValid(person)).isTrue();
    }
}
