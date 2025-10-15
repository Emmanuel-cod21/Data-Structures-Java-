import java.util.*;

/**
 * TaskManager demonstrates the use of different data structures
 * to manage tasks with various behaviors and operations.
 */
public class TaskManager {
    
    public static void main(String[] args) {
        // Create four different data structures to manage tasks
        ArrayList<Task> allTasks = new ArrayList<>();
        Stack<Task> taskStack = new Stack<>();
        Queue<Task> taskQueue = new LinkedList<>();
        PriorityQueue<Task> priorityTasks = new PriorityQueue<>();
        
        System.out.println("=== TASK MANAGER DEMONSTRATION ===\n");
        
        // Create predefined tasks with different priorities
        Task task1 = new Task("Write report", 2);
        Task task2 = new Task("Fix bug", 1);
        Task task3 = new Task("Attend meeting", 3);
        Task task4 = new Task("Update docs", 4);
        Task task5 = new Task("Code review", 2);
        
        // Add tasks to all data structures
        System.out.println("Adding tasks to all data structures...");
        addTaskToAllStructures(task1, allTasks, taskStack, taskQueue, priorityTasks);
        addTaskToAllStructures(task2, allTasks, taskStack, taskQueue, priorityTasks);
        addTaskToAllStructures(task3, allTasks, taskStack, taskQueue, priorityTasks);
        addTaskToAllStructures(task4, allTasks, taskStack, taskQueue, priorityTasks);
        addTaskToAllStructures(task5, allTasks, taskStack, taskQueue, priorityTasks);
        
        System.out.println("Tasks added successfully!\n");
        
        // Display all structures before undo
        System.out.println("=== BEFORE UNDO OPERATION ===");
        displayAllStructures(allTasks, taskStack, taskQueue, priorityTasks);
        
        // Perform undo operation (remove the most recently added task)
        System.out.println("\n=== PERFORMING UNDO OPERATION ===");
        Task undoneTask = performUndo(allTasks, taskStack, taskQueue, priorityTasks);
        if (undoneTask != null) {
            System.out.println("Undo Last Task: " + undoneTask);
        } else {
            System.out.println("No tasks to undo!");
        }
        
        // Display all structures after undo
        System.out.println("\n=== AFTER UNDO OPERATION ===");
        displayAllStructures(allTasks, taskStack, taskQueue, priorityTasks);
        
        // Demonstrate additional operations
        System.out.println("\n=== ADDITIONAL DEMONSTRATIONS ===");
        demonstrateDataStructureBehaviors(allTasks, taskStack, taskQueue, priorityTasks);
    }
    
    /**
     * Add a task to all data structures
     */
    private static void addTaskToAllStructures(Task task, ArrayList<Task> allTasks, 
                                             Stack<Task> taskStack, Queue<Task> taskQueue, 
                                             PriorityQueue<Task> priorityTasks) {
        allTasks.add(task);
        taskStack.push(task);
        taskQueue.offer(task);
        priorityTasks.offer(task);
        System.out.println("  Added: " + task);
    }
    
    /**
     * Perform undo operation by removing the most recently added task
     */
    private static Task performUndo(ArrayList<Task> allTasks, Stack<Task> taskStack, 
                                  Queue<Task> taskQueue, PriorityQueue<Task> priorityTasks) {
        if (taskStack.isEmpty()) {
            return null;
        }
        
        // Use stack to get the most recently added task
        Task lastTask = taskStack.pop();
        
        // Remove from other structures
        allTasks.remove(lastTask);
        taskQueue.remove(lastTask);  // Remove specific task from queue
        priorityTasks.remove(lastTask);  // Remove specific task from priority queue
        
        return lastTask;
    }
    
    /**
     * Display contents of all data structures
     */
    private static void displayAllStructures(ArrayList<Task> allTasks, Stack<Task> taskStack, 
                                           Queue<Task> taskQueue, PriorityQueue<Task> priorityTasks) {
        // ArrayList - maintains insertion order
        System.out.println("All Tasks (ArrayList): " + formatTaskList(allTasks));
        
        // Stack - LIFO (Last In, First Out)
        System.out.println("Tasks in Stack (LIFO): " + formatTaskList(taskStack));
        
        // Queue - FIFO (First In, First Out)  
        System.out.println("Tasks in Queue order: " + formatTaskList(taskQueue));
        
        // PriorityQueue - sorted by priority (lowest number = highest priority)
        System.out.println("Tasks in Priority order: " + formatPriorityQueue(priorityTasks));
    }
    
    /**
     * Format a collection of tasks for display
     */
    private static String formatTaskList(Collection<Task> tasks) {
        if (tasks.isEmpty()) {
            return "[No tasks]";
        }
        
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(task.toString());
        }
        return sb.toString();
    }
    
    /**
     * Format PriorityQueue to show tasks in priority order without modifying the queue
     */
    private static String formatPriorityQueue(PriorityQueue<Task> priorityQueue) {
        if (priorityQueue.isEmpty()) {
            return "[No tasks]";
        }
        
        // Create a copy to avoid modifying the original
        PriorityQueue<Task> copy = new PriorityQueue<>(priorityQueue);
        StringBuilder sb = new StringBuilder();
        
        while (!copy.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(copy.poll().toString());
        }
        return sb.toString();
    }
    
    /**
     * Demonstrate specific behaviors of each data structure
     */
    private static void demonstrateDataStructureBehaviors(ArrayList<Task> allTasks, Stack<Task> taskStack, 
                                                        Queue<Task> taskQueue, PriorityQueue<Task> priorityTasks) {
        System.out.println("Stack peek (top element): " + 
                          (taskStack.isEmpty() ? "Stack is empty" : taskStack.peek()));
        
        System.out.println("Queue peek (front element): " + 
                          (taskQueue.isEmpty() ? "Queue is empty" : taskQueue.peek()));
        
        System.out.println("Priority Queue peek (highest priority): " + 
                          (priorityTasks.isEmpty() ? "Priority queue is empty" : priorityTasks.peek()));
        
        System.out.println("ArrayList size: " + allTasks.size());
        System.out.println("Stack size: " + taskStack.size());
        System.out.println("Queue size: " + taskQueue.size());
        System.out.println("Priority Queue size: " + priorityTasks.size());
        
        // Show ArrayList access by index
        if (!allTasks.isEmpty()) {
            System.out.println("First task in ArrayList: " + allTasks.get(0));
            System.out.println("Last task in ArrayList: " + allTasks.get(allTasks.size() - 1));
        }
    }
}