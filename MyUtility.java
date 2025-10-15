/*
 * Generic Data Analyzer Utility Class
 * Author: Emmanuel Chigoirim Uzoma 
 * 
 * What did your utility class do?
 * My GenericDataAnalyzer<T> class is a utility that can analyze collections of any 
 * comparable data type. It stores a collection of items and provides methods to find
 * the minimum, maximum, average (for numeric types), and most frequent element.
 * It also includes a generic method to filter elements based on a condition.
 * 
 * Why did you choose this idea?
 * I chose this idea because data analysis is very common in programming, and making
 * it generic allows it to work with any type of data - numbers, strings, custom objects,
 * etc. This demonstrates the power of generics to write reusable code.
 * 
 * What challenges did you face when using Generics?
 * The main challenge was handling different types of operations. For example, calculating
 * an average only makes sense for numeric types, so I had to use bounded type parameters
 * with <T extends Number>. I also had to be careful about type erasure and make sure
 * my generic methods could work with different types safely.
 * 
 * What errors or bugs did you run into while writing and debugging the program?
 * 1. Initially, I tried to do arithmetic operations directly on generic type T, but got
 *    compile errors because T might not be a number. I fixed this by creating a separate
 *    method with bounded generics <T extends Number>.
 * 2. I got a ClassCastException when trying to compare elements that weren't Comparable.
 *    I fixed this by adding <T extends Comparable<T>> constraint to methods that need comparison.
 * 3. I had issues with null values in my collection causing NullPointerExceptions.
 *    I fixed this by adding null checks throughout my methods.
 */

 import java.util.*;
 import java.util.function.Predicate;
 
 public class GenericDataAnalyzer<T extends Comparable<T>> {
     private List<T> data;
     
     // Constructor
     public GenericDataAnalyzer() {
         this.data = new ArrayList<>();
     }
     
     // Constructor with initial data
     public GenericDataAnalyzer(List<T> initialData) {
         this.data = new ArrayList<>(initialData);
     }
     
     // Add single element
     public void addElement(T element) {
         if (element != null) {
             data.add(element);
         }
     }
     
     // Add multiple elements
     public void addElements(T... elements) {
         for (T element : elements) {
             addElement(element);
         }
     }
     
     // Generic method to find minimum value
     public T findMin() {
         if (data.isEmpty()) {
             return null;
         }
         
         T min = data.get(0);
         for (T item : data) {
             if (item.compareTo(min) < 0) {
                 min = item;
             }
         }
         return min;
     }
     
     // Generic method to find maximum value
     public T findMax() {
         if (data.isEmpty()) {
             return null;
         }
         
         T max = data.get(0);
         for (T item : data) {
             if (item.compareTo(max) > 0) {
                 max = item;
             }
         }
         return max;
     }
     
     // Generic method to find most frequent element
     public T findMostFrequent() {
         if (data.isEmpty()) {
             return null;
         }
         
         Map<T, Integer> frequencyMap = new HashMap<>();
         for (T item : data) {
             frequencyMap.put(item, frequencyMap.getOrDefault(item, 0) + 1);
         }
         
         T mostFrequent = null;
         int maxFrequency = 0;
         for (Map.Entry<T, Integer> entry : frequencyMap.entrySet()) {
             if (entry.getValue() > maxFrequency) {
                 maxFrequency = entry.getValue();
                 mostFrequent = entry.getKey();
             }
         }
         
         return mostFrequent;
     }
     
     // Generic method to filter elements based on a condition
     public <R extends Comparable<R>> List<R> filterAndTransform(List<R> inputData, Predicate<R> condition) {
         List<R> filtered = new ArrayList<>();
         for (R item : inputData) {
             if (item != null && condition.test(item)) {
                 filtered.add(item);
             }
         }
         return filtered;
     }
     
     // Method specific to numeric types - calculate average
     public static <N extends Number> double calculateAverage(List<N> numbers) {
         if (numbers == null || numbers.isEmpty()) {
             return 0.0;
         }
         
         double sum = 0.0;
         int count = 0;
         for (N num : numbers) {
             if (num != null) {
                 sum += num.doubleValue();
                 count++;
             }
         }
         
         return count > 0 ? sum / count : 0.0;
     }
     
     // Get all data
     public List<T> getData() {
         return new ArrayList<>(data);
     }
     
     // Get size
     public int size() {
         return data.size();
     }
     
     // Clear all data
     public void clear() {
         data.clear();
     }
     
     @Override
     public String toString() {
         return "GenericDataAnalyzer{data=" + data + "}";
     }
     
     // Main method to test the utility class
     public static void main(String[] args) {
         System.out.println("=== Testing GenericDataAnalyzer with Integer data ===");
         
         // Test with Integer data
         GenericDataAnalyzer<Integer> intAnalyzer = new GenericDataAnalyzer<>();
         intAnalyzer.addElements(45, 12, 78, 23, 45, 67, 12, 89, 34);
         
         System.out.println("Integer data: " + intAnalyzer.getData());
         System.out.println("Minimum: " + intAnalyzer.findMin());
         System.out.println("Maximum: " + intAnalyzer.findMax());
         System.out.println("Most frequent: " + intAnalyzer.findMostFrequent());
         System.out.println("Average: " + calculateAverage(intAnalyzer.getData()));
         
         // Test generic filter method with integers
         List<Integer> evenNumbers = intAnalyzer.filterAndTransform(
             intAnalyzer.getData(), 
             num -> num % 2 == 0
         );
         System.out.println("Even numbers: " + evenNumbers);
         
         System.out.println("\n=== Testing GenericDataAnalyzer with String data ===");
         
         // Test with String data
         GenericDataAnalyzer<String> stringAnalyzer = new GenericDataAnalyzer<>();
         stringAnalyzer.addElements("apple", "banana", "cherry", "apple", "date", "banana", "elderberry");
         
         System.out.println("String data: " + stringAnalyzer.getData());
         System.out.println("Minimum (alphabetically): " + stringAnalyzer.findMin());
         System.out.println("Maximum (alphabetically): " + stringAnalyzer.findMax());
         System.out.println("Most frequent: " + stringAnalyzer.findMostFrequent());
         
         // Test generic filter method with strings
         List<String> longWords = stringAnalyzer.filterAndTransform(
             stringAnalyzer.getData(),
             word -> word.length() > 5
         );
         System.out.println("Words longer than 5 characters: " + longWords);
         
         System.out.println("\n=== Testing GenericDataAnalyzer with Double data ===");
         
         // Test with Double data
         GenericDataAnalyzer<Double> doubleAnalyzer = new GenericDataAnalyzer<>();
         doubleAnalyzer.addElements(3.14, 2.71, 1.41, 3.14, 2.23, 1.73, 2.71);
         
         System.out.println("Double data: " + doubleAnalyzer.getData());
         System.out.println("Minimum: " + doubleAnalyzer.findMin());
         System.out.println("Maximum: " + doubleAnalyzer.findMax());
         System.out.println("Most frequent: " + doubleAnalyzer.findMostFrequent());
         System.out.println("Average: " + calculateAverage(doubleAnalyzer.getData()));
         
         // Test generic filter method with doubles
         List<Double> largeNumbers = doubleAnalyzer.filterAndTransform(
             doubleAnalyzer.getData(),
             num -> num > 2.0
         );
         System.out.println("Numbers greater than 2.0: " + largeNumbers);
         
         System.out.println("\n=== Testing with Custom Class ===");
         
         // Create some sample Person objects to test with custom class
         List<Person> people = Arrays.asList(
             new Person("Alice", 25),
             new Person("Bob", 30),
             new Person("Charlie", 25),
             new Person("Alice", 25)
         );
         
         GenericDataAnalyzer<Person> personAnalyzer = new GenericDataAnalyzer<>(people);
         System.out.println("Person data: " + personAnalyzer.getData());
         System.out.println("Minimum (by name): " + personAnalyzer.findMin());
         System.out.println("Maximum (by name): " + personAnalyzer.findMax());
         System.out.println("Most frequent: " + personAnalyzer.findMostFrequent());
     }
 }
 
 // Custom class to test generics with user-defined types
 class Person implements Comparable<Person> {
     private String name;
     private int age;
     
     public Person(String name, int age) {
         this.name = name;
         this.age = age;
     }
     
     public String getName() {
         return name;
     }
     
     public int getAge() {
         return age;
     }
     
     @Override
     public int compareTo(Person other) {
         // Compare by name first, then by age
         int nameComparison = this.name.compareTo(other.name);
         if (nameComparison != 0) {
             return nameComparison;
         }
         return Integer.compare(this.age, other.age);
     }
     
     @Override
     public boolean equals(Object obj) {
         if (this == obj) return true;
         if (obj == null || getClass() != obj.getClass()) return false;
         Person person = (Person) obj;
         return age == person.age && Objects.equals(name, person.name);
     }
     
     @Override
     public int hashCode() {
         return Objects.hash(name, age);
     }
     
     @Override
     public String toString() {
         return name + "(" + age + ")";
     }
 }