import com.example.Animal;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AnimalTest {
    private Animal animal;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Before
    public void init() {
        this.animal = new Animal();
    }

    @Test
    public void getFoodForHerbivorous() throws Exception{
        List<String> expectedFood = List.of("Трава", "Различные растения");
        assertEquals("Травоядные должны есть траву и растения", expectedFood, animal.getFood("Травоядное"));
    }

    @Test
    public void getFoodForPreditors()  throws Exception{
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals("Хищники должны есть мясо", expectedFood, animal.getFood("Хищник"));
    }

    @Test
    public void getGetFoodWithException() throws Exception{
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Неизвестный вид животного, используйте значение Травоядное или Хищник");
        animal.getFood("Млекопитающее");
    }

    @Test
    public void getFamilyTest(){
        String expectedFamilies = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
        assertEquals("Должны вернуться виды семейств", expectedFamilies, animal.getFamily());
    }
}
