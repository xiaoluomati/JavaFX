package util;

public class WeaponItemUtil {

    private WeaponItemUtil() {}

    public static final int WEAPON_DEFAULT_ATTACK = Integer.parseInt(PropertiesUtil.getProp("weapon.default.attack"));

    public static final double WEAPON_DEFAULT_AP = Double.parseDouble(PropertiesUtil.getProp("weapon.default.ap"));

    public static final int ITEM_WEAPON_ATTACK = Integer.parseInt(PropertiesUtil.getProp("item.upgrade.weapon.attack"));

    public static final int ITEM_LPC_UPGRADE = Integer.parseInt(PropertiesUtil.getProp("item.upgrade.role.lpc"));

    public static final int ITEM_LP_UPGRADE = Integer.parseInt(PropertiesUtil.getProp("item.upgrade.role.lp"));

    public static final int ITEM_ATTACK_UPGRADE = Integer.parseInt(PropertiesUtil.getProp("item.upgrade.role.attack"));
    
    public static final double ITEM_AP_UPGRADE = Double.parseDouble(PropertiesUtil.getProp("item.upgrade.role.ap"));

}
