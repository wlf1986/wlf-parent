package test;

/**
 * @Author 王岚枫
 * @Datetime 2017年08月24日 19:31
 */
public class WakjsBean {
    private int  id;
    private String code;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public WakjsBean(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }


    @Override
    public String toString() {
        return "WakjsBean{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
