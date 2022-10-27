/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ticketsystem;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JOptionPane;

/**
 *
 * @author umars
 */
public class BookingAppView extends javax.swing.JFrame implements Observer {

    /**
     * Creates new form BookingAppView
     */
    public BookingAppView() {
        initComponents();
        this.setSize(loginPanel.getSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createAccountPanel = new javax.swing.JPanel();
        jtfUName = new javax.swing.JTextField();
        jlUName = new javax.swing.JLabel();
        jtfNewPass = new javax.swing.JPasswordField();
        jtfConfirmPass = new javax.swing.JPasswordField();
        jlNewPass = new javax.swing.JLabel();
        jlConfirmPass = new javax.swing.JLabel();
        jbConfirm = new javax.swing.JButton();
        jlFName = new javax.swing.JLabel();
        jtfFName = new javax.swing.JTextField();
        jlHeader = new javax.swing.JLabel();
        homePanel = new javax.swing.JPanel();
        jlCurrentUser = new javax.swing.JLabel();
        jbCreateBooking = new javax.swing.JButton();
        jbViewBookings = new javax.swing.JButton();
        jbMembership = new javax.swing.JButton();
        jbAccSettings = new javax.swing.JButton();
        jbLogOut = new javax.swing.JButton();
        accSettingsPanel = new javax.swing.JPanel();
        jlSettingsUName = new javax.swing.JLabel();
        jlSettingsFName = new javax.swing.JLabel();
        jlChangePass = new javax.swing.JLabel();
        jlSettingsNPass = new javax.swing.JLabel();
        jlSettingsCNPass = new javax.swing.JLabel();
        jlSettingsCPass = new javax.swing.JLabel();
        jtfSettingsUName = new javax.swing.JTextField();
        jtfSettingsFName = new javax.swing.JTextField();
        jbChangePass = new javax.swing.JButton();
        jpfSettingsCPass = new javax.swing.JPasswordField();
        jpfSettingsNPass = new javax.swing.JPasswordField();
        jpfSettingsCNPass = new javax.swing.JPasswordField();
        jbSettingsBack = new javax.swing.JButton();
        jlSettingsPayMethod = new javax.swing.JLabel();
        jbSettingsPayMethod = new javax.swing.JButton();
        addPayAccountPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jtfPAEmail = new javax.swing.JTextField();
        jbAddPayAcc = new javax.swing.JButton();
        jbBackToSettings = new javax.swing.JButton();
        loginPanel = new javax.swing.JPanel();
        jtfUsername = new javax.swing.JTextField();
        jlUsername = new javax.swing.JLabel();
        jlPassword = new javax.swing.JLabel();
        jbLogin = new javax.swing.JButton();
        jtfPassword = new javax.swing.JPasswordField();
        jbCreateAccount = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jlInvalidPassword = new javax.swing.JLabel();

        jtfUName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfUNameActionPerformed(evt);
            }
        });

        jlUName.setText("New Username");

        jlNewPass.setText("New Password");

        jlConfirmPass.setText("Confirm Password");

        jbConfirm.setText("Confirm & Create Account");
        jbConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbConfirmActionPerformed(evt);
            }
        });

        jlFName.setText("Full Name");

        jlHeader.setText("Enter your new account details.");

        javax.swing.GroupLayout createAccountPanelLayout = new javax.swing.GroupLayout(createAccountPanel);
        createAccountPanel.setLayout(createAccountPanelLayout);
        createAccountPanelLayout.setHorizontalGroup(
            createAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createAccountPanelLayout.createSequentialGroup()
                .addGroup(createAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createAccountPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(createAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlNewPass)
                            .addComponent(jlUName)
                            .addComponent(jlFName))
                        .addGap(18, 18, 18)
                        .addGroup(createAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtfFName, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(createAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jtfNewPass, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                .addComponent(jtfUName))))
                    .addGroup(createAccountPanelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jlHeader))
                    .addGroup(createAccountPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jlConfirmPass)
                        .addGap(18, 18, 18)
                        .addComponent(jtfConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createAccountPanelLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jbConfirm)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        createAccountPanelLayout.setVerticalGroup(
            createAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createAccountPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jlHeader)
                .addGap(18, 18, 18)
                .addGroup(createAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlFName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfUName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlUName))
                .addGap(18, 18, 18)
                .addGroup(createAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNewPass)
                    .addComponent(jtfNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlConfirmPass)
                    .addComponent(jtfConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jbConfirm)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jlCurrentUser.setText("Signed in as: ");

        jbCreateBooking.setText("Create a Booking");

        jbViewBookings.setText("View Bookings");

        jbMembership.setText("Membership");

        jbAccSettings.setText("Account Settings");

        jbLogOut.setText("Log out");

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jlCurrentUser))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jbLogOut))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbViewBookings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbCreateBooking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbAccSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbMembership, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, homePanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jbCreateBooking)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbViewBookings)
                .addGap(108, 108, 108)
                .addComponent(jbMembership)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbAccSettings)
                .addGap(60, 60, 60)
                .addComponent(jlCurrentUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbLogOut)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jlSettingsUName.setText("Username");

        jlSettingsFName.setText("Full name");

        jlChangePass.setText("Change password");

        jlSettingsNPass.setText("New password");

        jlSettingsCNPass.setText("Confirm new password");

        jlSettingsCPass.setText("Current password");

        jtfSettingsUName.setEditable(false);

        jtfSettingsFName.setEditable(false);

        jbChangePass.setText("Change password");

        jbSettingsBack.setText("Back to main menu");

        jlSettingsPayMethod.setText("Saved payment method: ");

        jbSettingsPayMethod.setText("Add/Remove Payment Method");

        javax.swing.GroupLayout accSettingsPanelLayout = new javax.swing.GroupLayout(accSettingsPanel);
        accSettingsPanel.setLayout(accSettingsPanelLayout);
        accSettingsPanelLayout.setHorizontalGroup(
            accSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accSettingsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(accSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, accSettingsPanelLayout.createSequentialGroup()
                            .addComponent(jlSettingsUName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jtfSettingsUName, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, accSettingsPanelLayout.createSequentialGroup()
                            .addGap(90, 90, 90)
                            .addComponent(jlChangePass))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, accSettingsPanelLayout.createSequentialGroup()
                            .addComponent(jlSettingsFName)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jtfSettingsFName, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, accSettingsPanelLayout.createSequentialGroup()
                            .addGroup(accSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jlSettingsNPass)
                                .addComponent(jlSettingsCNPass)
                                .addComponent(jlSettingsCPass))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(accSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jpfSettingsCPass)
                                .addComponent(jpfSettingsNPass)
                                .addComponent(jpfSettingsCNPass)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, accSettingsPanelLayout.createSequentialGroup()
                            .addGap(74, 74, 74)
                            .addGroup(accSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jbSettingsBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jbChangePass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jlSettingsPayMethod)
                    .addComponent(jbSettingsPayMethod))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        accSettingsPanelLayout.setVerticalGroup(
            accSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accSettingsPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(accSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlSettingsUName)
                    .addComponent(jtfSettingsUName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(accSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlSettingsFName)
                    .addComponent(jtfSettingsFName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jlSettingsPayMethod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbSettingsPayMethod)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jlChangePass)
                .addGap(18, 18, 18)
                .addGroup(accSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlSettingsCPass)
                    .addComponent(jpfSettingsCPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(accSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlSettingsNPass)
                    .addComponent(jpfSettingsNPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(accSettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlSettingsCNPass)
                    .addComponent(jpfSettingsCNPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbChangePass)
                .addGap(18, 18, 18)
                .addComponent(jbSettingsBack)
                .addGap(14, 14, 14))
        );

        jLabel1.setText("Pay account details");

        jLabel2.setText("Email address");

        jLabel3.setText("Password");

        jbAddPayAcc.setText("Add Pay account");

        jbBackToSettings.setText("Back to account settings");

        javax.swing.GroupLayout addPayAccountPanelLayout = new javax.swing.GroupLayout(addPayAccountPanel);
        addPayAccountPanel.setLayout(addPayAccountPanelLayout);
        addPayAccountPanelLayout.setHorizontalGroup(
            addPayAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPayAccountPanelLayout.createSequentialGroup()
                .addGroup(addPayAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPayAccountPanelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(jLabel1))
                    .addGroup(addPayAccountPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jtfPAEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addPayAccountPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(addPayAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jbAddPayAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jbBackToSettings)))
                    .addGroup(addPayAccountPanelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        addPayAccountPanelLayout.setVerticalGroup(
            addPayAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPayAccountPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addPayAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jtfPAEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(addPayAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jbAddPayAcc)
                .addGap(18, 18, 18)
                .addComponent(jbBackToSettings)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jlUsername.setText("Username");

        jlPassword.setText("Password");

        jbLogin.setText("Login");

        jbCreateAccount.setText("Create Account");
        jbCreateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCreateAccountActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Book A Ferry");

        jlInvalidPassword.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlUsername)
                    .addComponent(jlPassword))
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(jtfUsername))
                .addGap(59, 59, 59))
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbCreateAccount, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(jbLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlInvalidPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlPassword)
                    .addComponent(jtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlInvalidPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbCreateAccount)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 238, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 173, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbCreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCreateAccountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbCreateAccountActionPerformed

    private void jbConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbConfirmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbConfirmActionPerformed

    private void jtfUNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfUNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfUNameActionPerformed

    public void addActionListener(ActionListener al) {
        // Add action listeners to login/createAccount panel buttons.
        jbLogin.addActionListener(al);
        jbCreateAccount.addActionListener(al);
        jbConfirm.addActionListener(al);
        
        // Add action listeners to home panel buttons.
        jbCreateBooking.addActionListener(al);
        jbViewBookings.addActionListener(al);
        jbMembership.addActionListener(al);
        jbAccSettings.addActionListener(al);
        jbLogOut.addActionListener(al);
        
        // Add action listener to accSettingsPanel buttons.
        jbChangePass.addActionListener(al);
        jbSettingsBack.addActionListener(al);
        jbSettingsPayMethod.addActionListener(al);
        
        // Add action listener to addPayAccountPanel buttons.
        jbAddPayAcc.addActionListener(al);
        jbBackToSettings.addActionListener(al);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BookingAppView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookingAppView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookingAppView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookingAppView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BookingAppView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel accSettingsPanel;
    private javax.swing.JPanel addPayAccountPanel;
    private javax.swing.JPanel createAccountPanel;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JButton jbAccSettings;
    private javax.swing.JButton jbAddPayAcc;
    private javax.swing.JButton jbBackToSettings;
    private javax.swing.JButton jbChangePass;
    private javax.swing.JButton jbConfirm;
    private javax.swing.JButton jbCreateAccount;
    private javax.swing.JButton jbCreateBooking;
    private javax.swing.JButton jbLogOut;
    private javax.swing.JButton jbLogin;
    private javax.swing.JButton jbMembership;
    private javax.swing.JButton jbSettingsBack;
    private javax.swing.JButton jbSettingsPayMethod;
    private javax.swing.JButton jbViewBookings;
    private javax.swing.JLabel jlChangePass;
    private javax.swing.JLabel jlConfirmPass;
    private javax.swing.JLabel jlCurrentUser;
    private javax.swing.JLabel jlFName;
    private javax.swing.JLabel jlHeader;
    private javax.swing.JLabel jlInvalidPassword;
    private javax.swing.JLabel jlNewPass;
    private javax.swing.JLabel jlPassword;
    private javax.swing.JLabel jlSettingsCNPass;
    private javax.swing.JLabel jlSettingsCPass;
    private javax.swing.JLabel jlSettingsFName;
    private javax.swing.JLabel jlSettingsNPass;
    private javax.swing.JLabel jlSettingsPayMethod;
    private javax.swing.JLabel jlSettingsUName;
    private javax.swing.JLabel jlUName;
    private javax.swing.JLabel jlUsername;
    public javax.swing.JPasswordField jpfSettingsCNPass;
    public javax.swing.JPasswordField jpfSettingsCPass;
    public javax.swing.JPasswordField jpfSettingsNPass;
    public javax.swing.JPasswordField jtfConfirmPass;
    public javax.swing.JTextField jtfFName;
    public javax.swing.JPasswordField jtfNewPass;
    public javax.swing.JTextField jtfPAEmail;
    public javax.swing.JPasswordField jtfPassword;
    private javax.swing.JTextField jtfSettingsFName;
    private javax.swing.JTextField jtfSettingsUName;
    public javax.swing.JTextField jtfUName;
    public javax.swing.JTextField jtfUsername;
    private javax.swing.JPanel loginPanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        // Take in an Output object, update the view based on Output.action.
        Output argument = (Output) arg;
        switch (argument.action) {
            case Output.BACK_TO_HOME:
                setContentPane(homePanel);
                setSize(homePanel.getPreferredSize());
                break;
            case Output.CHANGE_PASSWORD_SUCCESS:
                JOptionPane.showMessageDialog(null, "Your password has successfuly been changed.", "Success", JOptionPane.PLAIN_MESSAGE);
                jpfSettingsCPass.setText("");
                jpfSettingsNPass.setText("");
                jpfSettingsCNPass.setText("");
                break;
            case Output.CREATE_ACCOUNT:
                setContentPane(createAccountPanel);
                setSize(createAccountPanel.getPreferredSize());
                break;
            case Output.CREATE_ACCOUNT_SUCCESS:
                JOptionPane.showMessageDialog(null, "Your account has successfully been created.", "Success", JOptionPane.PLAIN_MESSAGE);
                jlCurrentUser.setText("Signed in as: "+argument.outputString1);
                setContentPane(homePanel);
                setSize(homePanel.getPreferredSize());
                break;
            case Output.INVALID_LOGIN_CREDENTIALS:
                jlInvalidPassword.setText("Invalid login credentials. Try again.");
                break;
            case Output.INVALID_NAME:
                JOptionPane.showMessageDialog(null, "Full name must only contain letters and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case Output.INVALID_NAME_LENGTH:
                JOptionPane.showMessageDialog(null, "Full name must be between 5 and 40 characters in length.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case Output.INVALID_NEW_PASSWORD:
                JOptionPane.showMessageDialog(null, "Passwords must be 8 to 20 characters in length and must not contain spaces.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case Output.INVALID_USERNAME:
                JOptionPane.showMessageDialog(null, "Username must be 5 to 15 characters in length and must not contain spaces.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case Output.LOG_IN_SUCCESS:
                jlCurrentUser.setText("Signed in as: "+argument.outputString1);
                jtfPassword.setText("");
                jlInvalidPassword.setText("");
                setContentPane(homePanel);
                setSize(homePanel.getPreferredSize());
                break;
            case Output.LOG_OUT:
                setContentPane(loginPanel);
                setSize(loginPanel.getPreferredSize());
                break;
            case Output.NEW_PASSWORD_MISMATCH:
                JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case Output.USERNAME_EXISTS:
                JOptionPane.showMessageDialog(null, "The username you entered is already in use.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case Output.VIEW_ACCOUNT_SETTINGS:
                jtfSettingsUName.setText(argument.outputString1);
                jtfSettingsFName.setText(argument.outputString2);
                jlSettingsPayMethod.setText(argument.outputString3);
                jpfSettingsCPass.setText("");
                jpfSettingsNPass.setText("");
                jpfSettingsCNPass.setText("");
                setContentPane(accSettingsPanel);
                setSize(accSettingsPanel.getPreferredSize());
                break;
            case Output.WRONG_CURRENT_PASSWORD:
                JOptionPane.showMessageDialog(null, "The current password you entered does not match your actual current password.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}
