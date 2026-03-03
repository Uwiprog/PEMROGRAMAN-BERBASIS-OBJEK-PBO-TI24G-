package TUGAS.SESI_2;
//Nomor 3 tugas sesi 2
public class Komputer { //1. nama class Komputer
    String jenis_komputer; 
    private String merk;
    //2. atribut dengan nama Jenis Komputer, Private hanya bisa diakses di dalam class

    public void setDataKomputer(String  jenis, String merk) {
        jenis_komputer = jenis;
        this.merk = merk;
    }
    //3. Setter digunakan untuk mengubah nilai jenis_komputer supaya sama dengan jenis dan
    //   "this.merk" untuk membedakan merk yang di class komputer dengan ynag di setter

    public String getJenis(){
        return jenis_komputer;
    }
    //4. membaca atau mengambil nilai atribut

    public String getMerk(){
        return merk;
    }
    //5. membaca atau mengambil nilai atribut

    public static void main(String [] args){
        Komputer mykom = new Komputer(); //6. membuat object baru dari class komputer
        mykom.setDataKomputer("LAPTOP","MACBOOK"); //7. menggunalam setter untuk mengubah nilai dari atribut jenis dan merk
        System.out.println(mykom.getJenis());
        System.out.println(mykom.getMerk());
        //8. Method untuk menampilkan hasil
    }
}
