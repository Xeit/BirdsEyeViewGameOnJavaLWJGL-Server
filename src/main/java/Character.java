import java.math.BigDecimal;

/**
 * Klasa przechowujaca dane dla kakretnej postaci po poprawnym zalogoawniu
 */
public class Character
{
    String nickname;
    Integer level;
    Long xpToNextLvl;
    Integer posX;
    Integer posY;
    Integer maxHP;
    Integer maxMana;
    Integer currentHP;
    Integer currentMana;

    /**
     * Funkcja tworzy zapis danych postaci z pliku do pamieci RAM po zalogowaniu sie
     * @param nickname - nazwa postaci
     * @param level - poziom postaci
     * @param xpToNextLvl - ilosc doswiadczenia potrzebnego do nastepnego poziomu
     * @param posX - aktualna pozycja w osi X postaci
     * @param posY - aktualna pozycja w osi Y postaci
     * @param maxHP - maxymalne HP posiadane przez postac
     * @param maxMana - maxymalna Mana posiadana przez postac
     * @param currentHP - aktualna ilosc HP postaci
     * @param currentMana - aktualna ilosc Many postaci
     */
    public Character(String nickname,int level, long xpToNextLvl, int posX, int posY, int maxHP, int maxMana, int currentHP, int currentMana) {
        this.nickname = nickname;
        this.level = level;
        this.xpToNextLvl = xpToNextLvl;
        this.posX = posX;
        this.posY = posY;
        this.maxHP = maxHP;
        this.maxMana = maxMana;
        this.currentHP = currentHP;
        this.currentMana = currentMana;
    }

    /**
     * Funkcja ma za zadanie zwrocic wszystkie dane postaci kiedy client o to poprosi
     * @return zwraca wszystkie dane postaci wypisane do stringa w odpowiedniej formie aby moc przeslac go do clienta
     */
    public String toString()
    {
        return nickname+";"+level+";"+xpToNextLvl+";"+posX+";"+posY+";"+maxHP+";"+maxMana+";"+currentHP+";"+currentMana+";";
    }

}
