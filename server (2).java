package final_Server;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{ 
    private static Socket socket;
    private static ServerSocket serverSocket;
    private static final int maxClientsCount = 100;
    private static final clientThread[] threads = new clientThread[maxClientsCount];
    public static void main(String[] args)
    {
        try
        {
            int port = 25919;
            serverSocket = new ServerSocket(port);

            while(true)
            {
               socket = serverSocket.accept();
               int i = 0; 
                for(i=0 ; i<maxClientsCount; i++)
                {
                    if(threads[i] == null)
                    {
                        System.out.println(i+"\n");
                        (threads[i] = new clientThread(socket)).start();
                        
                        threads[i]=null;
                        break;
                    }
                }
                System.out.println("parth tandel");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                socket.close();
            }
            catch(Exception e){}
        }
    }
}