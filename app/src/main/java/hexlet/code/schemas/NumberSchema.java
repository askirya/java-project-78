package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean required;
    private boolean positive;
    private Integer rangeMin;
    private Integer rangeMax;

    public NumberSchema() {
        addCheck(value -> !required || value != null);
        addCheck(value -> value == null || !positive || value > 0);
        addCheck(value -> value == null || rangeMin == null || value >= rangeMin);
        addCheck(value -> value == null || rangeMax == null || value <= rangeMax);
    }

    public NumberSchema required() {
        required = true;
        return this;
    }

    public NumberSchema positive() {
        positive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        rangeMin = min;
        rangeMax = max;
        return this;
    }

}