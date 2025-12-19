/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;
import controller.controller_DichVu;
import model.DichVu;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.TableModel;
/**
 *
 * @author luuxu
 */
public class Form_Quanlydichvu extends javax.swing.JPanel {

    /**
     * Creates new form Form_Quanlydichvu
     */
    private controller_DichVu dichVuController;
    private DefaultTableModel tableModel;
    
    public Form_Quanlydichvu() {
        initComponents();
        
        dichVuController = new controller_DichVu();
        initTable();
        loadData();
        setupUI();
        setupFilter();
    }
    private void initTable() {
        tableModel = new DefaultTableModel();
        tableModel = (DefaultTableModel) tbl_dichvu.getModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã dịch vụ", "Tên dịch vụ", "Giá dịch vụ"});
        tbl_dichvu.setModel(tableModel);
    }
    private void loadData() {
        tableModel.setRowCount(0);
        List<DichVu> dsDichVu = dichVuController.getAllDichVu();
        for (DichVu dv : dsDichVu) {
            tableModel.addRow(new Object[]{
                dv.getMaDichVu(),
                dv.getTenDichVu(),
                dv.getGiaDichVu()
            });
        }
    }
    private void clearForm() {
        txt_madv.setText("");
        txt_tendv.setText("");
        txt_giadv.setText("");
        txt_tendv.requestFocus();
    }
    //check loi hay gap tren form
    private boolean validateForm() {
        if (txt_tendv.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập tên dịch vụ!", 
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            txt_tendv.requestFocus();
            return false;
        }
        
        if (txt_giadv.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng nhập giá dịch vụ!", 
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            txt_giadv.requestFocus();
            return false;
        }
        
        try {
            int gia = Integer.parseInt(txt_giadv.getText().trim());
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, 
                        "Giá dịch vụ phải lớn hơn 0!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                txt_giadv.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Giá dịch vụ phải là số nguyên!",
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            txt_giadv.requestFocus();
            return false;
        }
        
        return true;
    }
    //lay thong tin tu UI gan vao model
    private DichVu getDichVuFromForm() {
        DichVu dv = new DichVu();
        if (!txt_madv.getText().trim().isEmpty()) {
            dv.setMaDichVu(Integer.parseInt(txt_madv.getText().trim()));
        }
        dv.setTenDichVu(txt_tendv.getText().trim());
        dv.setGiaDichVu(Integer.parseInt(txt_giadv.getText().trim()));
        return dv;
    }
    private void setupUI() {
        // T màu sắc
        jPanel1.setBackground(new Color(70, 130, 180)); // Steel blue
        jPanel4.setBackground(new Color(240, 248, 255)); // Alice blue
        jPanel2.setBackground(new Color(240, 248, 255));
        jPanel3.setBackground(new Color(240, 248, 255));
        jPanel5.setBackground(new Color(240, 248, 255));
        //  font cho tiêu đề
        jLabel1.setForeground(Color.WHITE);
        jLabel2.setForeground(Color.WHITE);
        
        //  font cho các nút
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 20);
        btn_them.setFont(buttonFont);
        btn_sua.setFont(buttonFont);
        btn_xoa.setFont(buttonFont);
        
        //  màu cho các nút
        btn_them.setBackground(new Color(34, 139, 34)); // Forest green
        btn_them.setForeground(Color.WHITE);
        
        btn_sua.setBackground(new Color(30, 144, 255)); // Dodger blue
        btn_sua.setForeground(Color.WHITE);
        
        btn_xoa.setBackground(new Color(220, 20, 60)); // Crimson
        btn_xoa.setForeground(Color.WHITE);
        
        //  font cho table header
        tbl_dichvu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
        tbl_dichvu.getTableHeader().setBackground(new Color(70, 130, 180));
        tbl_dichvu.getTableHeader().setForeground(Color.BLACK);
        
        //  font cho table content
        tbl_dichvu.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tbl_dichvu.setRowHeight(25);
        
        // Thiết lập border cho các panel
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin dịch vụ"));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Bộ lọc"));
        // Mã dịch vụ không thể chỉnh sửa
        txt_madv.setEditable(false);
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
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_dichvu = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_madv = new javax.swing.JTextField();
        txt_tendv = new javax.swing.JTextField();
        txt_giadv = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cbo_locdv = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Quản lý dịch vụ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Quản lý thông tin các dịch vụ và tiện ích trong khách sạn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(418, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
        );

        tbl_dichvu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tbl_dichvu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã dịch vụ", "Tên dịch vụ", "Giá dịch vụ"
            }
        ));
        tbl_dichvu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_dichvuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_dichvu);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Mã dịch vụ :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Tên dịch vụ :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Giá dịch vụ :");

        txt_madv.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txt_tendv.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txt_giadv.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_madv)
                    .addComponent(txt_tendv)
                    .addComponent(txt_giadv, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_madv, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tendv, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_giadv, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_them.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_them.setText("Thêm Dịch vụ");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_sua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_sua.setText("Sửa dịch vụ");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_xoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_xoa.setText("Xóa Dịch vụ");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_them, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
            .addComponent(btn_sua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_xoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Dữ liệu dịch vụ");

        cbo_locdv.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbo_locdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_locdvActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel7.setText("Chọn dịch vụ :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(cbo_locdv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_locdv, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_dichvuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_dichvuMouseClicked
        int row=tbl_dichvu.getSelectedRow();
        if(row>0){
            txt_madv.setText(tableModel.getValueAt(row, 0).toString());
            txt_tendv.setText(tableModel.getValueAt(row, 1).toString());
            txt_giadv.setText(tableModel.getValueAt(row, 2).toString());
            
        }else{
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }//GEN-LAST:event_tbl_dichvuMouseClicked

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        if (validateForm()) {
            DichVu dv = getDichVuFromForm();
            if (dichVuController.addDichVu(dv)) {
                JOptionPane.showMessageDialog(this,
                        "Thêm dịch vụ thành công!", 
                        "Thành công", 
                        JOptionPane.INFORMATION_MESSAGE);
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Thêm dịch vụ thất bại!", 
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
         if (txt_madv.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                    "Vui lòng chọn dịch vụ cần sửa!",
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (validateForm()) {
            DichVu dv = getDichVuFromForm();
            if (dichVuController.updateDichVu(dv)) {
                JOptionPane.showMessageDialog(this, 
                        "Cập nhật dịch vụ thành công!",
                        "Thành công", 
                        JOptionPane.INFORMATION_MESSAGE);
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Cập nhật dịch vụ thất bại!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
          if (txt_madv.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Vui lòng chọn dịch vụ cần xóa!",
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa dịch vụ này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            int maDichVu = Integer.parseInt(txt_madv.getText().trim());
            if (dichVuController.deleteDichVu(maDichVu)) {
                JOptionPane.showMessageDialog(this, 
                        "Xóa dịch vụ thành công!", 
                        "Thành công", 
                        JOptionPane.INFORMATION_MESSAGE);
                loadData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Xóa dịch vụ thất bại!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        clearForm();
    }//GEN-LAST:event_formMouseClicked

    private void cbo_locdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_locdvActionPerformed
        String selectedCategory = cbo_locdv.getSelectedItem().toString();
        filterDichVuByCategory(selectedCategory);
    }//GEN-LAST:event_cbo_locdvActionPerformed
    //phan loc
    private void setupFilter() {
        // Thêm các item vào combobox
        cbo_locdv.addItem("Tất cả");
        cbo_locdv.addItem("Tiện ích");
        cbo_locdv.addItem("Thể thao");
        cbo_locdv.addItem("Sức khỏe");
       

        // Thêm sự kiện cho combobox
        cbo_locdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_locdvActionPerformed(evt);
            }
        });
    }
    //loc theo loai dichvu
    private void filterDichVuByCategory(String category) {
        if (category.equals("Tất cả")) {
            loadData(); 
        } else {
            filterTableData(category); 
        }
    }
    
    private void filterTableData(String category) {
        List<DichVu> allDichVu = dichVuController.getAllDichVu();
        tableModel.setRowCount(0); // Xóa table hiện tại

        for (DichVu dv : allDichVu) {
            String tenDichVu = dv.getTenDichVu().toLowerCase();
            boolean match = false;

            switch (category) {
                case "Tiện ích":
                    match = tenDichVu.contains("wifi") || tenDichVu.contains("minibar")
                            || tenDichVu.contains("don phòng thêm") || tenDichVu.contains("giat là")
                            || tenDichVu.contains("đua dón sân bay")|| tenDichVu.contains("buffet") ;
                    break;
                case "Thể thao":
                    match = tenDichVu.contains("gym") || tenDichVu.contains("swimming pool")
                            || tenDichVu.contains("tennis") || tenDichVu.contains("yoga")
                            || tenDichVu.contains("khu golf") || tenDichVu.contains("oánh pickaball");
                    break;
                case "Sức khỏe":
                    match = tenDichVu.contains("spa thu giãn") || tenDichVu.contains("mát xa lành manh")
                            || tenDichVu.contains("xong hoi") || tenDichVu.contains("phuc vu tan phòng")
                            || tenDichVu.contains("làm đẹp");
                    break;
            }

            if (match) {
                tableModel.addRow(new Object[]{
                    dv.getMaDichVu(),
                    dv.getTenDichVu(),
                    dv.getGiaDichVu()
                });
            }
        }
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> cbo_locdv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_dichvu;
    private javax.swing.JTextField txt_giadv;
    private javax.swing.JTextField txt_madv;
    private javax.swing.JTextField txt_tendv;
    // End of variables declaration//GEN-END:variables
}
