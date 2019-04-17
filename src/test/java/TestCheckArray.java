import Lesson_6.MainCheckArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static Lesson_6.MainCheckArray.afterFourArray;

@RunWith(Parameterized.class)
public class TestCheckArray {

    private MainCheckArray mca;
    private Integer[] arrIn;
    private Integer[] arrExp;

    public TestCheckArray(Integer[] arrIn, Integer[] arrExp) {
        this.arrIn = arrIn;
        this.arrExp = arrExp;
    }

    @Parameterized.Parameters
    public static Collection data() {
        return Arrays.asList(new Integer[][][]{
                {{2, 5, 4, 1, 3, 9}, {1, 3, 9}},
                {{3, 8, 2, 4, 6, 1}, {6, 1}},
                {{4, 5, 9, 0, 2, 7}, {5, 9, 0, 2, 7}},
                {{1, 1, 5, 8, 4, 3}, {3}}
        });
    }

    @Before
    public void init() {
        mca = new MainCheckArray();
    }

    @Test
    public void massTestAfterFourArray1() {
        Assert.assertArrayEquals(arrExp, afterFourArray(arrIn));
    }
}
