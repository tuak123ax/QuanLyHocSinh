import java.io.Serializable;

public class HocSinh implements Serializable {
    private int MHS;
    private String TenHS;
    private float Diem;
    private String Hinhanh;
    private String DiaChi;
    private String GhiChu;

    public HocSinh() {

    }

    public HocSinh(int MHS,String TenHS,float Diem,String Hinhanh,String DiaChi,String GhiChu)
    {
        this.MHS=MHS;
        this.TenHS=TenHS;
        this.Diem=Diem;
        this.Hinhanh=Hinhanh;
        this.DiaChi=DiaChi;
        this.GhiChu=GhiChu;
    }

    public int getMHS() {
        return MHS;
    }

    public void setMHS(int MHS) {
        this.MHS = MHS;
    }

    public String getTenHS() {
        return TenHS;
    }

    public void setTenHS(String tenHS) {
        TenHS = tenHS;
    }

    public float getDiem() {
        return Diem;
    }

    public void setDiem(float diem) {
        Diem = diem;
    }

    public String getHinhanh() {
        return Hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        Hinhanh = hinhanh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
}
