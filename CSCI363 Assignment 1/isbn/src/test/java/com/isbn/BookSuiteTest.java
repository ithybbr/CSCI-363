package com.isbn;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ BookTest.class, BookInvalidTest.class })
public class BookSuiteTest {
}
