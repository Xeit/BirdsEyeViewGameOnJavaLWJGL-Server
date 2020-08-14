// echo server
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Podstawowa klasa serwera, odpowiada za nasluchiwanie i laczenie z clientem
 */
public class Server_X_Client
{

    /**
     * Glowna funkcja programu, tworzy sockety dla clientow kiedy nastepuje taka potrzeba.
     * @param args - obojetne, argumenty nic nie zmieniaja
     */
    public static void main(String args[])
    {
        LoadCharacters.loadCharacterComboList(LoadCharacters.characterComboList);

        for(Map.Entry<String, CharacterList> entry : LoadCharacters.characterComboList.entrySet())
        {
            String login = entry.getKey();
            CharacterList obiekt = entry.getValue();
            System.out.println("Login: " + login + obiekt.toString());
        }


        Socket s=null;
        ServerSocket ss2=null;
        System.out.println("Server Listening......");
        try
        {
            ss2 = new ServerSocket(4445);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Server error");
        }
        while(true)
        {
            try
            {
                s= ss2.accept();
                System.out.println("connection Established");
                ServerThread st=new ServerThread(s);
                //st.start();
                st.run();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                System.out.println("Connection Error");
            }

            //Idealny TPS = 20 (TPS - tick per second)
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ServerThread extends Thread
{
    String line=null;
    BufferedReader  is = null;
    PrintWriter os=null;
    Socket s=null;
    String connectedFrom = null;
    String characterName = null;

    /**
     * @param s socket przekazywany do wątku
     */
    public ServerThread(Socket s){
        this.s=s;
    }

    /**
     * Funkcja zapetlajaca dla watku.
     * W niej nastepuja wszystkie odpowiedzi dla clienta
     */
    public void run()
    {
        try
        {
            is= new BufferedReader(new InputStreamReader(s.getInputStream()));
            OutputStream outputFromServer = s.getOutputStream();
            os=new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);
            char[] combo = is.readLine().toCharArray();
            String login = "";
            String password = "";
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
            CharacterList value = null;
            Boolean ifKeyExist = LoadCharacters.characterComboList.containsKey(login);
            if(ifKeyExist == true)
            {
                value = LoadCharacters.characterComboList.get(login);
                if(value.password.equals(password))
                {
                    os.println("YES");
                    os.flush();
                    characterName = LoadCharacters.characterComboList.get(login).characterName;
                    CharacterList.loggedCharacters.put(characterName, LoadCharacters.loadCharacter(characterName));
                    System.out.println("Loaded character: " + characterName);
                    // CORRECT LOGIN AND PASSWORD
                    //CharacterList.character.put(value.characterName, LoadCharacters.loadCharacter(value.characterName));
                    //os.println(CharacterList.character.get(value.characterName).toString());

                }else
                {
                    os.println("NO");
                    os.flush();
                    return;
                    // WRONG PASSWORD
                }
            }else
            {
                os.println("NO");
                os.flush();
                return;
                // WRONG LOGIN
            }
        }
        catch(IOException e)
        {
            System.out.println("IO error in server thread");
        }
        try
        {
            line=is.readLine();
            System.out.println(line);
            while(line.compareTo("QUIT")!=0)
            {
                Answears.answears(line, os, characterName);
                //os.println(line);
                //os.flush();
                //System.out.println("Response to Client  :  "+line);
                line=is.readLine();
            }
        }
        catch (IOException e)
        {
            line=this.getName(); //reused String line for getting thread name
            System.out.println("IO Error/ Client "+line+" terminated abruptly");
        }
        catch(NullPointerException e)
        {
            line=this.getName(); //reused String line for getting thread name
            System.out.println("Client "+line+" Closed");
        }
        finally
        {
            try
            {
                System.out.println("Connection Closing..");
                if (is!=null)
                {
                    is.close();
                    System.out.println(" Socket Input Stream Closed");
                }

                if(os!=null)
                {
                    os.close();
                    System.out.println("Socket Out Closed");
                }
                if (s!=null)
                {
                    s.close();
                    System.out.println("Socket Closed");
                }
            }
            catch(IOException ie)
            {
                System.out.println("Socket Close Error");
            }
        }
    }
}