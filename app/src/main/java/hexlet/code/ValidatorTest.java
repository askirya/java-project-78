package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ValidatorTest {

    @Test
    void testStringSchemaByDefaultAllowsNullAndEmpty() {
        var validator = new Validator();
        var schema = validator.string();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("")).isTrue();
    }

    @Test
    void testStringSchemaRequiredRejectsNullAndEmpty() {
        var validator = new Validator();
        var schema = validator.string().required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("hexlet")).isTrue();
    }

    @Test
    void testStringSchemaMinLength() {
        var validator = new Validator();
        var schema = validator.string().minLength(5);

        assertThat(schema.isValid("1234")).isFalse();
        assertThat(schema.isValid("12345")).isTrue();
        assertThat(schema.isValid("hexlet")).isTrue();
    }

    @Test
    void testStringSchemaContains() {
        var validator = new Validator();
        var schema = validator.string().contains("hex");

        assertThat(schema.isValid("hexlet")).isTrue();
        assertThat(schema.isValid("java")).isFalse();
    }

    @Test
    void testStringSchemaCombinedRules() {
        var validator = new Validator();
        var schema = validator.string().required().minLength(5).contains("hex");

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid("he")).isFalse();
        assertThat(schema.isValid("hex")).isFalse();
        assertThat(schema.isValid("hexlet")).isTrue();
    }

    @Test
    void testMinLengthLastCallWins() {
        var validator = new Validator();
        var schema = validator.string().minLength(10).minLength(4);

        assertThat(schema.isValid("Hexlet")).isTrue();
        assertThat(schema.isValid("Hex")).isFalse();
    }

    @Test
    void testContainsLastCallWins() {
        var validator = new Validator();
        var schema = validator.string().contains("what").contains("whatthe");

        assertThat(schema.isValid("what does the fox say")).isFalse();
        assertThat(schema.isValid("whatthe fox")).isTrue();
    }
}