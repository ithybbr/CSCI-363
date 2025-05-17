package josephus;


import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectClasses({ IsbnTest.class, IsbnFormatExceptionTest.class })
public class IsbnSuiteTest {
}
