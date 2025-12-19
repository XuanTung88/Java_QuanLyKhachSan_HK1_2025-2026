/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;
import controller.controller_KhachHang;
import java.awt.Color;
import java.awt.Font;
import model.KhachHang;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Comparator;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

/**
 *
 * @author luuxu
 */
public class Form_Quanlykhachhang extends javax.swing.JPanel {

    /**
     * Creates new form Form_Quanlykhachhang
     */
    private controller_KhachHang ctrlKH;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private List<KhachHang> danhSachKH;
    
    public Form_Quanlykhachhang() {
        initComponents();
        
        ctrlKH = new controller_KhachHang();
        initTable();
        loadDataToTable();
        
        modifyUI();
    }
    private void modifyUI(){
        //  màu sắc cho cac label
        jPanel1.setBackground(new Color(70, 130, 180)); // Steel blue
        jPanel2.setBackground(new Color(240, 248, 255)); // Alice blue
        jPanel3.setBackground(new Color(240, 248, 255));
      
        //  font cho tiêu đề
        jLabel1.setForeground(Color.WHITE);
        jLabel2.setForeground(Color.WHITE);

        //  font cho table header
        tbl_khachhang.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
        tbl_khachhang.getTableHeader().setBackground(new Color(70, 130, 180));
        tbl_khachhang.getTableHeader().setForeground(Color.BLACK);
        //them border cho dep
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin Khách hàng"));
        
        //  font cho table content
        tbl_khachhang.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tbl_khachhang.setRowHeight(25);
    }
    private void initTable() {
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Mã khách hàng", "Tên khách hàng", "Số điện thoại",
                    "Số CCCD", "Email", "Địa chỉ"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tbl_khachhang.setModel(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        tbl_khachhang.setRowSorter(sorter);

        // Sự kiện click vào table
        tbl_khachhang.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displaySelectedRowData();
            }
        });
    }
    
    private void loadDataToTable() {
        tableModel.setRowCount(0);
        danhSachKH = ctrlKH.getAllKhachHang();

        for (KhachHang kh : danhSachKH) {
            tableModel.addRow(new Object[]{
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getSoDT(),
                kh.getCccd(),
                kh.getEmail(),
                kh.getDiaChi()
            });
        }
    }
    
    // Hiển thị dữ liệu từ row selected lên form
    private void displaySelectedRowData() {
        int selectedRow = tbl_khachhang.getSelectedRow();
        if (selectedRow >= 0) {
            int modelRow = tbl_khachhang.convertRowIndexToModel(selectedRow);
            txt_makh.setText(tableModel.getValueAt(modelRow, 0).toString());
            txt_ten.setText(tableModel.getValueAt(modelRow, 1).toString());
            txt_sdt.setText(tableModel.getValueAt(modelRow, 2).toString());
            txt_macccd.setText(tableModel.getValueAt(modelRow, 3).toString());
            txt_email.setText(tableModel.getValueAt(modelRow, 4).toString());
            txt_diachi.setText(tableModel.getValueAt(modelRow, 5).toString());
        }
    }

    // Tìm kiếm theo CCCD
    private void timKiemTheoCCCD() {
        String cccd = txt_cccd.getText().trim();
        if (!cccd.isEmpty()) {
            KhachHang kh = ctrlKH.getKhachHangByCCCD(cccd);
            if (kh != null) {
                // Hiển thị thông tin lên các textfield
                txt_makh.setText(String.valueOf(kh.getMaKH()));
                txt_ten.setText(kh.getTenKH());
                txt_sdt.setText(kh.getSoDT());
                txt_macccd.setText(kh.getCccd());
                txt_email.setText(kh.getEmail());
                txt_diachi.setText(kh.getDiaChi());

                // Tìm và chọn hàng tương ứng trong table
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if (tableModel.getValueAt(i, 3).equals(cccd)) {
                        int viewIndex = tbl_khachhang.convertRowIndexToView(i);
                        tbl_khachhang.setRowSelectionInterval(viewIndex, viewIndex);
                        tbl_khachhang.scrollRectToVisible(tbl_khachhang.getCellRect(viewIndex, 0, true));
                        break;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng với CCCD: " + cccd);
            }
        }
    }
    
    // Lọc theo tên (kiểu danh bạ)
    private void locTheoTen() {
        String ten = txt_ten.getText().trim();
        if (ten.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            // Lọc theo tên (tìm kiếm theo kiểu danh bạ - contains, không phân biệt hoa thường)
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + ten, 1));
        }
    }
    
    // Sắp xếp theo alphabet
    private void sapXepTheoAlphabet() {
         // Tạo comparator để sắp xếp theo tên cuối
        Comparator<String> tenCuoiComparator = new Comparator<String>() {
            @Override
            public int compare(String ten1, String ten2) {
                String tenCuoi1 = layTenCuoi(ten1);
                String tenCuoi2 = layTenCuoi(ten2);
                return tenCuoi1.compareToIgnoreCase(tenCuoi2);
            }

            private String layTenCuoi(String hoTen) {
                if (hoTen == null || hoTen.trim().isEmpty()) {
                    return "";
                }
                String[] parts = hoTen.trim().split("\\s+");
                return parts[parts.length - 1]; // Lấy từ cuối cùng
            }
        };

        // Áp dụng sắp xếp cho cột tên (cột index 1)
        sorter.setComparator(1, tenCuoiComparator);
        sorter.setSortKeys(List.of(new RowSorter.SortKey(1, SortOrder.ASCENDING)));
    }
    
    
    // Làm mới dữ liệu
    private void lamMoiDuLieu() {
        // Xóa dữ liệu trong các textfield
        txt_makh.setText("");
        txt_ten.setText("");
        txt_sdt.setText("");
        txt_macccd.setText("");
        txt_email.setText("");
        txt_diachi.setText("");
        txt_cccd.setText("");

        // Tải lại dữ liệu từ database
        loadDataToTable();

        // Xóa bộ lọc
        sorter.setRowFilter(null);

        JOptionPane.showMessageDialog(this, "Đã làm mới dữ liệu thành công!");
    }
    
    //xuat file .csv
    private void xuatFileCSV() {   
        String duongDan = "D:\\hocki5\\Netbean\\file excel\\khachhang1.csv";
        File file = new File(duongDan);

        // Tạo thư mục nếu chưa tồn tại
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        thucHienXuatCSV(file);
    }

    private void thucHienXuatCSV(File file) {
        try {
            // Sử dụng UTF-8 với BOM để hỗ trợ tiếng Việt trong Excel
            try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"))) {

                // Thêm BOM để Excel nhận diện UTF-8
                writer.write('\uFEFF');

                // Header
                writer.println("Mã KH,Tên khách hàng,Số điện thoại,CCCD,Email,Địa chỉ");

                // Data
                for (KhachHang kh : danhSachKH) {
                    writer.println(String.format("%d,%s,%s,%s,%s,%s",
                            kh.getMaKH(),
                            escapeCsv(kh.getTenKH()),
                            escapeCsv(kh.getSoDT()),
                            escapeCsv(kh.getCccd()),
                            escapeCsv(kh.getEmail()),
                            escapeCsv(kh.getDiaChi())
                    ));
                }
            }

            JOptionPane.showMessageDialog(this,
                    "Xuất file CSV thành công!\nĐường dẫn: " + file.getAbsolutePath()
                    + "\n\nLưu ý: Mở file bằng Excel và chọn Encoding UTF-8 để hiển thị tiếng Việt chính xác.",
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Lỗi khi xuất file CSV: " + e.getMessage(),
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Hàm escape dữ liệu CSV
    private String escapeCsv(String value) {
        if (value == null) {
            return "";
        }

        // Nếu chứa dấu phẩy, dấu ngoặc kép, hoặc xuống dòng -> bọc trong ngoặc kép
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_khachhang = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbl_timkiem = new javax.swing.JLabel();
        txt_cccd = new javax.swing.JTextField();
        lbl_lockh = new javax.swing.JLabel();
        txt_ten = new javax.swing.JTextField();
        btn_sapxep = new javax.swing.JButton();
        btn_xuatexcel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_makh = new javax.swing.JTextField();
        txt_macccd = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_diachi = new javax.swing.JTextField();
        btn_refresh = new javax.swing.JButton();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(611, 90));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý khách hàng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Quản lý dữ liệu cá nhân khách hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbl_khachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Số CCCD", "Email", "Địa chỉ"
            }
        ));
        jScrollPane1.setViewportView(tbl_khachhang);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Dữ liệu khách hàng");

        lbl_timkiem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_timkiem.setText("Tìm theo mã CCCD :");
        lbl_timkiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_timkiemMouseClicked(evt);
            }
        });

        txt_cccd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        lbl_lockh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_lockh.setText("Lọc khách hàng theo tên :");
        lbl_lockh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_lockhMouseClicked(evt);
            }
        });

        txt_ten.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btn_sapxep.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btn_sapxep.setText("Sắp xếp theo alphabet");
        btn_sapxep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sapxepActionPerformed(evt);
            }
        });

        btn_xuatexcel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btn_xuatexcel.setText("Xuất file excel");
        btn_xuatexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuatexcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btn_sapxep, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_lockh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_timkiem, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_cccd)
                    .addComponent(txt_ten)
                    .addComponent(btn_xuatexcel, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_timkiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_cccd, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbl_lockh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_ten, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_xuatexcel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sapxep, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Mã KH :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("CCCD :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Số điện thoại :");

        txt_makh.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txt_macccd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txt_sdt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Email :");

        txt_email.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Địa chỉ :");

        txt_diachi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        btn_refresh.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        btn_refresh.setText("Làm mới dữ liệu");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_diachi, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_makh, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txt_macccd))))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_email, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(txt_sdt)))
                    .addComponent(btn_refresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_makh, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_macccd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_diachi)
                        .addComponent(btn_refresh, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_timkiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_timkiemMouseClicked
        timKiemTheoCCCD();
    }//GEN-LAST:event_lbl_timkiemMouseClicked

    private void lbl_lockhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_lockhMouseClicked
        locTheoTen();
    }//GEN-LAST:event_lbl_lockhMouseClicked

    private void btn_sapxepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sapxepActionPerformed
        sapXepTheoAlphabet();
    }//GEN-LAST:event_btn_sapxepActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        lamMoiDuLieu();
    }//GEN-LAST:event_btn_refreshActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // Xóa dữ liệu trong các textfield
        txt_makh.setText("");
        txt_ten.setText("");
        txt_sdt.setText("");
        txt_macccd.setText("");
        txt_email.setText("");
        txt_diachi.setText("");
        txt_cccd.setText("");
    }//GEN-LAST:event_formMouseClicked

    private void btn_xuatexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuatexcelActionPerformed
        xuatFileCSV();
    }//GEN-LAST:event_btn_xuatexcelActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_sapxep;
    private javax.swing.JButton btn_xuatexcel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_lockh;
    private javax.swing.JLabel lbl_timkiem;
    private javax.swing.JTable tbl_khachhang;
    private javax.swing.JTextField txt_cccd;
    private javax.swing.JTextField txt_diachi;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_macccd;
    private javax.swing.JTextField txt_makh;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_ten;
    // End of variables declaration//GEN-END:variables
}
