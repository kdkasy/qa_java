import com.example.Feline;
import com.example.Lion;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

public class LionTest {
    @Mock
    private Feline feline;
    private AutoCloseable closeable;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createLionWithException() throws Exception {
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Используйте допустимые значения пола животного - самец или самка");
        new Lion("Женщина", feline);
    }

    @Test
    public void getKittensTest() throws Exception {
        Lion lion = new Lion("Самец", feline);
        int expectedKittens = 99;
        Mockito.when(feline.getKittens()).thenReturn(99);
        assertEquals("Количество котят должно совпадать", expectedKittens, lion.getKittens());
    }

    @Test
    public void getFoodTest() throws Exception {
        Lion lion = new Lion("Самец", feline);
        Mockito.when(feline.getFood(any())).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        List<String> actualFood = lion.getFood();
        assertEquals("Еда должна возвращаться для хищника", expectedFood, actualFood);
    }

    @Test
    public void getFoodWithException() throws Exception {
        Lion lion = new Lion("Самец", feline);
        doThrow(new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник"))
                .when(feline).getFood(any());

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Неизвестный вид животного, используйте значение Травоядное или Хищник");
        lion.getFood();
    }

    @After
    public void closeService() throws Exception {
        closeable.close();
    }
}
