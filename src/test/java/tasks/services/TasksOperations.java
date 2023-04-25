package tasks.services;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.model.TasksOperations;

import java.security.cert.TrustAnchor;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TasksOperationsTest {

    private TasksOperations tasksOperations;
    private TasksOperations nullTasksOperations;
    private ArrayList<Task> incomingTasks;
    private Date start, end;

    @BeforeEach
    void setUp() {

        incomingTasks = new ArrayList<>();
        start = new GregorianCalendar(2023, Calendar.JANUARY, 27, 10, 0, 0).getTime();
        end = new GregorianCalendar(2023, Calendar.JANUARY, 30, 10, 0, 0).getTime();

        Date d1 = new GregorianCalendar(2023, Calendar.JANUARY, 27, 12, 0, 0).getTime();
        Date d2 = new GregorianCalendar(2023, Calendar.JANUARY, 28, 12, 0, 0).getTime();
        Date d3 = new GregorianCalendar(2023, Calendar.JANUARY, 29, 12, 0, 0).getTime();
        Task task1 = new Task("task1", d1, d2, 10);
        task1.setActive(true);

        Task task2 = new Task("task2", d1, d3, 10);
        task2.setActive(true);

        Task task3 = new Task("task3", d2, d3, 10);
        task3.setActive(true);

        incomingTasks.addAll(Arrays.asList(task1, task2, task3));
        tasksOperations = new TasksOperations(FXCollections.observableArrayList(incomingTasks));
        nullTasksOperations = new TasksOperations(FXCollections.observableArrayList(new ArrayList<>()));
    }

    @AfterEach
    void tearDown() {
    }

    /*
         Invalid period of time
         start = null
         end = 31.01.2023 10:00
    */
    @Test
    public void F02_TC01_nonValid() {
        try {
            Iterable<Task> tasks = tasksOperations.incoming(null, end);
            assertTrue(false);
        } catch(RuntimeException e) {
            assertTrue(e.getMessage().contains("Start and End date cannot be null."));
        }
    }

    /*
        Valid period of time
        start = 20.05.2021 10:00
        end = 30.05.2021 10:00
     */

    @Test
    public void F02_TC02_valid() {

        List<Task> result = new ArrayList<>();
        nullTasksOperations.incoming(start, end).forEach(result::add);
        assertEquals(0, result.size());
    }

    /*
    Invalid period of time
    start = 31.01.2021 10:00
    end = null
 */
    @Test
    public void F02_TC03_nonValid() {
        try {
            Iterable<Task> tasks = tasksOperations.incoming(start, null);
            assertTrue(false);
        } catch(RuntimeException e) {
            assertTrue(e.getMessage().contains("Start and End date cannot be null."));
        }
    }

    /*
        Valid period of time
        start = 27.01.2023 10:00
        end = 30.01.2023 10:00
     */
    @Test
    public void F02_TC04_valid() {

        List<Task> result = new ArrayList<>();
        tasksOperations.incoming(start, end).forEach(result::add);
        assertEquals(3, result.size());
    }

}



