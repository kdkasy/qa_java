import com.example.Feline;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Spy
    Feline feline;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    @Test
    public void eatMeatTest() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        List<String> actualFood = feline.eatMeat();
        assertEquals("Еда должна возвращаться для хищника", expectedFood, actualFood);
    }

    @Test
    public void eatMeatWithExceptionTest() throws Exception{
        doThrow(new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник"))
                .when(feline).getFood(any());

        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Неизвестный вид животного, используйте значение Травоядное или Хищник");
        feline.eatMeat();

    }

    @Test
    public void getFamilyTest(){
        String expectedFamily = "Кошачьи";
        assertEquals("Семейство ожидается \"Кошачьи\"", expectedFamily, feline.getFamily());
    }


}
