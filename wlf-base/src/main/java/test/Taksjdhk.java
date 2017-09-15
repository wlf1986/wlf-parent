package test;

/**
 * @Author 王岚枫
 * @Datetime 2017年08月25日 14:45
 */
public class Taksjdhk {
    public static void main(String[] args) {
       String name ="asdfghjkl";
        String[] split = name.split("");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            System.out.println(i+":"+s);
        }
    }
}
