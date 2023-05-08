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

    @ParameterizedTest
    @Tag("Create Task")
    @DisplayName("Flow: Title error")
    @CsvSource({"'',2023-04-01 12:00,2023-04-02 12:00,2",
            "TEuPxtA5RxraXwN7Q69fpt2p27XGS5qz24Qq9ffGIwYf3phOUG3O62txHUhr8rd8WrCvR2BsKU4QqCUF8EqnhAuTWUm4Sc4p2vQlSWimKlUkBX0LDf2uUdI9au6zogmmLkBIUZwHVEmN0Z4oToafAA4WB7jF24aKpukaibhpnMd3XRpkxDryXe6UqskpkP2438925D4Vpf9qBBCBRACFrIJSFxWPwMr9iac7HBuPtbyEV2j2REpFnqR6EaMXwZ8Y" +
                    ",2023-04-01 12:00,2023-04-02 12:00,2",})
    void testCreateTaskTitleError(String title, String startTime, String endTime, int interval) throws ParseException {
        try {
            System.out.println(title);
            Task task = new Task(title, Task.getDateFormat().parse(startTime), Task.getDateFormat().parse(endTime), interval);
            assertTrue(false);
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            assertTrue(e.getMessage().contains("Invalid title"));
        }
    }

    @ParameterizedTest
    @Tag("Create Task")
    @DisplayName("Flow: Date error")
    @CsvSource({"task,2023-04-02 12:00,2023-04-01 12:00,-3",
            "task,2023-04-01 12:00,2023-04-01 12:00,2",
            "task,2023-04-02 12:00,2023-04-01 12:00,2",})
    void testCreateTaskDateError(String title, String startTime, String endTime, int interval) throws ParseException {
        try {
            Task task = new Task(title, Task.getDateFormat().parse(startTime), Task.getDateFormat().parse(endTime), interval);
            assertTrue(false);
        } catch(IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Start date cannot be after end date"));
        }
    }

    @ParameterizedTest
    @Tag("Create Task")
    @DisplayName("Flow: Interval error")
    @CsvSource({"task,2023-04-02 12:00,2023-04-01 12:00,-3",
            "task,2023-04-01 12:00,2023-04-02 12:00,0",})
    void testCreateTaskIntervalError(String title, String startTime, String endTime, int interval) throws ParseException {
        try {
            Task task = new Task(title, Task.getDateFormat().parse(startTime), Task.getDateFormat().parse(endTime), interval);
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