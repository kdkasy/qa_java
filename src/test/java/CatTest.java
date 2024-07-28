import com.example.Cat;
import com.example.Feline;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {
    @Mock
    Feline feline;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void getSoundTest(){
        Cat cat = new Cat(feline);
        String expectedSound = "Мяу";
        String actualSound = cat.getSound();
        assertEquals("Звук должен быть \"Мяу\"", expectedSound, actualSound);
    }

    @Test
    public void getCorrectFoodTest() throws Exception {
        Cat cat = new Cat(feline);
        Mockito.when(feline.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        List<String> actualFood = cat.getFood();
        assertEquals("Еда должна возвращаться для хищника", expectedFood, actualFood);
    }

    @Test
    public void getFoodWithException() throws Exception {
        Cat cat = new Cat(feline);
        doThrow(new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник"))
                .when(feline).eatMeat();

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Неизвестный вид животного, используйте значение Травоядное или Хищник");
        cat.getFood();
    }
}
