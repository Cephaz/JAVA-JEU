package GameState;

import java.io.*;
import java.net.*;

public class Client {
    private String serverAdress;
    private int port;
    private Socket s;
    private String enplayer;
    public Client(String serverAdress, int port){
        this.serverAdress = serverAdress;
        this.port = port;
    }
    public Socket connect() throws IOException{
        //Client Connected
        Socket s = new Socket(this.serverAdress, this.port);
        this.s = s;
        return s;
    }
    public void sendMessage(String pp) throws IOException {

        PrintWriter os = new PrintWriter(this.s.getOutputStream());
        os.println(pp);

        os.flush();
        if (pp.equals("0")){
            this.s.close();
            os.close();
        }
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        enplayer = bf.readLine();
    }
    public String getEnplayer(){
        return enplayer;
    }
}