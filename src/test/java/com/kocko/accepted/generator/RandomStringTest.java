package com.kocko.accepted.generator;

import com.kocko.accepted.random.RandomString;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class RandomStringTest {

    private RandomString sut;
    
    @Before
    public void setup() {
        sut = new RandomString();
    }
    
    @Test
    public void shouldGenerateARandomStringOfGivenLengthWithLettersOfGivenRange() {
        int start = 65, end = 90, size = 15;
        
        String result = sut.next(size, start, end);
        
        boolean valid = true;
        for (char c : result.toCharArray()) {
            valid &= (c >= start && c <= end);
        }
        
        assertThat(result, is(notNullValue()));
        assertThat(result.length(), is(size));
        assertThat(valid, is(true));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenTheRequestedStringIsOfNonPositiveSize() {
        sut.next(-1, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenTheGivenLetterRangeIsInvalid() {
        sut.next(5, 4, 2);
    }
    
}