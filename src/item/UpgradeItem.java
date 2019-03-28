package item;

import role.UserRole;

public interface UpgradeItem {

    String takeEffect(UserRole userRole);

    UpgradeItemType getType();

}
