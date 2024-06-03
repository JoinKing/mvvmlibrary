package com.hwq.mvvmlibrary.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * TODO this class desription here
 * <p>
 * Created by ww on 2018/2/27.
 */
public class StringNullAdapter extends TypeAdapter<String> {

    @Override
    public void write(JsonWriter writer, String value) {
        // TODO Auto-generated method stub
        if (value == null) {
            try {
                writer.nullValue();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        try {
            writer.value(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return "";
        }
        return reader.nextString();
    }
}
