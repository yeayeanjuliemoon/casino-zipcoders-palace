package io.zipcoder.casino;

public class CrapsWager {
    private Integer pass = 0;
    private Integer dontPass = 0;
    private Integer fieldWager = 0;
    private Integer seven = 0;
    private Integer anyCraps = 0;

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
