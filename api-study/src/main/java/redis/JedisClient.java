package redis;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Objects;
import java.util.Set;

/**
 * @author : lihp
 * @Description :
 * @date : 2023/4/14 15:37
 */
public class JedisClient {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("192.168.110.40", 6379);  //指定Redis服务Host和port
        jedis.auth("supconit"); //如果Redis服务连接需要密码，制定密码
        jedis.select(6);
        Long db = jedis.getDB();
        boolean connected = jedis.isConnected();
        System.out.println("connected = " + connected);
//        jedis.
        Set<String> value = jedis.keys("myhash-001"); //访问Redis服务
        String type = jedis.type("myhash-001");

        if (Objects.equals(type, "hash")) {
            Set<String> hkeys = jedis.hkeys("myhash-001");
            System.out.println("hkeys = " + hkeys);

        }
        System.out.println(type);
//
//        System.out.println("value = " + value);
        jedis.close(); //使用完关闭连接

    }


    @Test
    public void testdd() {

        JSONObject jsonObject = new JSONObject();
        String sql = "\"" + "table" + "\"";

        System.out.println("sql = " + sql);

        jsonObject.put("sql", sql);
        String jsonString = JSONObject.toJSONString(jsonObject, SerializerFeature.DisableCheckSpecialChar);

        System.out.println(jsonString);
//        jsonString= jsonString.replace("\"","\\\"");
        System.out.println("jsonString2 = " + jsonString);
        String param = "\"" + jsonString.replace("\"", "\\\"")  //  " 替换为 \"
                .replace("`", "\\\\\\`").replaceAll("\r\n", "") + "\"";


        System.out.println("param = " + param);
    }

    @Test
    public void test55() {

//        String escapedJsonString = JSON.toJSONString(jsonString, SerializerFeature.UseSingleQuotes);

    }

}
