package com.wastedpotential.nutritionapp.serviceimpl;

import java.net.HttpURLConnection;

public class WaySms {
 private static int responseCode = -1;
 private static String userCredentials = null;
 private static String cookie = null;
 private static String site = null;
 private static String token=null;
 private static Credentials credentials = new Credentials();

 public static void send(String number, String message) {
  
  login("8871966618", "9753952725");
  sendSMS(number, message);
  
  System.out.println("Message has been sent successfully!");
 }


 private static void getSite() {
  URLConnector.connect("http://www.way2sms.com/", false, "GET", null, null);
  responseCode = URLConnector.getResponseCode();
  System.out.println(responseCode);
  if(responseCode != HttpURLConnection.HTTP_MOVED_TEMP && responseCode != HttpURLConnection.HTTP_OK)
   exit("getSite failed!");
  else {
   site = URLConnector.getLocation();
   if(site != null)
    site = site.substring(7, site.length() - 1);
  }
  System.out.println(site);
  URLConnector.disconnect();
 }

 private static void preHome() {
  URLConnector.connect("http://" + site + "/content/prehome.jsp", false, "GET", null, null);
  responseCode = URLConnector.getResponseCode();
  System.out.println(responseCode);
  if(responseCode != HttpURLConnection.HTTP_MOVED_TEMP && responseCode != HttpURLConnection.HTTP_OK)
   exit("preHome failed");
  else
   cookie = URLConnector.getCookie();
  token = cookie.substring(cookie.indexOf("~") + 1);
  URLConnector.disconnect();
 }

 public static void login(String uid, String pwd) {
  getSite();
  preHome();

  String location = null;

  credentials.set("username", uid);
  credentials.append("password", pwd);
  credentials.append("button", "Login");
  userCredentials = credentials.getUserCredentials();

  URLConnector.connect("http://" + site + "/Login1.action", false, "POST", cookie, userCredentials);
  responseCode = URLConnector.getResponseCode();
  System.out.println(responseCode);
  if(responseCode != HttpURLConnection.HTTP_MOVED_TEMP && responseCode != HttpURLConnection.HTTP_OK)
   exit("authentication failed!");
  else
   location = URLConnector.getLocation();
  URLConnector.disconnect();

  URLConnector.connect(location, false, "GET", cookie, null);
  responseCode = URLConnector.getResponseCode();
  System.out.println(responseCode);
  if(responseCode != HttpURLConnection.HTTP_MOVED_TEMP && responseCode != HttpURLConnection.HTTP_OK)
   exit("redirection failed!");
  URLConnector.disconnect();
 }

 public static void sendSMS(String receiversMobNo, String msg) {

  credentials.reset();
  credentials.append("Token", token);
  credentials.append("message", msg);
  credentials.append("mobile", receiversMobNo);
  credentials.append("msgLen", "124");
  credentials.append("ssaction", "ss");
  

  userCredentials = credentials.getUserCredentials();
  System.out.println("Token=" + token);
  
  URLConnector.connect("http://" + site + "/smstoss.action" , true, "POST", cookie, credentials.getUserCredentials());
  
  responseCode = URLConnector.getResponseCode();
  System.out.println("IN "+responseCode);
  if(responseCode != HttpURLConnection.HTTP_MOVED_TEMP && responseCode != HttpURLConnection.HTTP_OK)
   exit("sendSMS failed!");
  URLConnector.disconnect();
 }

 @SuppressWarnings("unused")
private static void sendBulkSMS(String[] receiversMobNos, String msg) {
 int noOfReceivers = receiversMobNos.length;

 for(int i = 0; i < noOfReceivers; i++)
  sendSMS(receiversMobNos[i], msg);
 }
 private static void exit(String errorMsg) {
  System.err.println(errorMsg);
  System.exit(1);
 }
}