import java.util.List;

public class WeightedSelection<T> {
    private final List<T> items;
        // private: only exists in the class, cannot be accessed outside
        // final: can only be assigned once, but items inside the list can be .add(), .remove(), etc.
        // <T> is a sort of parameter to specify which type of WeightedSelection is being given, etc. String, Integer, Double
            // why do the primitive types have to be capitalized?
        // im simply documenting my learning, these comments are acting as my notes
    private final List<Double> weights;
    private final double totalWeight;

    private Rand random;

    public WeightedSelection(List<T> items, List<Double> weights) {
        if (items.size() != weights.size()) {
            throw new IllegalArgumentException("Items and weights must have the same size.");
        }

        this.items = items;
        this.weights = weights;
        this.totalWeight = weights.stream().mapToDouble(Double::doubleValue).sum();
            // .stream() turns the List into something of a "conveyor belt", letting other operators like
            // "mapToDouble()", or "filter()", to act upon each item in that conveyor belt
            // .mapToDouble(Double::doubleValue) unwraps each Double object into a primitive double value.
            // .sum() gets all of these primitive doubles and adds them together
    }

    public T selectRandomItem() {
        // T is the type given when calling WeightedSelection<T> variableName = new WeightedSelection<>();
        // T could be String, Integer, Double
        // general object T must be returned

        double randomValue = Rand.randomDouble() * totalWeight;
            // a random float between 0.0 and totalWeight
        double cumulativeWeight = 0.0;

        for (int i = 0; i < items.size(); i++) {
            // https://www.youtube.com/watch?v=9oiZZEJs37A
            // i dont 100% get this certain for loop

            cumulativeWeight += weights.get(i);

            if (randomValue < cumulativeWeight) {
                return items.get(i);
            }
        }
        // Should not be reached if totalWeight is calculated correctly
        throw new RuntimeException("Should not reach here. Check weights.");
    }
}
