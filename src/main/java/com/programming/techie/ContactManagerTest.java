package com.programming.techie;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 1. assertfalse
 * 2. asserttrue
 * 3. assertthrows
 * 4. beforeall
 * 5. beforeeach
 * 6. 在class上面添加
 *    "@TestInstance(TestInstance.Lifecycle.PER_CLASS)"
 *    JUNIT将每次只对实例初始化一次，就不需要再加static了
 *    （？为啥）
 * 7. disableOnOs
 *
 */
class ContactManagerTest {
    ContactManager contactManager;

    @BeforeAll
    public static void setupAll() {
        System.out.println("should print before all tests");
    }

    @BeforeEach
    public void setup() {
        contactManager = new ContactManager();
    }

    @Test
    @DisabledOnOs(OS.MAC)
    @DisplayName("should create the contact")
    public void shouldCreateTheContact() {
        contactManager.addContact("caliinn", "lee", "17668777654");
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"012345678910", "1788", "19999999999"})
    public void shouldRepeatedlyCreateTheContact(String phone) {
        contactManager.addContact("caliinn", "lee", phone);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @ParameterizedTest
    @MethodSource("phoneNumberList")
    public void shouldRepeatedlyMetaCreateTheContact(String phone) {
        contactManager.addContact("caliinn", "lee", phone);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @ParameterizedTest
    @CsvSource({"012345678910", "1788", "19999999999"})
    public void shouldRepeatedlyCsvCreateTheContact(String phone) {
        contactManager.addContact("caliinn", "lee", phone);
        Assertions.assertFalse(contactManager.getAllContacts().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContacts().size());
    }

    @Test
    @RepeatedTest(value = 5, name="Repeating {currentRepetition} of {totalRepetitions}")
    @DisplayName("should not create contact when last name is null")
    public void shouldNotCreateContactWhenLastNameIsNull() {
        ContactManager contactManager = new ContactManager();
        Assertions.assertThrows(RuntimeException.class, () -> {
            contactManager.addContact("lph", null, "17888888888");
        });
    }

    @AfterEach
    public void tearDown() {
        System.out.println("tear down the contact");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("tear down all");
    }

    public static List<String> phoneNumberList() {
        return Arrays.asList("012345678910", "1788", "19999999999");
    }

}