package de.hering.tdoo;

public class GlobalClass extends com.orm.SugarApp{

    private Boolean isOnline = false;
    private static GlobalClass instance;
    private static Boolean isOn;

    public static GlobalClass getContext(){
        return instance;
    }

    public static Boolean getIsOn(){
        return isOn;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
        isOn = isOnline;
    }


}