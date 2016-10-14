import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;


public class DS2Test {


    DS2Interface instance;

    @Before
    public void setUp() {
        instance  = new Main();
    }


    private void testRecursiveRodCutting(int[] input, int expected) {
        int result = instance.recursiveRodCutting(input);
        assertEquals(expected, result);
    }

    private void testDynamicRodCutting(int[] input, int expected) {
        int result = instance.dynamicRodCutting(input);
        assertEquals(expected, result);
    }

    private void testMatrix(int[][] input, int expected) {
        int result = instance.matrix(input);
        assertEquals(expected, result);
    }

    private void testRecursiveRodInputFile(String filename) throws FileNotFoundException, URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).toURI());
        int[] input = Util.readInput(file.getAbsolutePath());
        int expected = Util.readInput(file.getAbsolutePath() + ".ref")[0];
        testRecursiveRodCutting(input, expected);
    }

    private void testDynamicRodInputFile(String filename) throws FileNotFoundException, URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).toURI());        int[] input = Util.readInput(file.getAbsolutePath());
        int expected = Util.readInput(file.getAbsolutePath() + ".ref")[0];
        testDynamicRodCutting(input, expected);
    }

    private void testMatrixInputFile(String filename) throws FileNotFoundException, URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(filename).toURI());        int[][] input = Util.readMatrix(file.getAbsolutePath());
        int expected = Util.readInput(file.getAbsolutePath() + ".ref")[0];
        testMatrix(input, expected);
    }


    /* Recursive Rod cutting  */

    @Test(timeout=100)
    public void testSimpleRecursiveRod() throws FileNotFoundException {

        int[] input = {0, 2, 3, 7, 8, 9};
        int expected = 11;

        testRecursiveRodCutting(input, expected);

        int[] input2 = {0, 1, 5, 8, 9, 10, 17, 17 , 20, 24, 25};
        expected = 27;
        testRecursiveRodCutting(input2, expected);


    }

    @Test(timeout=500)
    public void testBasicRecursiveRodIncreasing() throws FileNotFoundException, URISyntaxException {
        testRecursiveRodInputFile("input/10_inc.1.inp");
    }

    @Test(timeout=500)
    public void testBasicRecursiveRodNonIncreasing() throws FileNotFoundException, URISyntaxException {
        testRecursiveRodInputFile("input/10_ninc.1.inp");
    }


    /* Dynamic Rod cutting  */

    @Test(timeout=100)
    public void testSimpleDynamicRod() throws FileNotFoundException {
        int[] input = {0, 2, 3, 7, 8, 9};
        int expected = 11;
        testDynamicRodCutting(input, expected);
    }

    @Test(timeout=100)
    public void testBasicDynamicRodIncreasing() throws FileNotFoundException, URISyntaxException {
        testDynamicRodInputFile("input/10_inc.1.inp");
    }

    @Test(timeout=100)
    public void testBasicDynamicRodNonIncreasing() throws FileNotFoundException, URISyntaxException {
        testDynamicRodInputFile("input/10_ninc.1.inp");
    }



    /* Matrix */

    @Test(timeout=100)
    public void testSimpleMatrix() throws FileNotFoundException {
        int[][] input = {
                {-2, 5, 3 ,2},
                {9, -6, 5, 3},
                {1, -8, 2, -3},
                {-1, 2, -5, 2}
            };
        int expected = 45;
        testMatrix(input, expected);

    }

    @Test(timeout=500)
    public void testEmptyMatrix() throws FileNotFoundException {
        int[][] input = {};
        int expected = 0;
        testMatrix(input, expected);
    }

    @Test(timeout=500)
    public void testNegativeMatrix() throws FileNotFoundException {
        int[][] input = {
                {-1,-1},
                {-1,-1}
        };
        int expected = 0;
        testMatrix(input, expected);
    }

    @Test(timeout=4000)
    public void testLargeMatrix() throws FileNotFoundException, URISyntaxException {
        testMatrixInputFile("input/1000x1000_pos.3.inp");

    }


}
