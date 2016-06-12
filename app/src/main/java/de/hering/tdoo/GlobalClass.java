package de.hering.tdoo;

public class GlobalClass extends com.orm.SugarApp{

    private Boolean isOnline = false;

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }


}