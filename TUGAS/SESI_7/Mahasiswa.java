package TUGAS.SESI_7;

public class Mahasiswa<N, Na, C> {
    private N nim;
    private Na nama;
    private C clas;

    public void setNim(N nim) {
        this.nim = nim;
    }

    public void setNama(Na nama) {
        this.nama = nama;
    }

    public void setClas(C clas) {
        this.clas = clas;
    }

    public N getNim() {
        return nim;
    }

    public Na getNama() {
        return nama;
    }

    public C getClas() {
        return clas;
    }
}
