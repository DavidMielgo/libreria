package pkgBooks;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pkgMain.frmMain;

public class clBooksController implements ActionListener, DocumentListener {

    private dlgBooks booksDialogue;
    private ResultSet resultQuery;
    
    public clBooksController(frmMain frmM) {
        booksDialogue = new dlgBooks(frmM, true, this);
        booksDialogue.setVisible(true);
    }

    public clBooksController() {
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
            
            } else if (e.getActionCommand() == "btnBuscar") {
                clODTBooks student = getInfo();
                resultQuery = bookSqlController.getBookSearch(student);
                resultQuery.next();
                booksDialogue.updateBook(resultQuery);
            
            }else if (e.getActionCommand() == "btnReset") {
                clODTBooks student = getInfo();
                booksDialogue.update();
            } else {
                booksDialogue.dispose();
            }
        } catch (SQLException sQLException) {
            booksDialogue.showError("Ha habido un error en la consulta");
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        
    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

}
