/**
 * Task class represents an individual task with a name and priority level.
 * Implements Comparable to allow sorting by priority (1 = highest, 5 = lowest).
 */
public class Task implements Comparable<Task> {
    private String name;
    private int priority;
    
    /**
     * Constructor to create a new task
     * @param name The task description
     * @param priority The task priority (1 = highest, 5 = lowest)
     */
    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
    
    /**
     * Get the task name
     * @return The task name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get the task priority
     * @return The task priority
     */
    public int getPriority() {
        return priority;
    }
    
    /**
     * Set the task name
     * @param name The new task name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Set the task priority
     * @param priority The new task priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    /**
     * Compare tasks by priority for sorting
     * Lower priority numbers come first (1 = highest priority)
     * @param other The task to compare with
     * @return Negative if this task has higher priority, positive if lower, 0 if equal
     */
    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }
    
    /**
     * String representation of the task
     * @return Formatted string showing priority and task name
     */
    @Override
    public String toString() {
        return "[Priority " + priority + "] " + name;
    }
    
    /**
     * Check if two tasks are equal based on name and priority
     * @param obj The object to compare with
     * @return True if tasks are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return priority == task.priority && name.equals(task.name);
    }
    
    /**
     * Generate hash code for the task
     * @return Hash code based on name and priority
     */
    @Override
    public int hashCode() {
        return name.hashCode() * 31 + priority;
    }
}