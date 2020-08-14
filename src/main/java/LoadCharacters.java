import java.io.*;
import java.util.HashMap;

/**
 * Klasa ladujaca postacie oraz combo login-haslo-nickname
 */
public class LoadCharacters
{
    public static HashMap<String, CharacterList> characterComboList
            = new HashMap<String, CharacterList>();

    /**
     * Funkcja ladujaca loginy, hasla i nazwy do pamieci aby szybko moc sprawdzac
     * czy login i haslo podane przez clienta sa poprawne
     * @param characterComboList - HashMap zawierajacy Login, Nickname i Haslo
     */
    public static void loadCharacterComboList(HashMap<String, CharacterList> characterComboList) {
        File plik = new File("logins.txt");


        try (BufferedReader br = new BufferedReader(new FileReader(plik))) {
            String line;
            while ((line = br.readLine()) != null)
            {
                String login = "";
                String password = "";
                String characterName = "";
                char[] combo = line.toCharArray();
                int letter = 0;

                //login
                while(combo[letter] != ';')
                {
                    login = login + combo[letter];
                    letter++;
                }
                letter++;
                //password
                while(combo[letter] != ';')
                {
                    password = password + combo[letter];
                    letter++;
                }
                letter++;
                //characterName
                while(combo[letter] != ';')
                {
                    characterName = characterName + combo[letter];
                    letter++;
                }
                characterComboList.put(login, new CharacterList(password,characterName));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Funkcja ladujaca postac do pamieci po poprawnym zalogowaniu sie,
     * umozliwia operacje danymi tej postaci dla clienta
     * @param nickname - nickname postaci
     * @return - zwraca dane postaci w odpowiednim formacie
     */
    public static Character loadCharacter(String nickname)
    {
        Integer level = null;
        Long xpToNextLvl = null;
        Integer posX = null;
        Integer posY = null;
        Integer maxHP = null;
        Integer maxMana = null;
        Integer currentHP = null;
        Integer currentMana = null;
        File plik = new File( "Characters/"+nickname+".txt");
        try (BufferedReader br = new BufferedReader(new FileReader(plik)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                String numberString = "";
                char[] number = line.toCharArray();
                int letter = 0;

                //lvl
                while(number[letter] != ';')
                {
                    numberString = numberString + number[letter];
                    level = Integer.valueOf(numberString);
                    letter++;
                }
                numberString = "";
                letter++;
                //xpToNextLvl
                while(number[letter] != ';')
                {
                    numberString = numberString + number[letter];
                    xpToNextLvl = Long.valueOf(numberString);
                    letter++;
                }
                numberString = "";
                letter++;
                //posX
                while(number[letter] != ';')
                {
                    numberString = numberString + number[letter];
                    posX = Integer.valueOf(numberString);
                    letter++;
                }
                numberString = "";
                letter++;
                //posY
                while(number[letter] != ';')
                {
                    numberString = numberString + number[letter];
                    posY = Integer.valueOf(numberString);
                    letter++;
                }
                numberString = "";
                letter++;
                //maxHP
                while(number[letter] != ';')
                {
                    numberString = numberString + number[letter];
                    maxHP = Integer.valueOf(numberString);
                    letter++;
                }
                numberString = "";
                letter++;
                //maxMana
                while(number[letter] != ';')
                {
                    numberString = numberString + number[letter];
                    maxMana = Integer.valueOf(numberString);
                    letter++;
                }
                numberString = "";
                letter++;
                //currentHP
                while(number[letter] != ';')
                {
                    numberString = numberString + number[letter];
                    currentHP = Integer.valueOf(numberString);
                    letter++;
                }
                numberString = "";
                letter++;
                //currentMana
                while(number[letter] != ';')
                {
                    numberString = numberString + number[letter];
                    currentMana = Integer.valueOf(numberString);
                    letter++;
                }
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new Character(nickname, level, xpToNextLvl, posX, posY, maxHP, maxMana, currentHP, currentMana);
    }
}
