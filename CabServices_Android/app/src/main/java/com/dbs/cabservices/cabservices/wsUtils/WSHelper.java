package com.dbs.cabservices.cabservices.wsUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dbs.cabservices.cabservices.Constants;
import com.dbs.cabservices.cabservices.MyApplication;


import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * COUPLEDATING
 * com.coupledating.utils.wsUtils
 */
public class WSHelper  {
    static int serverResponseCode = 0;
    public synchronized static void makeStringReq(Context mContext, String url, final int requestCode, final IResponseCallBack listener, int method) {
        final ProgressDialog pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(method,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                listener.onSuccess(requestCode, response);
                pDialog.hide();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                listener.onError(requestCode, error.getMessage());
                pDialog.hide();
            }
        });

// Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq, requestCode + "");
    }


    public synchronized static void StReqMap(Context mContext, String url, final int requestCode, final Map<String, String> params, final IResponseCallBack listener, int method) {
        final ProgressDialog pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Loading...");
        pDialog.show();


        final StringRequest strReq = new StringRequest(method,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                listener.onSuccess(requestCode, response);
                pDialog.hide();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                 if(error.networkResponse!=null)
                 listener.onError(error.networkResponse.statusCode, error.getMessage());
                 pDialog.hide();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        MyApplication.getInstance().addToRequestQueue(strReq, requestCode + "");
    }

    public synchronized static void StReqMapsJson(Context mContext, String url, final int requestCode, final Map params, final IResponseCallBack listener, int method) {
        final ProgressDialog pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Loading...");
        pDialog.show();
        RequestQueue queue = Volley.newRequestQueue(mContext);
        JSONObject jsonObject=new JSONObject(params);

        final String requestBody=jsonObject.toString();

        Log.v(WSHelper.class.getSimpleName(),"volley reuest params "+jsonObject.toString());
        StringRequest jsonObjReq = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response.getBytes().toString());
                        pDialog.hide();
                        listener.onSuccess(requestCode, response.getBytes().toString());

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        }) {

            @Override
            public String getBodyContentType() {
                return "application/json";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }

        };
// Adding request to request queue
        queue.add(jsonObjReq);
       // MyApplication.getInstance().addToRequestQueue(jsonObjReq, requestCode + "");
    }

    public static int uploadFile(final Context context, Bitmap bitmap) {



        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;


            try {

                // open a URL connection to the Servlet
               // FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(Constants.GET_CABS);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "multipart/form-data");

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data;userfile=test.png"
                                 + lineEnd);

                        dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                //bytesAvailable = fileInputStream.available();


                // read file and write it into form...
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, dos);

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + readInputStreamToString(conn));

                if(serverResponseCode == 200){

                    ((Activity)context).runOnUiThread(new Runnable() {
                        public void run() {


                            Toast.makeText(context, "File Upload Complete.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                //close the streams //
                //fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {



                ((Activity)context).runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(context, "MalformedURLException",
                                Toast.LENGTH_SHORT).show();
                    }
                });

                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {


                e.printStackTrace();

                ((Activity)context).runOnUiThread(new Runnable() {
                    public void run() {

                        Toast.makeText(context, "Got Exception : see logcat ",
                                Toast.LENGTH_SHORT).show();
                    }
                });

            }

            return serverResponseCode;

    }

    private static String readInputStreamToString(HttpURLConnection connection) {
        String result = null;
        StringBuffer sb = new StringBuffer();
        InputStream is = null;

        try {
            is = new BufferedInputStream(connection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            result = sb.toString();
        }
        catch (Exception e) {
            Log.i(TAG, "Error reading InputStream");
            result = null;
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                }
                catch (IOException e) {
                    Log.i(TAG, "Error closing InputStream");
                }
            }
        }

        return result;
    }
}
