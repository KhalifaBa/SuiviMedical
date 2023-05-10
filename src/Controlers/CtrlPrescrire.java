package Controlers;

import Tools.ConnexionBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CtrlPrescrire
{
    private Connection cnx;
    private PreparedStatement ps;
    private ResultSet rs;

    public CtrlPrescrire() {
        cnx = ConnexionBDD.getCnx();
    }

    public void InsertPrescrire(int idConsult, int numMedicament,int quantite)
    {
        // A vous de jouer
        try {
            ps = cnx.prepareStatement("insert into value(?,?,?,?) ");
            ps.setInt(1,idConsult);
            ps.setInt(2,numMedicament);
            ps.setInt(3,quantite);
            ps.executeUpdate();
            ps.close();
            rs.close();

        }catch (SQLException e)
        {
            throw new RuntimeException(e);
        }

    }
}
