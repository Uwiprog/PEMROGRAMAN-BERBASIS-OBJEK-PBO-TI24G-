import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BiodataMahasiswa extends JFrame {

    private JTextField nimField, namaField, jurusanField;
    private JTextArea TxtOutput;

    public BiodataMahasiswa() {
        super("Aplikasi Biodata Mahasiswa");
        setLayout(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Biodata"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // NIM
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("NIM: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        nimField = new JTextField(20);
        inputPanel.add(nimField, gbc);

        // Nama
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("Nama: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        namaField = new JTextField(20);
        inputPanel.add(namaField, gbc);

        // jurusan
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("Jurusan: "), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        jurusanField = new JTextField(20);
        inputPanel.add(jurusanField, gbc);

        // tombol
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        JButton btnTampilkan = new JButton("Tampilkan");
        JButton btnReset = new JButton("Reset");

        panelTombol.add(btnTampilkan);
        panelTombol.add(btnReset);

        // output
        JPanel panelOutput = new JPanel(new BorderLayout());
        panelOutput.setBorder(BorderFactory.createTitledBorder("Output"));

        TxtOutput = new JTextArea(8, 30);
        TxtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12));
        TxtOutput.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(TxtOutput);
        panelOutput.add(scrollPane, BorderLayout.CENTER);

        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(inputPanel, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        // ===== Tambahkan ke Frame =====
        add(panelAtas, BorderLayout.NORTH);
        add(panelOutput, BorderLayout.CENTER);

        // ===== Event Listener Tombol Tampilkan =====
        btnTampilkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = nimField.getText();
                String nama = namaField.getText();
                String prodi = jurusanField.getText();

                String output = "========== BIODATA MAHASISWA ==========\n\n";
                output += "NIM             : " + nim + "\n";
                output += "Nama            : " + nama + "\n";
                output += "Program Studi   : " + prodi + "\n";

                TxtOutput.setText(output);
            }
        });

        // ===== Event Listener Tombol Reset =====
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nimField.setText("");
                namaField.setText("");
                jurusanField.setText("");
                TxtOutput.setText("");
            }
        });

        // ===== Pengaturan Frame =====
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window
        setResizable(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BiodataMahasiswa().setVisible(true);
            }
        });
    }
}
