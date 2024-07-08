package 操作系统课程设计;
//银行家算法
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class main2 {
    private static int tesp = 0;
    private static int KINDS = 0;       //资源种类
    private static int[] resource;   //总资源数
    private static int ProcessCount;  //进程数量
    private static List<Process> team;//进程数组
    private static int[] avaliable;  //当前可分配资源
    private static JFrame jFrame = new JFrame();
    public static void InitAllResource() {


        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().setLayout(null);
        //空间
//            JFrame jFrame11 = new JFrame();
        jFrame.setSize(480, 430);
        jFrame.setTitle("提交界面");
        jFrame.setLocationRelativeTo(null);
        jFrame.getContentPane().setLayout(null);

//输入框
        JTextField text11 = new JTextField();
        JTextField text12 = new JTextField();
        JLabel jlabel1 = new JLabel(new ImageIcon("C:\\Users\\21250\\IdeaProjects\\untitled\\src\\操作系统课程设计\\picture\\Snipaste_2024-06-20_13-14-36.png"));


        text11.setBounds(195, 100, 200, 30);
        text12.setBounds(195, 150, 200, 30);
        jlabel1.setBounds(0, 0, 470, 390);//背景

//按钮
        JButton jButton11 = new JButton();
        jButton11.setBounds(133, 250, 90, 40);//登录按钮
        jButton11.setSize(200, 60);
        jButton11.setText("确定提交");
        jFrame.getContentPane().add(jButton11);
        jFrame.getContentPane().add(jlabel1);
        jFrame.getContentPane().add(text11);
        jFrame.getContentPane().add(text12);

        jFrame.getContentPane().repaint();
        jFrame.setAlwaysOnTop(true);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);

        System.out.println("第一行 请输入资源种类数量:");
        System.out.println("第二行 请输入总资源数量(逗号分隔):");

        jButton11.addActionListener(e -> {

            String kinds = text11.getText();
            String res = text12.getText();
            String[] res2 = res.split(",");

            //初始化总资源数和进程数量
            KINDS = Integer.parseInt(kinds);
            resource = new int[KINDS]; //资源总类数

            //各资源数量
            for (int i = 0; i < resource.length; i++) {
                resource[i] = Integer.parseInt(res2[i]);
            }

            menu();
            jFrame.getContentPane().repaint();


        });
    }

    public static void InitProcess() {//初始化进程


        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().setLayout(null);
        //空间
//            JFrame jFrame11 = new JFrame();
        jFrame.setSize(480, 430);
        jFrame.setTitle("提交界面");
        jFrame.setLocationRelativeTo(null);

//输入框
        JTextField text21 = new JTextField();
        JLabel jlabel1 = new JLabel(new ImageIcon("C:\\Users\\21250\\IdeaProjects\\untitled\\src\\操作系统课程设计\\picture\\Snipaste_2024-06-20_13-14-36.png"));

        text21.setBounds(195, 100, 200, 30);
        jlabel1.setBounds(0, 0, 470, 390);//背景

//按钮
        JButton jButton21 = new JButton();
        jButton21.setBounds(133, 250, 90, 40);//登录按钮
        jButton21.setSize(200, 60);
        jButton21.setText("确定提交");
        jFrame.getContentPane().add(jButton21);
        jFrame.getContentPane().add(jlabel1);
        jFrame.getContentPane().add(text21);

        jFrame.getContentPane().repaint();
        jFrame.setAlwaysOnTop(true);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);

        System.out.println("------------------------");
        System.out.println("第一行 请输入进程的个数:");


        jButton21.addActionListener(e -> {

            if (KINDS == 0) {
                System.out.println("请初始化总资源数");
                return;
            }
            System.out.println("输入进程的个数是" + text21.getText());
            ProcessCount = Integer.parseInt(text21.getText());  //初始化进程个数
            team = new ArrayList<>();
            jFrame.getContentPane().repaint();
            initt();

        });


    }

    private static void initt() {


        jFrame.getContentPane().removeAll();

        jFrame.getContentPane().setLayout(null);
        //空间
//            JFrame jFrame11 = new JFrame();
        jFrame.setSize(480, 430);
        jFrame.setTitle("提交界面");
        jFrame.setLocationRelativeTo(null);

//输入框

        JTextField text22 = new JTextField();
        JTextField text23 = new JTextField();
        JTextField text24 = new JTextField();
        JLabel jlabel2 = new JLabel(new ImageIcon("C:\\Users\\21250\\IdeaProjects\\untitled\\src\\操作系统课程设计\\picture\\Snipaste_2024-06-20_13-14-36.png"));


        text22.setBounds(195, 150, 200, 30);
        text23.setBounds(195, 200, 200, 30);
        text24.setBounds(195, 250, 200, 30);
        jlabel2.setBounds(0, 0, 470, 390);//背景

//按钮
        JButton jButton211 = new JButton();
        jButton211.setBounds(133, 300, 90, 40);//登录按钮
        jButton211.setSize(200, 60);
        jButton211.setText("确定提交");
        jFrame.getContentPane().add(jButton211);
        jFrame.getContentPane().add(jlabel2);
        jFrame.getContentPane().add(text22);
        jFrame.getContentPane().add(text23);
        jFrame.getContentPane().add(text24);

        jFrame.setAlwaysOnTop(true);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
        System.out.println("------------------------");
        System.out.println("请输入进程的名字:");
        System.out.println("请输入进程对资源最大需求数量(逗号分隔):");
        System.out.println("请输入进程对资源已经分配数量(逗号分隔):");

        jButton211.addActionListener(e -> {

            String namee = text22.getText();
            String[] maxneed = text23.getText().split(",");
            String[] allocation = text24.getText().split(",");

//            for (int i = 0; i < ProcessCount; i++) {
            Process newProcess = new Process();//每次创建一个新的进程信息，并对其初始化
            System.out.println("输入进程的名字是：" + namee);
            newProcess.name = namee;
            String name = newProcess.name;
            newProcess.MaxNeed = new int[KINDS];
            System.out.println(name + "进程对种资源最大需求数量是" + Arrays.toString(maxneed));
            for (int j = 0; j < KINDS; j++) {
                newProcess.MaxNeed[j] = Integer.parseInt(maxneed[j]);
            }
            newProcess.allocation = new int[KINDS];
            System.out.println(name + "进程对资源已分配数量是" + Arrays.toString(allocation));
            for (int j = 0; j < KINDS; j++) {
                newProcess.allocation[j] = Integer.parseInt(allocation[j]);
            }
            team.add(newProcess);//插入顺序表

//            }
//            jFrame.getContentPane().repaint();


            tesp++;
            jFrame.getContentPane().repaint();

            if (tesp < ProcessCount) {
                initt();
            } else {
                needs();
                setAvaliable();//初始化可分配资源数

                mainInitProcess();

            }


        });


    }

    private static void needs() {

        for (int i = 0; i < team.size(); i++) {
            team.get(i).needs = new int[KINDS];
            for (int j = 0; j < KINDS; j++) {
                team.get(i).needs[j] = team.get(i).MaxNeed[j] - team.get(i).allocation[j];
            }
        }//根据输入的已分配和最大需求，初始化各个进程的仍需

        for (int i = 0; i < team.size(); i++) {
            System.out.println(team.get(i).name + " need需求资源是 " + Arrays.toString(team.get(i).needs));
        }

    }

    public static void setAvaliable() {
        avaliable = new int[KINDS];
        for (int i = 0; i < KINDS; i++) {
            for (int j = 0; j < team.size(); j++) {
                avaliable[i] += team.get(j).allocation[i];
            }
        }
        for (int i = 0; i < avaliable.length; i++) {
            avaliable[i] = resource[i] - avaliable[i];
        }
    }

    public static boolean SafeCheck() {
        if (team == null) {
            System.out.println("请初始化进程资源信息!");
            return false;
        }


        for (int i = 0; i < KINDS; i++) {//初始化乱输出
            if (avaliable[i] < 0) {
                System.out.println("当前状态错误！请重新初始化！");
                System.out.println(Arrays.toString(avaliable));
                team = null;
                return false;
            }
        }


        String[] safeteam = new String[ProcessCount];//存放安全序列
        int safecount = 0;//记录安全序列的序数


        int work[] = new int[KINDS];
        for (int i = 0; i < KINDS; i++) {
            work[i] = avaliable[i];  //把当前的avaliable数组的值放置到work进行试分配
        }

        int index = 0;//循环找进程顺序表的下标
        boolean choose = false;//选择器,看当前状态是否能分配
        int tmp = 0;//计算值是否达到了进程长度，即说明查询一圈。

        while (safecount < team.size() && tmp < team.size()) {
            //当安全序列有效数小于进程数 或者 查询小于一圈
            if (index >= team.size()) {
                index = 0;   //防止越界，循环查找
            }
            if (!team.get(index).finshined) {//判断当前状态 针对false
                for (int i = 0; i < KINDS; i++) {//循环比较可用和进程所需,满足置选择器为true
                    if (team.get(index).needs[i] > work[i]) {
                        choose = false;
                        tmp++;//找到finish=true的经历判断次数
                        index++;//索引
                        break;
                    } else {
                        choose = true;
                    }
                }
                if (choose) {   //找到能分配的，修改work数组，暂时修改状态值
                    for (int j = 0; j < KINDS; j++) {
                        work[j] = work[j] + team.get(index).allocation[j];
                    }
                    team.get(index).finshined = true;
                    safeteam[safecount] = team.get(index).name;
                    safecount++;
                    index++;
                    tmp = 0;//重新计数
                }
            } else {
                tmp++;//当前进程已查找，增加查找次数
                index++;//增加下标值
            }
        }
        for (int i = 0; i < safeteam.length; i++) {
            if (safeteam[i] == null) {//安全队列有一个为空的话，说明不安全，输出阻塞进程信息
                System.out.println("当前状态不安全");
                Printmatrix(work);
                for (int k = 0; k < team.size(); k++) {
                    team.get(k).finshined = false;//把进程状态全部还原
                }
                return false;
            }
        }

        System.out.println("当前状态安全,安全序列为:");
        PrinSafe(safeteam);
        boolean chice = false;
        for (int i = 0; i < KINDS; i++) {
            if (team.get(index).needs[i] != 0) {
                chice = false;
                break;
            } else {
                chice = true;
            }
        }
        if (chice) {
            for (int l = 0; l < KINDS; l++) {
                avaliable[l] = team.get(index).allocation[l];
                team.get(index).allocation[l] = 0;
            }
        }

        for (int k = 0; k < team.size(); k++) {
            team.get(k).finshined = false;//把进程状态全部还原
        }
        return true;
    }

    private static void PrinSafe(String[] Safe) {
        //输出安全序列
        for (int i = 0; i < Safe.length; i++) {
            if (i == 0) {
                System.out.print("[");
            }
            if (i != Safe.length - 1) {
                System.out.print(Safe[i] + "、");
            } else {
                System.out.print(Safe[i] + "]");
            }
        }
        System.out.println();
        int[] work = new int[KINDS];
        for (int i = 0; i < Safe.length; i++) {
            for (int j = 0; j < team.size(); j++) {
                if (Safe[i].equals(team.get(j).name)) {
                    if (i == 0) {//第一个的话就是把avaliable+第一个进程的allocation
                        System.out.println("当前可用资源数:" + Arrays.toString(avaliable));
                        for (int k = 0; k < KINDS; k++) {
                            work[k] = team.get(j).allocation[k] + avaliable[k];
                        }
                        System.out.println(team.get(j));//初始化work的初值
                        System.out.println("当前可用资源数为" + Arrays.toString(work));
                        break;
                    } else {
                        System.out.println(team.get(j));
                        for (int k = 0; k < KINDS; k++) {
                            work[k] = team.get(j).allocation[k] + work[k];
                        }
                        System.out.println("当前可用资源数为" + Arrays.toString(work));
                        break;
                    }
                }
            }
        }
        System.out.println();


    }

    public static void Printmatrix() {

        //无参数的时候，就是显示当前的进程信息;
        if (team == null) {
            System.out.println("请初始化进程资源信息!");
            return;
        }
        System.out.println("资源总数" + Arrays.toString(resource));
        System.out.println("当前可用资源数" + Arrays.toString(avaliable));
        for (int i = 0; i < team.size(); i++) {
            System.out.println(team.get(i));
        }
    }

    public static void Printmatrix(int[] work) {
        //有参数的时候，就是显示当前可用的资源数，和各个进程运行的情况4
        if (team == null) {
            System.out.println("请初始化进程资源信息!");
            return;
        }
        System.out.println("资源总数" + Arrays.toString(work));
        System.out.println("当前可用资源数" + Arrays.toString(avaliable));
        for (int i = 0; i < team.size(); i++) {
            System.out.println(team.get(i));
        }
    }

    public static void Blank() {
        if (team == null) {
            System.out.println("请初始化进程资源信息!");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你要分配进程名字");
        String name = scanner.next();
        for (int i = 0; i < team.size(); i++) {
            if (team.get(i).name.equals(name)) {

                int request[] = new int[KINDS];
                for (int j = 0; j < KINDS; j++) {
                    System.out.println("请输入要分配的第" + j + "种资源数");
                    request[j] = scanner.nextInt();//保存request的值
                }
                for (int j = 0; j < KINDS; j++) {
                    //是否大于可利用数
                    if (team.get(i).needs[j] < request[j]) {
                        System.out.println("错误！请求数量大于进程所需");
                        return;
                    }
                }
                for (int j = 0; j < KINDS; j++) {
                    //是否大于当前可用的资源数
                    if (avaliable[j] < request[j]) {
                        System.out.println("错误！请求数量大于可以分配资源");
                        return;
                    }
                }//前两种都通过
                TryAllcotion(i, request);//i为进程的ID，request为请求资源数
                return;
            }
        }
        System.out.println("请核对后进程名进行检查");
    }

    private static void TryAllcotion(int i, int[] request) {
        for (int j = 0; j < KINDS; j++) {
            //把这个暂时分配给i进程，修改其need和allocation，修改当前状态可用资源数
            team.get(i).allocation[j] += request[j];
            team.get(i).needs[j] -= request[j];
            avaliable[j] -= request[j];
        }
        if (!SafeCheck()) {//安全性检查,不安全的话，还原刚才分配所得
            System.out.println("状态不安全，未分配");
            for (int j = 0; j < KINDS; j++) {
                team.get(i).allocation[j] -= request[j];
                team.get(i).needs[j] += request[j];
                avaliable[j] += request[j];
            }
            return;
        }
        System.out.println("状态安全，已分配");
    }

    public static void menu() {

        jFrame.getContentPane().removeAll();
        //空间
        Scanner sc = new Scanner(System.in);
        jFrame.setSize(480, 430);
        jFrame.setTitle("提交界面");
        jFrame.setLocationRelativeTo(null);
        jFrame.getContentPane().setLayout(null);


//按钮
        JButton jButton1 = new JButton();
        JButton jButton2 = new JButton();


        JMenuBar jMenuBar = new JMenuBar();
        JMenu functionjMenu = new JMenu("功能");
        JMenuItem replayItem = new JMenuItem("重新启动");


        jButton2.setBounds(133, 200, 200, 60);//登录按钮
        jButton1.setBounds(133, 50, 200, 60);//登录按钮
        jButton1.setText("初始化总资源数量");
        jButton2.setText("初始化各个进程的数量");


        jFrame.getContentPane().add(jButton1);
        jFrame.getContentPane().add(jButton2);

        functionjMenu.add(replayItem);
        jMenuBar.add(functionjMenu);


        jFrame.setJMenuBar(jMenuBar);

        jButton1.addActionListener(e -> {
            InitAllResource();
        });
        jButton2.addActionListener(e -> {
            InitProcess();//初始化各个进程

        });

        replayItem.addActionListener(e -> {
            jFrame.getContentPane().repaint();
            tesp = 0;
            KINDS = 0;

            menu();

        });


        jFrame.setAlwaysOnTop(true);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);
        jFrame.getContentPane().repaint();

    }


    public static void mainInitProcess() {//初始化进程


        jFrame.getContentPane().removeAll();
        jFrame.getContentPane().setLayout(null);
        jFrame.setSize(480, 430);
        jFrame.setTitle("提交界面");
        jFrame.setLocationRelativeTo(null);

//按钮
        JButton jButton1 = new JButton();
        jButton1.setBounds(133, 100, 90, 40);//登录按钮
        jButton1.setText("安全性检测");
        JButton jButton2 = new JButton();
        jButton2.setBounds(133, 150, 90, 40);//登录按钮
        jButton2.setText("银行家分配测试");
        JButton jButton3 = new JButton();
        jButton3.setBounds(133, 200, 90, 40);//登录按钮
        jButton3.setText("进程状态");


        JLabel jlabel2 = new JLabel(new ImageIcon("C:\\Users\\21250\\IdeaProjects\\untitled\\src\\操作系统课程设计\\picture\\Snipaste_2024-06-21_00-26-46.png"));
        jlabel2.setBounds(0, 0, 470, 390);//背景

        jFrame.getContentPane().add(jlabel2);
        jFrame.getContentPane().add(jButton1);
        jFrame.getContentPane().add(jButton2);
        jFrame.getContentPane().add(jButton3);

        jFrame.setAlwaysOnTop(true);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setVisible(true);

        jFrame.getContentPane().repaint();


        jButton1.addActionListener(e -> {

            SafeCheck();

        });

        jButton2.addActionListener(e -> {

            Blank();

        });

        jButton3.addActionListener(e -> {

            Printmatrix();

        });

    }

    public static void main(String[] args) {
        menu();
    }
}



