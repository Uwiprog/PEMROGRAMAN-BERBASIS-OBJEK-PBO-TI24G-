package TUGAS.SESI_7;

public class MahasiswaKu {
    public static void main(String[] args) {
        Mahasiswa <String, String, Integer> m = new Mahasiswa<>();
        m.setNim("1102020");
        m.setNama("Ferdi");
        m.setClas(21);

        System.out.println(m.getNim());
        System.out.println(m.getNama());
        System.out.println(m.getClas());
    }
}
