package tasks.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.model.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListUnitTest {
    private static LinkedTaskList taskList;
    private static Date d1;
    private static Date d2;

    @BeforeAll
    static void setUp() {
        taskList = new LinkedTaskList();
        d1 = new GregorianCalendar(2023, Calendar.JANUARY, 27, 12, 0, 0).getTime();
        d2 = new GregorianCalendar(2023, Calendar.JANUARY, 28, 12, 0, 0).getTime();
    }

    @AfterAll
    static void tearDown() {
    }

    @Test
    void add() {
        Task task1 = new Task("task1", d1, d2, 10);
        int size = taskList.size();
        taskList.add(task1);
        assertEquals(taskList.size(), size + 1);
    }

    @Test
    void remove() {
        Task task1 = new Task("task1", d1, d2, 10);
        taskList.add(task1);
        int size = taskList.size();
        taskList.remove(task1);
        assertEquals(taskList.size(), size - 1);
    }
}
