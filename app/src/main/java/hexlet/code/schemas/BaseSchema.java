package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final List<Predicate<T>> checks = new ArrayList<>();

    protected final void addCheck(Predicate<T> check) {
        checks.add(check);
    }

    public final boolean isValid(T value) {
        return checks.stream().allMatch(check -> check.test(value));
    }

    @SuppressWarnings("unchecked")
    public final boolean isValidObject(Object value) {
        try {
            return isValid((T) value);
        } catch (ClassCastException exception) {
            return false;
        }
    }
}