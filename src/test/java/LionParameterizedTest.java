import com.example.Feline;
import com.example.Lion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class LionParameterizedTest {
    private final String sex;
    private final boolean isHasMane;

    @Mock
    private Feline feline;
    private AutoCloseable closeable;

    public LionParameterizedTest(String sex, boolean isHasMane) {
        this.sex = sex;
        this.isHasMane = isHasMane;
    }

    @Before
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Parameterized.Parameters
    public static Object[][] getSex() {
        return new Object[][]{
                {"Самец", true},
                {"Самка", false},
        };
    }

    @Test
    public void hasManeTest() throws Exception {
        Lion lion = new Lion(sex, feline);
        assertEquals("Грива есть только у самца", isHasMane, lion.doesHaveMane());
    }

    @After
    public void closeService() throws Exception {
        closeable.close();
    }
}
