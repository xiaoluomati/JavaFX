package role;

public enum  ClassType {

    PRIEST,MAGE,ROGUE,WARRIOR;

    public static ClassType getByInt(int integer){
        for (ClassType type : ClassType.values()) {
            if(type.ordinal() == integer)
                return type;
        }
        return PRIEST;
    }

}
