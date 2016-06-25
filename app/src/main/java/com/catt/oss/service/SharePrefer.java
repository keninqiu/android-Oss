package com.catt.oss.service;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.json.JSONException;
import org.json.JSONObject;
public class SharePrefer {
	private static final String DBHELPSESSION="dbmobile";
	private SharedPreferences helpShareMobile;
	private Context context;
	private String res="";
	private String restemp="kk";
	public SharePrefer(Context con) {
		super();
		context=con;  
	}
	public void writeSessionID(String sessionID){
		helpShareMobile = context.getSharedPreferences(DBHELPSESSION, Context.MODE_WORLD_READABLE);
		Editor edit = helpShareMobile.edit();

		edit.putString("sessionID", sessionID);

		edit.commit();
	}
	public String readSessionID(){
		helpShareMobile = context.getSharedPreferences(DBHELPSESSION, Context.MODE_WORLD_READABLE);
		res=helpShareMobile.getString("sessionID", "0");
		return res;
	}
	public void writedata(String username,String userpassword){
		helpShareMobile = context.getSharedPreferences(DBHELPSESSION, Context.MODE_WORLD_READABLE);
		Editor edit = helpShareMobile.edit();

		edit.putString("username", username);
		edit.putString("userpassword", userpassword);

		edit.commit();
	}
	public String readusername(){
		helpShareMobile = context.getSharedPreferences(DBHELPSESSION, Context.MODE_WORLD_READABLE);		
		res=helpShareMobile.getString("username","");
		return res;
	}
	public String readuserpassword(){
		helpShareMobile = context.getSharedPreferences(DBHELPSESSION, Context.MODE_WORLD_READABLE);		
		res=helpShareMobile.getString("userpassword","");
		return res;
	}
	public String readuserkey(){
		helpShareMobile = context.getSharedPreferences(DBHELPSESSION, Context.MODE_WORLD_READABLE);		
		res=helpShareMobile.getString("key","no");
		return res;
	}
	public void saveStaffInfo(JSONObject json){
		helpShareMobile = context.getSharedPreferences(DBHELPSESSION,Context.MODE_WORLD_READABLE);
		Editor edit=helpShareMobile.edit();
		try {
			restemp=json.getString("staff");
			edit.putString("staffperson", restemp);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		edit.commit();
	}
	public String readStaffInfo(){
		helpShareMobile = context.getSharedPreferences(DBHELPSESSION, Context.MODE_WORLD_READABLE);		
		res=helpShareMobile.getString("staffperson","no body");
		return res;
	}
}
