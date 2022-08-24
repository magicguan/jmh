package my;

import com.fasterxml.jackson.core.JsonProcessingException;
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
@Fork(value = 1, jvmArgs = {"-Xms1G", "-Xmx1G", "-XX:MaxMetaspaceSize=128m", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=200"})
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
        String a = """
                William Shakespeare (bapt. 26 April 1564 – 23 April 1616)[a] was an English playwright, poet and actor. He is widely
                regarded as the greatest writer in the English language and the world's greatest dramatist.[2][3][4] He is often called
                England's national poet and the "Bard of Avon" (or simply "the Bard").[5][b] His extant works, including collaborations,
                consist of some 39 plays,[c] 154 sonnets, three long narrative poems, and a few other verses, some of uncertain
                authorship. His plays have been translated into every major living language and are performed more often than those of
                any other playwright.[7] He remains arguably the most influential writer in the English language, and his works continue
                to be studied and reinterpreted.
                Shakespeare was born and raised in Stratford-upon-Avon, Warwickshire. At the age of 18, he married Anne Hathaway, with
                whom he had three children: Susanna and twins Hamnet and Judith. Sometime between 1585 and 1592, he began a successful
                career in London as an actor, writer, and part-owner of a playing company called the Lord Chamberlain's Men, later known
                as the King's Men. At age 49 (around 1613), he appears to have retired to Stratford, where he died three years later.
                Few records of Shakespeare's private life survive; this has stimulated considerable speculation about such matters as
                his physical appearance, his sexuality, his religious beliefs and whether the works attributed to him were written by
                others.[8][9][10]
                Shakespeare produced most of his known works between 1589 and 1613.[11][12][d] His early plays were primarily comedies
                and histories and are regarded as some of the best works produced in these genres. He then wrote mainly tragedies until
                1608, among them Hamlet, Romeo and Juliet, Othello, King Lear, and Macbeth, all considered to be among the finest works
                in the English language.[2][3][4] In the last phase of his life, he wrote tragicomedies (also known as romances) and
                collaborated with other playwrights.
                Many of Shakespeare's plays were published in editions of varying quality and accuracy in his lifetime. However, in
                1623, two fellow actors and friends of Shakespeare's, John Heminges and Henry Condell, published a more definitive text
                known as the First Folio, a posthumous collected edition of Shakespeare's dramatic works that included all but two of
                his plays.[13] Its Preface was a prescient poem by Ben Jonson that hailed Shakespeare with the now famous epithet:
                "not of an age, but for all time".[13]
                Shakespeare was the son of John Shakespeare, an alderman and a successful glover (glove-maker) originally from
                Snitterfield in Warwickshire, and Mary Arden, the daughter of an affluent landowning family.[14] He was born in
                Stratford-upon-Avon, where he was baptised on 26 April 1564. His date of birth is unknown, but is traditionally observed
                on 23 April, Saint George's Day.[15] This date, which can be traced to William Oldys and George Steevens, has proved
                appealing to biographers because Shakespeare died on the same date in 1616.[16][17] He was the third of eight children,
                and the eldest surviving son.[18]
                Although no attendance records for the period survive, most biographers agree that Shakespeare was probably educated at
                the King's New School in Stratford,[19][20][21] a free school chartered in 1553,[22] about a quarter-mile (400 m) from
                his home. Grammar schools varied in quality during the Elizabethan era, but grammar school curricula were largely
                similar: the basic Latin text was standardised by royal decree,[23][24] and the school would have provided an intensive
                education in grammar based upon Latin classical authors.[25]
                At the age of 18, Shakespeare married 26-year-old Anne Hathaway. The consistory court of the Diocese of Worcester issued
                a marriage licence on 27 November 1582. The next day, two of Hathaway's neighbours posted bonds guaranteeing that no
                lawful claims impeded the marriage.[26] The ceremony may have been arranged in some haste since the Worcester chancellor
                allowed the marriage banns to be read once instead of the usual three times,[27][28] and six months after the marriage
                Anne gave birth to a daughter, Susanna, baptised 26 May 1583.[29] Twins, son Hamnet and daughter Judith, followed almost
                two years later and were baptised 2 February 1585.[30] Hamnet died of unknown causes at the age of 11 and was buried 11
                August 1596.[31]
                After the birth of the twins, Shakespeare left few historical traces until he is mentioned as part of the London theatre
                scene in 1592. The exception is the appearance of his name in the "complaints bill" of a law case before the Queen's
                Bench court at Westminster dated Michaelmas Term 1588 and 9 October 1589.[32] Scholars refer to the years between 1585
                and 1592 as Shakespeare's "lost years".[33] Biographers attempting to account for this period have reported many
                apocryphal stories. Nicholas Rowe, Shakespeare's first biographer, recounted a Stratford legend that Shakespeare fled
                the town for London to escape prosecution for deer poaching in the estate of local squire Thomas Lucy. Shakespeare is
                also supposed to have taken his revenge on Lucy by writing a scurrilous ballad about him.[34][35] Another 18th-century
                story has Shakespeare starting his theatrical career minding the horses of theatre patrons in London.[36] John Aubrey
                reported that Shakespeare had been a country schoolmaster.[37] Some 20th-century scholars suggested that Shakespeare
                may have been employed as a schoolmaster by Alexander Hoghton of Lancashire, a Catholic landowner who named a certain
                "William Shakeshafte" in his will.[38][39] Little evidence substantiates such stories other than hearsay collected after
                his death, and Shakeshafte was a common name in the Lancashire area.[40][41]""";
        for (String b : a.split("\n")) {
            String[] s1 = b.split(" ");
            String s = b.toLowerCase();
            boolean contains = a.contains("123");
            if (b.length() > 5) {
                String substring = a.substring(a.length() - 5);
            }
            if (b.length() > 8) {
                String substring = a.substring(a.length() - 8, a.length() - 3);
            }

            String replaceAll = b.replaceAll("abc", "cba");
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
        int age = 12 + (int) (30 * Math.random());
        String sex = Math.random() > 0.5 ? "female" : "male";
        String a = "{\"userId\":\"" +
                userId +
                "\",\"username\":\"" +
                username +
                "\",\"password\":\"" +
                password +
                "\",\"tel\":\"" +
                tel +
                "\",\"email\":\"" +
                email +
                "\",\"avatar\":\"" +
                avatar +
                "\",\"age\":" +
                age +
                ",\"sex\":\"" +
                sex +
                "\",\"auth\":" +
                isAuth +
                "}";

        try {
            objectMapper.readValue(a, TestUser.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * stream
     */
    @Benchmark
    public static void streamTest() {
        String a = """
                William Shakespeare (bapt. 26 April 1564 – 23 April 1616)[a] was an English playwright, poet and actor. He is widely
                regarded as the greatest writer in the English language and the world's greatest dramatist.[2][3][4] He is often called
                England's national poet and the "Bard of Avon" (or simply "the Bard").[5][b] His extant works, including collaborations,
                consist of some 39 plays,[c] 154 sonnets, three long narrative poems, and a few other verses, some of uncertain
                authorship. His plays have been translated into every major living language and are performed more often than those of
                any other playwright.[7] He remains arguably the most influential writer in the English language, and his works continue
                to be studied and reinterpreted.
                Shakespeare was born and raised in Stratford-upon-Avon, Warwickshire. At the age of 18, he married Anne Hathaway, with
                whom he had three children: Susanna and twins Hamnet and Judith. Sometime between 1585 and 1592, he began a successful
                career in London as an actor, writer, and part-owner of a playing company called the Lord Chamberlain's Men, later known
                as the King's Men. At age 49 (around 1613), he appears to have retired to Stratford, where he died three years later.
                Few records of Shakespeare's private life survive; this has stimulated considerable speculation about such matters as
                his physical appearance, his sexuality, his religious beliefs and whether the works attributed to him were written by
                others.[8][9][10]
                Shakespeare produced most of his known works between 1589 and 1613.[11][12][d] His early plays were primarily comedies
                and histories and are regarded as some of the best works produced in these genres. He then wrote mainly tragedies until
                1608, among them Hamlet, Romeo and Juliet, Othello, King Lear, and Macbeth, all considered to be among the finest works
                in the English language.[2][3][4] In the last phase of his life, he wrote tragicomedies (also known as romances) and
                collaborated with other playwrights.
                Many of Shakespeare's plays were published in editions of varying quality and accuracy in his lifetime. However, in
                1623, two fellow actors and friends of Shakespeare's, John Heminges and Henry Condell, published a more definitive text
                known as the First Folio, a posthumous collected edition of Shakespeare's dramatic works that included all but two of
                his plays.[13] Its Preface was a prescient poem by Ben Jonson that hailed Shakespeare with the now famous epithet:
                "not of an age, but for all time".[13]
                Shakespeare was the son of John Shakespeare, an alderman and a successful glover (glove-maker) originally from
                Snitterfield in Warwickshire, and Mary Arden, the daughter of an affluent landowning family.[14] He was born in
                Stratford-upon-Avon, where he was baptised on 26 April 1564. His date of birth is unknown, but is traditionally observed
                on 23 April, Saint George's Day.[15] This date, which can be traced to William Oldys and George Steevens, has proved
                appealing to biographers because Shakespeare died on the same date in 1616.[16][17] He was the third of eight children,
                and the eldest surviving son.[18]
                Although no attendance records for the period survive, most biographers agree that Shakespeare was probably educated at
                the King's New School in Stratford,[19][20][21] a free school chartered in 1553,[22] about a quarter-mile (400 m) from
                his home. Grammar schools varied in quality during the Elizabethan era, but grammar school curricula were largely
                similar: the basic Latin text was standardised by royal decree,[23][24] and the school would have provided an intensive
                education in grammar based upon Latin classical authors.[25]
                At the age of 18, Shakespeare married 26-year-old Anne Hathaway. The consistory court of the Diocese of Worcester issued
                a marriage licence on 27 November 1582. The next day, two of Hathaway's neighbours posted bonds guaranteeing that no
                lawful claims impeded the marriage.[26] The ceremony may have been arranged in some haste since the Worcester chancellor
                allowed the marriage banns to be read once instead of the usual three times,[27][28] and six months after the marriage
                Anne gave birth to a daughter, Susanna, baptised 26 May 1583.[29] Twins, son Hamnet and daughter Judith, followed almost
                two years later and were baptised 2 February 1585.[30] Hamnet died of unknown causes at the age of 11 and was buried 11
                August 1596.[31]
                After the birth of the twins, Shakespeare left few historical traces until he is mentioned as part of the London theatre
                scene in 1592. The exception is the appearance of his name in the "complaints bill" of a law case before the Queen's
                Bench court at Westminster dated Michaelmas Term 1588 and 9 October 1589.[32] Scholars refer to the years between 1585
                and 1592 as Shakespeare's "lost years".[33] Biographers attempting to account for this period have reported many
                apocryphal stories. Nicholas Rowe, Shakespeare's first biographer, recounted a Stratford legend that Shakespeare fled
                the town for London to escape prosecution for deer poaching in the estate of local squire Thomas Lucy. Shakespeare is
                also supposed to have taken his revenge on Lucy by writing a scurrilous ballad about him.[34][35] Another 18th-century
                story has Shakespeare starting his theatrical career minding the horses of theatre patrons in London.[36] John Aubrey
                reported that Shakespeare had been a country schoolmaster.[37] Some 20th-century scholars suggested that Shakespeare
                may have been employed as a schoolmaster by Alexander Hoghton of Lancashire, a Catholic landowner who named a certain
                "William Shakeshafte" in his will.[38][39] Little evidence substantiates such stories other than hearsay collected after
                his death, and Shakeshafte was a common name in the Lancashire area.[40][41]""";

        for (String b : a.split("\n")) {
            List<String> strings = Arrays.asList(b.split(" "));
            List<String> strings1 = strings.stream().filter(s -> s.length() > 8).toList();
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