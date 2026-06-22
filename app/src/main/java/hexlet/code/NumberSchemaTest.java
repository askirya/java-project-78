package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberSchemaTest {

    @Test
    void testNumberSchemaDefaultValueIsNullValid() {
        var validator = new Validator();
        var schema = validator.number();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(5)).isTrue();
    }

    @Test
    void testNumberSchemaRequiredRejectsNull() {
        var validator = new Validator();
        var schema = validator.number().required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(10)).isTrue();
    }

    @Test
    void testNumberSchemaPositive() {
        var validator = new Validator();
        var schema = validator.number().positive();

        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(-10)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
    }

    @Test
    void testNumberSchemaRange() {
        var validator = new Validator();
        var schema = validator.number().range(5, 10);

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    void testNumberSchemaCombinedRules() {
        var validator = new Validator();
        var schema = validator.number().required().positive().range(5, 10);

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(11)).isFalse();
        assertThat(schema.isValid(-5)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
    }

    @Test
    void testRequiredLastCallWins() {
        var validator = new Validator();
        var schema = validator.number().required();

        assertThat(schema.isValid(null)).isFalse();
    }

    @Test
    void testPositiveAndRangeCombined() {
        var validator = new Validator();
        var schema = validator.number().positive().range(5, 10);

        assertThat(schema.isValid(-5)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(15)).isFalse();
    }
}