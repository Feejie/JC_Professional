import Lesson_6.MainCheckArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static Lesson_6.MainCheckArray.checkOneFour;

@RunWith(Parameterized.class)
public class TestOneFour {

    private MainCheckArray mca;
    private Integer[] arrIn;
    private boolean result;

    public TestOneFour(Integer[] arrIn, boolean result) {
        this.arrIn = arrIn;
        this.result = result;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Integer[]{1, 4, 4, 1, 4}, true},
                {new Integer[]{4, 4, 1, 1, 4}, true},
                {new Integer[]{4, 7, 1, 0, 4}, false},
                {new Integer[]{1, 1, 1, 1, 1}, false}
        });
    }

    @Before
    public void init() {
        mca = new MainCheckArray();
    }

    @Test
    public void testOneFour() {
        Assert.assertEquals(result, checkOneFour(arrIn));
    }
}
