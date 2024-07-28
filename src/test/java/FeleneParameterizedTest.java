import com.example.Feline;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(Parameterized.class)
public class FeleneParameterizedTest {
    private final int numberOfKittens;
    private final int expectedKittens;

    @Spy
    Feline feline;
    private AutoCloseable closeable;

    public FeleneParameterizedTest(int numberOfKittens, int expectedKittens) {
        this.numberOfKittens = numberOfKittens;
        this.expectedKittens = expectedKittens;
    }

    @Before
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Parameterized.Parameters
    public static Object[][] getKittens() {
        return new Object[][]{
                {10, 10},
                {1, 1},
                {99, 99}
        };
    }

    @Test
    public void getKittensWithNumber(){
        assertEquals("Количество котов должно совпадать", expectedKittens, feline.getKittens(numberOfKittens));
    }


    @Test
    public void getKittensWithEmptyParameter(){
        Mockito.when(feline.getKittens(anyInt())).thenReturn(numberOfKittens);
        assertEquals("Количество котов должно совпадать", expectedKittens, feline.getKittens());
    }

    @After
    public void closeService() throws Exception {
        closeable.close();
    }
}
