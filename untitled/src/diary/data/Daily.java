package diary.data;

import java.io.Serializable;

// 日记对象
public class Daily implements Serializable {
    private static final long serialVersionUID = -3731209612900218877L;

    private int ID; // 编号
    private String title; // 标题
    private String content; // 内容

    public Daily() {
    }

    public Daily(int ID, String title, String content) {
        this.ID = ID;
        this.title = title;
        this.content = content;
    }

    /**
     * 获取
     * @return ID
     */
    public int getID() {
        return ID;
    }

    /**
     * 设置
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * 获取
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return "Daily{ID = " + ID + ", title = " + title + ", content = " + content + "}";
    }
}
