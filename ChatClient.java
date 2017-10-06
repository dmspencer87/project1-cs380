/************************************************************************************
 *	file: ChatClient.java
 *	author: Daniel Spencer
 *	class: CS 380 - computer networks
 *
 *	assignment: project 1
 *	date last modified: 10/05/2017
 *
 *	purpose:   simple server and client program
 *
 *
 ************************************************************************************/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintStream;


public class ChatClient
{
    public static void main(String args[]) throws IOException
    {
        try(Socket socket = new Socket("18.221.102.182", 38001)){

            PrintStream out = new PrintStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter user name: ");
            out.println(sc.nextLine());

            Runnable listener = () -> {
                try{
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    String userInput;
                    while(true)
                    {
                        userInput = br.readLine();
                        System.out.println(userInput);
                    }
                }
                catch(IOException e)
                {
                    System.out.println("");
                }
            };

            new Thread(listener).start();

            while(true){
                out.println(sc.nextLine());
            }
        }
    }
}