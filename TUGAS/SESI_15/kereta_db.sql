-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 10 Jul 2026 pada 15.16
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kereta_db`
--

DELIMITER $$
--
-- Prosedur
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_lihat_jadwal` ()   BEGIN
    SELECT * FROM trains ORDER BY id_kereta;
END$$

--
-- Fungsi
--
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_hitung_total` (`harga` DECIMAL(10,2), `jumlah` INT) RETURNS DECIMAL(10,2) DETERMINISTIC BEGIN
    RETURN harga * jumlah;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tickets`
--

CREATE TABLE `tickets` (
  `id_tiket` int(11) NOT NULL,
  `nama_penumpang` varchar(100) NOT NULL,
  `id_kereta` int(11) NOT NULL,
  `jumlah_tiket` int(11) NOT NULL,
  `total_harga` decimal(10,2) NOT NULL,
  `tanggal_pesan` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tickets`
--

INSERT INTO `tickets` (`id_tiket`, `nama_penumpang`, `id_kereta`, `jumlah_tiket`, `total_harga`, `tanggal_pesan`) VALUES
(1, '6', 6, 2, 160000.00, '2026-07-10 13:13:27');

--
-- Trigger `tickets`
--
DELIMITER $$
CREATE TRIGGER `tr_kurangi_kursi` AFTER INSERT ON `tickets` FOR EACH ROW BEGIN
    UPDATE trains 
    SET kursi_tersedia = kursi_tersedia - NEW.jumlah_tiket
    WHERE id_kereta = NEW.id_kereta;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `train`
--

CREATE TABLE `train` (
  `id_kereta` int(11) NOT NULL,
  `nama_kereta` varchar(100) NOT NULL,
  `asal` varchar(50) NOT NULL,
  `tujuan` varchar(50) NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `kursi_tersedia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `trains`
--

CREATE TABLE `trains` (
  `id_kereta` int(11) NOT NULL,
  `nama_kereta` varchar(100) NOT NULL,
  `asal` varchar(50) NOT NULL,
  `tujuan` varchar(50) NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `kursi_tersedia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `trains`
--

INSERT INTO `trains` (`id_kereta`, `nama_kereta`, `asal`, `tujuan`, `harga`, `kursi_tersedia`) VALUES
(1, 'Argo Bromo', 'Jakarta', 'Surabaya', 350000.00, 50),
(2, 'Argo Lawu', 'Jakarta', 'Solo', 320000.00, 45),
(3, 'Taksaka', 'Jakarta', 'Yogyakarta', 300000.00, 60),
(4, 'Gajayana', 'Jakarta', 'Malang', 380000.00, 40),
(5, 'Manggarai', 'Bekasi', 'Bogor', 7000.00, 150),
(6, 'Pangrango', 'Paledang', 'Sukabumi', 80000.00, 73);

-- --------------------------------------------------------

--
-- Stand-in struktur untuk tampilan `vw_pemesanan_lengkap`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `vw_pemesanan_lengkap` (
`id_tiket` int(11)
,`nama_penumpang` varchar(100)
,`nama_kereta` varchar(100)
,`asal` varchar(50)
,`tujuan` varchar(50)
,`jumlah_tiket` int(11)
,`total_harga` decimal(10,2)
,`tanggal_pesan` timestamp
);

-- --------------------------------------------------------

--
-- Struktur untuk view `vw_pemesanan_lengkap`
--
DROP TABLE IF EXISTS `vw_pemesanan_lengkap`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_pemesanan_lengkap`  AS SELECT `t`.`id_tiket` AS `id_tiket`, `t`.`nama_penumpang` AS `nama_penumpang`, `tr`.`nama_kereta` AS `nama_kereta`, `tr`.`asal` AS `asal`, `tr`.`tujuan` AS `tujuan`, `t`.`jumlah_tiket` AS `jumlah_tiket`, `t`.`total_harga` AS `total_harga`, `t`.`tanggal_pesan` AS `tanggal_pesan` FROM (`tickets` `t` join `trains` `tr` on(`t`.`id_kereta` = `tr`.`id_kereta`)) ;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id_tiket`),
  ADD KEY `id_kereta` (`id_kereta`);

--
-- Indeks untuk tabel `train`
--
ALTER TABLE `train`
  ADD PRIMARY KEY (`id_kereta`);

--
-- Indeks untuk tabel `trains`
--
ALTER TABLE `trains`
  ADD PRIMARY KEY (`id_kereta`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id_tiket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `train`
--
ALTER TABLE `train`
  MODIFY `id_kereta` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `trains`
--
ALTER TABLE `trains`
  MODIFY `id_kereta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`id_kereta`) REFERENCES `trains` (`id_kereta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
