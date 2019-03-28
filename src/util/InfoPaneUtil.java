package util;

public class InfoPaneUtil {

    private InfoPaneUtil() {}

    public static final String[] INFO_PANE_NAMES = {
            PropertiesUtil.getProp("info.pane.base"),
            PropertiesUtil.getProp("info.pane.weapon"),
            PropertiesUtil.getProp("info.pane.ability")
    };

}
