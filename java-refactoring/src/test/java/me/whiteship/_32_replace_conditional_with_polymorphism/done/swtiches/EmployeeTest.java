package me.whiteship._32_replace_conditional_with_polymorphism.done.swtiches;

import me.whiteship.refactoring._11_primitive_obsession._32_replace_conditional_with_polymorphism.done.swtiches.Employee;
import me.whiteship.refactoring._11_primitive_obsession._32_replace_conditional_with_polymorphism.done.swtiches.FullTimeEmployee;
import me.whiteship.refactoring._11_primitive_obsession._32_replace_conditional_with_polymorphism.done.swtiches.PartTimeEmployee;
import me.whiteship.refactoring._11_primitive_obsession._32_replace_conditional_with_polymorphism.done.swtiches.TemporalTimeEmployee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void fulltime() {
        Employee employee = new FullTimeEmployee();
        assertEquals(120, employee.vacationHours());
        assertTrue(employee.canAccessTo("new project"));
        assertTrue(employee.canAccessTo("spring"));
    }

    @Test
    void partime() {
        Employee employee = new PartTimeEmployee(List.of("spring", "jpa"));
        assertEquals(80, employee.vacationHours());
        assertFalse(employee.canAccessTo("new project"));
        assertTrue(employee.canAccessTo("spring"));
    }

    @Test
    void temporal() {
        Employee employee = new TemporalTimeEmployee(List.of("jpa"));
        assertEquals(32, employee.vacationHours());
        assertFalse(employee.canAccessTo("new project"));
        assertFalse(employee.canAccessTo("spring"));
        assertTrue(employee.canAccessTo("jpa"));
    }

}