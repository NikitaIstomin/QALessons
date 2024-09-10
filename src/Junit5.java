import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Junit5 {
    //1!=1
    @Test
    public void factorialOneTest(){
        Assertions.assertEquals(Factorial.factorial(1), 1);
    }
    //5!=120
    @Test
    public void FactorialOrdinaryNumberTest(){
        Assertions.assertEquals(Factorial.factorial(5), 120);
    }
    //0!=1
    @Test
    public void FactorialZeroNumberTest(){
        Assertions.assertEquals(Factorial.factorial(5), 120);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Значение не должно быть отрицательным")
    public void FactorialNegativeNumberTest() {
        Factorial.factorial(-1);
    }
}