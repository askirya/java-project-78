package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean required;
    private boolean positive;
    private Integer rangeMin;
    private Integer rangeMax;

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

    @Override
    public boolean isValid(Integer value) {
        if (value == null) {
            return !required;
        }

        if (positive && value <= 0) {
            return false;
        }

        if (rangeMin != null && value < rangeMin) {
            return false;
        }

        if (rangeMax != null && value > rangeMax) {
            return false;
        }

        return true;
    }
}