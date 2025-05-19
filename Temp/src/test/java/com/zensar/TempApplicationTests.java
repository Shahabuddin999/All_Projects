package com.zensar;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TempApplicationTests {

//	@BeforeEach
//	void before() {
//		System.out.println("Before each");
//	}
//	@AfterEach
//	void after() {
//		System.out.println("After each");
//	}
// Annotation @WebMvcTest, @MockBean, @SpringBootTest, @AfterEach , @BeforeEach @Test, @AfterAll , @BeforeAll
// Assert type
//	assertEquals(expected, actual);
//  assertArrayEquals(expected, actual);
//	assertNull(car,"The car should be null");
//	assertNotSame(cat, dog);
//  assertFalse( 5 > 6,"5 is not greater then 6");
//  assertTrue( 5 > 4,"5 is greater then 4");
    
	@Test
	void whenAssertingEquality_thenEqual() {
	    String expected = "Baeldung";
	    String actual = "Baeldung";

	    assertEquals(expected, actual);
	    assertEquals(expected, actual,"failure - strings are not equal");
	    System.out.println("executed");
	}
	@Test
	void whenAssertingArraysEquality_thenEqual() {
	    char[] expected = {'J','u','n','i','t'};
	    char[] actual = "Junit".toCharArray();
	    
	    assertArrayEquals(expected, actual);
	}
	@Test
	void givenNullArrays_whenAssertingArraysEquality_thenEqual() {
	    int[] expected = null;
	    int[] actual = null;

	    assertArrayEquals(expected, actual);
	}
	@Test
	void whenAssertingNull_thenTrue() {
	    String car = null;
	    assertNull(car,"The car should be null");
	}
	@Test
	void whenAssertingNotSameObject_thenDifferent() {
	    Object cat = new Object();
	    Object dog = new Object();

	    assertNotSame(cat, dog);
	}
	@Test
	void whenAssertingConditions_thenVerified() {
	    assertTrue( 5 > 4,"5 is greater then 4");
	    assertFalse( 5 > 6,"5 is not greater then 6");
	}
	@Test
	void whenAssertingArraysEquality_thenEqual1() {
	    char[] expected = { 'J', 'u', 'p', 'i', 't', 'e', 'r' };
	    char[] actual = "Jupiter".toCharArray();

	    assertArrayEquals(expected, actual, "Arrays should be equal");
	}
	@Test
	void whenAssertingEqualityWithDelta_thenEqual() {
	    float square = 2 * 2;
	    float rectangle = 3 * 2;
	    float delta = 2;

	    assertEquals(square, rectangle, delta);
	}
	@Test
	void whenAssertingConditions_thenVerified1() {
	    assertTrue(5 > 4, "5 is greater the 4");
	    assertTrue(null == null, "null is equal to null");
	}
	@Test
	void givenMultipleAssertion_whenAssertingAll_thenOK() {
	    Object obj = null;
	    assertAll(
	      () -> assertEquals(4, 2 * 2, "4 is 2 times 2"),
	      () -> assertEquals("java", "JAVA".toLowerCase()),
	      () -> assertNull(obj, "obj is null")
	    );
	}
}
