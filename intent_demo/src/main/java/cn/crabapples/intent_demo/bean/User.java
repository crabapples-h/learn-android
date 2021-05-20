package cn.crabapples.intent_demo.bean;

import androidx.annotation.NonNull;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * TODO
 *
 * @author Mr.He
 * 2021/5/20 15:09
 * e-mail crabapples.cn@gmail.com
 * qq 294046317
 * pc-name mrhe
 */
public class User implements Serializable {
    public String name;
    public String phone;

    @NonNull
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
