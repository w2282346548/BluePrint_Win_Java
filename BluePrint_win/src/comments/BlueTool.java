/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comments;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.swing.JOptionPane;

/**
 *
 * @author wangjie
 */
public class BlueTool extends javax.swing.JFrame {

    /**
     * Service serial-port UUID
     */
    protected UUID defaultUUID = new UUID(0x1101);

    /**
     * Local bluetooth device.
     */
    private LocalDevice local;

    /**
     * Agent responsible for the discovery of bluetooth devices.
     */
    private DiscoveryAgent agent;

    /**
     * Output stream used to send information to the bluetooth.
     */
    private DataOutputStream dout;

    /**
     * Bluetooth Connection.
     */
    private StreamConnection conn;

    /**
     * List of bluetooth devices of interest. (name starting with the defined
     * token)
     */
    private Vector<RemoteDevice> devices;

    /**
     * Services of interest (defined in UUID) of each device.
     */
    private Vector<ServiceRecord> services;

    public BlueTool() {
        initComponents();
        if (!initLocalDevice()) {//if no bluetooth
            JOptionPane.showMessageDialog(null, "the bluetool isn't useful");
            btnScanBlueTooth.setEnabled(false);
            btnPrint.setEnabled(false);
            btnPrintTestPage.setEnabled(false);
            btnPrintY.setEnabled(false);
            btnPrintQRCode.setEnabled(false);
            btnExit.setEnabled(true);

        } else {//btn can use
            btnScanBlueTooth.setEnabled(true);
            btnPrint.setEnabled(false);
            btnPrintTestPage.setEnabled(false);
            btnPrintY.setEnabled(false);
            btnPrintQRCode.setEnabled(false);
            btnExit.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnScanBlueTooth = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMsg = new javax.swing.JTextArea();
        btnPrint = new javax.swing.JButton();
        btnPrintTestPage = new javax.swing.JButton();
        btnPrintY = new javax.swing.JButton();
        btnPrintQRCode = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbDevice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnScanBlueTooth.setText("scan bluetooth");
        btnScanBlueTooth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScanBlueToothActionPerformed(evt);
            }
        });

        txtMsg.setColumns(20);
        txtMsg.setRows(5);
        jScrollPane1.setViewportView(txtMsg);

        btnPrint.setText("print");
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnPrintTestPage.setText("PrintTestPage");
        btnPrintTestPage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintTestPageActionPerformed(evt);
            }
        });

        btnPrintY.setText("PrintY");
        btnPrintY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintYActionPerformed(evt);
            }
        });

        btnPrintQRCode.setText("PrintQRCode");
        btnPrintQRCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintQRCodeActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel1.setText("Current connected device:");

        lbDevice.setText("here is no blueprint ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnScanBlueTooth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrintTestPage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                    .addComponent(btnPrintY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPrintQRCode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lbDevice, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbDevice))
                .addGap(18, 18, 18)
                .addComponent(btnScanBlueTooth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPrint)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrintTestPage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrintY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPrintQRCode)
                .addGap(36, 36, 36)
                .addComponent(btnExit)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnPrintYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintYActionPerformed
        // TODO add your handling code here:
        byte[] cmd = { 0x1D, 0x6B, 0x41, 0x0C, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x31, 0x32, 0x38 };
        sendMessgae(cmd);
    }//GEN-LAST:event_btnPrintYActionPerformed

    private void btnScanBlueToothActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScanBlueToothActionPerformed
        btnScanBlueTooth.setEnabled(false);

        final DeviceList list = new DeviceList(local);

        OnClickResult linster = new OnClickResult() {
            @Override
            public void onClickItem(RemoteDevice device, Vector<ServiceRecord> service) {
                // services=service;
                services = service;
                // MessagerBoxShow(device.getBluetoothAddress());
                list.dispose();
                btnScanBlueTooth.setEnabled(true);
                btnPrint.setEnabled(true);
                btnPrintTestPage.setEnabled(true);
                btnPrintY.setEnabled(true);
                btnPrintQRCode.setEnabled(true);
                if((services.size()<=0)){
                 //   MessagerBoxShow("dont find connect type");
                    lbDevice.setText("here is no blueprint can connection");     
                }else{
                    try {
                        lbDevice.setText(device.getFriendlyName(false)+":"+device.getBluetoothAddress());
                    } catch (IOException ex) {
                        lbDevice.setText("no name"+":"+device.getBluetoothAddress());
                    }
                }
            }

        };
        list.setLinster(linster);
        list.setVisible(true);
    }//GEN-LAST:event_btnScanBlueToothActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        // TODO add your handling code here:
    //    MessagerBoxShow("start");
        btnPrint.setEnabled(false);
        String msg = txtMsg.getText()+"\r\n";
        byte[] b;
        try {
            b=msg.getBytes("big5");
        } catch (UnsupportedEncodingException ex) {
          b=null;
        }
        sendMessgae(b);
        btnPrint.setEnabled(true);
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnPrintTestPageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintTestPageActionPerformed
        // TODO add your handling code here:
        btnPrintTestPage.setEnabled(false);
        byte[] cmd = new byte[3];
            cmd[0] = 0x1b;
            cmd[1] = 0x21;
            cmd[2] |= 0x10;
         sendMessgae(cmd);
         String msg = "Congratulations！\n";
         sendMessgae(String2Big(msg));
         cmd[2] &= 0xEF; //取消倍高、倍宽模式
          sendMessgae(cmd);
          msg = "  You have successfully connected our Bluetooth printer!\n"
                    + "  The company is a professional engaged in R & D, production, sales of commercial paper printers and bar code scanning equipment in one of the high-tech enterprises.\n";
       
          sendMessgae(String2Big(msg));
        btnPrintTestPage.setEnabled(true);
        
    }//GEN-LAST:event_btnPrintTestPageActionPerformed

    private void btnPrintQRCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintQRCodeActionPerformed
        // TODO add your handling code here:
        btnPrintQRCode.setEnabled(false);
        byte[] cmd = { 0x1b, 0x5a, 0x01, 0x03, 0x08, 0x09, 0x00, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x0A };
        sendMessgae(cmd);
        btnPrintQRCode.setEnabled(true);
    }//GEN-LAST:event_btnPrintQRCodeActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(BlueTool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BlueTool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BlueTool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BlueTool.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BlueTool().setVisible(true);
//            }
//        });
//    }
    private boolean initLocalDevice() {
        boolean isReady = false;
        try {
            local = LocalDevice.getLocalDevice();
            local.setDiscoverable(DiscoveryAgent.GIAC);
            //_discoveryAgent = localDevice.getDiscoveryAgent();
            // JOptionPane.showMessageDialog(null, localDevice.getBluetoothAddress().toString());
            // JOptionPane.showMessageDialog(null, LocalDevice.getProperty("bluetooth.api.version"));
            //ConfigDal.getConfigInfo();
            isReady = true;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + "the bluetool isn't useful");
        }
        return isReady;

    }

    private void MessagerBoxShow(String str) {
        JOptionPane.showMessageDialog(null, str);
    }

    public void sendMessgae(String msg) {
        if (services.size()>0) {
            String url = services.get(0).getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            conn = null;
            try {
                MessagerBoxShow("Sending command to " + url);

                conn = (StreamConnection) Connector.open(url);
                dout = new DataOutputStream(conn.openOutputStream());

                dout.writeUTF(msg);
               
                MessagerBoxShow(String.format("Sending %s", msg));

                dout.flush();
                dout.close();
                conn.close();

                MessagerBoxShow("Sent. Connection Closed.");

            } catch (Exception e) {
                MessagerBoxShow("Failed to connect to " + url);
                e.printStackTrace();
            }
        }else{
        MessagerBoxShow("Failed to connect to ");
                
        }
    }
    public void sendMessgae(byte[] b) {
        if (services.size()>0)  {
            String url = services.get(0).getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            conn = null;
            try {
                MessagerBoxShow("Sending command to " + url);

                conn = (StreamConnection) Connector.open(url);
                dout = new DataOutputStream(conn.openOutputStream());

                dout.write(b);
               
          //      MessagerBoxShow(String.format("Sending %s", msg));

                dout.flush();
                dout.close();
                conn.close();

              //  MessagerBoxShow("Sent. Connection Closed.");

            } catch (Exception e) {
              //  MessagerBoxShow("Failed to connect to " + url);
                e.printStackTrace();
            }
        }else{
        MessagerBoxShow("Failed to connect to ");
                
        }
    }
    public byte[] String2Big(String msg){       
        try {
            return msg.getBytes("Big5");
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }
    
  /*  
    public void sendMessage(String msg)
    {
        if (services.size()>0) {
            String url = services.get(0).getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            conn = null;
            try {
                MessagerBoxShow("Sending command to " + url);
                conn = (StreamConnection) Connector.open(url);
               // dout = new DataOutputStream(conn.openOutputStream());
               // OutputStreamWriter write=new OutputStreamWriter(dout,"GBK");
                dout.write(msg.getBytes("BIG5"));
             //   write.write(msg);
               
               // MessagerBoxShow(String.format("Sending %s", msg));

             //   write.flush();
                dout.flush();
             //   write.close();
                dout.close();
                conn.close();

                MessagerBoxShow("Sent. Connection Closed.");

            } catch (Exception e) {
                MessagerBoxShow("Failed to connect to " + url);
                e.printStackTrace();
            }
        }else{
        MessagerBoxShow("Failed to connect to ");
                
        }
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnPrintQRCode;
    private javax.swing.JButton btnPrintTestPage;
    private javax.swing.JButton btnPrintY;
    private javax.swing.JButton btnScanBlueTooth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDevice;
    private javax.swing.JTextArea txtMsg;
    // End of variables declaration//GEN-END:variables
}
