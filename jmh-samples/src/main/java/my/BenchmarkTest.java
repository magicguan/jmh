package my;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(value = 1, jvmArgs = {"-Xms1G","-Xmx1G","-XX:MaxMetaspaceSize=128m","-XX:+UseG1GC","-XX:MaxGCPauseMillis=200"})
@Threads(1)
public class BenchmarkTest {
    public static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 文本读入、输出
     */
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public static void fileHandle() {
        //文本读入
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("/Users/guanrongzhi/code/jmh/results/input/a.txt")))); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get("/Users/guanrongzhi/code/jmh/results/output/a-output.txt")), StandardCharsets.UTF_8))) {
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
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public static void stringHandle() {
        //文本读入
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("/Users/guanrongzhi/aspSVN/咪咕NET/上线操作手册/动漫20220705业务代码清理/计费点清理报错20220126csv.csv"))))) {
            String a;
            while ((a = reader.readLine()) != null) {
                String[] s1 = a.split(" ");
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
    @Benchmark
    public static void jsonSerialize() {
//        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get("/Users/guanrongzhi/code/jmh/results/input/json-input.txt")), StandardCharsets.UTF_8))) {
        try {
//            for (int i = 0; i < 10; i++) {
            String userId = UUID.randomUUID().toString().replaceAll("-", "");
            String username = userId.substring(15, (int) (Math.random() * 10 + 15));
            String password = userId.substring(12, (int) (Math.random() * 10 + 12));
            String tel = "1388888888";
            String email = userId.substring(1, (int) (Math.random() * 11 + 1)) + "@" +
                    userId.substring(18, (int) (Math.random() * 5 + 18)) +
                    ".com";
            String avatar = "http://" + userId.substring(20, (int) (Math.random() * 8 + 20)) +
                    ".com/avatar";
            boolean isAuth = Math.random() > 0.5;
            Integer age = 12 + (int) (30 * Math.random());
            String sex = Math.random() > 0.5 ? "female" : "male";

            TestUser user = new TestUser(userId, username, password, tel, email, avatar, isAuth, age, sex);

            String userJson = objectMapper.writeValueAsString(user);
//                writer.write(userJson + "\n");
//            }
            //随机属性生成

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json反序列化
     */
    @Benchmark
    public static void jsonDeserialize() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("/Users/guanrongzhi/code/jmh/results/input/json-input.txt"))))) {
            String a;
            while ((a = reader.readLine()) != null) {
                if (a.length() > 0)
                    objectMapper.readValue(a, TestUser.class);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * stream
     */
    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public static void streamTest() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("/Users/guanrongzhi/code/jmh/results/input/a.txt")))); BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Files.newOutputStream(Paths.get("/Users/guanrongzhi/code/jmh/results/output/a-output.txt")), StandardCharsets.UTF_8))) {
            String a;
            while ((a = reader.readLine()) != null) {
                List<String> strings = Arrays.asList(a.split(" "));
                List<String> strings1 = strings.stream().filter(s -> s.length() > 8).collect(Collectors.toList());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(BenchmarkTest.class.getSimpleName())
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(options).run();

    }
}