package test;

/**
 * @Author 王岚枫
 * @Datetime 2017年08月26日 9:13
 */
public class _35n_xiangtong {

    /**
     * 10.ind the smallest positive integer that is a multiple of 35 whose digits are all the same.
     * @param args
     */

    public static void main(String[] args) {
        for (Long i = 36L; i < Long.MAX_VALUE; i++) {
            String[] split = (i + "").split("");
            Boolean flag = true;
            for (int j = 1; j < split.length; j++) {
                if (!split[j - 1].equals(split[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (i % 35L == 0) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}
