import java.util.Arrays;
import java.util.Random;

public class SortingComparison {
    
    // Class to store sorting results
    static class SortResult {
        long timeTaken;
        long comparisons;
        
        SortResult(long timeTaken, long comparisons) {
            this.timeTaken = timeTaken;
            this.comparisons = comparisons;
        }
    }
    
    // Bubble Sort implementation with comparison counting
    public static SortResult bubbleSort(int[] arr) {
        long comparisons = 0;
        long startTime = System.nanoTime();
        
        int n = arr.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                comparisons++; // Count this comparison
                if (arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no swapping occurred, array is sorted
            if (!swapped) break;
        }
        
        long endTime = System.nanoTime();
        long timeTaken = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        
        return new SortResult(timeTaken, comparisons);
    }
    
    // Merge Sort implementation with comparison counting
    private static long mergeComparisons = 0;
    
    public static SortResult mergeSort(int[] arr) {
        mergeComparisons = 0; // Reset counter
        long startTime = System.nanoTime();
        
        mergeSortHelper(arr, 0, arr.length - 1);
        
        long endTime = System.nanoTime();
        long timeTaken = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        
        return new SortResult(timeTaken, mergeComparisons);
    }
    
    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            // Recursively sort both halves
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            
            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        // Create temporary arrays for left and right subarrays
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];
        
        // Copy data to temporary arrays
        System.arraycopy(arr, left, leftArr, 0, leftArr.length);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightArr.length);
        
        // Merge the temporary arrays back into arr[left..right]
        int i = 0, j = 0, k = left;
        
        while (i < leftArr.length && j < rightArr.length) {
            mergeComparisons++; // Count this comparison
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements
        while (i < leftArr.length) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < rightArr.length) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    
    // Method to generate random array
    public static int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000); // Random integers from 0 to 9999
        }
        return arr;
    }
    
    // Method to copy array
    public static int[] copyArray(int[] original) {
        return Arrays.copyOf(original, original.length);
    }
    
    // Method to verify if array is sorted
    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        final int ARRAY_SIZE = 1000;
        
        System.out.println("=== Sorting Algorithm Comparison ===");
        System.out.println("Array size: " + ARRAY_SIZE + " elements\n");
        
        // Generate original array
        int[] originalArray = generateRandomArray(ARRAY_SIZE);
        
        // Show first 10 elements of original array
        System.out.print("Original array (first 10 elements): ");
        for (int i = 0; i < Math.min(10, originalArray.length); i++) {
            System.out.print(originalArray[i] + " ");
        }
        System.out.println("...\n");
        
        // Test Bubble Sort
        System.out.println("--- BUBBLE SORT ---");
        int[] bubbleArray = copyArray(originalArray);
        SortResult bubbleResult = bubbleSort(bubbleArray);
        
        System.out.println("Time taken: " + bubbleResult.timeTaken + " ms");
        System.out.println("Number of comparisons: " + bubbleResult.comparisons);
        System.out.println("Array sorted correctly: " + isSorted(bubbleArray));
        
        // Show first 10 elements of sorted array
        System.out.print("Sorted array (first 10 elements): ");
        for (int i = 0; i < Math.min(10, bubbleArray.length); i++) {
            System.out.print(bubbleArray[i] + " ");
        }
        System.out.println("...\n");
        
        // Test Merge Sort
        System.out.println("--- MERGE SORT ---");
        int[] mergeArray = copyArray(originalArray);
        SortResult mergeResult = mergeSort(mergeArray);
        
        System.out.println("Time taken: " + mergeResult.timeTaken + " ms");
        System.out.println("Number of comparisons: " + mergeResult.comparisons);
        System.out.println("Array sorted correctly: " + isSorted(mergeArray));
        
        // Show first 10 elements of sorted array
        System.out.print("Sorted array (first 10 elements): ");
        for (int i = 0; i < Math.min(10, mergeArray.length); i++) {
            System.out.print(mergeArray[i] + " ");
        }
        System.out.println("...\n");
        
        // Performance comparison
        System.out.println("=== PERFORMANCE COMPARISON ===");
        System.out.println("Bubble Sort:");
        System.out.println("  Time: " + bubbleResult.timeTaken + " ms");
        System.out.println("  Comparisons: " + bubbleResult.comparisons);
        
        System.out.println("\nMerge Sort:");
        System.out.println("  Time: " + mergeResult.timeTaken + " ms");
        System.out.println("  Comparisons: " + mergeResult.comparisons);
        
        System.out.println("\nSpeed improvement (Merge vs Bubble): " + 
            String.format("%.2fx faster", (double) bubbleResult.timeTaken / mergeResult.timeTaken));
        System.out.println("Comparison reduction (Merge vs Bubble): " + 
            String.format("%.2fx fewer", (double) bubbleResult.comparisons / mergeResult.comparisons));
    }
}