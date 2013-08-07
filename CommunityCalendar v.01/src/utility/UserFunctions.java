package utility;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


import android.content.Context;

import android.util.Log;

public class UserFunctions {

   private JSONParser jsonParser;

   // Testing in localhost using wamp or xampp
   // use http://10.0.2.2/ to connect to your localhost ie http://localhost/
   private static String loginURL = "http://sandltestsite.site90.net/android_login_api/index.php";
   private static String registerURL = "http://sandltestsite.site90.net/android_login_api/index.php";
   //private static String info_tag = "getInfo";
   private static String login_tag = "login";
   private static String register_tag = "register";

   // constructor
   public UserFunctions(){
       jsonParser = new JSONParser();
   }

   /**
    * function make Login Request
    * @param email
    * @param password
    * */
   public JSONObject loginUser(String email, String password){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", login_tag));
       params.add(new BasicNameValuePair("email", email));
       params.add(new BasicNameValuePair("password", password));
      
       JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
       // return json
       //Log.e("JSON", json.toString());
       return json;
   }

   /**
    * function make Register Request
    * @param name
    * @param email
    * @param password
    * */
   public JSONObject registerUser(String password, String email, String name){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", register_tag));
       params.add(new BasicNameValuePair("password", password));
       params.add(new BasicNameValuePair("email", email));
       params.add(new BasicNameValuePair("name", name));

       // getting JSON Object
       JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
       // return json
       return json;
   }

   /**
    * Function get Login status
    * */
   public boolean isUserLoggedIn(Context context){
       DatabaseHandler db = new DatabaseHandler(context);
       int count = db.getRowCount();
       if(count > 0){
           // user logged in
           return true;
       }
       return false;
   }
   /**
    * function make Info Request
    * @param email
    * */
   public JSONObject getInfo(String email, String uid){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", "getInfo"));
       params.add(new BasicNameValuePair("email", email));
       params.add(new BasicNameValuePair("uid", uid));

       // getting JSON Object
       JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
       // return json
       return json;
   }
   
   /**
    * function make Info Request
    * @param email
    * */
   public JSONObject postInfo(String email, String uid, String password, String address, String name, String suite, String city, String state, String zip, String phone, String country){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", "postInfo"));
       params.add(new BasicNameValuePair("email", email));
       params.add(new BasicNameValuePair("uid", uid));
       params.add(new BasicNameValuePair("name", name));
       params.add(new BasicNameValuePair("address", address));
       params.add(new BasicNameValuePair("suite", suite));
       params.add(new BasicNameValuePair("city", city));
       params.add(new BasicNameValuePair("zip", zip));
       params.add(new BasicNameValuePair("state", state));
       params.add(new BasicNameValuePair("phone", phone));
       params.add(new BasicNameValuePair("country", country));
       params.add(new BasicNameValuePair("password", password));
       Log.e("email", email);
       Log.e("uid", uid);
       Log.e("name", name);
       Log.e("phone", phone);
       Log.e("password", password);
       
       Log.e("address", address);
       Log.e("suite", suite);
       Log.e("city", city);
       Log.e("state", state);
       Log.e("zip", zip);
       Log.e("country", country);
       


       // getting JSON Object
       JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
       // return json
       return json;
   }
   /**
    * TODO get products function
    * @param email
    * */
   public JSONObject getProductList(String uid, String clientId){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", "getProducts"));
       params.add(new BasicNameValuePair("uid", uid));
       params.add(new BasicNameValuePair("cid", clientId));


       // getting JSON Object
       JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
       // return json
       return json;
   }
   public JSONObject getProductInfo(String email, String uid, String clientId, String productId){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", "getProducts"));
       params.add(new BasicNameValuePair("uid", uid));
       params.add(new BasicNameValuePair("clientId", clientId));
       params.add(new BasicNameValuePair("productId", productId));
       


       // getting JSON Object
       JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
       // return json
       return json;
   }
   
   /**
    * function make Info Request
    * @param email
    * */
   public JSONObject addProduct(String email, String uid){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", "addProduct"));
       params.add(new BasicNameValuePair("email", email));
       params.add(new BasicNameValuePair("uid", uid));
       
       


       // getting JSON Object
       JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
       // return json
       return json;
   }
   
   /**
    * function make Info Request
    * @param email
    * */
   public JSONObject addProductLocation(String sn, String cid, String lat, String longitude){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", "addProductLocation"));
       params.add(new BasicNameValuePair("cid", cid));
       params.add(new BasicNameValuePair("sn", sn));
       params.add(new BasicNameValuePair("latitude", lat));
       params.add(new BasicNameValuePair("longitude", longitude));
       
       


       // getting JSON Object
       JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
       // return json
       return json;
   }
   
   public JSONObject getMaintenanceList(String product){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", "getMaintenanceList"));
       params.add(new BasicNameValuePair("product", product));

       // getting JSON Object
       JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
       // return json
       return json;
   }
   
   public JSONObject getMaintenanceHistory(String product){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", "getMaintenanceHistory"));
       params.add(new BasicNameValuePair("product", product));

       // getting JSON Object
       JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
       // return json
       return json;
   }
   
   public JSONObject getMaintenanceSchedule(String product){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", "getMaintenanceSchedule"));
       params.add(new BasicNameValuePair("product", product));

       // getting JSON Object
       JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
       // return json
       return json;
   }
   
   public JSONObject performMaintenance(String product, String performedBy, String type, String notes){
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       params.add(new BasicNameValuePair("tag", "performMaintenance"));
       params.add(new BasicNameValuePair("product", product));
       params.add(new BasicNameValuePair("performedBy", performedBy));
       params.add(new BasicNameValuePair("type", type));
       params.add(new BasicNameValuePair("notes", notes));

       // getting JSON Object
       JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
       // return json
       return json;
   }

   /**
    * Function to logout user
    * Reset Database
    * */
   public boolean logoutUser(Context context){
       DatabaseHandler db = new DatabaseHandler(context);
       db.resetTables();
       return true;
   }



}