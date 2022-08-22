/**
 * function description.
 *
 * <p><h2>Descriptions</h2>
 * <h3>Project</h3> jmh
 * <h3>Package</h3> PACKAGE_NAME
 * </p>
 * <p><h2>Change History</h2>
 * 2022/8/22 10:22 | guanrongzhi | created
 * </p>
 *
 * @author guanrongzhi
 * @version 1.0.0
 */
public class TestUser {
    private String userId;

    private String username;

    private String password;

    private String tel;

    private String email;

    private String avatar;

    private boolean isAuth;

    private Integer age;

    private String sex;

    public TestUser() {
    }

    public TestUser(String userId, String username, String password, String tel, String email, String avatar, boolean isAuth, Integer age, String sex) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.tel = tel;
        this.email = email;
        this.avatar = avatar;
        this.isAuth = isAuth;
        this.age = age;
        this.sex = sex;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isAuth() {
        return isAuth;
    }

    public void setAuth(boolean auth) {
        isAuth = auth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", isAuth=" + isAuth +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }
}