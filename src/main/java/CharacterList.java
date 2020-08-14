import java.util.HashMap;

/**
 * Klasa przechowujaca combo nickname-haslo oraz ladujaca to combo do pamieci
 */
public class CharacterList
{
    String characterName;
    String password;

    public static HashMap<String, Character> loggedCharacters = new HashMap<String, Character>();


    /**
     * Ustawia nickname postaci oraz haslo do hashmapy aby sprawdzac loginy i hasla podczas logowan
     * @param password - haslo do postaci
     * @param characterName - nazwa postaci
     */
    public CharacterList(String password, String characterName)
    {
        this.characterName = characterName;
        this.password = password;
    }

    @Override
    public String toString() {
        return "CharacterList{" +
                "characterName='" + characterName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
