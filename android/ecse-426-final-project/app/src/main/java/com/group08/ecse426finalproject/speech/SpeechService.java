package com.group08.ecse426finalproject.speech;


import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.group08.ecse426finalproject.R;
import com.group08.ecse426finalproject.utils.ResourceAccessor;
import com.group08.ecse426finalproject.bluetooth.BluetoothTransmitter;

import org.json.JSONException;
import org.json.JSONObject;

public class SpeechService {
//    private static final String TAG = "SpeechService";
//    private static final String SPEECH_URL =
//            "https://speech.googleapis.com/v1/speech:recognize?key=";
//    private Context context;
//    private final BluetoothTransmitter bluetoothTransmitter;
//    private final ResourceAccessor resourceAccessor;
//
//    public SpeechService(Context context, BluetoothTransmitter bluetoothTransmitter, ResourceAccessor resourceAccessor) {
//        this.context = context;
//        this.bluetoothTransmitter = bluetoothTransmitter;
//        this.resourceAccessor = resourceAccessor;
//    }
//
//    public void sendDemoRequestString() {
//        sendRequest(resourceAccessor.readRawResourceString(R.raw.audio_64));
//    }
//
//    public void sendDemoRequestBytes() {
//        sendRequest(resourceAccessor.readRawResourceBytes(R.raw.audio));
//    }
//
//    public void sendRequest(byte[] audio_bytes) {
//        String audio_base64 = Base64.encodeToString(audio_bytes, Base64.NO_WRAP);
//        sendRequest(audio_base64);
//    }
//
//    public void sendRequest(String audio_base64) {
//        RequestQueue queue = Volley.newRequestQueue(context);
//        JSONObject jsonRequest = new JSONObject();
//        try {
//            jsonRequest = new JSONObject(resourceAccessor.readRawResourceString(R.raw.sync_request));
//            jsonRequest.getJSONObject("audio").put("content", audio_base64);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String api_key = resourceAccessor.readRawResourceString(R.raw.gcloud_api_key);
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
//                SPEECH_URL + api_key, jsonRequest, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d(TAG, "Response: " + response);
//                try {
//                    String transcript = response.getJSONArray("results")
//                            .getJSONObject(0).getJSONArray("alternatives")
//                            .getJSONObject(0).getString("transcript");
//                    Log.d(TAG, "Transcript: " + transcript);
//                    bluetoothTransmitter.transmitString(transcript);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d(TAG, "POST failed: " + error.getMessage() + ", " + error.toString());
//            }
//        });
//        queue.add(jsonObjectRequest);
//    }
}