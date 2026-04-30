package MATERI.SESI_6.model;

import MATERI.SESI_6.interfacekasir.BisaDiskon;
import MATERI.SESI_6.interfacekasir.BisaStok;

public class Minuman extends Produk implements BisaStok, BisaDiskon {

    @Override
    public int hitungDiskon(){
        return (harga * qty) * PERSEN_DISKON / 100;
    }

    @Override
    public int hitungTotal(){
        return (harga * qty) - hitungDiskon();
    }

    @Override
    public boolean cekStok(){
        return stok >= qty;
    }

    @Override
    public void kurangiStok(){
        if (cekStok()) {
            stok -= qty;
        }
    }

    @Override
    public void tampil(){
        super.tampil();
        System.out.println("Stok   : " + stok);
    }

}