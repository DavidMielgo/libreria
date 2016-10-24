/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgMain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkgBooks.clBooksController;
import pkgStudent.clStudentController;

/**
 *
 * @author DavSosMie
 */
public class clMainController implements ActionListener {

    frmMain frmMain;

    public void clCreateFrm() {
        frmMain = new frmMain();
        frmMain.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "btnStudent") {

            clStudentController studentController = new clStudentController(frmMain);

        } else if (e.getActionCommand() == "btnBooks") {
           
            clBooksController BooksController = new clBooksController(frmMain);

        } else if (e.getActionCommand() == "btnLoan") {

        } else if (e.getActionCommand() == "btnReports") {

        } else {
            System.exit(0);
        }
    }
}
