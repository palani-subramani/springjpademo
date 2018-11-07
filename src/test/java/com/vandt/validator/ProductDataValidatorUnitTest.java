package com.vandt.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductDataValidatorUnitTest
{
    @InjectMocks
    private ProductDataValidator cut;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIsValidNameWhenNameIsNull()
    {
        // Given currency = null

        // When
        boolean result = cut.isValidName(null);

        // Then
        assertEquals(false, result);
    }

    @Test
    public void testIsValidNameWhenNameIsEmpty()
    {
        // Given currency = null

        // When
        boolean result = cut.isValidName("");

        // Then
        assertEquals(false, result);
    }

    @Test
    public void testIsValidNameWhenNameIsGreaterThan50Chars()
    {
        // Given currency = null

        // When
        boolean result = cut.isValidName("Lorem Ipsum is unadulterated drivel. It means nothing whatsoever, but it's surprisingly useful. The main idea behind lorem ipsum is to have 'convincing' text, separated into words, paragraphs and with punctuation etc.");

        // Then
        assertEquals(false, result);
    }

    @Test
    public void testIsValidNameWhenNameIsValid()
    {
        // Given currency = null

        // When
        boolean result = cut.isValidName("Lorem Ipsum");

        // Then
        assertEquals(true, result);
    }

    @Test
    public void testIsValidCurrencyWhenCurrencyNull()
    {
        // Given currency = null

        // When
        boolean result = cut.isValidCurrency(null);

        // Then
        assertEquals(false, result);
    }

    @Test
    public void testIsValidCurrencyWhenCurrencyIncorrect()
    {
        // Given currency is garbage

        // When
        boolean result = cut.isValidCurrency("HELLO");

        // Then
        assertEquals(false, result);
    }

    @Test
    public void testIsValidCurrencyWhenCurrencyIsCorrect()
    {
        // Given currency is garbage

        // When
        boolean result = cut.isValidCurrency("GBP");

        // Then
        assertEquals(true, result);
    }

    @Test
    public void testIsValidAmountWhenAmountIsNull()
    {
        // Given amount is null

        // When
        boolean result = cut.isValidAmount(null);

        // Then
        assertEquals(false, result);
    }

    @Test
    public void testIsValidAmountWhenAmountIsZero()
    {
        // Given amount is null

        // When
        boolean result = cut.isValidAmount("0");

        // Then
        assertEquals(false, result);
    }

    @Test
    public void testIsValidAmountWhenAmountIsNonNumeric()
    {
        // Given amount is null

        // When
        boolean result = cut.isValidAmount("HI");

        // Then
        assertEquals(false, result);
    }

    @Test
    public void testIsValidAmountWhenAmountIsNumeric()
    {
        // Given amount is null

        // When
        boolean result = cut.isValidAmount("9.99");

        // Then
        assertEquals(true, result);
    }
}