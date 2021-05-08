package cn.crabapples.main.entity;

import androidx.annotation.NonNull;
import com.alibaba.fastjson.JSONObject;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/5/9 1:06
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public class User {
    public String name;
    public byte sex;
    public int age;

    @NonNull
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
