package Phase_3_Generics_Collection;

// ─────────────────────────────────────────────────────────────
// PHASE 3 — L6: Generics
//
// Generics = write code that works with ANY type, but type-safe.
// The <T> is a "type parameter" — a placeholder for any class.
//
// Without Generics:
//   List list = new ArrayList();      // raw type — no safety
//   list.add("hello");
//   list.add(42);                     // different types — compiles!
//   String s = (String) list.get(1);  // ClassCastException at RUNTIME
//
// With Generics:
//   List<String> list = new ArrayList<>();
//   list.add(42);  // ❌ COMPILE ERROR — caught early!
//
// WHY it matters: Spring Boot, JPA, REST responses — all use Generics.
// ResponseEntity<T>, Optional<T>, List<T>, Page<T>, CompletableFuture<T>
//
// Type parameter naming conventions:
//   T → Type (general)
//   E → Element (in collections)
//   K → Key
//   V → Value
//   N → Number
//   R → Return type
// ─────────────────────────────────────────────────────────────

import java.util.*;
import java.util.function.Predicate;

// ── GENERIC CLASS ─────────────────────────────────────────────
// Like a typed box that works for any type T
class Box<T> {

    private T content;   // T is replaced at compile time with actual type

    public Box(T content) {
        this.content = content;
    }

    public T getContent() { return content; }      // returns T
    public void setContent(T content) { this.content = content; }

    public boolean isEmpty() { return content == null; }

    @Override
    public String toString() {
        return "Box[" + content + "]";
    }
}

// ── GENERIC CLASS WITH MULTIPLE TYPE PARAMS ────────────────────
// Like Map.Entry<K, V>
class Pair<K, V> {

    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key   = key;
        this.value = value;
    }

    public K getKey()   { return key; }
    public V getValue() { return value; }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    // Static factory method — cleaner creation syntax
    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<>(key, value);
    }
}

// ── GENERIC METHODS ───────────────────────────────────────────
// The <T> before return type declares it as a generic method
class GenericUtils {

    // Works on any type of array
    public static <T> void printArray(T[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Find max — T must be Comparable (bounded type parameter)
    // <T extends Comparable<T>> means T can be Integer, String, Double, etc.
    // but NOT random custom classes that don't implement Comparable
    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list.isEmpty()) throw new IllegalArgumentException("Empty list!");
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    // Filter list by predicate — like .filter() in JS
    public static <T> List<T> filter(List<T> list, Predicate<T> condition) {
        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (condition.test(item)) {
                result.add(item);
            }
        }
        return result;
    }

    // Swap two elements in a list — works for any type
    public static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

// ── BOUNDED WILDCARDS (? extends T / ? super T) ───────────────
// ? = unknown type   (wildcard)
// ? extends T = unknown type that IS-A T  (upper bound) — READ only
// ? super T   = unknown type that T IS-A  (lower bound) — WRITE ok
//
// Easy mnemonic: PECS — Producer Extends, Consumer Super
//   If you're READING (producing) from it → ? extends T
//   If you're WRITING (consuming) into it → ? super T

class WildcardDemo {

    // Accepts List of Number or any subtype (Integer, Double, Long...)
    // You can READ from it — but cannot ADD (type unsafe)
    public static double sumList(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    // Accepts List of Integer or any supertype (Number, Object)
    // You can ADD Integers into it
    public static void addNumbers(List<? super Integer> list) {
        list.add(10);
        list.add(20);
        list.add(30);
    }
}

public class GenericsDemo {
    public static void main(String[] args) {

        // ── Generic Box ───────────────────────────────────────
        Box<String>  nameBox  = new Box<>("Rashid");
        Box<Integer> ageBox   = new Box<>(25);
        Box<Double>  priceBox = new Box<>(99.99);

        System.out.println(nameBox.getContent());   // Rashid
        System.out.println(ageBox.getContent());    // 25
        System.out.println(priceBox);               // Box[99.99]

        // ── Generic Pair ──────────────────────────────────────
        Pair<String, Integer> nameAge  = Pair.of("Rashid", 25);
        Pair<String, String>  keyValue = Pair.of("role", "developer");
        System.out.println(nameAge);    // (Rashid, 25)
        System.out.println(keyValue);   // (role, developer)

        // ── Generic Methods ───────────────────────────────────
        Integer[] intArr = {3, 1, 4, 1, 5, 9, 2, 6};
        String[]  strArr = {"Java", "Python", "Go"};
        GenericUtils.printArray(intArr);  // [3, 1, 4, 1, 5, 9, 2, 6]
        GenericUtils.printArray(strArr);  // [Java, Python, Go]

        List<Integer> nums   = Arrays.asList(10, 50, 30, 20, 40);
        List<String>  words  = Arrays.asList("apple", "banana", "cherry");
        System.out.println(GenericUtils.findMax(nums));   // 50
        System.out.println(GenericUtils.findMax(words));  // cherry (alphabetical)

        // Filter — pass lambda as Predicate<T>
        List<Integer> evens = GenericUtils.filter(nums, n -> n % 2 == 0);
        System.out.println("Evens: " + evens);   // [10, 50, 30, 20, 40] — all even

        List<String> longWords = GenericUtils.filter(words, w -> w.length() > 5);
        System.out.println("Long: " + longWords); // [banana, cherry]

        // ── Wildcards ─────────────────────────────────────────
        List<Integer> ints    = Arrays.asList(1, 2, 3, 4, 5);
        List<Double>  doubles = Arrays.asList(1.1, 2.2, 3.3);
        System.out.println(WildcardDemo.sumList(ints));    // 15.0
        System.out.println(WildcardDemo.sumList(doubles)); // 6.6

        List<Number> numList = new ArrayList<>();
        WildcardDemo.addNumbers(numList);   // adds 10, 20, 30
        System.out.println(numList);        // [10, 20, 30]
    }
    
}
