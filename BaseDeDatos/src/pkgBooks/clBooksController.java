/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgBooks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import pkgMain.frmMain;

/**
 *
 * @author DavSosMie
 */
public class clBooksController implements ActionListener {

    dlgBooks booksDialogue;

    public clBooksController(frmMain frmM) {
        booksDialogue = new dlgBooks(frmM, true, this);
        booksDialogue.setVisible(true);
    }

    public clODTBooks getInfo() {
        clODTBooks book = new clODTBooks();
        book.setCodigo(booksDialogue.getTxtCodigo().getText());
        book.setAutor(booksDialogue.getTxtAutor().getText());
        book.setAsignatura(booksDialogue.getTxtAsignatura().getText());
        book.setEditorial(booksDialogue.getTxtEditorial().getText());
        book.setEstado(booksDialogue.getTxtEstado().getText());
        book.setTitulo(booksDialogue.getTxtTitulo().getText());

        return book;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            clBooksSQLController bookSqlController = new clBooksSQLController();
            if (e.getActionCommand() == "btnAltas") {
                clODTBooks student = getInfo();
                bookSqlController.subscribe(student);
                booksDialogue.update();
            } else if (e.getActionCommand() == "btnBajas") {
                clODTBooks student = getInfo();
                bookSqlController.unsubscribe(student);
                booksDialogue.update();
            } else if (e.getActionCommand() == "btnModificar") {
                clODTBooks student = getInfo();
                bookSqlController.modify(student);
                booksDialogue.update();
            } else {
                booksDialogue.dispose();
            }
        } catch (SQLException sQLException) {
            booksDialogue.showError("Ha habido un error en la consulta");
        }
    }

}
