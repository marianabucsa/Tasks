package tasks.model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@Tag("TaskTest")
class TaskTest {

    @Test
    void testCreateTask() throws ParseException {
        Task task = new Task("task", Task.getDateFormat().parse("2023-04-01 12:00"), Task.getDateFormat().parse("2023-04-02 12:00"), 2);

        Assertions.assertEquals("task", task.getTitle());
        Assertions.assertEquals(Task.getDateFormat().parse("2023-04-01 12:00"), task.getStartTime());
        Assertions.assertEquals(Task.getDateFormat().parse("2023-04-02 12:00"), task.getEndTime());
        Assertions.assertEquals(2, task.getRepeatInterval());
    }

    @Test
    void testCreateTaskTitleError() throws ParseException {
        try {
            Task task = new Task("", Task.getDateFormat().parse("2023-04-01 12:00"), Task.getDateFormat().parse("2023-04-02 12:00"), 2);
            assertTrue(false);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            assertTrue(e.getMessage().contains("Invalid title"));
        }
    }

    @Test
    void testCreateTaskDateError() throws ParseException {
        try {
            Task task = new Task("task", Task.getDateFormat().parse("2023-04-02 12:00"), Task.getDateFormat().parse("2023-04-01 12:00"), -3);
            assertTrue(false);
        } catch(IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Start date cannot be after end date"));
        }
    }

    @Test
    void testCreateTaskIntervalError() throws ParseException {
        try {
            Task task = new Task("task", Task.getDateFormat().parse("2023-04-02 12:00"), Task.getDateFormat().parse("2023-04-01 12:00"), -3);
        }catch(IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Interval should be >= 1"));
        }
    }

    @Disabled("do not run test!!!!")
    @Test
    void dummyTest() {
        assertTrue(1 == 2);
    }
}