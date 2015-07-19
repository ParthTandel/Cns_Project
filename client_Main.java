import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.DefaultListModel;
 
public class Main 
{
    
    private static Socket socket;
    final DefaultListModel<String> model = new DefaultListModel<String>();
    OutputStream os;
    OutputStreamWriter osw;
    BufferedWriter bw;
     
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    
    DataInputStream dis;
    FileOutputStream fos;
    Main()
    {
        os=null;
        osw=null;
        bw=null;
        
    }
    
    public int connections(String s)
    {
         int port = 25919;
         try
         {
         InetAddress address = InetAddress.getByName(s);
         socket = new Socket(s, port);
         System.out.println("connected");
         
         
         
         this.os = socket.getOutputStream();
         this.osw = new OutputStreamWriter(os);
         this.bw = new BufferedWriter(osw);
         
         this.is = socket.getInputStream();
         this.isr = new InputStreamReader(is);
         this.br = new BufferedReader(isr);
         return 1;
         }
         
         catch(Exception e)
         {
            System.out.println("connection error"); 
         }
         
         return 0;
    }
    
    public void send()
    {
        try
        {
            
        }
        catch(Exception e)
        {
            
        }
    }
    
  
    
    public String Sign_in(String s)
    {
        try
        {
            bw.write("1"+"\n");
            bw.flush();
            
            bw.write(s+"\n");
            bw.flush();
            String fl = br.readLine();
            return fl;
        }
        catch(Exception e)
        {
            
        }
        return null;
    }
    
    public String register(String s)
    {
        try
        {
            bw.write("2"+"\n");
            bw.flush();
            
            bw.write(s+"\n");
            bw.flush();
            String fl = br.readLine();
            return fl;
        }
        catch(Exception e)
        {
            
        }
        return null;
    }
    

    public DefaultListModel<String> download() 
    {
        try
        {
            bw.write("1"+"\n");
            bw.flush();
            String message = br.readLine();
            this.model.addElement(message);
            while(!message.equals("end_character_end"))
            {
                System.out.println(message);
                message = br.readLine();
                this.model.addElement(message);
                
            }
            return this.model;
        }
        catch(Exception e)
        {
            
        }
        return null;
    }
    
    public int download_part2(String s , int status)
    {
        try
        {
            
            String f_to_r = s;
            String FILE_TO_RECEIVED = "C:\\project\\client\\"+f_to_r;        //download address
            System.out.println(f_to_r);
            bw.write(f_to_r+"\n");
            
            bw.flush();
            
        String check="";
        check = br.readLine();
        System.out.println(check);

        if(check.charAt(0) == '1')
        {
    
            
            this.fos = null;
            this.fos = new FileOutputStream(FILE_TO_RECEIVED);
            this.dis = new DataInputStream(is) ;
            int n = 0;
            byte[]buf = new byte[4092];
            
            long fileSize = dis.readLong();
            while (fileSize > 0 && (n = dis.read(buf, 0, (int)Math.min(buf.length, fileSize))) != -1)
            {
              fos.write(buf,0,n);
              fileSize -= n;
            }
            fos.close();
            System.out.println("done");
            return 1;
            
        }
        else
        {
            return 0;
        }
        }
        catch(Exception e)
        {
            return 0;
        }
    }

 
    
    public int upload( String s , String s1)
    {
        try
        {
        //System.out.println("enter file name (File size less than 5MB):");
        bw.write("2"+"\n");
        bw.flush();
        String file_name = s;
        bw.write(file_name+"\n");
        bw.flush();
    
        String dup_check = br.readLine();
        System.out.println(s + " " + s1 + " "+ dup_check);
        if(dup_check.equals("0"))
        {
            try
            {
                System.out.println("enter file path with name: for \\ use \\\\");
                String FILE_TO_SEND=s1;
                File myFile = new File (FILE_TO_SEND);
                
            //    if(myFile.length()<6022386)
                {
                     FileInputStream fis = new FileInputStream(myFile);
                     DataOutputStream dos = new DataOutputStream(os) ; 
                     bw.write("1"+"\n");
                     bw.flush();
                          dos.writeLong(myFile.length());
                          dos.flush();
                   
                     int n =0;
                     
                     byte[]buf = new byte[4092];
                     while((n =fis.read(buf)) != -1)
                     {
                          dos.write(buf,0,n);
                          dos.flush();

                     }
                     System.out.println("done");
                     return 1;
                }
            //    else
            /*    {
                    System.out.println("File size exceded");
                    bw.write("0"+"\n");
                    bw.flush();
                    return 2;

                }*/
            }
            catch(IOException excep)
            {
                System.out.println("File not Found");
                bw.write("0"+"\n");
                bw.flush();
                return 3;
            }
        }
        else
        {
            System.out.println("Error Duplicate file name");
            return 4;
        }
    
    }
    catch(Exception E)
    {
        return -1;
    }
    }
}