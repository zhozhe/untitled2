package diary.data;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class NoteJFrame extends JFrame implements ActionListener {


    //创建三个按钮
    JButton add = new JButton("添加");
    JButton update = new JButton("修改");
    JButton delete = new JButton("删除");

    //创建表格组件
    JTable table;

    //创建菜单的导入和导出
    JMenuItem exportItem = new JMenuItem("导出");
    JMenuItem importItem = new JMenuItem("导入");

    public NoteJFrame() {
        //初始化界面
        initFrame();
        //初始化菜单
        initJmenuBar();
        //初始化组件
        initView();
        //让界面显示出来
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前那个组件被点击
        Object obj = e.getSource();
        if (obj == add) {
            System.out.println("添加按钮被点击");
            this.setVisible(false);
            new AddJFrame();

        } else if (obj == update) {
            System.out.println("修改按钮被点击");
            //逻辑：
            //1.先判断用户有没有选择表格中的数据
            //2.如果没有选择，弹框提示：未选择。此时提示的弹框用showJDialog方法即可
            //3.如果选择了，跳转添加界面


            //获取用户选择了表格中的哪一行
            //i = 0: 表示用户选择了第一行
            //i = 1: 表示用户选择了第一行
            //i有两个作用：
            //i小于0，表示用户没有选择，此时无法修改
            //i大于等于0：通过这个行数就可以获取二维数组中对应的数据
            // 情况1：如果没有选中某条数据，直接点击修改，则提示：未选中要修改的行，请重试
            // 获取选中的是哪条数据：二维数组中元素的索引
            int i = table.getSelectedRow();

            // 如果i小于0
            if (i < 0) {
                showJDialog("未选中要修改的行~ 请重试！");
                return;
            }

            // 情况2：如果选中了某条数据，但是是空的，则提示：已没有可修改的行，请重试
            Object daily = tableDatas[i][0];
            if (daily == null) {
                showJDialog("该行没有数据可修改~ 请重试！");
                return;
            }

            // 情况3：如果选中了某条数据，是存在的，则关闭当前界面后，跳转到修改日记的界面
            this.setVisible(false);
            // 传递被选中的日记的编号给修改构造方法
            new UpdateJFrame((int) daily);


        } else if (obj == delete) {
            System.out.println("删除按钮被点击");
            //逻辑：
            //1.先判断用户有没有选择表格中的数据
            //2.如果没有选择，弹框提示：未选择。此时提示的弹框用showJDialog方法即可
            //3.如果选择了，弹框提示：是否确定删除。此时提示的弹框用showChooseJDialog方法


            //作用：展示一个带有确定和取消按钮的弹框
            //方法的返回值：0 表示用户点击了确定
            //             1 表示用户点击了取消
            //该弹框用于用户删除时候，询问用户是否确定删除
            // 情况1：如果没有选中某条数据，直接点击删除，则提示：未选中要删除的行，请重试
            int i = table.getSelectedRow();
            // 如果i小于0
            if (i < 0) {
                showJDialog("未选中要删除的行~ 请重试！");
                return;
            }

            // 情况2：如果选中了某条数据，但是是空的，则提示：已没有可删除的行，请重试
            Object daily = tableDatas[i][0];
            if (daily == null) {
                showJDialog("该行没有数据可删除~ 请重试！");
                return;
            }

            // 情况3：如果选中了某条数据，点击删除后，提示：是否要删除选中的数据
            // 获取被选中的日记文档的序号
            int ID = (int) daily;
            // 弹出一个提示框
            int num = showChooseJDialog();
            // 如果选择是，则删除，否则不做任何操作
            if (num == 0) {
                // 删除
                File file = new File("untitled\\src\\diary\\data\\data\\data" + ID + ".txt");
                file.delete();
                // 刷新主界面
                this.setVisible(false);
                new NoteJFrame();
            }

        } else if (obj == exportItem) {
            System.out.println("菜单的导出功能");


        } else if (obj == importItem) {
            System.out.println("菜单的导入功能");


        }
    }

    public static Object[][] tableDatas = null;

    //初始化组件
    private void initView() {
        //定义最上面的每日一记
        JLabel title = new JLabel("每日一记");
        title.setBounds(220, 20, 584, 50);
        title.setFont(new Font("宋体", Font.BOLD, 32));
        this.getContentPane().add(title);

        //定义表格的标题
        Object[] tableTitles = {"编号", "标题", "正文"};
        //定义表格的内容

        tableDatas = new Object[10000][3];

        //定义表格组件
        //并给表格设置标题和内容
        table = new JTable(tableDatas, tableTitles);
        table.setBounds(40, 70, 504, 380);

        //创建可滚动的组件，并把表格组件放在滚动组件中间
        //好处：如果表格中数据过多，可以进行上下滚动
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(40, 70, 504, 380);
        this.getContentPane().add(jScrollPane);

        // 3.读取日记对象
        // 创建日记存储文档的文件夹对象

        File file = new File("untitled\\src\\diary\\data\\data");
        getFileDatas(file);

        //给三个按钮设置宽高属性，并添加点击事件
        add.setBounds(40, 466, 140, 40);
        update.setBounds(222, 466, 140, 40);
        delete.setBounds(404, 466, 140, 40);

        add.setFont(new Font("宋体", Font.PLAIN, 24));
        update.setFont(new Font("宋体", Font.PLAIN, 24));
        delete.setFont(new Font("宋体", Font.PLAIN, 24));

        add.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);


        this.getContentPane().add(add);
        this.getContentPane().add(update);
        this.getContentPane().add(delete);
    }

    /**
     * 获取文件夹中的所有文件数据
     *
     * @param src 数据源文件路径
     */
    private void getFileDatas(File src) {
        // 得到文件夹下所有的日记存储文档的数组
        File[] files = src.listFiles();
        System.out.println(files);

        // 遍历数组，依次得到所有日记文档的路径
        for (int i = 0; i < files.length; i++) {
            // 判断是文件还是文件夹
            if (files[i].isFile()) {
                // 是文件
                try {
                    // 依次与每一个日记文档建立连接管道
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(files[i]));
                    // 依次读取每一个日记文档（返回一个反序列化的日记对象）
                    Daily daily = (Daily) ois.readObject();
                    // 关闭流
                    ois.close();

                    // 依次给二维数组中的每一个一维数组添加元素（添加行数据）
                    tableDatas[i][0] = daily.getID();
                    tableDatas[i][1] = daily.getTitle();
                    tableDatas[i][2] = daily.getContent();
                    System.out.println(daily);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // 是文件夹：递归
                getFileDatas(files[i]);
            }
        }
    }
    //初始化菜单

    private void initJmenuBar() {
        //创建整个的菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上面的两个选项的对象 （功能  关于我们）
        JMenu functionJMenu = new JMenu("功能");

        //把5个存档，添加到saveJMenu中
        functionJMenu.add(exportItem);
        functionJMenu.add(importItem);

        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);

        //绑定点击事件
        exportItem.addActionListener(this);
        importItem.addActionListener(this);

        //给菜单设置颜色
        jMenuBar.setBackground(new Color(230, 230, 230));

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    //初始化界面
    private void initFrame() {
        //设置界面的宽高
        this.setSize(600, 600);
        //设置界面的标题
        this.setTitle("每日一记");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认的居中放置，只有取消了才会按照XY轴的形式添加组件
        this.setLayout(null);
        //设置背景颜色
        this.getContentPane().setBackground(new Color(212, 212, 212));
    }

    //只创建一个弹框对象
    JDialog jDialog = new JDialog();

    //因为展示弹框的代码，会被运行多次
    //所以，我们把展示弹框的代码，抽取到一个方法中。以后用到的时候，就不需要写了
    //直接调用就可以了。
    //展示弹框
    public void showJDialog(String content) {
        if (!jDialog.isVisible()) {
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //给弹框设置大小
            jDialog.setSize(200, 150);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭永远无法操作下面的界面
            jDialog.setModal(true);

            //创建Jlabel对象管理文字并添加到弹框当中
            JLabel warning = new JLabel(content);
            warning.setBounds(0, 0, 200, 150);
            jDialog.getContentPane().add(warning);

            //让弹框展示出来
            jDialog.setVisible(true);
        }
    }

    /*
     *  作用：展示一个带有确定和取消按钮的弹框
     *
     *  方法的返回值：
     *       0 表示用户点击了确定
     *       1 表示用户点击了取消
     *       该弹框用于用户删除时候，询问用户是否确定删除
     * */
    public int showChooseJDialog() {
        return JOptionPane.showConfirmDialog(this, "是否删除选中数据？", "删除信息确认", JOptionPane.YES_NO_OPTION);
    }
}
