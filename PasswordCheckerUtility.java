import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordCheckerUtility {
	
	/**
	 * Compare equality of two passwords
	 * @param password  - password string to be checked for
	 * @param passwordConfirm  - passwordConfirm string to be checked against password for
	 * @throws UnmatchedException  - thrown if not same (case sensitive)
	 */
	static void	comparePasswords​(String password,String passwordConfirm)throws UnmatchedException
	{
		if(!password.equals(passwordConfirm))
		{
			throw new UnmatchedException();
		}
	}
	
	/**
	 * Compare equality of two passwords
	 * @param password  - password string to be checked for
	 * @param passwordConfirm  - passwordConfirm string to be checked against password for
	 * @return true if both same (case sensitive), false otherwise
	 */
	static boolean	comparePasswordsWithReturn​(String password,String passwordConfirm)
	{
		if(password.equals(passwordConfirm))
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * This method will accept an ArrayList of passwords as the parameter and return an ArrayList 
	 * with the status of any invalid passwords (weak passwords are not considered invalid). The ArrayList of invalid 
	 * passwords will be of the following format: password BLANK message of the exception thrown
	 * @param passwords  - list of passwords
	 * @return ArrayList of invalid passwords in the correct format
	 */
	static ArrayList<String>getInvalidPasswords​(ArrayList<String> passwords)
	{
		ArrayList<String> invalid = new ArrayList<String>();
		
		for(int i=0;i< passwords.size();i++)
		{
			try {
				isValidPassword​(passwords.get(i));
			}
			
			catch (LengthException e) {
				invalid.add(passwords.get(i)+ "  "+ e.getMessage());
			} catch (NoUpperAlphaException e) {
				invalid.add(passwords.get(i)+ "  "+ e.getMessage());
			} catch (NoLowerAlphaException e) {
				invalid.add(passwords.get(i)+ "  "+ e.getMessage());
			} catch (NoDigitException e) {
				invalid.add(passwords.get(i)+ "  "+ e.getMessage());
			} catch (NoSpecialCharacterException e) {
				invalid.add(passwords.get(i)+ "  "+ e.getMessage());
			} catch (InvalidSequenceException e) {
				invalid.add(passwords.get(i)+ "  "+ e.getMessage());
			}
		}
		return invalid;
	}
	
	/**
	 * checks if the password contains 6 to 9 characters
	 * @param password  - password string to be checked for
	 * @return true if password contains 6 to 9 characters, false otherwise
	 */
	static boolean	hasBetweenSixAndNineChars​(String password)
	{
		if(6<= password.length() && password.length()<=9)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Checks the password Digit requirement - Password must contain a numeric character
	 * @param password  - password string to be checked for Digit requirement
	 * @return true if meet Digit requirement
	 * @throws NoDigitException - thrown if does not meet Digit requirement
	 */
	static boolean	hasDigit​(String password) throws NoDigitException
	{
		Pattern pattern = Pattern.compile(".*[0-9].*");
		Matcher matcher = pattern.matcher(password);
		
		if(matcher.matches())
		{
			return true;
		}
		else
		{
			throw new NoDigitException();
		}		
	}
	
	/**
	 * Checks the password lower case requirement - Password must contain at least one lower case alpha character
	 * @param password  - password string to be checked for lower case requirement
	 * @return true if meets lower case requirement
	 * @throws NoLowerAlphaException  - thrown if does not meet lower case requirement
	 */
	static boolean	hasLowerAlpha​(String password) throws NoLowerAlphaException
	{
		Pattern pattern = Pattern.compile(".*[a-z].*");
		Matcher matcher = pattern.matcher(password);
		
		if(matcher.matches())
		{
			return true;
		}
		else
		{
			throw new NoLowerAlphaException();
		}
	}
	
	/**
	 * Checks the password SpecialCharacter requirement - Password must contain a Special Character
	 * @param password  - password string to be checked for SpecialCharacter requirement
	 * @return true if meets SpecialCharacter requirement
	 * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
	 */
	static boolean	hasSpecialChar​(String password) throws NoSpecialCharacterException
	{
		Pattern pattern = Pattern.compile(".*[^a-zA-Z0-9].*");
		Matcher matcher = pattern.matcher(password);
		
		if(matcher.matches())
		{
			return true;
		}
		else
		{
			throw new NoSpecialCharacterException();
		}
	}
	
	/**
	 * Checks the password alpha character requirement - Password must contain an uppercase alpha character
	 * @param password  - password string to be checked for alpha character requirement
	 * @return true if meet alpha character requirement
	 * @throws NoUpperAlphaException - thrown if does not meet alpha character requirement
	 */
	static boolean	hasUpperAlpha​(String password)throws NoUpperAlphaException
	{
		Pattern pattern = Pattern.compile(".*[A-Z].*");
		Matcher matcher = pattern.matcher(password);
		
		if(matcher.matches())
		{
			return true;
		}
		else
		{
			throw new NoUpperAlphaException();
		}
	}
	
	/**
	 * Checks the password length requirement - The password must be at least 6 characters long
	 * @param password  - password string to be checked for length
	 * @return true if meets minimum length requirement
	 * @throws LengthException - thrown if does not meet minimum length requirement
	 */
	static boolean	isValidLength​(String password) throws LengthException
	{
		if(6< password.length())
		{
			return true;
		}
		else
		{
			throw new LengthException();
		}
	}
	
	/**
	 * Return true if valid password (follows all the following rules), returns false if an invalid password 
	 * 1. At least 6 characters long 
	 * 2. At least 1 numeric character
	 * 3. At least 1 upper case alphabetic character
	 * 4. At least 1 lower case alphabetic character
	 * 5. At least 1 special character 
	 * 6. No more than 2 of the same character in a sequence - Hello@123 – OK AAAbb@123 – not OK Aaabb@123 – OK
	 * @param password - string to be checked for validity
	 * @return true if valid password (follows all rules from above), false if an invalid password
	 * @throws LengthException - thrown if length is less than 6 characters
	 * @throws NoUpperAlphaException  - thrown if no upper case alphabetic
	 * @throws NoLowerAlphaException  - thrown if no lower case alphabetic
	 * @throws NoDigitException  - thrown if no digit
	 * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
	 * @throws InvalidSequenceException - thrown if more than 2 of same character.
	 */
	static boolean	isValidPassword​(String password) throws LengthException , NoUpperAlphaException, NoLowerAlphaException, 
													NoDigitException, NoSpecialCharacterException, InvalidSequenceException
	{
		if(isValidLength​(password)&& hasUpperAlpha​(password)&&hasLowerAlpha​(password)&& hasDigit​(password)
				&& hasSpecialChar​(password)&& NoSameCharInSequence​(password))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Checks if password is VALID and the length is NOT between 6-9 characters
	 * @param password - string to be checked for validity
	 * @return - if length of password is between 6 and 9 (inclusive), ALTHOUGH the password may be VALID.

	 * @throws WeakPasswordException - thrown if password is valid but not strong enough
	 * @throws LengthException - thrown if length is less than 6 characters
	 * @throws NoUpperAlphaException  - thrown if no upper case alphabetic
	 * @throws NoLowerAlphaException  - thrown if no lower case alphabetic
	 * @throws NoDigitException  - thrown if no digit
	 * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
	 * @throws InvalidSequenceException - thrown if more than 2 of same character.
	 */
	static boolean	isWeakPassword​(String password) throws WeakPasswordException, LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException
	{
		if(isValidPassword​(password)&& !hasBetweenSixAndNineChars​(password))
		{
			return false;
		}
		else
		{
			throw new WeakPasswordException();
		}
	}
	
	/**
	 * Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
	 * @param password - password string to be checked for Sequence requirement
	 * @return true if password meets Sequence requirement
	 * @throws InvalidSequenceException - thrown if meets Sequence requirement
	 */
	static boolean	NoSameCharInSequence​(String password) throws InvalidSequenceException
	{
		Pattern pattern = Pattern.compile("^(?!.*(.)\\1{2}).*$");
		Matcher matcher = pattern.matcher(password);
		
		if(matcher.matches())
		{
			return true;
		}
		else
		{
			throw new InvalidSequenceException();
		}
	}
}
