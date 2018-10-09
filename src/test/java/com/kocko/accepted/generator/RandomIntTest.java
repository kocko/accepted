package com.kocko.accepted.generator;

import com.kocko.accepted.random.RandomInt;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RandomIntTest {

    private RandomInt sut;
    
    @Before
    public void setup() {
        sut = new RandomInt();
    }
    
    @Test
    public void shouldGenerateARandomNumberWithinAGivenRange() {
        int min = 5, max = 10;
        int result = sut.next(min, max);

        assertThat(result >= 5, is(true));
        assertThat(result <= 10, is(true));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenTheRangeIsInvalid() {
        int min = 10, max = 5;
        sut.next(min, max);
    }
    
}