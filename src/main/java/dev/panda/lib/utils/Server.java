package dev.panda.lib.utils;

import dev.panda.lib.chat.CC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Server {

    public static String getIP() {
        URL url;
        BufferedReader in;
        String ipAddress = "";
        try {
            url = new URL("http://bot.whatismyipaddress.com");
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            ipAddress = in.readLine().trim();
            if (ipAddress.length() <= 0)
                try {
                    InetAddress ip = InetAddress.getLocalHost();
                    System.out.println(ip.getHostAddress().trim());
                    ipAddress = ip.getHostAddress().trim();
                } catch (Exception exp) {
                    ipAddress = "ERROR";
                }
        }
        catch (Exception ex) {
            CC.log("Error in check your host ip!");
            ex.printStackTrace();
        }
        return ipAddress;
    }

    public static String getDate(String dateFormat, String timeZone) {
        Date date = new Date();
        SimpleDateFormat dtf = new SimpleDateFormat(dateFormat);
        dtf.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dtf.format(date);
    }

    public static String getHour(String hourFormat, String timeZone) {
        Date date = new Date();
        SimpleDateFormat dtf = new SimpleDateFormat(hourFormat);
        dtf.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dtf.format(date);
    }
}
