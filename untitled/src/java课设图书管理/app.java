package java课设图书管理;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

public class app {
    private static Integer numbers = 5;

    public static void main(String[] args) throws Exception {
        begin1();
    }

    public static void begin1() throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("----------------");
            System.out.println("输入序号使用对应功能:");
            System.out.println("1.用户");
            System.out.println("2.图书管理员");
            System.out.println("3.系统管理员");
            System.out.println("4.system.exit(0)");
            System.out.println("请输入你的选择:");
            String num = sc.next();
            switch (num) {
                case "1": {
                    student();
                    break;
                }
                case "2": {
                    manager();
                    break;
                }
                case "3": {
                    manager2();
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

    private static void manager2() throws Exception {
//插入图书时,触发器会设置数量为5
        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("请选择操作类型:");
        System.out.println("1.查询信息");
        System.out.println("2.修改信息");
        System.out.println("3.删除信息");
        System.out.println("4.录入信息");
        String num = sc.next();
        switch (num) {
            case "1": {
                select22();
                break;
            }
            case "2": {
                update2();
                break;
            }
            case "3": {
                delete2();
                break;
            }

            case "4": {
                insert2();
                break;
            }
            default:
                System.out.println("没有这个选项...");
        }

    }

    private static void insert2() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("输入类型 名称-1 2 -");
        String score1 = sc.next();
        String score2 = sc.next();
        String sql2 = "insert into project (类型,名称 ) values (" + "\'" + score1 + "\'" + "," + "\'" + score2 + "\'" + ")";

        Statement stmt = begin("2").createStatement();
        int count = stmt.executeUpdate(sql2);
        System.out.println("插入成功");
        stmt.close();
        begin("2").close();

    }

    private static void delete2() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("输入编号:");
        String id = sc.next();

        System.out.println("正在删除--");
        Statement stmt = begin("2").createStatement();
        String sql1 = "delete  from project " + " where 编号=" + id;
        int count = stmt.executeUpdate(sql1);
        System.out.println("删除成绩成功---");
        stmt.close();
        begin("2").close();

    }

    private static void update2() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("输入编号:");
        String score = sc.next();
        System.out.println("输入修改后名称:");
        String name = sc.next();

        Statement stmt = begin("2").createStatement();
        String sql2 = "update project  set 名称 = " + name + " " + " where 编号= " + score;
        int count = stmt.executeUpdate(sql2);
        System.out.println("修改成功");
        stmt.close();
        begin("2").close();

    }

    private static void select22() throws Exception {
        Statement stmt = begin("2").createStatement();
        String sql = "select * from project ";
        ResultSet r = stmt.executeQuery(sql);
        while (r.next()) {
            String id = r.getString(1);
            String name = r.getString(2);
            String book = r.getString(3);
            System.out.println(id + "\t" + name + '\t' + book + '\t');
        }
        stmt.close();
        begin("2").close();

    }


    private static void manager() throws Exception {

        Scanner sc = new Scanner(System.in);


        System.out.println("-----------图书库------------");
        System.out.println("编号"+"\t"+"名称"+"\t"+"库存数量");
        selectlib();
        System.out.println("---------借阅信息----------");
        Statement stmt = getStatement();
        stmt.close();
        begin("2").close();

        System.out.println("请输入编号:");
        String id = sc.next();
        System.out.println("----------------");
        System.out.println("请选择操作类型:");
        System.out.println("1.查询借阅");
        System.out.println("2.修改借阅");
        System.out.println("3.删除借阅");
        System.out.println("4.录入借阅");
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
    }

    private static Statement getStatement() throws Exception {
        Statement stmt = begin("2").createStatement();
        String sql = "select * from readtable ";
        ResultSet r = stmt.executeQuery(sql);
        while (r.next()) {
            String id = r.getString(1);
            String name = r.getString(2);
            String book = r.getString(3);
            String Borrowing = r.getString(4);
            String Return = r.getString(5);
            String issupertime = r.getString(6);
            String isdamage = r.getString(7);
            String number = r.getString(8);
            System.out.println(id + "\t" + name + '\t' + book + '\t' + Borrowing + '\t' + Return + '\t' + issupertime + '\t' + isdamage + '\t' + number);
        }
        return stmt;
    }

    private static void selectlib() throws Exception {
        Statement stmt = begin("2").createStatement();
        String sql = "select * from project where 类型 =  "+"\'"+"图书"+"\'";
        ResultSet r = stmt.executeQuery(sql);
        while (r.next()) {
            String id = r.getString(1);
            String book = r.getString(3);
            String number = r.getString(4);
            System.out.println(id + "\t" + book + '\t' + number);
        }
        stmt.close();
        begin("2").close();
    }

    public static void insert(String id) throws Exception {
        //发起了一次借阅
        Scanner sc = new Scanner(System.in);
        System.out.println("输入姓名 书籍 借阅数量-1 2 3-");
        String score1 = sc.next();
        String score2 = sc.next();
        Integer num = sc.nextInt();//想要借阅数量
        //select 书籍count个数
        int countt = extractedd(score2);//库存中数量

        //update数量5-count4 若是1可以插一个,若是==0 不可以借书了

        if (countt - num >= 0) {
            //insert进借阅 判断能不能借书 了
//            numbers = 5 - countt - 1;
            String sql2 = "insert into readtable (编号,姓名, 书籍, 借阅时间, 归还时间,图书数量) values (" + id + "," + "\"" + score1 + "\"" + "," + "\"" + score2 + "\"" + "," + "now()" + "," + "DATE_ADD(NOW()" + ", INTERVAL 7 DAY)" + "," + num + ")";
            Statement stmt = begin("2").createStatement();
            int count = stmt.executeUpdate(sql2);
            System.out.println("借书成功");
            stmt.close();
            begin("2").close();

            updatee(countt, score2, num);//系统管理的内容

        } else {
            System.out.println("最多可以借阅" + (countt) + "本");
            System.out.println("借书失败!");
        }

    }

    private static int extractedd(String score2) throws Exception {

        String sql3 = "select 数量 from project where 名称 = " + "\'" + score2 + "\'";
        Statement stmt = begin("2").createStatement();
        ResultSet r = stmt.executeQuery(sql3);

        while (r.next()) {
            String num = r.getString(1);
            return Integer.parseInt(num);
        }
        return 0;


    }

    private static void updatee(int countt, String score2, int num) throws Exception {
        String sql3 = "update project set 数量 = " + (countt - num) + " where 名称 =" + "\'" + score2 + "\'";
        Statement stmt = begin("2").createStatement();
        stmt.executeUpdate(sql3);
        System.out.println("库存更新成功!");
        stmt.close();
        begin("2").close();
    }


    private static void delete(String id) throws Exception {
        String [] score2 = selectt(id);
        System.out.println("正在删除--");
        Statement stmt = begin("2").createStatement();
        String sql1 = "delete from readtable " + " where 编号= " + id;
        int count = stmt.executeUpdate(sql1);
        System.out.println("删除记录成功---");
        stmt.close();
        begin("2").close();
        int countt = Integer.parseInt(score2[1]);//归还数量
        resume(countt, score2[0]);//更新
    }

    private static String[] selectt(String id) throws Exception {
        String []arr=new String[2];
        Scanner sc = new Scanner(System.in);
        Statement stmt = begin("2").createStatement();
        String sql = "select 书籍,图书数量 from readtable where 编号 = " + "\"" + id + "\"";
        ResultSet r = stmt.executeQuery(sql);
        while (r.next()) {
            String book = r.getString(1);
            String countt = r.getString(2);
            arr[0]=book;
            arr[1]=countt;
            return arr;
        }
        return null;
    }

    private static void resume(int countt, String score2) throws Exception {
        String sql3 = "update project set 数量 = 数量 + " + countt + " where 名称 =" + "\'" + score2 + "\'";
        Statement stmt = begin("2").createStatement();
        stmt.executeUpdate(sql3);
        System.out.println("库存更新成功!");
        stmt.close();
        begin("2").close();
    }

    private static void update(String id) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入1 添加超期处理--");
        System.out.println("输入2 添加损坏处理--");
        System.out.println("输入3 去除超期处理--");
        System.out.println("输入4 去除损坏处理--");
        String score = sc.next();
        Statement stmt = begin("2").createStatement();
        if (score.equals("1")) {
            String sql2 = "update readtable  set 是否超期 = '超期' " + " where 编号= " + id;
            int count = stmt.executeUpdate(sql2);
        }
        if (score.equals("2")) {
            String sql2 = "update readtable  set 是否损坏 = '损坏' " + " where 编号= " + id;
            int count = stmt.executeUpdate(sql2);
        }
        if (score.equals("3")) {
            String sql2 = "update readtable  set 是否超期 = null " + " where 编号= " + id;
            int count = stmt.executeUpdate(sql2);
        }
        if (score.equals("4")) {
            String sql2 = "update readtable  set 是否损坏 = null " + " where 编号= " + id;
            int count = stmt.executeUpdate(sql2);
        }
        System.out.println("修改成功");
        stmt.close();
        begin("2").close();
    }

    private static void register() throws Exception {
        Statement stmt = begin("2").createStatement();
        String sql = "select 编号,姓名,书籍,是否超期,是否损坏 from stumanagesystem.readtable where (是否超期 is not null) or (是否损坏 is not null)";
        ResultSet r = stmt.executeQuery(sql);
        while (r.next()) {
            String num = r.getString(1);
            String name = r.getString(2);
            String book = r.getString(3);
            String yesno1 = r.getString(4);
            String yesno2 = r.getString(5);
            System.out.println(num + "\t" + name + '\t' + book + '\t' + yesno1 + '\t' + yesno2);
        }
        stmt.close();
        begin("2").close();
    }

    private static void student() throws Exception {

        Scanner sc = new Scanner(System.in);
        System.out.println("----------------");
        System.out.println("请选择操作类型:");
        System.out.println("1.显示借阅信息");
        System.out.println("2.查询超期或损坏信息");
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
                System.out.println("没有这个借阅人...");
        }
    }

    private static void select() throws Exception {
        Scanner sc = new Scanner(System.in);
        Statement stmt = begin("2").createStatement();
        System.out.println("输入你的姓名:");
        String id = sc.next();
        String sql = "select * from stumanagesystem.readtable where 姓名 = " + "\"" + id + "\"";
        ResultSet r = stmt.executeQuery(sql);
        while (r.next()) {
            String num = r.getString(1);
            String name = r.getString(2);
            String book = r.getString(3);
            String Borrowing = r.getString(4);
            String Return = r.getString(5);
            String issupertime = r.getString(6);
            String isdamage = r.getString(7);
            System.out.println(num + "\t" + name + '\t' + book + '\t' + Borrowing + '\t' + Return + '\t' + issupertime + '\t' + isdamage + '\t');
        }
        stmt.close();
        begin("2").close();
    }

    private static void select2(String id) throws Exception {
        Scanner sc = new Scanner(System.in);
        Statement stmt = begin("2").createStatement();
        String sql = "select * from readtable where 编号 = " + "\"" + id + "\"";
        ResultSet r = stmt.executeQuery(sql);
        while (r.next()) {
            String name = r.getString(2);
            String book = r.getString(3);
            String Borrowing = r.getString(4);
            String Return = r.getString(5);
            String issupertime = r.getString(6);
            String isdamage = r.getString(7);
            String number = r.getString(8);
            System.out.println(id + "\t" + name + '\t' + book + '\t' + Borrowing + '\t' + Return + '\t' + issupertime + '\t' + isdamage + '\t' + number);
        }
        stmt.close();
        begin("2").close();
    }


    public static Connection begin(String num) throws Exception {

        String url = null;
        if (num.equals("1")) {
            url = "jdbc:mysql://192.168.56.128:3306/stumanagesystem?useSSL=false";
        }
        if (num.equals("2")) {
            url = "jdbc:mysql://192.168.56.128:3306/stumanagesystem?useSSL=false";
        }
        String name = "root";
        String passwd = "fff";
        Connection conn = DriverManager.getConnection(url, name, passwd);
        return conn;
    }

}













