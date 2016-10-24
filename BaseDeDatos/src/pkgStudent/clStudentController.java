/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgStudent;

import pkgConexiones.clConexionSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkgMain.frmMain;

/**
 *
 * @author DavSosMie
 */
public class clStudentController implements ActionListener {

    dlgStudent studentDialogue;
    ResultSet resultQuery;
    public clStudentController(frmMain frmM) {
        studentDialogue = new dlgStudent(frmM, true, this);
        studentDialogue.setVisible(true);
    }

    
    public clODTStudent getInfo() {
        clODTStudent student = new clODTStudent();
        student.setName(studentDialogue.getTxtNombre().getText());
        student.setSurname1(studentDialogue.getTxtApellido1().getText());
        student.setSurname2(studentDialogue.getTxtApellido2().getText());
        student.setDni(studentDialogue.getTxtDni().getText());
        student.setRegist(studentDialogue.getTxtRegistro().getText());
        return student;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            clStudentSQLController studentSqlController = new clStudentSQLController();
            if (e.getActionCommand() == "btnAltas") {
                clODTStudent student = getInfo();
                studentSqlController.subscribe(student);
                studentDialogue.update();
            } else if (e.getActionCommand() == "btnBajas") {
                clODTStudent student = getInfo();
                studentSqlController.unsubscribe(student);
                studentDialogue.update();
            } else if (e.getActionCommand() == "btnModificar") {
                clODTStudent student = getInfo();
                studentSqlController.modify(student);
                studentDialogue.update();
                
            }else if (e.getActionCommand() == "btnSearch"){
                resultQuery = studentSqlController.search(studentDialogue.getTxtRegistro().getText());
                resultQuery.next();                
                studentDialogue.setTextDni(resultQuery.getString("dni")); 
                studentDialogue.setTextName(resultQuery.getString("nombre"));
                studentDialogue.setTextApellido1(resultQuery.getString("apellido1"));
                studentDialogue.setTextApellido2(resultQuery.getString("apellido2"));
                studentDialogue.update();
            } else {
                studentDialogue.dispose();
            }

        } catch (SQLException ex) {
            studentDialogue.showError("Ha habido un error en la consulta");
        }
    }
//btnSearch
}
