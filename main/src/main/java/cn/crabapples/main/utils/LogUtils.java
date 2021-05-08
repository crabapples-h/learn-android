package cn.crabapples.main.utils;

import android.util.Log;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO 日志工具类
 *
 * @author Mr.He
 * 2021/5/8 21:34
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public class LogUtils {
    private final static String TAG = "cn.crabapples";

    public static void info(String tag, String template, Object... args) {
        String content = MessageFormat.format(formatTemplate(template), args);
        Log.i(tag, content);
    }

    public static void warn(String tag, String template, Object... args) {
        String content = MessageFormat.format(formatTemplate(template), args);
        Log.w(tag, content);
    }

    public static void error(String tag, String template, Object... args) {
        String content = MessageFormat.format(formatTemplate(template), args);
        Log.e(tag, content);
    }

    public static void debug(String tag, String template, Object... args) {
        String content = MessageFormat.format(formatTemplate(template), args);
        Log.d(tag, content);
    }

//    public static void main(String[] args) {
//        String template = formatTemplate("喜欢你：[{},{}]");
//        System.err.println(template);
//        String cont = MessageFormat.format(template, 1, 2, 3);
//        System.err.println(cont);
//    }

    private static String formatTemplate(String template) {
        Pattern pattern = Pattern.compile("\\{}");
        Matcher matcher = pattern.matcher(template);
        int i = 0;
        while (matcher.find()) {
            template = matcher.replaceFirst("{" + i++ + "}");
            matcher = pattern.matcher(template);
        }
        return template;
    }
}
