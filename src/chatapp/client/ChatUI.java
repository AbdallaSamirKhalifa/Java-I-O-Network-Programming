package chatapp.client;

import chatapp.interfaces.*;
import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Map;
import java.util.Vector;

public class ChatUI {
    private static Vector<ChatUI>uis = new Vector<>();
    JTextArea messageArea;
    JTextField textField, serverAddress, userName;
    JButton btnConnect;
    JList<String> loggedInList;
    JFrame frame;
    JButton btnSend;
    private ChatClient client;
    private ChatServerInt server;

    public ChatUI(){
        frame =new JFrame("Group Chat");
        JPanel main=new JPanel();
        JPanel top=new JPanel();
        JPanel cn=new JPanel();
        JPanel bottom=new JPanel();
        serverAddress =new JTextField();
        textField =new JTextField();
        userName =new JTextField();
        messageArea =new JTextArea();
        btnConnect =new JButton("Connect");
        btnSend=new JButton("Send");
        btnSend.setEnabled(false);
        loggedInList = new JList<>();
        loggedInList.setFixedCellWidth(100);
        main.setLayout(new BorderLayout(5,5));
        top.setLayout(new GridLayout(1,0,5,5));
        cn.setLayout(new BorderLayout(5,5));
        bottom.setLayout(new BorderLayout(5,5));
        top.add(new JLabel("Your Name: "));
        top.add(userName);
        top.add(new JLabel("Server Address: "));
        top.add(serverAddress);
        top.add(btnConnect);
        cn.add(new JScrollPane(messageArea),BorderLayout.CENTER);
        cn.add(loggedInList,BorderLayout.EAST);
        bottom.add(textField,BorderLayout.CENTER);
        bottom.add(btnSend,BorderLayout.EAST);

        main.add(top,BorderLayout.NORTH );
        main.add(cn,BorderLayout.CENTER);
        main.add(bottom,BorderLayout.SOUTH);
        main.setBorder(new EmptyBorder(10,10,10,10));

        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startConnection();
                textField.grabFocus();
            }
        });

        serverAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startConnection();
                textField.grabFocus();

            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessage();
            }
        });
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sendMessage();
            }
        });

        frame.setContentPane(main);
        frame.setSize(600,600);
        frame.setVisible(true);
    }

    public void startConnection(){
        if (!btnConnect.getText().equals("Connect")){
            try {
                server.logout(client);
                for (ChatUI ui:uis)
                    ui.updateUsers(server.getConnected());
            }catch (RemoteException e){
                JOptionPane.showMessageDialog(frame,"Couldn't logout user "+e);
            }

            btnConnect.setText("Connect");
            btnSend.setEnabled(false);
            return;
        }

        if (userName.getText().length()<2){
            JOptionPane.showMessageDialog(frame,"You need to enter your name first.");
            return;
        }

        if (serverAddress.getText().length()<4){
            JOptionPane.showMessageDialog(frame,"You need to enter the server address.");
            return;
        }

        try {
            client=new ChatClient(userName.getText());
            client.setUi(this);
            uis.add(this);
            server=(ChatServerInt) Naming.lookup(serverAddress.getText());
//            server=(ChatServerInt) Naming.lookup("rmi://"+serverAddress.getText()+":1099/chat");

            server.login(client);
            for (ChatUI ui:uis)
                ui.updateUsers(server.getConnected());

            btnConnect.setText("Disconnect");
            btnSend.setEnabled(true);
        }catch (RemoteException | MalformedURLException | NotBoundException e){
            JOptionPane.showMessageDialog(frame,"Error, couldn't connect the user "+e);
        }

    }

    public void writeMessage(String message){
        messageArea.setText(messageArea.getText()+"\n"+message);
    }

    public void sendMessage(){
        if (btnConnect.getText().equals("Connect")){
             JOptionPane.showMessageDialog(frame,"You need to connect first.");
             return;
        }

        String message=userName.getText()+": "+textField.getText();
        textField.setText("");
        try {
            server.publishToClients(message);
        }catch (RemoteException e){
            JOptionPane.showMessageDialog(frame,"Error, Couldn't send the message "+e);
        }
    }

    public void updateUsers(Vector<ChatClientInt >clients){
        DefaultListModel<String>listModel= new DefaultListModel<>();
        if (clients!=null){
            for (ChatClientInt client:clients){
                try {
                    listModel.addElement(client.getName());
                }catch (RemoteException e){
                    JOptionPane.showMessageDialog(frame,"Couldn't get client name "+e);
                }
            }
        }
        loggedInList.setModel(listModel);
    }

    public static void main(String[] args) {
        new ChatUI();
        new ChatUI();
    }
}
