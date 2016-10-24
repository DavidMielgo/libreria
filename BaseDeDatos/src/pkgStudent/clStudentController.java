package pkgStudent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pkgMain.frmMain;

public class clStudentController implements ActionListener, DocumentListener {

    private dlgStudent studentDialogue;
    private ResultSet resultQuery;
    private boolean bRegistro, bDni, bNombre, bApellido1, bApellido2;

    public clStudentController(frmMain frmM) {
        studentDialogue = new dlgStudent(frmM, true, this);
        studentDialogue.setVisible(true);
    }

    public clStudentController() {

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

            } else if (e.getActionCommand() == "btnSearch") {
                clODTStudent student = getInfo();
                resultQuery = studentSqlController.getStudentSearch(student);
                resultQuery.next();
                studentDialogue.updateStudent(resultQuery);

            } else if (e.getActionCommand() == "btnReset") {
                studentDialogue.update();
            } else {
                studentDialogue.dispose();
            }

        } catch (SQLException ex) {
            studentDialogue.showError("Ha habido un error en la consulta");
        }
    }


    public boolean isbRegistro() {
        return bRegistro;
    }

    public void setbRegistro(boolean bregistro) {
        this.bRegistro = bregistro;
    }

    public boolean isbDni() {
        return bDni;
    }

    public void setbDni(boolean bDni) {
        this.bDni = bDni;
    }

    public boolean isbNombre() {
        return bNombre;
    }

    public void setbNombre(boolean bNombre) {
        this.bNombre = bNombre;
    }

    public boolean isbApellido1() {
        return bApellido1;
    }

    public void setbApellido1(boolean bApellido1) {
        this.bApellido1 = bApellido1;
    }

    public boolean isbApellido2() {
        return bApellido2;
    }

    public void setbApellido2(boolean bApellido2) {
        this.bApellido2 = bApellido2;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        if (e.getDocument() == studentDialogue.getTxtRegistro().getDocument() && bRegistro == false) {
            bRegistro = true;
        } else if (e.getDocument() == studentDialogue.getTxtNombre().getDocument() && bNombre == false) {
            bNombre = true;
        } else if (e.getDocument() == studentDialogue.getTxtDni().getDocument() && bDni == false) {
            bDni = true;
        } else if (e.getDocument() == studentDialogue.getTxtApellido1().getDocument() && bApellido1 == false) {
            bApellido1 = true;
        } else if (e.getDocument() == studentDialogue.getTxtApellido2().getDocument() && bApellido2 == false) {
            bApellido2 = true;
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        if (e.getDocument() == studentDialogue.getTxtRegistro().getDocument() && bRegistro == true && studentDialogue.getTxtRegistro().getText().equals("")) {
            bRegistro = false;

        } else if (e.getDocument() == studentDialogue.getTxtNombre().getDocument() && bNombre == true && studentDialogue.getTxtNombre().getText().equals("")) {
            bNombre = false;

        } else if (e.getDocument() == studentDialogue.getTxtDni().getDocument() && bDni == true && studentDialogue.getTxtDni().getText().equals("")) {
            bDni = false;

        } else if (e.getDocument() == studentDialogue.getTxtApellido1().getDocument() && bApellido1 == true && studentDialogue.getTxtApellido1().getText().equals("")) {
            bApellido1 = false;

        } else if (e.getDocument() == studentDialogue.getTxtApellido2().getDocument() && bApellido2 == true && studentDialogue.getTxtApellido2().getText().equals("")) {
            bApellido2 = false;

        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        System.out.println("ah, a si que funcionaba para esto");
    }
}
