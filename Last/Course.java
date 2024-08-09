import java.util.*;

interface Course {
    static Course create(String courseId, String courseName, Teacher instructor, String place, Map<Student, Integer> scores, String type) {
        if ("inperson".equals(type)) {
            return new InPersonCourse(courseId, courseName, instructor, place, scores);
        } else {
            return new OnlineCourse(courseId, courseName, instructor, place, scores);
        }
    }

    String getCourseId();
    String getCourseName();
    Teacher getInstructor();
    Map<Student, Integer> getScores();
    Course setScore(Student student, int score);
    Course clone();
    void displayCourseInfo();
}
