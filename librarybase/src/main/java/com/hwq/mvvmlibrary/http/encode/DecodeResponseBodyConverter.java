package com.hwq.mvvmlibrary.http.encode;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.hwq.mvvmlibrary.http.utils.RetrofitClient;
import com.hwq.mvvmlibrary.ssm.PublicGmResult;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


public  class DecodeResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private TypeAdapter<T> adapter;
    private Gson gson;

    public DecodeResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        if (RetrofitClient.enCodeTypeBody == 1) {
            String ciphertext = new String(responseBody.bytes());
            try {
                JSONObject obj = new JSONObject(ciphertext);
                String dataString = obj.optString("data");
                if (null!=dataString&&dataString.length()>0){
                    String object= PublicGmResult.decryptData(dataString);
                    JSONObject data = new JSONObject(object);
                    obj.put("data",data);
                    return adapter.fromJson(obj.toString());
                }else {
                    return adapter.fromJson(obj.toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            return adapter.fromJson(new String(responseBody.bytes()));
        }

        return null;

    }

}