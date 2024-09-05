import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.table.DefaultTableModel;

public class StudentAchievementView extends javax.swing.JFrame {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public StudentAchievementView() {
        initComponents();
        Connect();
        displayStudentAchievements();
    }

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/achievement", "root", "Anjali@123");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentAchievementView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void displayStudentAchievements() {
        try {
            pst = con.prepareStatement("SELECT * FROM student_achieves_view");
            rs = pst.executeQuery();
            DefaultTableModel df = (DefaultTableModel) jTable1.getModel();
            df.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("S_id"));
                v.add(rs.getString("USN"));
                v.add(rs.getString("S_name"));
                v.add(rs.getString("S_Sem"));
                v.add(rs.getString("dept_name"));
                v.add(rs.getString("event_name"));
                v.add(rs.getString("A_date"));
                v.add(rs.getString("A_securedplace"));
                df.addRow(v);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentAchievementView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(220, 220, 220)); // Light grey background

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "S_id", "USN", "S_name", "S_Sem", "dept_name", "event_name", "A_date", "A_securedplace"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable1.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Border around the table

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new StudentAchievementView().setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration
}
