import java.util.*;

class Teacher extends User {

    Teacher(String userId, String name, String email, String password, List<String> courseIds) {
        super(userId, name, email, password, "teacher", courseIds);
    }

    //担当するコースを追加するメソッド
    Teacher addCourse(String courseId){
        List<String> newCourseIds = new ArrayList<>(this.getCourseIds());
        newCourseIds.add(courseId);
        return new Teacher(this.getUserId(), this.getName(), this.getEmail(), this.getPassword(), newCourseIds);
    }

    // 授業を作成するメソッド
    Pair<Map<String, Course>, Map<String, User>> createCourse(String courseId, String courseName, Teacher instructor, String place, String type, Map<String, Course> Courses, Map<String, User> Users) {
        Course NewCourse = Course.create(courseId, courseName, instructor, place, new HashMap<>(), type);
        Teacher newTeacher = addCourse(NewCourse.getCourseId());
        Map<String, Course> NewCourses = new HashMap<>(Courses);
        Map<String, User> NewUsers = new HashMap<>(Users);
        NewCourses.put(NewCourse.getCourseId(), NewCourse);
        NewUsers.put(newTeacher.getUserId(), newTeacher);
        return new Pair<>(NewCourses, NewUsers);
    }

    // 生徒の成績を入力するメソッド
    Map<String, Course> changeScore(Course course, Student student, int NewScore, Map<String, Course> Courses) {
        Course NewCourse = course.clone();
        NewCourse = NewCourse.setScore(student, NewScore);
        Map<String, Course> NewCourses = new HashMap<>(Courses);
        NewCourses.put(NewCourse.getCourseId(), NewCourse);
        return NewCourses;
    }

    // 成績を出力するメソッド
    void displayScores(Course target){
        for(Map.Entry<Student, Integer> entry: target.getScores().entrySet()){
            System.out.println("ユーザID: " + entry.getKey().getUserId());
            System.out.println("氏名: " + entry.getKey().getName());
            System.out.println("成績: " + entry.getValue());
        }
    }

    // 生徒の情報をシステムに登録する
    Map<String, User> enrollStudent(String userId, String name, String email, String password, Map<String, User> Users) {
        Map<String, User> NewUsers = new HashMap<>(Users);
        User NewStudent = new Student(userId, name, email, password, new ArrayList<>());
        NewUsers.put(NewStudent.getUserId(), NewStudent);
        return NewUsers;
    }

    // 教師の情報をシステムに登録する
    Map<String, User> enrollTeacher(String userId, String name, String email, String password, Map<String, User> Users) {
        Map<String, User> NewUsers = new HashMap<>(Users);
        User NewTeacher = new Teacher(userId, name, email, password, new ArrayList<>());
        NewTeacher.displayProfile();
        NewUsers.put(NewTeacher.getUserId(), NewTeacher);
        return NewUsers;
    }
}
