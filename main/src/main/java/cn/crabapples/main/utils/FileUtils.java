package cn.crabapples.main.utils;

import android.util.Log;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

/**
 * TODO 文件操作工具类
 *
 * @author Mr.He
 * 2021/5/8 16:00
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public class FileUtils {
    private final static String TAG = "FileUtils";

    /**
     * 保存json数据
     *
     * @param path       文件路径
     * @param jsonObject json数据
     */
    public static void saveFile2Json(String path, JSONObject jsonObject) {
        try {
            writeFile2Text(path, jsonObject);
        } catch (Exception e) {
            Log.e(TAG, "保存文件失败");
            Log.e(TAG, "文件路径:" + path);
            e.printStackTrace();
        }
    }

    /**
     * 读取json数据
     *
     * @param path 文件路径
     */
    public static JSONObject getJson2File(String path) {
        try {
            return readTextFile(path);
        } catch (Exception e) {
            Log.e(TAG, "读取文件失败");
            Log.e(TAG, "文件路径:" + path);
            e.printStackTrace();
            return null;
        }
    }

    private static void writeFile2Text(String path, JSONObject data) throws Exception {
        Log.i(TAG, data.toString());
        File file = new File(path);
        if (file.delete() | file.createNewFile()) {
            PrintWriter printWriter = new PrintWriter(file.toString());
            printWriter.println(data);
            printWriter.close();
        }
    }


    private static JSONObject readTextFile(String path) throws Exception {
        Log.i(TAG, path);
        BufferedReader br = new BufferedReader(new FileReader(path));
        StringBuilder data = new StringBuilder();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            data.append(line);
        }
        JSONObject json = JSONObject.parseObject(String.valueOf(data));
        br.close();
        return json;
    }
}
