package pkgMain;

import pkgStudent.dlgStudent;

public class Main {

    public static void main(String[] args) {
        clMainController mainController;
        mainController = new clMainController();
        mainController.clCreateFrm();
        //dlgStudent s = new dlgStudent();
        /*s.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });*/
        //s.setVisible(true);
    }

}
