package com.android.triton;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

public class UserStorage {
    private static final String PREFS_NAME = "UserData";
    private static final String USERS_KEY = "users";

    // Save new user
    public static void saveUser(Context context, String name, String email, String phone, String password) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String usersJson = prefs.getString(USERS_KEY, "{}");

        try {
            JSONObject users = new JSONObject(usersJson);
            JSONObject userObject = new JSONObject();
            userObject.put("name", name);
            userObject.put("phone", phone);
            userObject.put("password", password);

            users.put(email, userObject);  // use email as unique key

            prefs.edit().putString(USERS_KEY, users.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Check if user exists
    public static boolean userExists(Context context, String email) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String usersJson = prefs.getString(USERS_KEY, "{}");

        try {
            JSONObject users = new JSONObject(usersJson);
            return users.has(email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Validate login
    public static boolean validateLogin(Context context, String email, String password) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String usersJson = prefs.getString(USERS_KEY, "{}");

        try {
            JSONObject users = new JSONObject(usersJson);
            if (users.has(email)) {
                JSONObject userObject = users.getJSONObject(email);
                String savedPassword = userObject.getString("password");
                return savedPassword.equals(password);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get user details
    public static JSONObject getUserDetails(Context context, String email) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String usersJson = prefs.getString(USERS_KEY, "{}");

        try {
            JSONObject users = new JSONObject(usersJson);
            if (users.has(email)) {
                return users.getJSONObject(email);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
