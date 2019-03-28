package util;

public class RoleUtil {

    private RoleUtil() {}

    public static final int ROLE_DEFAULT_ATTACK = Integer.parseInt(PropertiesUtil.getProp("role.user.default.attack"));

    public static final int ENEMY_DEFAULT_ATTACK = Integer.parseInt(PropertiesUtil.getProp("role.enemy.default.attack"));

    public static final int ROLE_DEFAULT_LP = Integer.parseInt(PropertiesUtil.getProp("role.user.default.lp"));

    public static final int ENEMY_DEFAULT_LP = Integer.parseInt(PropertiesUtil.getProp("role.enemy.default.lp"));

    public static final int ENEMY_BASE_EXP = Integer.parseInt(PropertiesUtil.getProp("role.enemy.default.exp"));

    public static final int EXP_PER_LEVEL = Integer.parseInt(PropertiesUtil.getProp("role.user.default.exp"));
}
