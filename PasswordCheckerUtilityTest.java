 import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class PasswordCheckerUtilityTest {

    @Test(expected = LengthException.class)
    public void testIsValidPasswordTooShort() throws Exception {
        PasswordCheckerUtility.isValidPassword​("abc");
    }

    @Test
    public void testIsValidPasswordSuccess() throws Exception {
        assertTrue(PasswordCheckerUtility.isValidPassword​("Hello@123"));
    }

    @Test(expected = NoUpperAlphaException.class)
    public void testHasUpperAlphaNoUpper() throws Exception {
        PasswordCheckerUtility.hasUpperAlpha​("abcdef1!");
    }

    @Test(expected = NoLowerAlphaException.class)
    public void testHasLowerAlphaNoLower() throws Exception {
        PasswordCheckerUtility.hasLowerAlpha​("ABCDEF1!");
    }

    @Test(expected = NoDigitException.class)
    public void testHasDigitNoDigit() throws Exception {
        PasswordCheckerUtility.hasDigit​("Abcdef!");
    }

    @Test(expected = NoSpecialCharacterException.class)
    public void testHasSpecialCharNoSpecial() throws Exception {
        PasswordCheckerUtility.hasSpecialChar​("Abcdef1");
    }

    @Test(expected = InvalidSequenceException.class)
    public void testNoSameCharInSequenceInvalid() throws Exception {
        PasswordCheckerUtility.NoSameCharInSequence​("aaab1!");
    }

    @Test
    public void testGetInvalidPasswords() {
        ArrayList<String> passwords = new ArrayList<>();
        passwords.add("short");
        passwords.add("noUpper1");
        passwords.add("NOLOWER1");
        passwords.add("noDigit!");
        passwords.add("Valid1!");
        
        ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords​(passwords);
        
        assertEquals(4, invalidPasswords.size());
    }
}
