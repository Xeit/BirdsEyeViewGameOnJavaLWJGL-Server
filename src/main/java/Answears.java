import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * Klasa odpowiadajaca na zapytania clienta
 */
public class Answears
{
    /**
     * Funkcja odpowiadajaca clientowi na prosbe wyslana z jego strony
     * @param codename - string otrzymany od clienta, oznacza kod operacji, ktora client chce wykonac
     * @param os - printwriter przekazywany z glownej funki, slozy odpowiadaniu clientowi co ma robic
     * @param characterName - nazwa postaci z ktorej dane maja byc obslogiwane
     */
    public static void answears(String codename, PrintWriter os, String characterName)
    {
        if(codename.equals("100")) //up
        {
            if(CharacterList.loggedCharacters.get(characterName).posY == 9)
            {
                os.println("NO");
                os.flush();
            }else
            {
                CharacterList.loggedCharacters.get(characterName).posY -= 1;
                os.println(CharacterList.loggedCharacters.get(characterName).posY.toString());
                os.flush();
            }
        }else if(codename.equals("101")) //down
        {
            if(CharacterList.loggedCharacters.get(characterName).posY == 73)
            {
                os.println("NO");
                os.flush();
            }else
            {
                CharacterList.loggedCharacters.get(characterName).posY += 1;
                os.println(CharacterList.loggedCharacters.get(characterName).posY.toString());
                os.flush();
            }
        }else if(codename.equals("102")) //left
        {
            if(CharacterList.loggedCharacters.get(characterName).posX == 8)
            {
                os.println("NO");
                os.flush();
            }else
            {
                CharacterList.loggedCharacters.get(characterName).posX -= 1;
                os.println(CharacterList.loggedCharacters.get(characterName).posX.toString());
                os.flush();
            }
        }else if(codename.equals("103")) //right
        {
            if(CharacterList.loggedCharacters.get(characterName).posX == 72)
            {
                os.println("NO");
                os.flush();
            }else
            {
                CharacterList.loggedCharacters.get(characterName).posX += 1;
                os.println(CharacterList.loggedCharacters.get(characterName).posX.toString());
                os.flush();
            }
        }else if(codename.equals("001")) // fulldata
        {
            System.out.println("Message: " + CharacterList.loggedCharacters.get(characterName).toString());
            os.println(CharacterList.loggedCharacters.get(characterName).toString());
            os.flush();
        }
    }
}
