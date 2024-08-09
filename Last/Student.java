import java.util.*;

class Student extends User {

    Student(String userId, String name, String email, String password, List<String> courseIds) {
        super(userId, name, email, password, "student", courseIds);
    }

    // 履修登録するメソッド
    Pair<Map<String, User>, Map<String, Course>> enrollCourse(Map<String, User> Users, Map<String, Course> Courses, Course course) {
        Map<String, Course> NewCourses = new HashMap<>(Courses);
        Map<String, User> NewUsers = new HashMap<>(Users);
        Course NewCourse = course.clone();
        List<String> NewEnrollCourseIds = new ArrayList<>(super.getCourseIds());
        NewEnrollCourseIds.add(NewCourse.getCourseId());
        Student NewStudent = new Student(this.getUserId(), this.getName(), this.getEmail(), this.getPassword(), NewEnrollCourseIds);
        NewCourse = NewCourse.setScore(NewStudent, 0);
        NewCourses.put(NewCourse.getCourseId(), NewCourse);
        NewUsers.put(NewStudent.getUserId(), NewStudent);

        return new Pair<>(NewUsers, NewCourses);
    }

    // 成績を表示するメソッド
    void displayScores(Map<String, Course> Courses) {
        if (this.getCourseIds().isEmpty()) {  
            System.out.println("履修科目なし");
            return;
        }
        for (String courseId : this.getCourseIds()) {  
            Course course = Courses.get(courseId);
            if (course != null) {
                Map<Student, Integer> score = course.getScores();
                
                // 成績を表示
                System.out.println("コースID: " + course.getCourseId());
                System.out.println("コース名: " + course.getCourseName());
                
                if (score != null && score.containsKey(this)) {
                    System.out.println("成績: " + score.get(this));
                } else {
                    System.out.println("成績が登録されていません。");
                }
            } else {
                System.out.println("コースID: " + courseId + " のコースは存在しません。");
            }
            System.out.println("");
        }
    }
}