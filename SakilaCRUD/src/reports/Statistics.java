

package reports;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Servicio de estadísticas para cálculos generales.
 * Esta clase proporciona métodos para realizar cálculos estadísticos generales sobre listas de valores numéricos.
 */
public class Statistics {

    /**
     * Calcula el total de una lista de valores.
     * 
     * @param values Lista de valores de tipo Double sobre los que se va a calcular el total.
     * @return El total de los valores de la lista.
     */
    public double calculateTotal(List<Double> values) {
        // Suma todos los valores de la lista usando el flujo (stream) y devuelve el total
        return values.stream().mapToDouble(Double::doubleValue).sum();
    }

    /**
     * Calcula el promedio de una lista de valores.
     * 
     * @param values Lista de valores de tipo Double sobre los que se va a calcular el promedio.
     * @return El promedio de los valores de la lista. Si la lista está vacía, devuelve 0.
     */
    public double calculateAverage(List<Double> values) {
        // Si la lista está vacía, devuelve 0. De lo contrario, calcula el promedio.
        return values.isEmpty() ? 0 : values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    /**
     * Agrupa los datos y suma los valores asociados a cada grupo.
     * 
     * @param <T> Tipo de los elementos en la lista de entrada.
     * @param <K> Tipo de la clave de agrupación.
     * @param data Lista de datos sobre los cuales realizar la agrupación y suma.
     * @param keyMapper Función que mapea cada elemento a su clave de agrupación.
     * @param valueMapper Función que mapea cada elemento a su valor numérico para ser sumado.
     * @return Un mapa con las claves de agrupación y la suma de los valores asociados a cada clave.
     */
    public <T, K> Map<K, Double> groupAndSum(List<T> data, Function<T, K> keyMapper, Function<T, Double> valueMapper) {
        // Agrupa los elementos según la clave y luego suma los valores asociados a cada grupo
        return data.stream().collect(Collectors.groupingBy(keyMapper, Collectors.summingDouble(valueMapper::apply)));
    }
}
