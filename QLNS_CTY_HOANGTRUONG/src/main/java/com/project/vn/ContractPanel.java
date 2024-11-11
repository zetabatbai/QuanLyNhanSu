package com.project.vn;

import com.project.vn.dao.ContractDAO;
import com.project.vn.dao.EmployeeDAO;
import com.project.vn.dto.Contract;
import com.project.vn.dto.Employee;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ContractPanel extends javax.swing.JPanel {

    private Boolean isAdd = false;

    public ContractPanel() {
        initComponents();
        redesign();
    }

    private void clearTextbox() {
        txtId.setText("");
        txtEnd.setText("");
        txtStart.setText("");
        txtType.setText("");
        cboEmp.setSelectedItem("Chọn nhân viên");
    }

    private void disableTextbox() {
        txtId.setEnabled(false);
        txtEnd.setEditable(false);
        txtType.setEditable(false);
        txtStart.setEditable(false);
        cboEmp.setEnabled(false);
    }

    private void enableTextbox() {
        txtEnd.setEditable(true);
        txtType.setEditable(true);
        txtStart.setEditable(true);
        cboEmp.setEnabled(true);
    }

    private void redesign() {
        tableModel = (DefaultTableModel) tblData.getModel();
        tableModel.setRowCount(0);

        tblData.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int pos = tblData.rowAtPoint(e.getPoint());
                txtId.setText(tblData.getValueAt(pos, 0).toString());
                cboEmp.setSelectedItem(tblData.getValueAt(pos, 1).toString());
                txtType.setText(tblData.getValueAt(pos, 2).toString());
                txtStart.setText(tblData.getValueAt(pos, 3).toString());
                txtEnd.setText(tblData.getValueAt(pos, 4).toString());

                btnDel.setEnabled(true);
                btnEdit.setEnabled(true);
                btnSave.setEnabled(false);
            }
        });
        loadCombobox();
        fillData();
        disableTextbox();
        disableButton();
    }

    private void loadCombobox() {
        try {
            cboEmpModel = new DefaultComboBoxModel<>();
            var allEmpl = EmployeeDAO.getInstance().getAllEmployeNotHaveContract();
            for (Employee employee : allEmpl) {
                cboEmpModel.addElement(employee.getId() + " - " + employee.getFullName() + " - " + employee.getPosition());
            }
            cboEmp.setModel(cboEmpModel);
        } catch (Exception ex) {
            Logger.getLogger(ContractPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void disableButton() {
        btnAdd.setEnabled(true);

        btnEdit.setEnabled(false);
        btnDel.setEnabled(false);
        btnSave.setEnabled(false);
    }

    private void enableButton() {
        btnAdd.setEnabled(false);

        btnEdit.setEnabled(false);
        btnDel.setEnabled(false);
        btnSave.setEnabled(true);
    }

    private void fillData() {
        // TODO Auto-generated method stub
        tableModel.setRowCount(0);
        try {
            for (Contract b : ContractDAO.getInstance().getAll()) {
                Object data[] = new Object[10];
                data[0] = b.getId();
                data[1] = b.getFullName();
                data[2] = b.getContractType();
                data[3] = b.getStartDate();
                data[4] = b.getEndDate();
                tableModel.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
        // TODO Auto-generated catch block
    }

    private void reload() {
        fillData();
        disableButton();
        disableTextbox();
        clearTextbox();
        loadCombobox();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTop = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEnd = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        txtType = new javax.swing.JTextField();
        txtStart = new javax.swing.JTextField();
        cboEmp = new javax.swing.JComboBox<>();
        btnReload = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        scrollData = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(893, 600));
        setMinimumSize(new java.awt.Dimension(893, 600));
        setPreferredSize(new java.awt.Dimension(893, 600));
        setLayout(new java.awt.BorderLayout());

        pnTop.setBackground(new java.awt.Color(247, 184, 139));
        pnTop.setPreferredSize(new java.awt.Dimension(893, 230));
        pnTop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Ngày kết thúc:");
        pnTop.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 100, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã hợp đồng:");
        pnTop.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 100, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Nhân viên:");
        pnTop.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Loại hợp đồng:");
        pnTop.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 100, 30));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày bắt đầu:");
        pnTop.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 100, 30));
        pnTop.add(txtEnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, 290, 40));
        pnTop.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 290, 40));
        pnTop.add(txtType, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 290, 40));
        pnTop.add(txtStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 290, 40));

        pnTop.add(cboEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 290, 40));

        btnReload.setText("Làm mới");
        btnReload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnReloadMouseClicked(evt);
            }
        });
        pnTop.add(btnReload, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 130, 40));

        btnAdd.setText("Thêm");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });
        pnTop.add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 130, 40));

        btnEdit.setText("Sửa");
        btnEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditMouseClicked(evt);
            }
        });
        pnTop.add(btnEdit, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 130, 40));

        btnDel.setText("Xóa");
        btnDel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDelMouseClicked(evt);
            }
        });
        pnTop.add(btnDel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 130, 40));

        btnSave.setText("Lưu");
        btnSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSaveMouseClicked(evt);
            }
        });
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        pnTop.add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, 130, 40));

        add(pnTop, java.awt.BorderLayout.NORTH);

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hợp đồng", "Nhân viên", "Loại hợp đồng", "Ngày bắt đầu", "Ngày kết thúc"
            }
        ));
        scrollData.setViewportView(tblData);
        if (tblData.getColumnModel().getColumnCount() > 0) {
            tblData.getColumnModel().getColumn(0).setResizable(false);
            tblData.getColumnModel().getColumn(1).setResizable(false);
            tblData.getColumnModel().getColumn(2).setResizable(false);
            tblData.getColumnModel().getColumn(3).setResizable(false);
            tblData.getColumnModel().getColumn(4).setResizable(false);
        }

        add(scrollData, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
        // TODO add your handling code here:
        isAdd = true;
        enableButton();
        enableTextbox();
        clearTextbox();
        txtType.requestFocus();
    }//GEN-LAST:event_btnAddMouseClicked
    private int getEmployeeId() {
        String text = cboEmp.getSelectedItem().toString();
        var rs = text.split("-");
        return Integer.parseInt(rs[0].trim());
    }
    private void btnSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveMouseClicked
                   // Kiểm tra dữ liệu trống cho các thuộc tính khác
            if (txtType.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Loại hợp đồng không được để trống", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }



            if (txtStart.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu không được để trống", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (txtEnd.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Ngày kết thúc không được để trống", "Lỗi", JOptionPane.WARNING_MESSAGE);
                return;
            }

           

// TODO add your handling code here:
        try {
            var item = new Contract();
            item.setId(txtId.getText().isEmpty() ? 0 : Integer.valueOf(txtId.getText().trim()));
            item.setEmployeeId(cboEmp.getSelectedItem() != null ? getEmployeeId() : 0);
            item.setContractType(txtType.getText());
            item.setStartDate(txtStart.getText());
            item.setEndDate(txtEnd.getText());
            if (isAdd) {
                if (ContractDAO.getInstance().add(item)) {
                    JOptionPane.showMessageDialog(null, "Thêm thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại", "Lỗi", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                if (ContractDAO.getInstance().update(item)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại", "Lỗi", JOptionPane.WARNING_MESSAGE);
                }
            }
            reload();
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveMouseClicked

    private void btnEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditMouseClicked
        // TODO add your handling code here:
        isAdd = false;
        enableButton();
        enableTextbox();
        cboEmp.setEnabled(false);
        txtType.requestFocus();
    }//GEN-LAST:event_btnEditMouseClicked

    private void btnDelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelMouseClicked
        // TODO add your handling code here:
        if (!txtId.getText().isEmpty()) {
            int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Xác nhận",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                ContractDAO.getInstance().delete(Integer.parseInt(txtId.getText()));
                reload();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn hợp đồng muốn xóa", "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnDelMouseClicked

    private void btnReloadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReloadMouseClicked
        // TODO add your handling code here:
        reload();
    }//GEN-LAST:event_btnReloadMouseClicked

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveActionPerformed

    private javax.swing.DefaultComboBoxModel<String> cboEmpModel;
    private javax.swing.table.DefaultTableModel tableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cboEmp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel pnTop;
    private javax.swing.JScrollPane scrollData;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtEnd;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtStart;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables
}
