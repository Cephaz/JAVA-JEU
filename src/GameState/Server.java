package GameState;

import Entity.Player;
import Entity.Players;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[]args) {
        try {
            ServerSocket ss = new ServerSocket(1331) ;
            Socket s = ss.accept();
            Socket s1 = ss.accept();

            System.out.println("Server started: @" + ss.getInetAddress().getHostAddress() + " port:" + ss.getLocalPort());

            InputStreamReader in = new InputStreamReader(s.getInputStream());
            BufferedReader bf = new BufferedReader(in);

            InputStreamReader inn = new InputStreamReader(s1.getInputStream());
            BufferedReader bfs = new BufferedReader(inn);

            while (true){
                String str = bf.readLine();
                String strr = bfs.readLine();
                //System.out.println(bfs.getHp() + " " + bf.getHp());
                if (str.equals("0")){
                    System.out.println("Connection closed! s1 ");
                    s1.close();
                    s.close();
                    ss.close();
                    in.close();
                    inn.close();
                    bf.close();
                    bfs.close();
                    break;
                }

                PrintWriter os = new PrintWriter(s.getOutputStream());
                PrintWriter oss = new PrintWriter(s1.getOutputStream());

                os.println(strr);
                oss.println(str);
                os.flush();
                oss.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
