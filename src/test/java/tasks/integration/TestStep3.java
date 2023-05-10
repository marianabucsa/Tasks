package tasks.integration;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import tasks.model.Task;
import tasks.repository.LinkedTaskList;
import tasks.services.TasksService;

import java.security.Provider;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
class TestStep3 {

    private LinkedTaskList tasks = new LinkedTaskList();
    private TasksService service = new TasksService(tasks);
    Task task1;
    Task task2;

    @BeforeEach
    void setUp() {
        Date d1 = new GregorianCalendar(2023, Calendar.JANUARY, 27, 12, 0, 0).getTime();
        Date d2 = new GregorianCalendar(2023, Calendar.JANUARY, 28, 12, 0, 0).getTime();
        Date d3 = new GregorianCalendar(2023, Calendar.JANUARY, 29, 12, 0, 0).getTime();
        task1 = new Task("task1", d1, d2, 10);
        task1.setActive(true);

        task2 = new Task("task2", d1, d3, 10);
        task2.setActive(true);

    }

    @AfterEach
    void tearDown(){
        tasks = new LinkedTaskList();
        service = new TasksService(tasks);
    }

    @Test
    void getObservableList() {
        service.addTask(task1);
        service.addTask(task2);
        ObservableList<Task> result = service.getObservableList();
        assertEquals(2, result.size());
    }

    @Test
    void getFilteredTasks() {
        Date d1 = new GregorianCalendar(2023, Calendar.JANUARY, 27, 12, 0, 0).getTime();
        Date d2 = new GregorianCalendar(2023, Calendar.JANUARY, 28, 12, 0, 0).getTime();

        service.addTask(task1);
        service.addTask(task2);

        ArrayList<Task> result = (ArrayList<Task>) service.filterTasks(d1, d2);
        assertEquals(2, result.size());
    }
}
