import com.example.AlexTheLion;
import com.example.Feline;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AlexTheLionTest {

    private AlexTheLion alex;
    @Mock
    Feline feline;

    @Before
    public void init() throws Exception {
        this.alex = new AlexTheLion(feline);
    }

    @Test
    public void alexFriendsTest(){
        List<String> expectedFriends = List.of("Марти", "Глория", "Мелман");
        assertEquals("Список друзей должен быть Марти, Глория, Мелман", expectedFriends, alex.getFriends());
    }

    @Test
    public void alexLivingPlaceTest(){
        String expectedPlace = "Нью-Йоркский зоопарк";
        assertEquals("Место жительства Алекса в Нью-Йоркском зоопарке", expectedPlace, alex.getPlaceOfLiving());
    }

    @Test
    public void alexKittensTest(){
        assertEquals("У Алекса нет детей",0, alex.getKittens());
    }



}
