package util;

public class ChessBroadUtil {

    private ChessBroadUtil(){}

    public static final int DEFAULT_SIZE = Integer.parseInt(PropertiesUtil.getProp("broad.size"));

    public static final int ROLE = 0;

    public static final int ROAD = 1;

    public static final int STONE = 2;

    public static final int ITEM = 3;

    public static final int ENEMY = 4;

    public static final int WEAPON = 5;

    public static final int NEXT = 6;

    public static final int INVALID = -1;

    public static final String IMAGE_PATH_PREFIX = PropertiesUtil.getProp("broad.image.path.prefix");

    private static String[][] imageURLs = {
            {PropertiesUtil.getProp("broad.image.path.role")},
            {PropertiesUtil.getProp("broad.image.path.road")},
            {PropertiesUtil.getProp("broad.image.path.stone")},
            PropertiesUtil.getProp("broad.image.path.item").split(";"),
            PropertiesUtil.getProp("broad.image.path.enemy").split(";"),
            PropertiesUtil.getProp("broad.image.path.weapon").split(";"),
            {PropertiesUtil.getProp("broad.image.path.next")}
    };

    public static final String[] ROLE_DESCRIPTION = {
            "牧师\n普通攻击造成20%额外伤害\n受到伤害减少20%\n初始血量上限增加10%",
            "法师\n普通攻击造成20%额外伤害\n初始技能强度增加30%",
            "盗贼\n普通攻击造成40%额外伤害\n受到伤害减少10%\n初始技能强度增加10%",
            "战士\n受到伤害减少40%\n初始血量上限增加30%"
    };

    public static final String[] CLASS_IMAGES = PropertiesUtil.getProp("broad.image.path.class").split(";");

    public static String getImageURL(int imageClass, int subClass) {
        if(subClass == -1){
            return IMAGE_PATH_PREFIX + "/" + imageURLs[imageClass][0];
        }
        return IMAGE_PATH_PREFIX + "/" + imageURLs[imageClass][subClass];
    }
}
