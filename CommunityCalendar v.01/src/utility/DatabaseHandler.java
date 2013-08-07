/*TODO 
 * add Functions for adding and viewing and updating events, requirements, other stuff
 */

package utility;


import java.util.HashMap;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "Community_Calendar";

	// user table name
	private static final String TABLE_USER_DETAILS = "user";
	
	// group list table name
	private static final String TABLE_GROUPS = "group_list";
	
	// event table name
	private static final String TABLE_EVENTS = "event_list";
	
	// event details table name
	private static final String TABLE_EVENT = "event_details";
	
	// requirements table name
	private static final String TABLE_REQUIREMENTS = "requirement_list";
	
	// family list table name
	private static final String TABLE_FAMILY = "family_list";


	// user Table Columns names
	public static final String KEY_LOGGED_IN = "is_logged_in";
	public static final String KEY_PASSWORD = "password";
	public static final String KEY_USER_NAME = "user_name";
	public static final String KEY_USER_ID = "uid";
	public static final String KEY_USER_EMAIL = "email";
	public static final String KEY_USER_BDAY = "birthday";
	public static final String KEY_USER_TYPE = "user_type";
	
	// group list column names
	public static final String KEY_GROUP_NAME = "group_name";
	public static final String KEY_GROUP_ID = "gid";
	public static final String KEY_MEMBER = "is_member";
	public static final String KEY_GROUP_MESSAGE = "group_message";
	public static final String KEY_GROUP_CONTACT = "contact_id";
	
	// EVENT LIST COLUMN NAMES
	public static final String KEY_EVENT_ID = "event_id";
	public static final String KEY_EVENT_NAME = "event_name";
	public static final String KEY_EVENT_DATE = "event_date";
	
	// EVENT DETAILS TABLE COLUMN NAMES
	public static final String KEY_EVENT_CONTACT = "event_contact_id";
	public static final String KEY_EVENT_ATTENDING = "is_attending";
	public static final String KEY_LOCATION = "event_location";
	public static final String KEY_EVENT_DESCRIPTION = "event_description";
	
	//REQUIREMENT TABLE COLUMN NAMES
	
	public static final String KEY_REQUIREMENT_ID = "requirement_id";
	public static final String KEY_REQUIREMENT_TYPE = "requirement_type";
	public static final String KEY_REQUIREMENT_CONDITION = "requirement_condition";
	public static final String KEY_REQUIREMENT = "requirement";
	
	
	

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER_DETAILS + "("
				+ KEY_USER_ID + " INTEGER PRIMARY KEY," 
				+ KEY_USER_NAME + " TEXT,"
				+ KEY_USER_EMAIL + " TEXT UNIQUE," 
				+ KEY_USER_ID + " TEXT,"
				+ KEY_PASSWORD + " TEXT," 
				+ KEY_LOGGED_IN + " BOOLEAN," 
				+ KEY_USER_BDAY + " TEXT, " 
				+ KEY_USER_TYPE + " TEXT )";
		

		String CREATE_GROUP_TABLE = "CREATE TABLE " + TABLE_GROUPS + "("
				+ KEY_GROUP_ID + " TEXT PRIMARY KEY," 
				+ KEY_GROUP_NAME + " TEXT,"
				+ KEY_MEMBER + " BOOLEAN," 
				+ KEY_GROUP_MESSAGE + " TEXT,"
				+ KEY_GROUP_CONTACT + " TEXT )";
		
		String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
				+ KEY_EVENT_ID + " TEXT PRIMARY KEY," 
				+ KEY_EVENT_NAME + " TEXT,"
				+ KEY_EVENT_DATE + " TEXT," 
				+ KEY_EVENT_DESCRIPTION + " TEXT,"
				+ KEY_EVENT_CONTACT + " TEXT,"
				+ KEY_EVENT_ATTENDING + "TEXT,"
				+ KEY_LOCATION + " TEXT )";
		
		String CREATE_REQUIREMENTS_TABLE = "CREATE TABLE " + TABLE_REQUIREMENTS + "("
				+ KEY_REQUIREMENT_ID + " TEXT PRIMARY KEY," 
				+ KEY_REQUIREMENT_TYPE + " TEXT,"
				+ KEY_REQUIREMENT_CONDITION + " BOOLEAN," 
				+ KEY_REQUIREMENT + " TEXT,"
				+ KEY_EVENT_ID + " TEXT )";
		
		db.execSQL(CREATE_GROUP_TABLE);
		db.execSQL(CREATE_USER_TABLE);
		db.execSQL(CREATE_EVENTS_TABLE);
		db.execSQL(CREATE_REQUIREMENTS_TABLE);
		
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_DETAILS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUIREMENTS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public void addUser(String name, String email, String uid, String birthday, String password, String uType, String loggedIn) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_LOGGED_IN, loggedIn); // Name
		values.put(KEY_USER_EMAIL, email); // Email
		values.put(KEY_USER_ID, uid); // uid
		values.put(KEY_USER_BDAY, birthday); // birthday
		values.put(KEY_USER_TYPE, uType); // user type (full, restricted)
		values.put(KEY_USER_NAME, name); // users full name
		values.put(KEY_PASSWORD, password); // password

		// Inserting Row
		db.insert(TABLE_USER_DETAILS, null, values);
		db.close(); // Closing database connection
	}
	
	/**
	 * Return whether or not the user is logged in
	 * 
	 **/
	public boolean checkLogin(){
		Boolean returnBool = false;
		String selectQuery = "SELECT * FROM " + TABLE_USER_DETAILS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		// Move to first row
		cursor.moveToFirst();
		if (cursor.getCount() > 0) {
		
			String stringBool = cursor.getString(0);
			if(stringBool.equals("true")){
				returnBool = true;
			}
			
		}
		cursor.close();
		db.close();
		// return user
		return returnBool;
		
	}
	
	/**
	 * Re crate database Delete all tables and create them again
	 **/
	public void resetTables() {
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_USER_DETAILS, null, null);
		db.delete(TABLE_EVENTS, null, null);
		db.delete(TABLE_GROUPS, null, null);
		db.delete(TABLE_REQUIREMENTS, null, null);
		db.close();
	}
	

}