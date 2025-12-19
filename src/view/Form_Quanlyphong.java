/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;
import controller.controller_Phong;
import model.Phong;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
/**
 *
 * @author luuxu
 */
public class Form_Quanlyphong extends javax.swing.JPanel {

    /**
     * Creates new form Form_Quanlyphong
     */
    private controller_Phong phongController;
    private DefaultTableModel tableModel;
    
    public Form_Quanlyphong() {
        initComponents();
        phongController = new controller_Phong();
        modifyUI();
        initTable();
        loatData();
        setupFilter();
    }
    private void modifyUI(){
        //  màu sắc cho cac label
        jPanel1.setBackground(new Color(70, 130, 180)); // Steel blue
        jPanel2.setBackground(new Color(240, 248, 255)); // Alice blue
        jPanel3.setBackground(new Color(240, 248, 255));
        jPanel4.setBackground(new Color(240, 248, 255));
        jPanel5.setBackground(new Color(240, 248, 255));
        //  font cho tiêu đề
        jLabel1.setForeground(Color.WHITE);
        jLabel2.setForeground(Color.WHITE);
        
        //  font cho các nút
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 20);
        btn_them.setFont(buttonFont);
        btn_sua.setFont(buttonFont);
        btn_xoa.setFont(buttonFont);
        
        // Thiết lập màu cho các nút
        btn_them.setBackground(new Color(34, 139, 34)); // Forest green
        btn_them.setForeground(Color.WHITE);
        
        btn_sua.setBackground(new Color(30, 144, 255)); // Dodger blue
        btn_sua.setForeground(Color.WHITE);
        
        btn_xoa.setBackground(new Color(220, 20, 60)); // Crimson
        btn_xoa.setForeground(Color.WHITE);
        
        //  font cho table header
        tbl_phong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 20));
        tbl_phong.getTableHeader().setBackground(new Color(70, 130, 180));
        tbl_phong.getTableHeader().setForeground(Color.BLACK);
        //them border cho dep
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin Phòng"));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Chức năng"));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc theo giá"));
        //  font cho table content
        tbl_phong.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tbl_phong.setRowHeight(25);
        
        txt_maphong.setEditable(false);
    }
    //khoi tao table
    private void initTable(){
        tableModel = new DefaultTableModel();
        tableModel = (DefaultTableModel) tbl_phong.getModel();
        tableModel.setColumnIdentifiers(new String[]{"Mã Phòng", "Tên Phòng", "Giá Phòng"});
        tbl_phong.setModel(tableModel);
    }
    //day du lieu len table
    private void loatData(){
        tableModel.setRowCount(0);
        List<Phong> dsPhong = phongController.getAllPhong();
        for (Phong phong : dsPhong) {
            tableModel.addRow(new Object[]{
                phong.getMaPhong(),
                phong.getTenPhong(),
                phong.getGiaPhong()
            });
        }
    }
    //xoa text field
    private void clearForm(){
        txt_maphong.setText("");
        txt_tenphong.setText("");
        txt_giaphong.setText("");
        txt_tenphong.requestFocus();
    }
    //xac thuc cac dieu kien trong form
    private boolean validationPhong(){
        if (txt_tenphong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên phòng!",
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            txt_tenphong.requestFocus();
            return false;
        }
        
        if (txt_giaphong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                    "Vui lòng nhập giá phòng!", "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            txt_giaphong.requestFocus();
            return false;
        }
        
        try {
            int gia = Integer.parseInt(txt_giaphong.getText().trim());
            if (gia <= 0) {
                JOptionPane.showMessageDialog(this, 
                        "Giá phòng phải lớn hơn 0!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                txt_giaphong.requestFocus();
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Giá phòng phải là số nguyên!",
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            txt_giaphong.requestFocus();
            return false;
        }
        
        return true;
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
        jPanel2 = new javax.swing.JPanel();
        JScrollPane1 = new javax.swing.JScrollPane();
        tbl_phong = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_maphong = new javax.swing.JTextField();
        txt_tenphong = new javax.swing.JTextField();
        txt_giaphong = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btn_them = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbo_locgia = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(610, 520));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(610, 90));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Quản lý phòng");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Quản lý thông tin các phòng trong khách sạn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1096, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        JScrollPane1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        JScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JScrollPane1MouseClicked(evt);
            }
        });

        tbl_phong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Phòng", "Tên Phòng", "Giá Phòng"
            }
        ));
        tbl_phong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_phongMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tbl_phongMouseExited(evt);
            }
        });
        JScrollPane1.setViewportView(tbl_phong);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Tên Phòng :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Mã Phòng :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Giá Phòng");

        txt_maphong.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        txt_tenphong.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        txt_giaphong.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_giaphong))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txt_tenphong))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txt_maphong, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_maphong, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenphong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_giaphong, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btn_them.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_them.setText("Thêm phòng");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_xoa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_xoa.setText("Xóa phòng");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_sua.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btn_sua.setText("Sửa phòng");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_sua, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                    .addComponent(btn_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_them, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Dữ liệu Phòng");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel7.setText("Giá :");

        cbo_locgia.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cbo_locgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_locgiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbo_locgia, 0, 298, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_locgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(JScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void JScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JScrollPane1MouseClicked
        
    }//GEN-LAST:event_JScrollPane1MouseClicked
    //lay du lieu tu cac o txt
    private Phong getPhongtuForm(){
        Phong phong = new Phong();
        if (!txt_maphong.getText().trim().isEmpty()) {
            phong.setMaPhong(Integer.parseInt(txt_maphong.getText().trim()));
        }
        phong.setTenPhong(txt_tenphong.getText().trim());
        phong.setGiaPhong(Integer.parseInt(txt_giaphong.getText().trim()));
        return phong;
    }
    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        if (validationPhong()) {
            Phong phong = getPhongtuForm();
            if (phongController.addPhong(phong)) {
                JOptionPane.showMessageDialog(this, 
                        "Thêm phòng thành công!", 
                        "Thành công", 
                        JOptionPane.INFORMATION_MESSAGE);
                loatData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, 
                        "Thêm phòng thất bại!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void tbl_phongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_phongMouseClicked
        int row=tbl_phong.getSelectedRow();
        if(row>0){
            txt_maphong.setText(tableModel.getValueAt(row,0).toString());
            txt_tenphong.setText(tableModel.getValueAt(row, 1).toString());
            txt_giaphong.setText(tableModel.getValueAt(row, 2).toString());
        }else{
            JOptionPane.showMessageDialog(this, "Loi");
        }
    }//GEN-LAST:event_tbl_phongMouseClicked

    private void tbl_phongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_phongMouseExited
       
    }//GEN-LAST:event_tbl_phongMouseExited

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        clearForm();
    }//GEN-LAST:event_formMouseClicked

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        if (txt_maphong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                    "Vui lòng chọn phòng cần sửa!", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (validationPhong()) {
            Phong phong = getPhongtuForm();
            if (phongController.updatePhong(phong)) {
                JOptionPane.showMessageDialog(this, 
                        "Cập nhật phòng thành công!", 
                        "Thành công",
                        JOptionPane.INFORMATION_MESSAGE);
                loatData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, 
                        "Cập nhật phòng thất bại!",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        if (txt_maphong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                    "Vui lòng chọn phòng cần xóa!",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Bạn có chắc chắn muốn xóa phòng này?", 
            "Xác nhận xóa", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            int maPhong = Integer.parseInt(txt_maphong.getText().trim());
            if (phongController.deletePhong(maPhong)) {
                JOptionPane.showMessageDialog(this,
                        "Xóa phòng thành công!", 
                        "Thành công", 
                        JOptionPane.INFORMATION_MESSAGE);
                loatData();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, 
                        "Xóa phòng thất bại!", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_xoaActionPerformed
    //bo loc
    private void setupFilter(){
        cbo_locgia.addItem("Tất cả");
        cbo_locgia.addItem("0 - 300,000 VNĐ");
        cbo_locgia.addItem("300,000 - 600,000 VNĐ");
        cbo_locgia.addItem("600,000 - 1,000,000 VNĐ");
        cbo_locgia.addItem("Trên 1,000,000 VNĐ");

        // Thêm sự kiện cho combobox
        cbo_locgia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_locgiaActionPerformed(evt);
            }
        });
        
    }
    private void cbo_locgiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_locgiaActionPerformed
        String selectedRange = cbo_locgia.getSelectedItem().toString();
        filterPhongByGia(selectedRange);
    }//GEN-LAST:event_cbo_locgiaActionPerformed
    private void filterPhongByGia(String range) {
        if (range.equals("Tất cả")) {
            loatData();
        } else {
            filterTableDataByGia(range);
        }
    }

    private void filterTableDataByGia(String range) {
        // Lấy tất cả dữ liệu từ controller
        List<Phong> allPhong = phongController.getAllPhong();
        tableModel.setRowCount(0); // Xóa table hiện tại

        for (Phong phong : allPhong) {
            int giaPhong = phong.getGiaPhong();
            boolean match = false;
            switch (range) {
                case "0 - 300,000 VNĐ":
                    match = giaPhong >= 0 && giaPhong <= 300000;
                    break;
                case "300,000 - 600,000 VNĐ":
                    match = giaPhong > 300000 && giaPhong <= 600000;
                    break;
                case "600,000 - 1,000,000 VNĐ":
                    match = giaPhong > 600000 && giaPhong <= 1000000;
                    break;
                case "Trên 1,000,000 VNĐ":
                    match = giaPhong > 1000000;
                    break;
            }
            if (match) {
                tableModel.addRow(new Object[]{
                    phong.getMaPhong(),
                    phong.getTenPhong(),
                    phong.getGiaPhong()
                });
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane1;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JComboBox<String> cbo_locgia;
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
    private javax.swing.JTable tbl_phong;
    private javax.swing.JTextField txt_giaphong;
    private javax.swing.JTextField txt_maphong;
    private javax.swing.JTextField txt_tenphong;
    // End of variables declaration//GEN-END:variables
}
