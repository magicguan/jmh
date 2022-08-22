import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * function description.
 *
 * <p><h2>Descriptions</h2>
 * <h3>Project</h3> jmh
 * <h3>Package</h3> PACKAGE_NAME
 * </p>
 * <p><h2>Change History</h2>
 * 2022/8/18 13:47 | guanrongzhi | created
 * </p>
 *
 * @author guanrongzhi
 * @version 1.0.0
 */
public class BenchmarkTest {
    public static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 文本读入、输出
     */
    public static void fileHandle() {
        //文本读入
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/guanrongzhi/code/jmh/results/input/a.txt"))); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/guanrongzhi/code/jmh/results/output/a-output.txt"), StandardCharsets.UTF_8));) {
            String a;
            while ((a = reader.readLine()) != null) {
                writer.write(a);
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * String处理
     */
    public static void stringHandle() {
        //文本读入
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/guanrongzhi/aspSVN/咪咕NET/上线操作手册/动漫20220705业务代码清理/计费点清理报错20220126csv.csv")))) {
            String a;
            while ((a = reader.readLine()) != null) {
                String s = a.toLowerCase();
                boolean contains = a.contains("123");
                if (a.length() > 5) {
                    String substring = a.substring(a.length() - 5);
                }
                if (a.length() > 8) {
                    String substring = a.substring(a.length() - 8, a.length() - 3);
                }

                String replaceAll = a.replaceAll("abc", "cba");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * json序列化
     */
    public static void jsonSerialize() {
        //随机属性生成
        String userId = "1234561341";
        String username = "same";
        String password = "1234566";
        String tel = "1388888888";
        String email = "12312341234@qq.com";
        String avatar = "http://1231231.com/avatar";
        boolean isAuth = false;
        Integer age = 18;
        String sex = "female";

        TestUser user = new TestUser(userId, username, password, tel, email, avatar, isAuth, age, sex);

        try {
            objectMapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json反序列化
     */
    public static void jsonDeserialize() {
        String user = "{\n" + "  \"userId\": \"12345145\",\n" + "  \"username\": \"same\",\n" + "  \"password\": \"123456\",\n" + "  \"tel\": \"13888888888\",\n" + "  \"email\": \"1231234@qq.com\",\n" + "  \"avatar\": \"http://1231231.com/avatar\",\n" + "  \"age\": 18,\n" + "  \"sex\": \"female\",\n" + "  \"auth\": false\n" + "}";
        try {
            objectMapper.readValue(user, TestUser.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void streamTest() {

    }

    public static void main(String[] args) {
        fileHandle();
    }
}
