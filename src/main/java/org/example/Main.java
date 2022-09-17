package org.example;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url;
        try {
            url = new URL("https://run.mocky.io/v3/19fa89b0-d9f9-4a82-8f95-0a9048d3a4af");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responseCode= conn.getResponseCode();
        if(responseCode==200){
            System.out.println(conn.getResponseMessage());
            StringBuilder response=new StringBuilder();
            Scanner scanner=new Scanner(url.openStream());
            while(scanner.hasNext()){
                response.append(scanner.nextLine());
            }
            scanner.close();
            System.out.println(response);
        }
        else
            System.out.println("Error");

        System.out.println("Hello world!");
    }
}