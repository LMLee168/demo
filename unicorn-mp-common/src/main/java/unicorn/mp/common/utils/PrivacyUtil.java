package unicorn.mp.common.utils;

public class PrivacyUtil {
    public PrivacyUtil() {
    }

    public static String hidePhone(String phone) {
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static String hideEmail(String email) {
        return email.replaceAll("(\\w?)(\\w+)(\\w)(@\\w+\\.[a-z]+(\\.[a-z]+)?)", "$1****$3$4");
    }

    public static String hideIDCard(String idCard) {
        return idCard.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1*****$2");
    }

    public static String hideChineseName(String chineseName) {
        return chineseName == null ? null : desValue(chineseName, 1, 0, "*");
    }

    public static String desValue(String origin, int prefixNoMaskLen, int suffixNoMaskLen, String maskStr) {
        if (origin == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            int i = 0;

            for(int n = origin.length(); i < n; ++i) {
                if (i < prefixNoMaskLen) {
                    sb.append(origin.charAt(i));
                } else if (i > n - suffixNoMaskLen - 1) {
                    sb.append(origin.charAt(i));
                } else {
                    sb.append(maskStr);
                }
            }

            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(hideChineseName("张三三"));
    }
}
