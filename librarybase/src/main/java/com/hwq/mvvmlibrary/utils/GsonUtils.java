package com.hwq.mvvmlibrary.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/29.
 */

public class GsonUtils {
    private static Gson gson;

    static {
        if (null == gson) {
//            偷懒了
            gson = new GsonBuilder()
                    .serializeNulls()//序列化为null对象
                    .setPrettyPrinting()
                    .disableHtmlEscaping()
//                    .registerTypeAdapterFactory(ResponseDataTypeAdaptor.FACTORY)
//                    .registerTypeAdapter(new TypeToken<Map<String,Object>>(){}.getType(),new DataTypeAdapter())
//            .registerTypeAdapter(HashMap.class, new JsonDeserializer<HashMap>() {
//                public HashMap deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
//                        throws JsonParseException {
//                    HashMap<String, Object> resultMap = new HashMap<>();
//                    JsonObject jsonObject = json.getAsJsonObject();
//                    Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
//                    for (Map.Entry<String, JsonElement> entry : entrySet) {
//                        resultMap.put(entry.getKey(), entry.getValue());
//                    }
//                    return resultMap;
//                }
//            })
                    .create();

//            try {
//                Field factories = Gson.class.getDeclaredField("factories");
//                factories.setAccessible(true);
//                Object o = factories.get(gson);
//                Class<?>[] declaredClasses = Collections.class.getDeclaredClasses();
//                for (Class c : declaredClasses) {
//                    if ("java.util.Collections$UnmodifiableList".equals(c.getName())) {
//                        Field listField = c.getDeclaredField("list");
//                        listField.setAccessible(true);
//                        List<TypeAdapterFactory> list = (List<TypeAdapterFactory>) listField.get(o);
//                        int i = list.indexOf(ObjectTypeAdapter.FACTORY);
//                        list.set(i, MapTypeAdapter.FACTORY);
//                        break;
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }
    }

    public static <T> T getObject(String jsonString, Class<T> classOfT) {
        return gson.fromJson(jsonString, classOfT);
    }
    public static <T> T getObject(JsonElement jsonElement, Class<T> classOfT) {
        return gson.fromJson(jsonElement, classOfT);
    }

    public static String getJsonString(Object src) {

        return gson.toJson(src);
    }
    public static JsonElement toJsonTree(Object src) {

        return gson.toJsonTree(src);
    }

    public static <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();

        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = gson.fromJson(json, type);

        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            if (null == jsonObject) {
                continue;
            }
            arrayList.add(gson.fromJson(jsonObject, clazz));
        }
        return arrayList;
    }
    public static <T> ArrayList<T> jsonToArrayList(JsonElement json, Class<T> clazz) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapterFactory(new NullStringToEmptyAdapterFactory()).create();

        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = gson.fromJson(json, type);

        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            if (null == jsonObject) {
                continue;
            }
            arrayList.add(gson.fromJson(jsonObject, clazz));
        }
        return arrayList;
    }

    public static String getValueByName(String result, String name) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String value = jsonObject.optString(name);
        return value;
    }

    public static int getIntValue(String result, String name) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int value = jsonObject.optInt(name);
        return value;
    }

    public static long getLongValue(String result, String name) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        long value = jsonObject.optLong(name);
        return value;
    }

    public static double getDoubleValue(String result, String name) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        double value = jsonObject.optDouble(name);
        return value;
    }

    public static boolean getBooleanValue(String result, String name) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        boolean value = jsonObject.optBoolean(name);
        return value;
    }


    private static String jsonString(String s) {
        char[] temp = s.toCharArray();
        int n = temp.length;
        for (int i = 0; i < n; i++) {
            if (temp[i] == ':' && temp[i + 1] == '"') {
                for (int j = i + 2; j < n; j++) {
                    if (temp[j] == '"') {
                        if (temp[j + 1] != ',' && temp[j + 1] != '}') {
                            temp[j] = '”';
                        } else if (temp[j + 1] == ',' || temp[j + 1] == '}') {
                            break;
                        }
                    }
                }
            }
        }
        return new String(temp);
    }

    /**
     * 将Json数组解析成相应的映射对象列表
     * 解决类型擦除的问题
     */
    public static <T> List<T> toList(String jsonStr, Class<T> clz) {
        List<T> list = gson.fromJson(jsonStr, new type(clz));
        if (list == null) list = new ArrayList<>();
        return list;
    }

    private static class type implements ParameterizedType {
        private Type type;

        private type(Type type) {
            this.type = type;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{type};
        }

        @Override
        public Type getRawType() {
            return ArrayList.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
