package final_Server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.io.FileInputStream;
 
public class clientThread extends Thread
{
      private Socket socket = null;
      String  FILE_TO_RECEIVED = "C:\\Users\\DELL\\Desktop\\server\\temp4.JPG";
      String FILE_TO_SEND = "C:\\Users\\DELL\\Desktop\\server\\DSC_0858.JPG";
      int FILE_SIZE = 6022386;
     
      public clientThread(Socket socket )
      {
          this.socket = socket;
      }
      
      public void run()
      {
          
      try 
      {    
          InputStream is = socket.getInputStream();
          InputStreamReader isr = new InputStreamReader(is);
          BufferedReader br = new BufferedReader(isr);
         
          OutputStream os = socket.getOutputStream();
          OutputStreamWriter osw = new OutputStreamWriter(os);
          BufferedWriter bw = new BufferedWriter(osw);
          
          String returnMessage="";
          Scanner scan = new Scanner(new File("C:\\Users\\DELL\\Desktop\\server\\ser.txt"));
          Scanner s = new Scanner(System.in);
         
          int flag=0;
          String check="0";
          String year = "";
          while(true)
          {
          String choice_si = br.readLine();
          System.out.println(choice_si + " ****** bansari");
        
          if(choice_si.equals("2"))                        //sign up
          {
            scan = new Scanner(new File("C:\\Users\\DELL\\Desktop\\server\\ser.txt"));  
            int fl = 0;
            String id_pass = br.readLine();
            String id[] = id_pass.split(" ");
            while(scan.hasNextLine())
            {
             String thisline = scan.nextLine();
             if(!thisline.isEmpty())
             {
                String idd[] = thisline.split(" ");
                 if(idd[0].equals(id[0]))
                 {
                 fl=1;
                 break;
                 }
             }
            }
            //System.out.println(fl);
            
            if(fl == 0)                    
            {
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\project\\server\\ser.txt", true)));
                out.println(id_pass);
                out.close();
                bw.write("0"+"\n");
                bw.flush();
            }
            
            else                        
            {
                bw.write("1"+"\n");
                bw.flush();
            }
        
        }
          
        else if(choice_si.equals("1"))                //sign in
        {
            scan = new Scanner(new File("C:\\project\\server\\ser.txt"));  
            System.out.println("xxxx");
            String id_pass = br.readLine();
            System.out.println(id_pass);
            while(scan.hasNextLine())
            {
                String thisline= scan.nextLine();
                if(!thisline.isEmpty())
                if(thisline.equals(id_pass))
                {
                    year =year+thisline.charAt(0)+thisline.charAt(1)+thisline.charAt(2)+thisline.charAt(3)+thisline.charAt(4)+thisline.charAt(5);
                    flag =1;        
                }
            }
            scan.close();
      
            if(flag==0)
            {
                returnMessage="Incorrect id or password"+"\n";
            }
            else
            {
                returnMessage="Enter Your Choice \n";
            } 
           
          
            bw.write(returnMessage);
            bw.flush();
            
            while(flag == 1)
            {
            System.out.println("xyz");
                
            if(flag == 1)
            { 
            //      bw.write(" 1 to Download"+"\n");
            //     bw.write(" 2 to Upload"+"\n");
            //     bw.write(" 3 to Signout"+"\n");
            //     System.out.println("Message sent to the client is "+returnMessage);
                  bw.flush();
                  
                  String choice= br.readLine();
                  Scanner data = new Scanner(new File("C:\\project\\server\\database.txt"));
                  
                  if(choice.equals("1"))    // sending a file
                  {
                      while(data.hasNextLine())
                      {
                          String line = data.nextLine();
                          String file[] = line.split(" ");
                          String Display ="";
                          if(file[0].equals(year))
                          {
                              Display =file[1]+"\n";
                              System.out.println(Display);
                              bw.write(Display);
                              bw.flush();
                          } 
                      }
               
                      bw.write("end_character_end"+"\n");
                      bw.flush();
                      data.close();
                      String f_to_s=br.readLine();
                      System.out.println(f_to_s);
                      data = new Scanner(new File("C:\\project\\server\\database.txt"));
                      
                      while(data.hasNextLine())
                      {
                         
                          String line = data.nextLine();
                          if(!line.isEmpty())
                          {
                          String file[] = line.split(" ");
                          System.out.println(f_to_s +" "+file[1]);
                         
                         
                          if(file[1].equals(f_to_s))
                          {
                              FILE_TO_SEND = file[2];
                              check="1";
                              System.out.println("x");
                              break;
                            
                          } 
                          }
                      }
                      bw.write(check+"\n");
                      bw.flush();
                     
                      if( check.equals("1"))
                      {
              
                          System.out.println(FILE_TO_SEND);
                          File myFile = new File (FILE_TO_SEND);
                          FileInputStream fis = null;
                        
                          
                          fis = new FileInputStream(myFile);
                    
                          DataOutputStream dos = new DataOutputStream(os) ; 
                         
                          
                          dos.writeLong(myFile.length());
                          dos.flush();
                          
                          int n =0;
                          
                          byte[]buf = new byte[4092];
      
                          while((n =fis.read(buf)) != -1){
                               dos.write(buf,0,n);
                               dos.flush();

                            }
                            fis.close();
                          System.out.println("done");
                      }
                        s.close();
                        data.close();
                    
                     
                  }
                  else if(choice.equals("2"))//  receiving files 
                  {
                      String file_reci = br.readLine();
                      FILE_TO_RECEIVED="C:\\project\\server\\"+file_reci;
                      Scanner data2 = new Scanner(new File("C:\\project\\server\\database.txt"));
                      String dup_check = "0"; 
                      while(data.hasNextLine())
                      {
                          String line = data.nextLine();
                          if(!line.isEmpty())
                          {
                              String file[] = line.split(" ");
                              if(file_reci.equals(file[1]))
                                  dup_check = "1";
                          
                          }
                          
                      }
                      
                      bw.write(dup_check+"\n");
                      bw.flush();
                      data2.close();
                     
                      if(dup_check.equals("0"))
                      try{
                            
                        String ch = br.readLine();
                        System.out.println(ch);
                         if(ch.equals("1"))
                         {
                            FileOutputStream fos = null;
                            fos = new FileOutputStream(FILE_TO_RECEIVED);
                            DataInputStream dis = new DataInputStream(is) ;
                            int n = 0;
                            byte[]buf = new byte[4092];
                            long fileSize = dis.readLong();
                            
                                while (fileSize > 0 && (n = dis.read(buf, 0, (int)Math.min(buf.length, fileSize))) != -1)
                                {
                                    fos.write(buf,0,n);
                                    fileSize -= n;
                                }
                                System.out.println("d");
                                System.out.println("File Recieved");
                                String data_file =year+" "+file_reci+" "+FILE_TO_RECEIVED;
                                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("C:\\project\\server\\database.txt", true)));
                                out.println(data_file);
                                out.close();
                                fos.close();
                         }
                            
                          }
                          catch(Exception e)
                          {
                              System.out.print("here");
                              System.out.println(FILE_TO_RECEIVED);
                              File myFile = new File(FILE_TO_RECEIVED);
                              myFile.delete();
                              
                          }
                      else
                          System.out.println("duplicate");
                  }
                  else if(choice.equals("3"))
                  {
                      socket.close();
                  }
              }
           }
        }
        }
      
          }
          catch (IOException e) 
        {
                          
        }
         
      }
}