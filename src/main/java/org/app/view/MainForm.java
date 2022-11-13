package org.app.view;

import org.app.model.Location;
import org.app.model.Weather;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.JOptionPane;
import javax.net.ssl.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MainForm extends JFrame{
    public JPanel mainPanel;
    JFrame window;
    Container con;
    JPanel textPanel,inputPanel,disPlayPanel;
    JLabel textLabel,displayLabel,displayLabelTemp,displayLabelobservation_time,displayLabelweather_icons,
            displayLabelweather_descriptions,displayLabelwind_speed,displayLabelwind_degree,displayLabelwind_dir,
            displayLabelpressure, displayLabelprecip,displayLabelhumidity,displayLabelcloudcover,displayLabelfeelslike,
            displayLabeluv_index,displayLabelvisibility,displayLabelis_day,displayLabellocation;
    JTextField jtf;
    JButton enterB;
    Font normalFont = new Font("Times New Roman",Font.PLAIN,26);
    Font normalFontCountry = new Font("Times New Roman",Font.PLAIN,20);
    Font normalFontTemp = new Font("Times New Roman",Font.PLAIN,40);
    JScrollPane scrPane;

    int sublocationY = 100;

    public MainForm() throws SQLException {
        Image img = Toolkit.getDefaultToolkit().getImage("https://www.google.com/search?q=night&sxsrf=ALiCzsakoi6cjc7pROEh5LP0R3NnBCfbxA:1668192928963&source=lnms&tbm=isch&sa=X&ved=2ahUKEwj3-8n35qb7AhUzjokEHercCxUQ_AUoAXoECAIQAw&biw=1512&bih=866&dpr=2#imgrc=kV50N2Q-eEP4FM");
        //Image img = Toolkit.getDefaultToolkit().getImage("/Users/shiransilva/Repos/Aca/ConcordiaAPPProject/src/main/resources/night.jpeg");
        window= new JFrame();
        window.setSize(800,700);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.BLACK);
        //JImageComponent ic = new JImageComponent(myImageGoesHere);
        //imagePanel.add(ic);
        window.add(new JLabel(new ImageIcon("Path/To/Your/Image.png")));

        //window.getContentPane()
        window.setTitle("සිරා Weather");
        window.setLayout(null);
        /*this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        });*/
        con=window.getContentPane();

        disPlayPanel = new JPanel();
        scrPane = new JScrollPane(disPlayPanel);
        disPlayPanel.setBounds(1,100,800,100);
        disPlayPanel.setBackground(Color.BLACK);
        displayLabelTemp = new JLabel("");
        displayLabelTemp.setForeground(Color.white);
        displayLabelTemp.setFont(normalFontTemp);
        //displayLabelTemp.setBounds(1,200,1000,100);
        //displayLabelTemp.setFontSize(30);
        disPlayPanel.add(displayLabelTemp);
        con.add(disPlayPanel);

        textPanel= new JPanel();
        textPanel.setBounds(1,1,500,50);
        textPanel.setBackground(Color.BLACK);
        textLabel = new JLabel("Search a city to check weather");
        textLabel.setForeground(Color.white);
        textLabel.setFont(normalFont);
        textPanel.add(textLabel);
        con.add(textPanel);
        //JPanel dynamic = createsubLocation();
        //con.add(dynamic);
        inputPanel = new JPanel();
        inputPanel.setBounds(450,1,300,50);
        inputPanel.setBackground(Color.BLACK);
        inputPanel.setLayout(new GridLayout(1,2));

        jtf =new JTextField();
        inputPanel.add(jtf);

        enterB=new JButton("Find");
        enterB.setBackground(Color.BLACK);
        enterB.setForeground(Color.BLACK);
        inputPanel.add(enterB);
        con.add(inputPanel);
        window.setVisible(true);
        enterB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String city = jtf.getText().toString().trim();
                System.out.println("city : "+city);
                Location locationCurrent = null;
                try {
                    locationCurrent = invokeApi(city);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if(locationCurrent!=null){
                    JPanel dynamic = null;
                    try {
                        dynamic = createsubLocation(locationCurrent);
                        //Location location = new Location(jobjLocation);
                        String LocationExistName= locationCurrent.handleSelectLocation(locationCurrent.getName()).getName();
                        if(LocationExistName ==""){
                            locationCurrent.handleInsert();
                        }
                        //boolean ifLocationExist = ;
                        Weather weather =new Weather(locationCurrent.getWeatherObj(),locationCurrent.getName());
                        weather.handleInsert();
                        Weather weatherSelectObj = weather.handleSelectLocation(locationCurrent.getName());

                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    sublocationY=sublocationY+110;

                /*if (con.getComponentCount()==9){
                    con.remove(5);
                    sublocationY = 100;
                }*/
                    con.add(dynamic);
                    //System.out.println("Con Size:"+con.getComponentCount());
                    jtf.setText("");
                    window.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Invalid City, Please re enter the city", "InfoBox: " + "Error", JOptionPane.INFORMATION_MESSAGE);
                    jtf.setText("");
                }

            }
        });
        relodLocations();
    }
    public static void main(String[] args) throws InterruptedException, SQLException {
        new MainForm();

    }

    public JPanel createsubLocation(Location location) throws MalformedURLException {
        Weather loWeather= location.getWeatherObj();
        JPanel disPlayPanelSub = new JPanel();
        //JLabel displayImage = new JLabel();
        String urlString = loWeather.getWeather_icons();
        String modifiedUrl = urlString.replaceAll("\\\\","").replace("[","").replace("]","");
        System.out.println("modifiedUrl :"+modifiedUrl);

        JLabel displayLabelSub = new JLabel("<html>"+location.getName()+"<br/>["+location.getCountry()+"]</html>", SwingConstants.CENTER);
        //JLabel displayLabelSubLT = new JLabel("<html>Local Time"+"<br/>"+location.getLocaltime+"</html>");
        JLabel displayLabelSubTemp = new JLabel("   "+loWeather.getTemperature()+"° C", SwingConstants.CENTER);
        JLabel displayLabelSubfeelslike = new JLabel("<html>Feels Like<br/>"+loWeather.getFeelslike()+"° C"+"</html>", SwingConstants.CENTER);
        JLabel displayLabelhumidity = new JLabel("<html>Humidity<br/>&nbsp;&nbsp;&nbsp;"+loWeather.getHumidity()+"</html>", SwingConstants.CENTER);
        JLabel displayLabelSubweather_descriptions = new JLabel("<html>Sky<br/>"+loWeather.getWeather_descriptions()+"</html>", SwingConstants.CENTER);
        JLabel displayLabelSubwind_speed = new JLabel("<html>Wind Speed<br/>&nbsp;&nbsp;&nbsp;"+loWeather.getWind_speed()+"</html>", SwingConstants.CENTER);
        JLabel displayLabelSubwind_dir = new JLabel("<html>Direction<br/>&nbsp;&nbsp;&nbsp;"+loWeather.getWind_dir()+"</html>", SwingConstants.CENTER);



        disPlayPanelSub.setBounds(5,sublocationY,790,100);
        disPlayPanelSub.setBackground(Color.DARK_GRAY);


        displayLabelSub.setForeground(Color.white);
        displayLabelSub.setFont(normalFontCountry);

        displayLabelSubTemp.setForeground(Color.white);
        //displayLabelSubTemp.setFont(normalFontTemp);
        displayLabelSubTemp.setForeground(Color.white);
        displayLabelSubTemp.setFont(normalFont);
        displayLabelSubTemp.setBackground(Color.CYAN);

        displayLabelhumidity.setForeground(Color.white);
        displayLabelhumidity.setFont(normalFontTemp);
        displayLabelhumidity.setForeground(Color.white);
        displayLabelhumidity.setFont(normalFontCountry);

        displayLabelSubweather_descriptions.setForeground(Color.white);
        displayLabelSubweather_descriptions.setFont(normalFontTemp);
        displayLabelSubweather_descriptions.setForeground(Color.white);
        displayLabelSubweather_descriptions.setFont(normalFontCountry);

        displayLabelSubwind_speed.setForeground(Color.white);
        displayLabelSubwind_speed.setFont(normalFontTemp);
        displayLabelSubwind_speed.setForeground(Color.white);
        displayLabelSubwind_speed.setFont(normalFontCountry);

        displayLabelSubfeelslike.setForeground(Color.white);
        displayLabelSubfeelslike.setFont(normalFontTemp);
        displayLabelSubfeelslike.setForeground(Color.white);
        displayLabelSubfeelslike.setFont(normalFontCountry);

        displayLabelSubwind_dir.setForeground(Color.white);
        displayLabelSubwind_dir.setFont(normalFontTemp);
        displayLabelSubwind_dir.setForeground(Color.white);
        displayLabelSubwind_dir.setFont(normalFontCountry);

        disPlayPanelSub.add(displayLabelSub);
        disPlayPanelSub.add(displayLabelSubTemp);
        disPlayPanelSub.add(displayLabelSubfeelslike);
        disPlayPanelSub.add(displayLabelhumidity);
        disPlayPanelSub.add(displayLabelSubweather_descriptions);
        disPlayPanelSub.add(displayLabelSubwind_speed);
        disPlayPanelSub.add(displayLabelSubwind_dir);
        //disPlayPanelSub.add( displayImage, BorderLayout.CENTER );

        /*URL img = new URL(modifiedUrl);
        ImageIcon image = new ImageIcon(img);
        JLabel displayImage = new JLabel("", image, JLabel.CENTER);
        System.out.println("modifiedUrl :"+modifiedUrl);
        JPanel panel = new JPanel(new BorderLayout());*/

        disPlayPanelSub.setLayout(new GridLayout());
        //disPlayPanelSub.setBackground(Color.LIGHT_GRAY);
        return disPlayPanelSub;
    }

    public void relodLocations() throws SQLException {
        Location location =new Location();
        List<Location> locationList = location.handleAllSelectLocation();
        //System.out.println("locationList size : "+locationList.size());
        //System.out.println(locationList.get(0).getName());
        //System.out.println(locationList.get(1).getName());
        for(Location l:locationList){
            JPanel dynamic = null;
            try {
                //System.out.println("for each :"+l.getName().toString());
                Location locationCurrent = invokeApi(l.getName().toString().trim().replace(" ","%20"));
                dynamic = createsubLocation(locationCurrent);
            } catch (MalformedURLException ex) {
                throw new RuntimeException(ex);
            }
            sublocationY=sublocationY+110;
            con.add(dynamic);
        }
        //System.out.println("Con Size:"+con.getComponentCount());
        window.setVisible(true);

    }
    public Location invokeApi(String city) throws SQLException {
        URL url;
        Location location=null;
        try {
            //url = new URL("https://run.mocky.io/v3/19fa89b0-d9f9-4a82-8f95-0a9048d3a4af");
            //url = new URL("https://radio-world-50-000-radios-stations.p.rapidapi.com/v1/radios/getTopByCountry/{query:'fr'}");
            //url = new URL("https://api.geekflare.com/dnsrecord");

            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            //url = new URL("http://api.weatherstack.com/current?access_key=cf3006e8186f805ea13f07dc593b5f34&query="+city);
            url = new URL("https://run.mocky.io/v3/10758310-05ab-4338-bed7-28392f1b9d30"+"?access_key=cf3006e8186f805ea13f07dc593b5f34&query="+city);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("access_key","cf3006e8186f805ea13f07dc593b5f34");
            conn.setRequestProperty("accept","application/json");
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode= conn.getResponseCode();
            System.out.println("responseCode :"+responseCode);
            System.out.println("responseMessage :"+conn.getContent());
            StringBuilder response=new StringBuilder();
            String inline="";
            if(responseCode==200){
                System.out.println(conn.getResponseMessage());

                Scanner scanner=new Scanner(url.openStream());
                while(scanner.hasNext()){
                    inline+=scanner.nextLine();
                    //response.append(scanner.nextLine());
                }
                scanner.close();
                System.out.println(response);
                JSONParser parse = new JSONParser();
                JSONObject jobj = (JSONObject)parse.parse(inline);
                JSONObject jobjLocation = (JSONObject)jobj.get("location");
                JSONObject jobjCurrent = (JSONObject)jobj.get("current");

                if(jobjLocation!=null && jobjCurrent!=null){
                    location = new Location(jobjLocation,jobjCurrent);
                }
            }
            else
                System.out.println("Error :"+conn.getResponseMessage());

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return location;
    }
}
