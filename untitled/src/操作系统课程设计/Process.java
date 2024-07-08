package 操作系统课程设计;

import java.util.Arrays;

class Process {
    String name;          //进程名字
    int allocation[];    //已分配的资源数
    int MaxNeed[];       //最大需求数量
    int needs[];         //仍然需要
    boolean finshined = false;  //状态

    @Override
    public String toString() {
        return "Process{" +
                "name='" + name + '\'' +
                ", allocation=" + Arrays.toString(allocation) +
                //", MaxNeed=" + Arrays.toString(MaxNeed) + 可选项，最大需求矩阵不输出
                ", needs=" + Arrays.toString(needs) +
                ", finshined=" + finshined +
                '}';//重写tostring方法，用来输出进程信息
    }
}
