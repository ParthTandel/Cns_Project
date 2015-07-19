

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.CardLayout;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.JScrollPane;


public class Gui 
{
    private JFrame frame;
    private JTextField textField;
    private JPasswordField passwordField;
    String id_pass = "";
    private JTextField textField_1;
    private JTextField textField_2;
    Main m = new Main();
    int status = 0;
    private JTextField textField_3;
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                try 
                {
                    Gui window = new Gui();
                    window.frame.setVisible(true);
                } 
                
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        });
    }

    
    public Gui() 
    {
        initialize();
    }

    private void initialize() 
    {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final CardLayout card = new CardLayout();
        final CardLayout card2 = new CardLayout();
        frame.getContentPane().setLayout(card);
        final DefaultListModel<String> model = new DefaultListModel<String>();
        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().add(panel, "ip_address");
        card.show(frame.getContentPane(),"ip_addresss");
        textField = new JTextField();
        textField.setForeground(Color.BLACK);
        textField.setBackground(Color.WHITE);
        textField.setColumns(10);
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().add(panel_1, "sign_in_up");
        JButton btnNewButton = new JButton("ENTER");
        btnNewButton.setForeground(Color.BLACK);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) 
            {
                String s = textField.getText();
                int i = m.connections(s);
                System.out.println(s);
                if(i == 1)
                {
                card.show(frame.getContentPane(),"sign_in_up");
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();    
            
                }
                else
                JOptionPane.showMessageDialog(null, "Incorrect ip address");
            }
        });
        
        
        
        JLabel lblEnterIpAddress = new JLabel("ENTER IP ADDRESS");
        lblEnterIpAddress.setForeground(Color.BLACK);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(76)
                            .addComponent(textField, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
                        .addGroup(gl_panel.createSequentialGroup()
                            .addGap(149)
                            .addComponent(lblEnterIpAddress, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(96, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addGap(81)
                    .addComponent(lblEnterIpAddress, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(136, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);
        JPanel panel_2 = new JPanel();
        panel_2.setForeground(Color.BLACK);
        panel_2.setBackground(Color.LIGHT_GRAY);
        frame.getContentPane().add(panel_2, "signed_in");
        
        JButton btnNewButton_3 = new JButton("Upload");
        JButton btnNewButton_4 = new JButton("Download");
        JButton btnNewButton_5 = new JButton("Sign Out");
        
        btnNewButton_5.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                ex exit_ = new ex();
                exit_.setVisible(true);
                
            }
        });
        JButton btnNewButton_1 = new JButton("SIGN IN");
        btnNewButton_1.setForeground(Color.BLACK);
        btnNewButton_1.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
                String s = textField_3.getText()+" "+passwordField.getText();
                String res  = m.Sign_in(s);
                System.out.println(res);
                if(!res.equals("Incorrect id or password"))
                {
                System.out.println(s);        
                card.show(frame.getContentPane(),"signed_in");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Incorrect ID and Password");
                }
            }
        });
        passwordField = new JPasswordField();
        JButton btnNewButton_2 = new JButton("SIGN UP");
        btnNewButton_2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) 
            {
                
                String s = textField_3.getText()+" "+passwordField.getText();
                String x = m.register(s);
                if(x.equals("0"))
                JOptionPane.showMessageDialog(null, "Signed Up Succesfully");
                else
                JOptionPane.showMessageDialog(null, "Id already Exist");
            }
        });
        btnNewButton_2.setForeground(Color.BLACK);
        
        JLabel lblUserId = new JLabel("User ID");
        lblUserId.setForeground(Color.BLACK);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
            gl_panel_1.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(112)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addComponent(btnNewButton_1)
                            .addPreferredGap(ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                            .addComponent(btnNewButton_2)
                            .addGap(91))
                        .addGroup(gl_panel_1.createSequentialGroup()
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblPassword)
                                .addComponent(lblUserId))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                .addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                                .addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                            .addGap(88))))
        );
        gl_panel_1.setVerticalGroup(
            gl_panel_1.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_1.createSequentialGroup()
                    .addGap(89)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblUserId, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                        .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
                        .addComponent(lblPassword)
                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
                    .addGap(35)
                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnNewButton_1)
                        .addComponent(btnNewButton_2))
                    .addGap(31))
        );
        panel_1.setLayout(gl_panel_1);
        
        
        
        final JPanel panel_3 = new JPanel();
        GroupLayout gl_panel_2 = new GroupLayout(panel_2);
        gl_panel_2.setHorizontalGroup(
            gl_panel_2.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_2.createSequentialGroup()
                    .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_2.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnNewButton_5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(gl_panel_2.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnNewButton_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(gl_panel_2.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(btnNewButton_4)))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(47, Short.MAX_VALUE))
        );
        gl_panel_2.setVerticalGroup(
            gl_panel_2.createParallelGroup(Alignment.TRAILING)
                .addGroup(gl_panel_2.createSequentialGroup()
                    .addContainerGap(88, Short.MAX_VALUE)
                    .addComponent(btnNewButton_4)
                    .addGap(27)
                    .addComponent(btnNewButton_3)
                    .addGap(24)
                    .addComponent(btnNewButton_5)
                    .addGap(58))
                .addGroup(gl_panel_2.createSequentialGroup()
                    .addGap(22)
                    .addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 238, Short.MAX_VALUE)
                    .addContainerGap())
        );
    //    panel_3.setLayout(new CardLayout(0, 0));
        panel_3.setLayout(card2);
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Color.LIGHT_GRAY);
        panel_3.add(panel_4, "download");
        
        JPanel panel_6 = new JPanel();
        panel_6.setBackground(Color.LIGHT_GRAY);
        panel_3.add(panel_6, "upload");
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);

        JButton btnNewButton_8 = new JButton("Submit");
        btnNewButton_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) 
            {
                
                String s = textField_1.getText();
                String s1 = textField_2.getText();
                int i = m.upload(s, s1);
                if(i==1)
                    JOptionPane.showMessageDialog(null, "File Successfully Uploaded");
                else if(i==2)
                    JOptionPane.showMessageDialog(null, "File Size Exceeded");
                else if(i==3)
                    JOptionPane.showMessageDialog(null, "File Not Found");
                else if(i==4)
                    JOptionPane.showMessageDialog(null, "Duplicate File Name");
                else if(i==-1)
                    JOptionPane.showMessageDialog(null, "File Error");
                
            }
        });
        JLabel lblFileName = new JLabel("File Name");
        
        JLabel lblFilePath = new JLabel("File Path");
        GroupLayout gl_panel_6 = new GroupLayout(panel_6);
        gl_panel_6.setHorizontalGroup(
            gl_panel_6.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_6.createSequentialGroup()
                    .addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_6.createSequentialGroup()
                            .addGap(34)
                            .addComponent(btnNewButton_8))
                        .addGroup(gl_panel_6.createSequentialGroup()
                            .addGap(27)
                            .addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
                                .addComponent(lblFilePath)
                                .addComponent(lblFileName))
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
                                .addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                .addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))))
                    .addContainerGap())
        );
        gl_panel_6.setVerticalGroup(
            gl_panel_6.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_6.createSequentialGroup()
                    .addGap(95)
                    .addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
                        .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFileName))
                    .addGap(18)
                    .addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
                        .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblFilePath))
                    .addGap(27)
                    .addComponent(btnNewButton_8)
                    .addGap(31))
        );
        panel_6.setLayout(gl_panel_6);
        JScrollPane scrollPane = new JScrollPane();
        
        final JList<String>list = new JList<String>(model);
        
        JButton btnNewButton_6 = new JButton("Select");
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0)
            {
                
                String s = (String) list.getSelectedValue();
                int z = m.download_part2(s , status);
                if(z==1)
                {
                    JOptionPane.showMessageDialog(null, s + " Downloaded Succesfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null," Download Failed");
                }
            }
        });
        GroupLayout gl_panel_4 = new GroupLayout(panel_4);
        gl_panel_4.setHorizontalGroup(
            gl_panel_4.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4.createSequentialGroup()
                    .addGap(24)
                    .addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
                        .addComponent(btnNewButton_6, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(26, Short.MAX_VALUE))
        );
        gl_panel_4.setVerticalGroup(
            gl_panel_4.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel_4.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(btnNewButton_6)
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
    
        scrollPane.setViewportView(list);
        
        JLabel lblChooseFromFiles = new JLabel("Choose From Files");
        scrollPane.setColumnHeaderView(lblChooseFromFiles);
        panel_4.setLayout(gl_panel_4);
            
    /*    model.addElement("1");
        model.addElement("1");
        model.addElement("1");
        model.addElement("1");
        model.addElement("1");
        model.addElement("1");
        model.addElement("1");
        model.addElement("1");
        model.addElement("1");
        model.addElement("1");
        model.addElement("1");
        model.addElement("1");
        model.addElement("1");
        model.addElement("1");
        */
        JPanel panel_5 = new JPanel();
        panel_5.setBackground(Color.LIGHT_GRAY);
        panel_3.add(panel_5, "default");
        panel_2.setLayout(gl_panel_2);
        
        btnNewButton_4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                
                
                    model.clear();
                    DefaultListModel<String> temp = m.download();
                    int i =0;
                    status = 1;
                    while (!temp.elementAt(i).equals("end_character_end"))
                    {
                    model.addElement(temp.elementAt(i));
                    i++;
                    }
                    card2.show(panel_3,"download");
            }
        });
        
        
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
                card2.show(panel_3,"upload");
            }
        });
        card2.show(panel_3,"default");
    }
}
