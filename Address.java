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
public class Address {
    
    private String Country;
    private String State;
    private String City;
    private String Zipcode;
    private String StreetAddress;
    private int Addr_id;
    private String C_id;

    public Address() {
		super();
	}

	public Address(String addr_id,String country, String state, String city,
			String zipcode, String streetAddress,String id
			) {
		super();
                Addr_id = Integer.parseInt(addr_id);
		Country = country;
		State = state;
		City = city;
		Zipcode = zipcode;
		StreetAddress = streetAddress;
                C_id =id;
                
	}

    public String getC_id() {
        return C_id;
    }

    public void setC_id(String C_id) {
        this.C_id = C_id;
    }

    
        

    public String getCountry() {
        return Country;
    }

    public String getState() {
        return State;
    }

    public String getCity() {
        return City;
    }

    public String getZipcode() {
        return Zipcode;
    }
    
    public String getStreetAddress() {
        return StreetAddress;
    }

    public int getAddr_id() {
        return Addr_id;
    }

    public void setCountry(String country) {
        this.Country = country;
    }

    public void setState(String State) {
        this.State = State;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setZipcode(String zipcode) {
        this.Zipcode = zipcode;
    }

    public void setStreetAddress(String StreetAddress) {
        this.StreetAddress = StreetAddress;
    }

    public void setAddr_id(int Addr_id) {
        this.Addr_id = Addr_id;
    }

    @SuppressWarnings("deprecation")
	public void insert(JTable jTable_display_person,int c_id)
	{
		try
		{
			System.out.println("Inside insert address try block");
			String sql="INSERT INTO address "
					+ "(Addr_Id,Country,State,City,Zipcode,StreetAddress,Contact_Id)"
					+ "values (?,?,?,?,?,?,?)";
			PreparedStatement pst=ContactEditor.getJDBCConnection().prepareStatement(sql);
                        pst.setInt(1,this.Addr_id);
			pst.setString(2,this.Country);
			pst.setString(3,this.State);
			pst.setString(4,this.City);
			pst.setString(5,this.Zipcode);
                        pst.setString(6,this.StreetAddress);
                        pst.setString(7, this.C_id);
                        pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Records are Successfully Inserted into Address Tables");
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

	}
}

