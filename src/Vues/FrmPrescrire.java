package Vues;

import Controlers.*;
import Tools.ConnexionBDD;
import Tools.ModelJTable;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.*;

import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class FrmPrescrire extends JFrame
{
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JLabel lblNumero;
    private JLabel lblDate;
    private JLabel lblNomMedecin;
    private JTextField txtNumeroConsultation;
    private JComboBox cboPatients;
    private JComboBox cboMedecins;
    private JButton btnInserer;
    private JTable tblMedicaments;
    private JPanel pnlDate;
    private JLabel lblNomPatient;
    private JLabel lblMedicaments;
    private JDateChooser dcDateConsultation;

    CtrlConsultation ctrlConsultation;
    CtrlMedecin ctrlMedecin;
    CtrlPatient ctrlPatient;
    CtrlMedicament ctrlMedicament;
    CtrlPrescrire ctrlPrescrire;
    ModelJTable mdl;
    public FrmPrescrire() throws SQLException, ClassNotFoundException {
        this.setTitle("Prescrire");
        this.setContentPane(pnlRoot);
        this.pack();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        ConnexionBDD maCnx = new ConnexionBDD();

        // Gestion de la date : ne pas supprimer les 3 lignes de code
        dcDateConsultation = new JDateChooser();
        dcDateConsultation.setDateFormatString("yyyy-MM-dd");
        pnlDate.add(dcDateConsultation);

        // A vous de jouer
        ctrlMedecin = new CtrlMedecin();
        ctrlPatient = new CtrlPatient();
        ctrlMedicament = new CtrlMedicament();
        ctrlConsultation = new CtrlConsultation();
        mdl = new ModelJTable();
        mdl.loadDatasMedicaments(ctrlMedicament.getAllMedicaments());
        tblMedicaments.setModel(mdl);
        txtNumeroConsultation.setText(String.valueOf(ctrlConsultation.getLastNumberOfConsultation()));
        for (String pat : ctrlPatient.getAllPatients())
        {
            cboPatients.addItem(pat);
        }
        for (String med : ctrlMedecin.getAllMedecins())
        {
            cboMedecins.addItem(med);
        }







        btnInserer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // A vous de jouer
                ctrlPrescrire = new CtrlPrescrire();
                ctrlPatient = new CtrlPatient();
                ctrlMedecin = new CtrlMedecin();
                ctrlConsultation = new CtrlConsultation();
                if (cboMedecins.getSelectedItem().equals("") || cboPatients.getSelectedItem().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Veuillez selectionnez au moins un patient et un medecin");
                }
                else
                {
                    String nomPatient = cboPatients.getSelectedItem().toString();
                    String nomMedecin = cboMedecins.getSelectedItem().toString();
                    String date = String.valueOf(dcDateConsultation.getDateFormatString());
                    int numConsulation = Integer.parseInt(txtNumeroConsultation.getText());
                    int numMedicament = Integer.parseInt(tblMedicaments.getValueAt(tblMedicaments.getSelectedRow(),0).toString());
                    int quantite = Integer.parseInt(tblMedicaments.getValueAt(tblMedicaments.getSelectedRow(),3).toString());
                    ctrlConsultation.InsertConsultation(numConsulation,date,ctrlPatient.getIdPatientByName(nomPatient),ctrlMedecin.getIdMedecinByName(nomMedecin));
                    ctrlPrescrire.InsertPrescrire(numConsulation,numMedicament,quantite);
                }

            }
        });
    }
}
