package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskUnitTest {
    private Task task;
    private Date start, end;

    @BeforeEach
    void setUp() {
        start = new GregorianCalendar(2023, Calendar.JANUARY, 27, 10, 0, 0).getTime();
        end = new GregorianCalendar(2023, Calendar.JANUARY, 30, 10, 0, 0).getTime();

        task = new Task("Lab4", start, end, 1);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getStartDate() {
        assertEquals(start, task.getStartTime());
    }

    @Test
    void createTask() {
        Task task1 = new Task("Lab4", start, end, 1);
        assertEquals(task1.getTitle(), "Lab4");
        assertEquals(task1.getStartTime(), start);
        assertEquals(task1.getEndTime(), end);
        assertEquals(task1.getRepeatInterval(), 1);
    }
}
