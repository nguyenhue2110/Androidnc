package hue.com.mob201_ps08729.model;

public class kh {

    private String tenKH;
    private String moTa;
    private String thoigianBD;
    private String thoigianKT;
    private String giangVien;

    public kh() {
    }

    public kh(String tenKH, String moTa, String thoigianBD, String thoigianKT, String giangVien) {
        this.tenKH = tenKH;
        this.moTa = moTa;
        this.thoigianBD = thoigianBD;
        this.thoigianKT = thoigianKT;
        this.giangVien = giangVien;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getThoigianBD() {
        return thoigianBD;
    }

    public void setThoigianBD(String thoigianBD) {
        this.thoigianBD = thoigianBD;
    }

    public String getThoigianKT() {
        return thoigianKT;
    }

    public void setThoigianKT(String thoigianKT) {
        this.thoigianKT = thoigianKT;
    }

    public String getGiangVien() {
        return giangVien;
    }

    public void setGiangVien(String giangVien) {
        this.giangVien = giangVien;
    }
}
