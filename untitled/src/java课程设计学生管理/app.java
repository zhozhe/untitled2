package java课程设计学生管理;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class app {
    public static void main(String[] args) throws Exception {
        begin1();
    }

    public static void begin1() throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("----------------");
            System.out.println("输入序号使用对应功能:1.学生");
            System.out.println("2.老师");
            System.out.println("3.管理员");
            System.out.println("4.system.exit(0)");
            System.out.println("请输入你的选择:");
            String num = sc.next();
            switch (num) {
                case "1": {
                    legin();
                    break;
                }
                case "2": {
                    teacher();
                    break;
                }
                case "3": {
                    manager();
                    break;
                }
                case "4": {
                    System.exit(0);
                }
                default:
                    System.out.println("没有这个选项...");
            }
        }
    }

    private static void manager() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("请选择操作类型:");
        System.out.println("1.统计平均绩点");
        System.out.println("2.按平均绩点排序学生");
        String num = sc.next();
        switch (num) {
            case "1": {
                avg();
                break;
            }
            case "2": {
                sortt();
                break;
            }
            default:
                System.out.println("没有这个选项...");
        }
    }

    private static void sortt() throws Exception {

        Scanner sc = new Scanner(System.in);
        Statement stmt = begin("2").createStatement();
        String sql = "SELECT 学号,姓名,(COALESCE(成绩1, 0) + COALESCE(成绩2, 0) + COALESCE(成绩3, 0))/3 AS 平均成绩 FROM    student " + "order by 平均成绩 desc;";
        ResultSet r = stmt.executeQuery(sql);
        Integer count = 0;
        System.out.println("排序后为:");
        while (r.next()) {
            count++;
            // 正确获取各列的值，注意索引是从1开始的
            System.out.println("排名:" + count);
            String studentId = r.getString(1); // 学号
            String name = r.getString(2); // 姓名
            double score1 = r.getDouble(3);
            // 打印完整的记录信息
            System.out.printf("学号: %s, 姓名: %s", studentId, name);
            System.out.printf("平均成绩是: %.2f%n", score1);
        }
    }

    private static void avg() throws Exception {

        Statement stmt = begin("2").createStatement();
        String sql = "SELECT 学号,姓名,(COALESCE(成绩1, 0) + COALESCE(成绩2, 0) + COALESCE(成绩3, 0))/3 AS 平均成绩 FROM    student ;";
        ResultSet r = stmt.executeQuery(sql);

        while (r.next()) {
            // 正确获取各列的值，注意索引是从1开始的
            String studentId = r.getString(1); // 学号
            String name = r.getString(2); // 姓名
            double averageScore = r.getDouble(3); // 平均成绩

            // 打印完整的记录信息
            System.out.printf("学号: %s, 姓名: %s, 平均成绩: %.2f%n", studentId, name, averageScore);
        }

    }

    private static void teacher() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生学号:");
        String id = sc.next();
        System.out.println("----------------");
        System.out.println("请选择操作类型:");
        System.out.println("1.查询成绩");
        System.out.println("2.修改成绩");
        System.out.println("3.删除成绩");
        System.out.println("4.录入成绩");
        String num = sc.next();
        switch (num) {
            case "1": {
                select2(id);
                break;
            }
            case "2": {
                update(id);
                break;
            }
            case "3": {
                delete(id);
                break;
            }
            case "4": {
                insert(id);
                break;
            }
            default:
                System.out.println("没有这个选项...");
        }


//         stmt.close();
//        studentsdatabases.begin("2").close();
    }

    private static void insert(String id) throws Exception{

        Scanner sc = new Scanner(System.in);
        System.out.println("输入成绩数据-1 2 3-");
        Integer score1= sc.nextInt();
        Integer score2= sc.nextInt();
        Integer score3= sc.nextInt();
        String sql2 = "update  student set 成绩1="+score1+" ,成绩2= "+score2 + " ,成绩3= "+score3+" where 学号=" +"\'" +id+"\'";
        Statement stmt = begin("2").createStatement();
        int count = stmt.executeUpdate(sql2);
        System.out.println("插入成功");

    }

    private static void delete(String id) throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("删除成绩xxx:");
        int num = sc.nextInt();
        System.out.println("正在删除--");
        Statement stmt = begin("2").createStatement();

            String sql3 = "update  student set 成绩"+num+" = null" + " where 学号 = " +"\'" +id +"\'";
            int count = stmt.executeUpdate(sql3);

        System.out.println("删除成绩成功---");
    }

    private static void update(String id) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入需要修改成绩xx");
        String cishu = sc.next();
        System.out.println("输入数据--");
        String score = sc.next();
        String sql2 = "update  student set 成绩" + cishu + "=" + "\"" + score + "\"" + "where 学号=" + id;
        Statement stmt = begin("2").createStatement();
        int count = stmt.executeUpdate(sql2);
        System.out.println("修改成功");
    }

    private static void register() throws Exception {
        Statement stmt = begin("2").createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.print("输入学生姓名:");
        String name = sc.next();
        System.out.println("请输入学生学号:");
        String id = sc.next();
        String sql = "insert into stumanagesystem.student (学号,姓名) values (" + "\"" + id + "\"" + "," + "\"" + name + "\"" + ")";
        stmt.executeUpdate(sql);
        stmt.close();
        begin("2").close();
        System.out.println("!添加学生成功!");
    }

    private static void legin() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("请选择操作类型:");
        System.out.println("1.查询学生信息");
        System.out.println("2.插入学生信息");
        String num = sc.next();
        switch (num) {
            case "1": {
                select();
                break;
            }
            case "2": {
                register();
                break;
            }
            default:
                System.out.println("没有这个选项...");
        }
    }

    private static void select() throws Exception {
        Scanner sc = new Scanner(System.in);
        Statement stmt = begin("2").createStatement();
        System.out.println("输入学号:");
        String id = sc.next();
        String sql = "select * from stumanagesystem.student where 学号 = " + "\"" + id + "\"";
        ResultSet r = stmt.executeQuery(sql);
        while (r.next()) {
            String name = r.getString(2);
            System.out.println(name + "\t" + id + '\t');
        }
    }

    private static void select2(String id) throws Exception {
        Scanner sc = new Scanner(System.in);
        Statement stmt = begin("2").createStatement();
        String sql = "select * from stumanagesystem.student where 学号 = " + "\"" + id + "\"";
        ResultSet r = stmt.executeQuery(sql);

        while (r.next()) {
            String name = r.getString(2);
            String score1 = r.getString(3);
            String score2 = r.getString(4);
            String score3 = r.getString(5);
            System.out.println(name + "\t" + id + '\t' + score1 + '\t' + score2 + '\t' + score3 + '\t');
        }

    }

    public static Connection begin(String num) throws Exception {

        String url=null;
        if (num.equals("1")) {
            url = "jdbc:mysql://192.168.56.128:3306/stumanagesystem?useSSL=false";
        }
        if(num.equals("2")) {
            url = "jdbc:mysql://192.168.56.128:3306/stumanagesystem?useSSL=false";
        }
        String name = "root";
        String passwd = "fff";
        Connection conn = DriverManager.getConnection(url, name, passwd);
        return conn;
    }

}













