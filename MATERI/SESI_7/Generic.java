public class Generic {
    // Satu kelas generic untuk berbagai tipe data.
    static class DataStore<T> {
        private T data;
        public void simpan(T data) {
            this.data = data;
        }
        public T ambil() {
            return data;
        }
    }

    public static void main(String[] args) {
        DataStore<String> kodePromo = new DataStore<>();
        kodePromo.simpan("HEMAT10");

        DataStore<Integer> poin = new DataStore<>();
        poin.simpan(250);
        

        System.out.println("Kode Promo Aktif : " + kodePromo.ambil());
        System.out.println("Poin Loyalti     : " + poin.ambil());
    }
}