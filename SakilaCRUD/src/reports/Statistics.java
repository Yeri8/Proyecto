

package reports;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Servicio de estadísticas para cálculos generales.
 */
public class Statistics {

    public double calculateTotal(List<Double> values) {
        return values.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double calculateAverage(List<Double> values) {
        return values.isEmpty() ? 0 : values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public <T, K> Map<K, Double> groupAndSum(List<T> data, Function<T, K> keyMapper, Function<T, Double> valueMapper) {
        return data.stream().collect(Collectors.groupingBy(keyMapper, Collectors.summingDouble(valueMapper::apply)));
    }
}

