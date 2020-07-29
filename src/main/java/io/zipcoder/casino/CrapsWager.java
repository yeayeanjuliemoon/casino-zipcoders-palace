package io.zipcoder.casino;

public class CrapsWager {
    private Integer pass;
    private Integer dontPass;
    private Integer fieldWager;
    private Integer seven;
    private Integer anyCraps;

    public CrapsWager(){

    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    public Integer getDontPass() {
        return dontPass;
    }

    public void setDontPass(Integer dontPass) {
        this.dontPass = dontPass;
    }

    public Integer getFieldWager() {
        return fieldWager;
    }

    public void setFieldWager(Integer fieldWager) {
        this.fieldWager = fieldWager;
    }

    public Integer getSeven() {
        return seven;
    }

    public void setSeven(Integer seven) {
        this.seven = seven;
    }

    public Integer getAnyCraps() {
        return anyCraps;
    }

    public void setAnyCraps(Integer anyCraps) {
        this.anyCraps = anyCraps;
    }
}
