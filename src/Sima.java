import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class Sima {
    public static void main(String[] args) {

        //InsertData();
        GenerateReport();

    }

    public static void InsertData() {
        // Insert data from HIGHSCHOOL.CSV
        String dbUrl = "jdbc:mysql://localhost:3306/sima";
        String dbUsername = "root";
        String dbPassword = "vOID000!";

        String studentsCsvFile = "/Users/yuval04/Desktop/school/java/highschool_sql_assignment/highschool.csv";
        try (FileReader reader = new FileReader(studentsCsvFile);
             Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO students (student_id, first_name, last_name, email, gender, ip_address, height, age, has_car, car_color, grade, grade_avg, id_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                statement.setInt(1, Integer.parseInt(record.get("id")));
                statement.setString(2, record.get("first_name"));
                statement.setString(3, record.get("last_name"));
                statement.setString(4, record.get("email"));
                statement.setString(5, record.get("gender"));
                statement.setString(6, record.get("ip_address"));
                statement.setInt(7, Integer.parseInt(record.get("cm_height")));
                statement.setInt(8, Integer.parseInt(record.get("age")));
                statement.setBoolean(9, Boolean.parseBoolean(record.get("has_car")));
                if (record.get("car_color").isEmpty() && Boolean.parseBoolean(record.get("has_car"))) {
                    statement.setString(10, "unknown");
                } else if (Boolean.parseBoolean(record.get("has_car"))) {
                    statement.setString(10, record.get("car_color"));
                } else {
                    statement.setNull(10, Types.NULL);
                }
                statement.setInt(11, Integer.parseInt(record.get("grade")));
                statement.setDouble(12, Double.parseDouble(record.get("grade_avg")));
                statement.setInt(13, Integer.parseInt(record.get("identification_card")));
                statement.executeUpdate();
            }
            System.out.println("Data from HIGHSCHOOL.CSV inserted successfully!");
        } catch (SQLException e) {
            System.out.println("Error inserting data from HIGHSCHOOL.CSV: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Insert data from HIGHSCHOOL_FRIENDSHIPS.CSV
        String friendshipsCsvFile = "/Users/yuval04/Desktop/school/java/highschool_sql_assignment/highschool_friendships.csv";
        try (FileReader reader = new FileReader(friendshipsCsvFile);
             Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO friends (friendship_id, friend_id, other_friend_id) VALUES (?, ?, ?)")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            for (CSVRecord record : records) {
                // Check if student_id and friend_id columns have a value
                if (record.get("friend_id").length() > 0 && record.get("other_friend_id").length() > 0) {
                    statement.setInt(1, Integer.parseInt(record.get("id")));
                    statement.setInt(2, Integer.parseInt(record.get("friend_id")));
                    statement.setInt(3, Integer.parseInt(record.get("other_friend_id")));
                    statement.executeUpdate();
                } else if (!record.get("friend_id").isEmpty() || !record.get("other_friend_id").isEmpty()) {
                    statement.setInt(1, Integer.parseInt(record.get("id")));
                    if (record.get("friend_id").isEmpty()) {
                        statement.setNull(2, Types.NULL);
                        statement.setInt(3, Integer.parseInt(record.get("other_friend_id")));
                    } else {
                        statement.setInt(2, Integer.parseInt(record.get("friend_id")));
                        statement.setNull(3, Types.NULL);
                    }
                    statement.executeUpdate();
                }
            }
            System.out.println("Data from HIGHSCHOOL_FRIENDSHIPS.CSV inserted successfully!");
        } catch (SQLException e) {
            System.out.println("Error inserting data from HIGHSCHOOL_FRIENDSHIPS.CSV: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void GenerateReport() {

        Scanner input = new Scanner(System.in);
        String dbUrl = "jdbc:mysql://localhost:3306/sima";
        String dbUsername = "root";
        String dbPassword = "vOID000!";
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

            // Print welcome message
            System.out.println("   SSSSSSSSSSSSSSS IIIIIIIIIIMMMMMMMM               MMMMMMMM               AAA               ");
            System.out.println(" SS:::::::::::::::SI::::::::IM:::::::M             M:::::::M              A:::A              ");
            System.out.println("S:::::SSSSSS::::::SI::::::::IM::::::::M           M::::::::M             A:::::A             ");
            System.out.println("S:::::S     SSSSSSSII::::::IIM:::::::::M         M:::::::::M            A:::::::A            ");
            System.out.println("S:::::S              I::::I  M::::::::::M       M::::::::::M           A:::::::::A           ");
            System.out.println("S:::::S              I::::I  M:::::::::::M     M:::::::::::M          A:::::A:::::A          ");
            System.out.println(" S::::SSSS           I::::I  M:::::::M::::M   M::::M:::::::M         A:::::A A:::::A         ");
            System.out.println("  SS::::::SSSSS      I::::I  M::::::M M::::M M::::M M::::::M        A:::::A   A:::::A        ");
            System.out.println("    SSS::::::::SS    I::::I  M::::::M  M::::M::::M  M::::::M       A:::::A     A:::::A       ");
            System.out.println("       SSSSSS::::S   I::::I  M::::::M   M:::::::M   M::::::M      A:::::AAAAAAAAA:::::A      ");
            System.out.println("            S:::::S  I::::I  M::::::M    M:::::M    M::::::M     A:::::::::::::::::::::A     ");
            System.out.println("            S:::::S  I::::I  M::::::M     MMMMM     M::::::M    A:::::AAAAAAAAAAAAA:::::A    ");
            System.out.println("SSSSSSS     S:::::SII::::::IIM::::::M               M::::::M   A:::::A             A:::::A   ");
            System.out.println("S::::::SSSSSS:::::SI::::::::IM::::::M               M::::::M  A:::::A               A:::::A  ");
            System.out.println("S:::::::::::::::SS I::::::::IM::::::M               M::::::M A:::::A                 A:::::A ");
            System.out.println(" SSSSSSSSSSSSSSS   IIIIIIIIIIMMMMMMMM               MMMMMMMMAAAAAAA                   AAAAAAA\n");
            System.out.println("                                    likes to yell at kids\n\n");

            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Average score for the whole school");
                System.out.println("2. Average score for male students");
                System.out.println("3. Average score for female students");
                System.out.println("4. Average score for students taller than 2 meters who have a purple car");
                System.out.println("5. Friends of friends for a specific student");
                System.out.println("6. Percentage of popular, regular, and single students");
                System.out.println("7. Average score for a specific student");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                int choice = input.nextInt();

                switch (choice) {
                    case 1:
                        // Average score for the whole school
                        Statement statement1 = connection.createStatement();
                        ResultSet resultSet1 = statement1.executeQuery("SELECT AVG(grade_avg) FROM students");
                        while (resultSet1.next()) {
                            System.out.println("Average score for the whole school: " + resultSet1.getDouble(1));
                        }
                        break;
                    case 2:
                        // Average score for Zionist students
                        PreparedStatement statement2 = connection.prepareStatement("SELECT AVG(grade_avg) FROM students WHERE gender = 'Male'");
                        ResultSet resultSet2 = statement2.executeQuery();
                        while (resultSet2.next()) {
                            System.out.println("Average score for male students: " + resultSet2.getDouble(1));
                        }
                        break;
                    case 3:
                        // Average score for all students
                        Statement statement3 = connection.createStatement();
                        ResultSet resultSet3 = statement3.executeQuery("SELECT AVG(grade_avg) FROM students WHERE gender = 'Female'");
                        while (resultSet3.next()) {
                            System.out.println("Average score for female students: " + resultSet3.getDouble(1));
                        }
                        break;
                    case 4:
                        // Average score for students taller than 2 meters who have a purple car
                        PreparedStatement statement4 = connection.prepareStatement("SELECT AVG(grade_avg) FROM students WHERE height > 2 AND car_color = 'purple'");
                        ResultSet resultSet4 = statement4.executeQuery();
                        while (resultSet4.next()) {
                            System.out.println("Average score for students taller than 2 meters who have a purple car: " + resultSet4.getDouble(1));
                        }
                        break;
                    case 5:
                        // Friends of friends for a specific student
                        System.out.print("Enter student id: ");
                        int studentId = input.nextInt();
                        PreparedStatement statement5 = connection.prepareStatement("""
                                SELECT other_friend_id FROM friends WHERE friend_id = ? 
                                UNION SELECT friend_id FROM friends WHERE other_friend_id = ? 
                                UNION SELECT other_friend_id FROM friends WHERE friend_id IN (SELECT other_friend_id FROM friends WHERE friend_id = ? UNION SELECT friend_id FROM friends WHERE other_friend_id = ?)
                                UNION SELECT friend_id FROM friends WHERE other_friend_id IN (SELECT other_friend_id FROM friends WHERE friend_id = ? UNION SELECT friend_id FROM friends WHERE other_friend_id = ?)
                            """);
                        statement5.setInt(1, studentId);
                        statement5.setInt(2, studentId);
                        statement5.setInt(3, studentId);
                        statement5.setInt(4, studentId);
                        statement5.setInt(5, studentId);
                        statement5.setInt(6, studentId);
                        ResultSet resultSet5 = statement5.executeQuery();
                        System.out.println("Friends of friends for student " + studentId + ":");
                        while (resultSet5.next()) {
                            System.out.println(resultSet5.getInt(1));
                        }
                        break;
                    case 6:
                        // Percentage of popular, regular, and single students
                        Statement statement6 = connection.createStatement();
                        ResultSet resultSet6 = statement6.executeQuery("""
                                SELECT
                                    (SUM(CASE WHEN f.friend_id IS NOT NULL AND f.other_friend_id IS NOT NULL THEN 1 ELSE 0 END) / COUNT(s.student_id)) * 100 AS popular,
                                    (SUM(CASE WHEN (f.friend_id IS NOT NULL OR f.other_friend_id IS NOT NULL) AND (f.friend_id IS NULL OR f.other_friend_id IS NULL) THEN 1 ELSE 0 END) / COUNT(s.student_id)) * 100 AS regular,
                                    (SUM(CASE WHEN f.friend_id IS NULL AND f.other_friend_id IS NULL THEN 1 ELSE 0 END) / COUNT(s.student_id)) * 100 AS lonely
                                FROM students s
                                LEFT JOIN friends f ON s.student_id = f.friend_id OR s.student_id = f.other_friend_id                   
                                """);
                        while (resultSet6.next()) {
                            System.out.println("Percentage of popular students: " + resultSet6.getDouble("popular"));
                            System.out.println("Percentage of regular students: " + resultSet6.getDouble("regular"));
                            System.out.println("Percentage of lonely students: " + resultSet6.getDouble("lonely"));
                        }
                        break;
                    case 7:
                        // Average score for a specific student
                        System.out.print("Enter student id: ");
                        int studentId2 = input.nextInt();
                        PreparedStatement statement7 = connection.prepareStatement("SELECT avg_score FROM avg_score WHERE student_id = ?");
                        statement7.setInt(1, studentId2);
                        ResultSet resultSet7 = statement7.executeQuery();
                        while (resultSet7.next()) {
                            System.out.println("Average score for student " + studentId2 + ": " + resultSet7.getDouble(1));
                        }
                        break;
                    case 8:
                        // Exit
                        System.out.println("Exiting program...");
                        connection.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                        break;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }
}
