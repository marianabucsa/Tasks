package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import tasks.model.Task;
import tasks.repository.LinkedTaskList;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@MockitoSettings(strictness = Strictness.LENIENT)
class TasksServiceMockitoTest {
    @Mock
    private LinkedTaskList tasks;

    @InjectMocks
    private TasksService service;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getObservableList() {
        Task task1 = mock(Task.class);
        Task task2 = mock(Task.class);
        when(service.getObservableList()).thenReturn(FXCollections.observableArrayList(Arrays.asList(task1, task2)));
        ObservableList<Task> result = service.getObservableList();
        assertEquals(result.size(), 2);
    }

    @Test
    void getStringsToSeconds() {
        Mockito.when(service.parseFromStringToSeconds("12:00")).thenReturn(43200);
        assertEquals(service.parseFromStringToSeconds("12:00"), 43200);
    }
}
