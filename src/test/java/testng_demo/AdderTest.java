package testng_demo;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AdderTest {

    private Adder adder = new Adder();

    @Test
    public void adderTest() throws Exception {
        assertEquals(12, adder.add(5,7));
    }
}
