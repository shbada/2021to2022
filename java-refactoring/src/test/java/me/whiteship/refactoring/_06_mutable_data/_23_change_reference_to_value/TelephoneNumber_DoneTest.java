package me.whiteship.refactoring._06_mutable_data._23_change_reference_to_value;

import me.whiteship.refactoring._06_mutable_data._23_change_reference_to_value.done.TelephoneNumber_Done;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TelephoneNumber_DoneTest {
    @Test
    void testEquals() {
        TelephoneNumber_Done number1 = new TelephoneNumber_Done("123", "1234");
        TelephoneNumber_Done number2 = new TelephoneNumber_Done("123", "1234");

        assertEquals(number1, number2);
    }
}