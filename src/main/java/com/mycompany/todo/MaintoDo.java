/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.todo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author jleeg
 */
public class MaintoDo extends javax.swing.JFrame {

    /**
     * Creates new form MaintoDo
     */
    JButton delButton;
    JCheckBox checkbox;
    JLabel tasklabel, desclabel, datelabel, timelabel;
    Map<JPanel, GridBagConstraints> panelConstraintsMap = new HashMap<>();
    ArrayList<JPanel> AllTasks = new ArrayList<>();
    
    public MaintoDo() {
        initComponents();
        jLabel4.setVisible(false);
        complete.setVisible(false);
        jTextField2.setVisible(false);
        getContentPane().setBackground(new java.awt.Color(30,30,30));
        titlehead.setOpaque(true);
        titlehead.setBackground(new java.awt.Color(30, 30, 30));
        MainPage.setBackground(new java.awt.Color(30, 30, 30));
                
    }
    
    public static MaintoDo toDoFrame;
    
    public void deleteTasks() {
        ArrayList<Component> ComponentstoRemove = new ArrayList<>();
        for (JPanel taskContainer : AllTasks) {
            for (Component taskComponent : taskContainer.getComponents()) {
                if (taskComponent instanceof JCheckBox checkBox) {
                    if (checkBox.isSelected()) {
                        ComponentstoRemove.add(taskContainer);
                        break;
                        // tasklabel = new JLabel(tasks.remove(0));
                    }
                }
            }
        }
        for (int i = ComponentstoRemove.size() - 1; i >= 0; i--) {
            taskPanel.remove(ComponentstoRemove.get(i));
        }
        taskPanel.revalidate();
        taskPanel.repaint();
    }
    public void alltasks(){
        taskPanel.removeAll();
        for(JPanel panel : AllTasks){
            GridBagConstraints gbc = panelConstraintsMap.get(panel);
            if(gbc!=null){
                taskPanel.add(panel, gbc);
            }else{
                taskPanel.add(panel);
            }
        }   
    }
    public void todaytasks() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate today = LocalDate.now();
        ArrayList<JPanel> panelsToKeep = new ArrayList<>();

        for (JPanel taskContainer : AllTasks) {
            boolean hastodaytask = false;
            for (Component taskComponent : taskContainer.getComponents()) {
                if (taskComponent instanceof JLabel dateLabel && !dateLabel.isVisible()) {
                    try {
                        LocalDate taskdate = LocalDate.parse(dateLabel.getText(), format);
                        String date = taskdate.format(format);
                        if (taskdate.equals(today)) {
                            hastodaytask = true;
                            break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (hastodaytask) {
                panelsToKeep.add(taskContainer);
            }
        }
        taskPanel.removeAll();
        for (JPanel panel : panelsToKeep) {
            GridBagConstraints gbc = panelConstraintsMap.get(panel);
            if (gbc != null) {
                taskPanel.add(panel, gbc);
            } else {
                taskPanel.add(panel);
            }
        }
        taskPanel.revalidate();
        taskPanel.repaint();
    }
    public void addTask(ArrayList<String> tasks){
        tasklabel = new JLabel(tasks.get(0));
        desclabel = new JLabel(tasks.get(1));
        datelabel = new JLabel(tasks.get(2));
        timelabel = new JLabel(tasks.get(3));
        tasklabel.setForeground(Color.WHITE);
        timelabel.setForeground(Color.WHITE);datelabel.setForeground(Color.WHITE);desclabel.setForeground(Color.WHITE);

        datelabel.setVisible(false);
        //Format date from generic dd-mm-yyyy to an readable format
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate taskdate = LocalDate.parse(datelabel.getText(), format);
        DayOfWeek dow = taskdate.getDayOfWeek();
        Integer dom = taskdate.getDayOfMonth();
        Month Month = taskdate.getMonth();
        String month = Month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        String dayofweek = dow.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        JLabel Displaydate = new JLabel();Displaydate.setForeground(Color.WHITE);
        Displaydate.setText(dayofweek +" "+ month + ", "+ dom);
        
        // Create a task container panel with BoxLayout.X_AXIS
        JPanel taskContainer = new JPanel();
        taskContainer.setBackground(new java.awt.Color(25, 37, 56));
        taskContainer.setLayout(new BoxLayout(taskContainer, BoxLayout.X_AXIS));
        taskContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));        
        taskContainer.setFont(new java.awt.Font("Tahoma", Font.PLAIN, 12));
        checkbox = new JCheckBox();
        delButton = new JButton();
        delButton.setBounds(100, 100, 60, 60);
        checkbox.setBounds(100, 100, 60, 60);
        delButton.setText("DEL");
        var text = ""; checkbox.setForeground(Color.WHITE);
        checkbox.setText(text);
    
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = taskPanel.getComponentCount();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(0,1,1,0 );
       
        JPanel textContainer = new JPanel();
        JPanel timeContainer = new JPanel();
        timeContainer.setLayout(new BoxLayout(timeContainer, BoxLayout.X_AXIS));
        textContainer.setLayout(new BoxLayout(textContainer, BoxLayout.Y_AXIS));
        textContainer.setBackground(new java.awt.Color(25, 37, 56));
        timeContainer.setBackground(new java.awt.Color(25, 37, 56));
        textContainer.add(Box.createHorizontalStrut(7));
        textContainer.add(tasklabel);
        textContainer.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
        textContainer.add(desclabel);
        timeContainer.add(timelabel);
        taskContainer.add(Box.createHorizontalStrut(5));        
        taskContainer.add(checkbox);
        taskContainer.add(Box.createHorizontalStrut(15));        
        taskContainer.add(textContainer);
        taskContainer.add(Box.createHorizontalStrut(35));   
        taskContainer.add(timeContainer);
        taskContainer.add(Box.createHorizontalStrut(15));        
        taskContainer.add(Displaydate);
        taskContainer.add(datelabel);
        taskContainer.add(Box.createHorizontalStrut(25));        
        
        taskPanel.add(taskContainer, gbc);
        panelConstraintsMap.put(taskContainer, gbc);    
        AllTasks.add(taskContainer);
        taskPanel.revalidate();
        taskPanel.repaint();

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
        tasks = new javax.swing.JButton();
        UpcomingBtn = new javax.swing.JButton();
        myday = new javax.swing.JButton();
        newlist = new javax.swing.JButton();
        MainPage = new javax.swing.JPanel();
        titlehead = new javax.swing.JLabel();
        complete = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        taskPanel = new javax.swing.JPanel();
        addtask = new javax.swing.JButton();
        Delete = new javax.swing.JButton();
        head2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("To Do");

        jPanel1.setBackground(new java.awt.Color(25, 37, 56));

        tasks.setBackground(new java.awt.Color(25, 37, 56));
        tasks.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tasks.setForeground(new java.awt.Color(255, 255, 255));
        tasks.setText("Tasks");
        tasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tasksActionPerformed(evt);
            }
        });
        tasks.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tasksKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tasksKeyReleased(evt);
            }
        });

        UpcomingBtn.setBackground(new java.awt.Color(25, 37, 56));
        UpcomingBtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        UpcomingBtn.setForeground(new java.awt.Color(255, 255, 255));
        UpcomingBtn.setText("Upcoming");
        UpcomingBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpcomingBtnActionPerformed(evt);
            }
        });

        myday.setBackground(new java.awt.Color(25, 37, 56));
        myday.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        myday.setForeground(new java.awt.Color(255, 255, 255));
        myday.setText("My Day");
        myday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mydayActionPerformed(evt);
            }
        });
        myday.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mydayKeyPressed(evt);
            }
        });

        newlist.setBackground(new java.awt.Color(25, 37, 56));
        newlist.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        newlist.setForeground(new java.awt.Color(255, 255, 255));
        newlist.setText("Add new list");
        newlist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newlistActionPerformed(evt);
            }
        });
        newlist.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newlistKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(myday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tasks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UpcomingBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(newlist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(tasks)
                .addGap(18, 18, 18)
                .addComponent(myday)
                .addGap(18, 18, 18)
                .addComponent(UpcomingBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(newlist)
                .addContainerGap())
        );

        MainPage.setForeground(new java.awt.Color(30, 30, 30));

        titlehead.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        titlehead.setForeground(new java.awt.Color(102, 153, 255));
        titlehead.setText("Tasks");

        complete.setText("Completed");

        jLabel4.setBackground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Filter");

        taskPanel.setBackground(new java.awt.Color(173, 216, 230));
        taskPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        taskPanel.setLayout(new java.awt.GridBagLayout());

        addtask.setBackground(new java.awt.Color(25, 37, 56));
        addtask.setForeground(new java.awt.Color(255, 255, 255));
        addtask.setText("Add Task");
        addtask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtaskActionPerformed(evt);
            }
        });

        Delete.setBackground(new java.awt.Color(25, 37, 56));
        Delete.setForeground(new java.awt.Color(255, 255, 255));
        Delete.setText("Del Task");
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });

        head2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        head2.setForeground(new java.awt.Color(255, 255, 255));
        head2.setText("Task List");

        jTextField2.setBackground(new java.awt.Color(173, 216, 230));
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(153, 153, 153));
        jTextField2.setText("Try typing  pay utilities by 5:00pm");
        jTextField2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout MainPageLayout = new javax.swing.GroupLayout(MainPage);
        MainPage.setLayout(MainPageLayout);
        MainPageLayout.setHorizontalGroup(
            MainPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPageLayout.createSequentialGroup()
                .addGroup(MainPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPageLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(MainPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(MainPageLayout.createSequentialGroup()
                                .addComponent(addtask)
                                .addGap(500, 500, 500)
                                .addComponent(Delete))
                            .addGroup(MainPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(complete, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(MainPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(taskPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField2)
                                    .addGroup(MainPageLayout.createSequentialGroup()
                                        .addComponent(head2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                                        .addComponent(jLabel4)
                                        .addGap(88, 88, 88))))))
                    .addGroup(MainPageLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(titlehead, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        MainPageLayout.setVerticalGroup(
            MainPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPageLayout.createSequentialGroup()
                .addComponent(titlehead)
                .addGap(18, 18, 18)
                .addGroup(MainPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(head2)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(complete, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(taskPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MainPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addtask)
                    .addComponent(Delete))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(MainPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(MainPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addtaskActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtaskActionPerformed
        Input input = new Input();
        input.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_addtaskActionPerformed

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
        // TODO add your handling code here:
        int confirm = JOptionPane.showConfirmDialog(this, "Delete Selected Tasks!");
        if(confirm ==JOptionPane.YES_OPTION){
                deleteTasks();
        }
    }//GEN-LAST:event_DeleteActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
        if(jTextField2.getText().equals("Try typing  pay utilities by 5:00pm")){
            jTextField2.setBackground(new java.awt.Color(0,0,0));
            jTextField2.setText("");
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusGained
        // TODO add your handling code here:
        if(jTextField2.getText().equals("Try typing  pay utilities by 5:00pm")){
            jTextField2.setCaretPosition(0);
        }
    }//GEN-LAST:event_jTextField2FocusGained

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        // TODO add your handling code here:
        if(jTextField2.getText().equals("")){
            jTextField2.setBackground(new java.awt.Color(153,153,153));
            jTextField2.setText("Try typing  pay utilities by 5:00pm");
        }
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        String newtask = jTextField2.getText();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void newlistKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newlistKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_newlistKeyPressed

    private void newlistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newlistActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_newlistActionPerformed

    private void mydayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mydayKeyPressed
        // TODO add your handling code here:
        myday.setBackground(new java.awt.Color(173,216,230));
    }//GEN-LAST:event_mydayKeyPressed

    private void mydayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mydayActionPerformed
        // TODO add your handling code here:
        todaytasks();
        jTextField2.setVisible(true);
        titlehead.setText("My Day");
        LocalDate today = LocalDate.now();
        DayOfWeek dayOweek = today.getDayOfWeek();
        Month month = today.getMonth();
        int dom = today.getDayOfMonth();
        String dow = dayOweek.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        String getmonth = month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        head2.setText(dow + "," + " " + getmonth + " " + dom);

    }//GEN-LAST:event_mydayActionPerformed

    private void UpcomingBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpcomingBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UpcomingBtnActionPerformed

    private void tasksKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tasksKeyReleased
        // TODO add your handling code here:
        tasks.setBackground(new java.awt.Color(25,37,56));
    }//GEN-LAST:event_tasksKeyReleased

    private void tasksKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tasksKeyPressed
        // TODO add your handling code here:
       // tasks.setBackground(new java.awt.Color(173,216,230));
    }//GEN-LAST:event_tasksKeyPressed

    private void tasksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tasksActionPerformed
        // TODO add your handling code here:
        alltasks();
        jTextField2.setVisible(false);
        titlehead.setText("Tasks");
        head2.setText("Task List");

    }//GEN-LAST:event_tasksActionPerformed

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
            java.util.logging.Logger.getLogger(MaintoDo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MaintoDo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MaintoDo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MaintoDo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        LocalDate lt = LocalDate.now();
        String text = "Submit by 9:00m";
        String[] strArr = text.split("by");     

        // print result 
        System.out.println("LocalDate : "
                + lt);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                toDoFrame = new MaintoDo();
                toDoFrame.setVisible(true);
                // new MaintoDo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Delete;
    private javax.swing.JPanel MainPage;
    private javax.swing.JButton UpcomingBtn;
    private javax.swing.JButton addtask;
    private javax.swing.JLabel complete;
    private javax.swing.JLabel head2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton myday;
    private javax.swing.JButton newlist;
    private javax.swing.JPanel taskPanel;
    private javax.swing.JButton tasks;
    private javax.swing.JLabel titlehead;
    // End of variables declaration//GEN-END:variables
}
