import java.util.*;

abstract class User {

    private String userId;
    private String name;
    private String email;
    private String password;
    private String role;
    private final List<String> courseIds;

    // コンストラクタ: ユーザーの基本情報を初期化
    User(String userId, String name, String email, String password, String role, List<String> courseIds) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.courseIds = new ArrayList<>(courseIds);
    }

    // ユーザーIDを取得するメソッド
    String getUserId() {
        return userId;
    }

    // ユーザー名を取得するメソッド
    String getName() {
        return name;
    }

    // メールアドレスを取得するメソッド
    String getEmail() {
        return email;
    }

    // パスワードを取得するメソッド
    String getPassword() {
        return password;
    }

    // 役割を取得するメソッド
    String getRole() {
        return role;
    }

    // courseIdを取得するメソッド
    List<String> getCourseIds() {
        return new ArrayList<>(courseIds);
    }

    // 名前を設定するメソッド
    Map<String, User> setName(String NewName, Map<String, User> Users) {
        Map<String, User> NewUsers = new HashMap<>(Users);
        String index = this.userId;

        if ("teacher".equals(this.role)) {
            NewUsers.put(index, new Teacher(this.userId, NewName, this.email, this.password, this.courseIds));
        } else {
            NewUsers.put(index, new Student(this.userId, NewName, this.email, this.password, this.courseIds));
        }
        return NewUsers;
    }

    // メールアドレスを設定するメソッド
    Map<String, User> setEmail(String NewEmail, Map<String, User> Users) {
        Map<String, User> NewUsers = new HashMap<>(Users);
        String index = this.userId;
        if ("teacher".equals(this.role)) {
            NewUsers.put(index, new Teacher(this.userId, this.name, NewEmail, this.password, this.courseIds));
        } else {
            NewUsers.put(index, new Student(this.userId, this.name, NewEmail, this.password, this.courseIds));
        }
        return NewUsers;
    }

    // パスワードを設定するメソッド
    Map<String, User> setPassword(String NewPassword, Map<String, User> Users) {
        Map<String, User> NewUsers = new HashMap<>(Users);
        String index = this.userId;
        if ("teacher".equals(this.role)) {
            NewUsers.put(index, new Teacher(this.userId, this.name, this.email, NewPassword, this.courseIds));
        } else {
            NewUsers.put(index, new Student(this.userId, this.name, this.email, NewPassword, this.courseIds));
        }
        return NewUsers;
    }

    //ユーザのプロフィール表示
    void displayProfile() {
        System.out.println("ユーザID: " + this.getUserId());
        System.out.println("名前: " + this.getName());
        System.out.println("メールアドレス: " + this.getEmail());
        System.out.println("パスワード: " + this.getPassword());
    }

    //担当教科の出力
    void displayCourses(Map<String, Course> Courses) {
        for (String courseId : this.getCourseIds()) {
            Course course = Courses.get(courseId);
            if (course != null) {
                course.displayCourseInfo();
            } else {
                System.out.println("コースID: " + courseId + " に対応するコースが存在しません。");
            }
        }
    }
}
