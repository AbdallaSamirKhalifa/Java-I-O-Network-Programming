package udpassignments;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class URLExample {
    public static void main(String[] args) {
        urlExample("https://iti.gov.eg/");
    }
    public static void urlExample(String urlName){
        try {
            URL url=new URL(urlName);
            URLConnection connection = url.openConnection();
            connection.connect();
            Map<String, List<String>>headers=connection.getHeaderFields();
            for (Map.Entry<String,List<String>>entry: headers.entrySet()){
                String key=entry.getKey();
                for (String value: entry.getValue()){
                    System.out.println(key+": "+value);
                }
            }
            System.out.println("---------------");
            System.out.println("Connection Functions:");
            System.out.println("getContentType: "+ connection.getContentType());
            System.out.println("getContentLength: "+ connection.getContentLength());
            System.out.println("getContentEncoding: "+ connection.getContentEncoding());
            System.out.println("getDate: "+ new Date(connection.getDate()));
            System.out.println("getExpiration: "+ connection.getExpiration());
            System.out.println("getLastModified: "+ connection.getLastModified());
            System.out.println("---------------------");
            String encoding=connection.getContentEncoding();
            if (encoding==null)encoding="UTF-8";

            try (Scanner scanner=new Scanner(connection.getInputStream())){
                for (int line=1;line<=10 && scanner.hasNextLine();line++){
                    System.out.println(scanner.nextLine());
                }
            }
        }catch (IOException e){
            System.out.println("Error occurred on the url example: "+e);
        }
    }
}
