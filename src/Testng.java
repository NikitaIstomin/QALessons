import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class Testng {
    //1!=1
    @Test
    public void factorialOneTest(){
        Assert.assertEquals(Factorial.factorial(1), 1);
    }
    //5!=120
    @Test
    public void FactorialOrdinaryNumberTest(){
        Assert.assertEquals(Factorial.factorial(5), 120);
    }
    //0!=1
    @Test
    public void FactorialZeroNumberTest(){
        Assert.assertEquals(Factorial.factorial(5), 120);
    }

    @Test(expectedExceptions = IllegalArgumentException.class,
            expectedExceptionsMessageRegExp = "Значение не должно быть отрицательным")
    public void factorialNegativeNumberTest() {
        Factorial.factorial(-1);
    }

}

