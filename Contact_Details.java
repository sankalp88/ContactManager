/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacteditor;

import contacteditor.ContactEditor;
import contacteditor.ContactEditorUI;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;
import javax.swing.JTable;
/**
 *
 * @author hp
 */
public class Contact_Details {
    
    private int Contact_No;
    private String Email_Id;
    private int C_id;
    public Contact_Details() {
		super();
	}

    public Contact_Details(String contact_no,String email_id,String id
			) {
		super();
                Contact_No = Integer.parseInt(contact_no);
		Email_Id = email_id;
                C_id =Integer.parseInt(id);
	}

    public int getC_id() {
        return C_id;
    }

    public void setC_id(int C_id) {
        this.C_id = C_id;
    }

    

     public int getContact_No() {
        return Contact_No;
    }

    public String getEmail_Id() {
        return Email_Id;
    }

    public void setContact_No(int Contact_No) {
        this.Contact_No = Contact_No;
    }

    public void setEmail_Id(String Email_Id) {
        this.Email_Id = Email_Id;
    }
        
    public void insert(JTable jTable_display_cdetails,int c_id)
    {
        try
		{
			System.out.println("Inside insert try block");
			String sql="INSERT INTO contact_details "
					+ "(Contact_No,Email_id,C_Id)"
					+ "values (?,?,?)";
			PreparedStatement pst=ContactEditor.getJDBCConnection().prepareStatement(sql);
                        pst.setInt(1,this.Contact_No);
			pst.setString(2,this.Email_Id);
                        pst.setInt(3,this.C_id);
                        pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Records are Successfully Inserted into Contact_Details ");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
    }
    
    
}