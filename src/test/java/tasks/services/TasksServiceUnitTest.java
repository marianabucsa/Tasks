package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.model.Task;
import tasks.repository.LinkedTaskList;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TasksServiceUnitTest {
    private LinkedTaskList tasks;
    private TasksService service;
    private java.util.Date start, end;

    @BeforeEach
    void setUp() {
        tasks = mock(LinkedTaskList.class);
        service = new TasksService(tasks);
        start = new GregorianCalendar(2023, Calendar.JANUARY, 27, 10, 0, 0).getTime();
        end = new GregorianCalendar(2023, Calendar.JANUARY, 30, 10, 0, 0).getTime();

    }

    @Test
    void getObservableList() {
        Task task1 = new Task("task1", start, end, 10);
        Task task2 = new Task("task2", start, end, 10);
        when(tasks.getAll()).thenReturn(FXCollections.observableArrayList(Arrays.asList(task1, task2)));
        ObservableList<Task> result = service.getObservableList();
        assertEquals(result.size(), 2);
    }

    @Test
    void getStringsToSeconds() {
        int result = service.parseFromStringToSeconds("12:00");
        assertEquals(result, 43200);
    }
}
