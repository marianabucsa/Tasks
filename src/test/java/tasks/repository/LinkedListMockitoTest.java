package tasks.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import tasks.model.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LinkedListMockitoTest {

    @Mock
    private LinkedTaskList linkedTaskList;

    @Test
    public void testAddTaskRepository() {
        String title = "Task";
        Date start = new GregorianCalendar(2020, Calendar.APRIL, 1, 20, 10).getTime();
        Task task = new Task(title, start);
        Mockito.doNothing().when(linkedTaskList).add(task);
        Mockito.when(linkedTaskList.size()).thenReturn(1);
        Assertions.assertEquals(linkedTaskList.size(), 1);
    }

    @Test
    public void testRemoveTaskRepository() {
        String title = "Task";
        Date start = new GregorianCalendar(2020, Calendar.APRIL, 1, 20, 10).getTime();
        Task task = new Task(title, start);
        Mockito.doNothing().when(linkedTaskList).add(task);
        Mockito.when(linkedTaskList.remove(task)).thenReturn(true);
        Assertions.assertEquals(linkedTaskList.size(), 0);
    }
}
