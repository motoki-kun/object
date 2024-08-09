import java.util.*;

class DisplayAndMemory {
    Scanner scan = new Scanner(System.in);
    final Teacher owner = new Teacher("000001", "Shimakrua Motoki", "abc@gmail.com", "ABC", new ArrayList<>());
    private Map<String, User> Users = new HashMap<>();
    private Map<String, Course> Courses = new HashMap<>();
    private static User Login = null;

    // コンストラクタで owner を Users に追加する
    DisplayAndMemory() {
        Users.put(owner.getUserId(), owner);
    }

    // userIDからuserインスタンスを返すメソッド
    private User getUserByID(String userID) {
        return Users.get(userID);
    }

    // courseIDからcourseインスタンスを返すメソッド
    private Course getCourseByID(String courseID) {
        return Courses.get(courseID);
    }

    // 生徒だったらTrueを返すメソッド
    private boolean isStudent(User user) {
        return user instanceof Student;
    }

    // 教師だったらTrueを返すメソッド
    private boolean isTeacher(User user) {
        return user instanceof Teacher;
    }

    // パスワードが正しいか確認する関数
    private boolean isPasswordCorrect(User user) {
        System.out.println("---パスワードを入力してください---");
        String inputPassword = scan.next();
        return user.getPassword().equals(inputPassword);
    }

    // ユーザID,メールアドレスが正しいか確認する関数
    private boolean isUserIDAndEmailCorrect(User user) {
        System.out.println("---ユーザIDを入力してください---");
        String inputUserID = scan.next();
        System.out.println("---メールアドレスを入力してください---");
        String inputEmail = scan.next();
        return user.getUserId().equals(inputUserID) && user.getEmail().equals(inputEmail);
    }

    // 指定された生徒が指定されたコースを履修しているかを返すメソッド
    private boolean isCompletion(Course course, Student student) {
        return course.getScores().containsKey(student);
    }

    //Coursesにコースが存在するか確認
    private boolean hasCourse(Course course) {
        return Courses.containsKey(course.getCourseId());
    }

    // 教師と生徒の共通のプロフィールメニュー処理をまとめるメソッド
    private User handleProfileMenu(User user) {
        if (user instanceof Teacher || user instanceof Student) {
            int select2;
            System.out.println("1, プロフィール表示");
            System.out.println("2, 名前変更");
            System.out.println("3, メールアドレス変更");
            System.out.println("4, パスワード変更");
            System.out.println("5, 戻る"); 
            select2 = scan.nextInt();
            while (select2 < 1 || 5 < select2) { 
                System.out.println("---1~5の値を入力してください。---");
                select2 = scan.nextInt();
            }
    
            switch (select2) {
                case 1:
                    for (int i = 0; i < 3; i++) {
                        System.out.println("プロフィール表示");
                        if (isPasswordCorrect(user)) {
                            user.displayProfile();
                            return user;
                        } else {
                            System.out.println("パスワードが違います");
                            if (i == 2) {
                                System.out.println("最初から処理をやり直してください");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("名前変更");
                    for (int i = 0; i < 3; i++) {
                        if (isPasswordCorrect(user)) {
                            System.out.println("新しい名前を入力してください");
                            String NewName = scan.next();
                            Users = user.setName(NewName, Users);
                            user = (User) Users.get(user.getUserId());
                            user.displayProfile();
                            return user;
                        } else {
                            System.out.println("パスワードが違います");
                            if (i == 2) {
                                System.out.println("最初から処理をやり直してください");
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("メールアドレス変更");
                    for (int i = 0; i < 3; i++) {
                        if (isPasswordCorrect(user)) {
                            System.out.println("新しいメールアドレスを入力してください");
                            String newEmail = scan.next();
                            Users = user.setEmail(newEmail, Users);
                            user = (User) Users.get(user.getUserId());
                            user.displayProfile();
                            return user;
                        } else {
                            System.out.println("パスワードが違います");
                            if (i == 2) {
                                System.out.println("最初から処理をやり直してください");
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("パスワード変更");
                    for (int i = 0; i < 3; i++) {
                        if (isUserIDAndEmailCorrect(user)) {
                            System.out.println("新しいパスワードを入力してください");
                            String NewPassword = scan.next();
                            Users = user.setPassword(NewPassword, Users);
                            user = (User) Users.get(user.getUserId());
                            user.displayProfile();
                            return user;
                        } else {
                            System.out.println("ユーザIDかメールアドレスが違います");
                            if (i == 2) {
                                System.out.println("最初から処理をやり直してください");
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("戻ります");
            }
        }
        return user; 
    }
    // 教師がシステムを使う際の表示を管理しているメソッド
    private Teacher Teacher(Teacher user) {
        int select1;
        System.out.println("1, プロフィール");
        System.out.println("2, 授業");
        System.out.println("3, 登録");
        System.out.println("4, ログアウト");
        System.out.println("---1~4の値を入力してください。---");
        select1 = scan.nextInt();
        while (select1 < 1 || 4 < select1) {
            System.out.println("---1~4の値を入力してください。---");
            select1 = scan.nextInt();
        }

        int select2;
        if (select1 == 1) {
            user = (Teacher)handleProfileMenu(user);
        } else if (select1 == 2) {
            System.out.println("1, 成績出力");
            System.out.println("2, 成績入力");
            System.out.println("3, 担当教科");
            System.out.println("---1~3どちらかの値を入力してください。---");
            select2 = scan.nextInt();
            while (select2 < 1 || 3 < select2) {
                System.out.println("---1~3どちらかの値を入力してください。---");
                select2 = scan.nextInt();
            }

            if (select2 == 1) {
                System.out.println("成績出力");
                System.out.println("---コースIDを入力してください---");
                String CourseID = scan.next();
                Course course = getCourseByID(CourseID);
                if (course != null) {
                    if (course.getScores().isEmpty()) {
                        System.out.println("履修者なし");
                    } else {
                        user.displayScores(course);
                    }
                } else {
                    System.out.println("コースIDが" + CourseID + "のコースは存在しません");
                }
            } else if (select2 == 2) {
                for (int i = 0; i < 3; i++) {
                    System.out.println("成績入力");
                    System.out.println("---コースIDを入力してください---");
                    String CourseID = scan.next();
                    Course course = getCourseByID(CourseID);

                    if (course == null) {
                        System.out.println("コースIDが" + CourseID + "のコースは存在しません");
                        continue; 
                    }

                    System.out.println("生徒のUserIDを入力してください");
                    String UserID = scan.next();
                    User student = getUserByID(UserID);

                    if (student == null) {
                        System.out.println("ユーザIDが" + UserID + "の生徒は存在しません");
                        continue; 
                    }

                    if (isTeacher(student)) {
                        System.out.println("ユーザIDが" + UserID + "のユーザーは教師です。生徒のIDを入力してください。");
                        continue; 
                    }

                    if (isCompletion(course, (Student) student)) {
                        System.out.println("成績を整数で入力してください");
                        int score = scan.nextInt();
                        Courses = user.changeScore(course, (Student) student, score, Courses);
                        user.displayScores(Courses.get(course.getCourseId()));
                        break;
                    } else {
                        System.out.println(student.getName() + "は" + course.getCourseName() + "を履修していません");
                    }
                }
            } else {
                System.out.println("担当教科");
                if (user.getCourseIds().isEmpty()) {
                    System.out.println("担当なし");
                } else {
                    user.displayCourses(Courses);
                }
            }
        } else if (select1 == 3) {
            System.out.println("1, 生徒情報登録");
            System.out.println("2, 教員情報登録");
            System.out.println("3, 授業情報登録");
            select2 = scan.nextInt();
            while (select2 < 1 || 3 < select2) {
                System.out.println("1~3の値を入力してください。");
                select2 = scan.nextInt();
            }

            if (select2 == 1) {
                System.out.println("生徒情報登録");
                System.out.println("教師のユーザIDを入力してください");
                String userId = scan.next();
                System.out.println("生徒の氏名を入力してください");
                String name = scan.next();
                System.out.println("生徒のメールアドレスを入力してください");
                String mailAdr = scan.next();
                System.out.println("生徒のパスワードを入力してください");
                String Password = scan.next();
                Users = user.enrollStudent(userId, name, mailAdr, Password, Users);
                Users.get(userId).displayProfile();
            } else if (select2 == 2){
                System.out.println("教員情報登録");
                System.out.println("教師のユーザIDを入力してください");
                String userId = scan.next();
                System.out.println("教師の氏名を入力してください");
                String name = scan.next();
                System.out.println("教師のメールアドレスを入力してください");
                String mailAdr = scan.next();
                System.out.println("教師のパスワードを入力してください");
                String Password = scan.next();
                Users = user.enrollTeacher(userId, name, mailAdr, Password, Users);
                Users.get(userId).displayProfile();
            } else {
                System.out.println("授業情報登録");
                System.out.println("授業名を入力してください");
                String className = scan.next();
                System.out.println("コースIDを入力してください");
                String courseID = scan.next();
                System.out.println("対面授業かオンライン授業を入力してください");
                System.out.println("1. 対面授業");
                System.out.println("2. オンライン授業");
                System.out.println("---1か2の値を入力してください。---");
                int plat = scan.nextInt();
                while (plat < 1 || 2 < plat) {
                    System.out.println("---1か2の値を入力してください。---");
                    plat = scan.nextInt();
                }
                String type;
                String place;
                if(plat == 1){
                    type = "inperson";
                    System.out.println("教室を入力してください");
                    place = scan.next();
                }else{
                    type = "online";
                    System.out.println("プラットフォームを入力してください");
                    place = scan.next();
                }
                Pair<Map<String, Course>, Map<String, User>> result = user.createCourse(courseID, className, user, place, type, Courses, Users);
                Courses = result.getKey();
                Users = result.getValue();
                user = (Teacher)Users.get(user.getUserId());
                user.displayCourses(Courses);
            }
        } else {
            System.out.println("ログアウトしました");
            user = null;
        }

        return user;
    }

    // 生徒がシステムを使う際の表示を管理しているメソッド
    private Student Student(Student user) {
        int select1;
        System.out.println("1, プロフィール");
        System.out.println("2, 授業");
        System.out.println("3, 履修登録");
        System.out.println("4, ログアウト");
        System.out.println("---1~4の値を入力してください。---");
        select1 = scan.nextInt();
        while (select1 < 1 || 4 < select1) {
            System.out.println("---1~4の値を入力してください。---");
            select1 = scan.nextInt();
        }

        int select2;
        if (select1 == 1) {
            user = (Student) handleProfileMenu(user);
        } else if (select1 == 2) {
            System.out.println("1, 成績出力");
            System.out.println("2, 履修科目");
            System.out.println("---1,2どちらかの値を入力してください。---");
            select2 = scan.nextInt();
            while (select2 < 1 || 2 < select2) {
                System.out.println("---1,2どちらかの値を入力してください。---");
                select2 = scan.nextInt();
            }
            if (select2 == 1) {
                System.out.println("成績出力");
                user.displayScores(Courses);
            } else {
                System.out.println("履修科目");
                user.displayCourses(Courses);
            }
        } else if (select1 == 3) {
            System.out.println("3, 履修登録");
            System.out.println("---コースIDを入力してください---");
            String courseID = scan.next();
            Course course = getCourseByID(courseID);

            if(hasCourse(course)){
                Pair<Map<String, User>, Map<String, Course>> result = user.enrollCourse(Users, Courses, course);
                Users = result.getKey();
                Courses = result.getValue();
                user = (Student)Users.get(user.getUserId());
                user.displayCourses(Courses);
            }else{
                System.out.println("その科目は存在しません");
                System.out.println("Coursesに含まれる全ての科目:");
                for (Map.Entry<String, Course> entry : Courses.entrySet()) {
                    System.out.println("コースID: " + entry.getKey() + ", コース名: " + entry.getValue().getCourseName());
                }
            }
            
        } else {
            System.out.println("ログアウトしました");
            user = null;
        }

        return user;
    }

    public static void main(String[] args) {
        DisplayAndMemory dam = new DisplayAndMemory();
        while (true) {
            if (Login == null) {
                System.out.println("ログインしてください");
                System.out.println("ユーザIDを入力してください");
                String userID = dam.scan.next();
                Login = dam.getUserByID(userID);
            } else {
                if (dam.isTeacher(Login)) {
                    Login = dam.Teacher((Teacher) Login);
                } else {
                    Login = dam.Student((Student) Login);
                }
            }
        }
    }
}
