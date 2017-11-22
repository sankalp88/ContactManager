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
import java.sql.ResultSet;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Person {
	
	private int Contact_Id;
	private String First_Name;
	private String Last_Name;
	private String Date_Of_Birth;
	private String Date_Of_String;
	private String Gender;
	//private int Family_seq;
        //private Address address;
	//private Contact_Details contact;
	

	public Person() {
		super();
	}

	/*public Person(String contact_Id, String first_Name, String last_Name,
			String date_Of_Birth, String date_Of_String, String gender,
			String family_seq,Address addr,Contact_Details cont) {
		super();
		Contact_Id = Integer.parseInt(contact_Id);
		First_Name = first_Name;
		Last_Name = last_Name;
		Date_Of_Birth = date_Of_Birth;
		Date_Of_String = date_Of_String;
		Gender = gender;
		Family_seq = (family_seq != null) ? Integer.parseInt(family_seq) : 0;
                address=addr;
                contact=cont;
	}*/
        public Person(String contact_Id, String first_Name, String last_Name,
			String date_Of_Birth, String date_Of_String, String gender) {
		super();
		Contact_Id = Integer.parseInt(contact_Id);
		First_Name = first_Name;
		Last_Name = last_Name;
		Date_Of_Birth = date_Of_Birth;
		Date_Of_String = date_Of_String;
		Gender = gender;
	}


   /* public Address getAddress() {
        return address;
    }

    public Contact_Details getContact() {
        return contact;
    }*/

	public int getContact_Id() {
		return Contact_Id;
	}

	public void setContact_Id(int contact_Id) {
		Contact_Id = contact_Id;
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getDate_Of_Birth() {
		return Date_Of_Birth;
	}

	public void setDate_Of_Birth(String date_Of_Birth) {
		Date_Of_Birth = date_Of_Birth;
	}

	public String getDate_Of_String() {
		return Date_Of_String;
	}

	public void setDate_Of_String(String date_Of_String) {
		Date_Of_String = date_Of_String;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
        }
        
        public static Object[][] search(String ID) throws Exception{
            String query="SELECT Contact_Id,First_Name,Last_Name,Date_Of_Birth,Date_Of_Storing,Gender FROM Person where Contact_Id=?";
            ResultSet rs;
            int rowCount;
            PreparedStatement pst1=ContactEditor.getJDBCConnection().prepareStatement(query);
            pst1.setInt(1,Integer.parseInt(ID));
            rs = pst1.executeQuery();
            
            
            Object[][] res = new Object [1][6];
            rs = pst1.executeQuery();
            //rs = pst1.executeQuery(query);
           for (int i = 0; i < 1; i++){
                        System.out.println("hi");
                         System.out.println(i);                 
                        res[i][0] = rs.getInt(1);
                        res[i][1] = rs.getString(2);
                        res[i][2] = rs.getString(3); 
                        res[i][3] = rs.getDate(4);
                        res[i][4] = rs.getDate(5);
                        res[i][5] = rs.getString(6);            
            }
            return res;
        }
	
	@SuppressWarnings("deprecation")
	public void insert(JTable jTable_display_person)
	{
		try
		{
			System.out.println("Inside person insert try block");
			String sql="INSERT INTO person "
					+ "(Contact_Id,First_Name,Last_Name,Date_Of_Birth,Date_Of_Storing,Gender)"
					+ "values (?,?,?,?,?,?)";
			PreparedStatement pst=ContactEditor.getJDBCConnection().prepareStatement(sql);
			pst.setInt(1,this.Contact_Id);
			pst.setString(2,this.First_Name);
			pst.setString(3,this.Last_Name);
			String[] dts = Date_Of_Birth.split("-");
			Date dt = new Date(Integer.parseInt(dts[0])-1900, Integer.parseInt(dts[1])-1, Integer.parseInt(dts[2]));
			pst.setDate(4, dt);
                        String [] dtstore=Date_Of_String.split("-");
                        Date ds= new Date(Integer.parseInt(dtstore[0])-1900, Integer.parseInt(dtstore[1])-1, Integer.parseInt(dtstore[2]));
                        pst.setDate(5,ds);
			pst.setString(6,this.Gender);
			pst.executeUpdate();
			JOptionPane.showMessageDialog(null, "Records are Successfully Inserted into Person table");
                            
                        
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}

        }

}
