package broadcastChatApp;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadedEchoHandler implements Runnable {
    final private OutputStream outputStream;
    final private Socket socket;
    final private PrintWriter writer;
    final private static List<PrintWriter> writers=new CopyOnWriteArrayList<>();

    public ThreadedEchoHandler(Socket socket) throws IOException {
        this.socket=socket;
        outputStream=socket.getOutputStream();
        writer=new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8),true);
        writers.add(writer);
    }

    public void run() {
        try (InputStream inputStream=socket.getInputStream()){
            Scanner in=new Scanner(inputStream,"UTF-8");
            this.writer.println("You have entered the chat, enter bye to exit.");
            StringBuffer line=new StringBuffer();
            boolean done=false;
            while (!done && in.hasNextLine()){
                line.delete(0,line.length());
                line.append(in.nextLine());
                if(line.toString().trim().equalsIgnoreCase("bye"))
                {
                    done=true;
                    writers.remove(writer);
                    writers.stream().filter(x->x!=this.writer).forEach(w->w.println(socket.getRemoteSocketAddress().toString()+" Have leaved the chat."));
                    continue;
                }
                String sender=socket.getRemoteSocketAddress().toString()+": ";
                writers.stream().filter(x->x!=this.writer).forEach(w->w.println(sender+line.toString()));
            }


        }catch (IOException e){
            System.out.println("Error in the runnable class: "+e);
        }finally {
            try {
                this.outputStream.close();
                this.socket.close();
                this.writer.close();
            }catch (IOException e){
                System.out.println("An Error occurred while closing the resources: "+e);
            }
        }
    }
}
