package com.app.qbik.qbik;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

/**
 * Created by Omar on 27/11/2014.
 */
public class Statics {

    public static String BASE_URL = "http://108.61.205.173/";

    public static Usuario currentUser;

    public static void logIn(final Usuario usuario){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.add("username", usuario.nombre);
        params.add("password", usuario.password);
        client.post(String.format("%s%s", BASE_URL, "secure/login"), params , new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                currentUser = usuario;
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public static void logOut(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(String.format("%s%s", BASE_URL, "secure/logout"), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                currentUser = null;
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });
    }

    public static boolean loggedId(){
        return currentUser != null;
    }

}
