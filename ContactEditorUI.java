/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacteditor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class ContactEditorUI extends javax.swing.JFrame {

    /**
     * Creates new form ContactEditorUI
     */
    public ContactEditorUI() {
        initComponents();
    }
    
     public Connection getConnection()
    {
        Connection con;
        try{
            con=DriverManager.getConnection("jdbc:mysql://localhost/mydb?user=root&password=Sanjukta@12345");
            return con;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<Person> personList()
    {
         ArrayList<Person> personList=new  ArrayList<Person> ();
         Connection connection=getConnection();
         int idField = Integer.parseInt(jTextFieldCid.getText());
         //Getting the Data from the person table for the specified ContactID which is a primary Key
         String query="Select p.Contact_Id,First_Name,Last_Name,Date_Of_Birth,Date_Of_Storing,Gender from person p where p.Contact_Id="+idField;
         
         Statement st;
         ResultSet rs;
         try{
             st=connection.createStatement();
             rs=st.executeQuery(query);
             Person person;
             //Looping through and adding Objects of Person class into the ArrayList
             while(rs.next())
             {
                 person=new Person(rs.getString("Contact_Id"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getString("Date_Of_Birth"),rs.getString("Date_Of_Storing"),rs.getString("Gender"));
                 personList.add(person);
                 
             }
             
         }catch(Exception e)
         {
             e.printStackTrace();
         }
         return personList;
    }
    public ArrayList<Person> personListAll()
    {
         ArrayList<Person> personListAll=new  ArrayList<Person> ();
         Connection connection=getConnection();
         //To display all the Records of tables after any DML operation
         String query="Select Contact_Id,First_Name,Last_Name,Date_Of_Birth,Date_Of_Storing,Gender from person";
         
         Statement st;
         ResultSet rs;
         try{
             st=connection.createStatement();
             rs=st.executeQuery(query);
             Person person;
             while(rs.next())
             {
                 person=new Person(rs.getString("Contact_Id"),rs.getString("First_Name"),rs.getString("Last_Name"),rs.getString("Date_Of_Birth"),rs.getString("Date_Of_Storing"),rs.getString("Gender"));
                 personListAll.add(person);
                 
             }
             
             
         }catch(Exception e)
         {
             e.printStackTrace();
         }
         return personListAll;
    }
    public ArrayList<Address> addressList()
    {
         ArrayList<Address> addressList=new  ArrayList<Address> ();
         Connection connection=getConnection();
         
         int addrfield=Integer.parseInt(jTextFieldAddr_id.getText());
         //To display the records from Address table based on the Contact Id and Address Id
         String query="Select Country,State,City,Zipcode,StreetAddress,Addr_Id,Contact_Id from Address addr where addr.Addr_Id="+addrfield;
         
         Statement st;
         ResultSet rs;
         try{
             st=connection.createStatement();
             rs=st.executeQuery(query);
             Address addr;
             while(rs.next())
             {
                 addr=new Address(rs.getString("Addr_id"),
                                    rs.getString("Country"),
                                   rs.getString("State"),
                                    rs.getString("City"),
                                    rs.getString("Zipcode"),
                                   rs.getString("StreetAddress"),
                                   rs.getString("Contact_Id")
                                    );
                 addressList.add(addr);
                 
             }
             
         }catch(Exception e)
         {
             e.printStackTrace();
         }
         return addressList;
    }
    public ArrayList<Address> addressListAll()
    {
         ArrayList<Address> addressListAll=new  ArrayList<Address> ();
         Connection connection=getConnection();
         //To display all the records from Address table
         String query="Select Country,State,City,Zipcode,StreetAddress,Addr_Id,Contact_Id from Address";
         
         Statement st;
         ResultSet rs;
         try{
             st=connection.createStatement();
             rs=st.executeQuery(query);
             Address addr;
             while(rs.next())
             {
                 addr=new Address(rs.getString("Addr_id"),
                                    rs.getString("Country"),
                                   rs.getString("State"),
                                    rs.getString("City"),
                                    rs.getString("Zipcode"),
                                   rs.getString("StreetAddress"),
                                   rs.getString("Contact_Id")
                                    );
                 addressListAll.add(addr);
                 
             }
             
         }catch(Exception e)
         {
             e.printStackTrace();
         }
         return addressListAll;
    }
    public ArrayList<Contact_Details> contactdetailslist()
    {
         ArrayList<Contact_Details> contactdetailslist=new  ArrayList<Contact_Details> ();
         Connection connection=getConnection();
         int idField = Integer.parseInt(jTextField2.getText());
         //To display the records from Contact Details table based on the Contact Id
          String query="Select Contact_No,Email_id,C_id from Contact_Details cd where cd.C_id="+idField;
         
         
         Statement st;
         ResultSet rs;
         try{
             st=connection.createStatement();
             rs=st.executeQuery(query);
             Contact_Details cd;
             while(rs.next())
             {
                 cd=new Contact_Details(rs.getString("Contact_No"),
                                   rs.getString("Email_id"),
                                   rs.getString("C_id"));
                 contactdetailslist.add(cd);
                 
             }
             
         }catch(Exception e)
         {
             e.printStackTrace();
         }
         return contactdetailslist;
    }
    public ArrayList<Contact_Details> contactdetailslistall()
    {
         ArrayList<Contact_Details> contactdetailslistall=new  ArrayList<Contact_Details> ();
         Connection connection=getConnection();
         //To display all the records from Contact Details table 
          String query="Select Contact_No,Email_id,C_id from Contact_Details";
         
         
         Statement st;
         ResultSet rs;
         try{
             st=connection.createStatement();
             rs=st.executeQuery(query);
             Contact_Details cd;
             while(rs.next())
             {
                 cd=new Contact_Details(rs.getString("Contact_No"),
                                   rs.getString("Email_id"),
                                   rs.getString("C_id"));
                 contactdetailslistall.add(cd);
                 
             }
             
         }catch(Exception e)
         {
             e.printStackTrace();
         }
         return contactdetailslistall;
    }
    //Below function use for Displaying all the records from Person table based on the Contact Id
    public void show_person_in_Jtable()
    {
         ArrayList<Person> list=personList();
         DefaultTableModel model=(DefaultTableModel)jTable_display_person.getModel();
         Object[] row=new Object[6];
         //Iterate through all the rows and display the records in Jtable
         for(Person person:list)
         {
             row[0]=person.getContact_Id();
             row[1]=person.getFirst_Name();
             row[2]=person.getLast_Name();
             row[3]=person.getGender();
             row[4]=person.getDate_Of_Birth();
             row[5]=person.getDate_Of_String();
             model.addRow(row);
         }
    }
    //Below function use for Displaying all the records from Person table
    public void show_personall_in_Jtable()
    {
        ArrayList<Person> list=personListAll();
         DefaultTableModel model=(DefaultTableModel)jTable_display_person.getModel();
         Object[] row=new Object[6];
         //Iterate through all the rows and display the records in Jtable
         for(Person person:list)
         {
             row[0]=person.getContact_Id();
             row[1]=person.getFirst_Name();
             row[2]=person.getLast_Name();
             row[3]=person.getGender();
             row[4]=person.getDate_Of_Birth();
             row[5]=person.getDate_Of_String();
             model.addRow(row);
         }
    }
    //Below function use for Displaying all the records from Address table based on the Contact Id and Address ID
    public void show_address_in_Jtable()
    {
         ArrayList<Address> list=addressList();
         DefaultTableModel model=(DefaultTableModel)jTable_display_address.getModel();
         Object[] row=new Object[7];
         for(Address addr:list)
         {
             row[0]=addr.getAddr_id();
             row[1]=addr.getCountry();
             row[2]=addr.getState();
             row[3]=addr.getCity();
             row[4]=addr.getZipcode();
             row[5]=addr.getStreetAddress();
             row[6]=addr.getC_id();
             model.addRow(row);
         }
    }
    //Below function use for Displaying all the records from Address  table
     public void show_addressall_in_Jtable()
    {
         ArrayList<Address> list=addressListAll();
         DefaultTableModel model=(DefaultTableModel)jTable_display_address.getModel();
         Object[] row=new Object[7];
         for(Address addr:list)
         {
             row[0]=addr.getAddr_id();
             row[1]=addr.getCountry();
             row[2]=addr.getState();
             row[3]=addr.getCity();
             row[4]=addr.getZipcode();
             row[5]=addr.getStreetAddress();
             row[6]=addr.getC_id();
             model.addRow(row);
         }
    }
     //Below function use for Displaying all the records from Contact Details table based on the Contact Id
     public void show_contactdetails_in_Jtable()
    {
         ArrayList<Contact_Details> list=contactdetailslist();
         DefaultTableModel model=(DefaultTableModel)jTable_display_cdetails.getModel();
         Object[] row=new Object[3];
         for(Contact_Details cd:list)
         {
             row[0]=cd.getContact_No();
             row[1]=cd.getEmail_Id();
             row[2]=cd.getC_id();
             model.addRow(row);
         }
    }
     //Below function use for Displaying all the records from Contact Details table
     public void show_contactall_in_Jtable(){
            
         ArrayList<Contact_Details> list=contactdetailslistall();
         DefaultTableModel model=(DefaultTableModel)jTable_display_cdetails.getModel();
         Object[] row=new Object[3];
         for(Contact_Details cd:list)
         {
             row[0]=cd.getContact_No();
             row[1]=cd.getEmail_Id();
             row[2]=cd.getC_id();
             model.addRow(row);
         }
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabelFirstName = new javax.swing.JLabel();
        jTextFieldFirstName = new javax.swing.JTextField();
        jLabelLastName = new javax.swing.JLabel();
        jLabelGender = new javax.swing.JLabel();
        jTextFieldGender = new javax.swing.JComboBox<>();
        jLabelCid = new javax.swing.JLabel();
        jTextFieldCid = new javax.swing.JTextField();
        jLabelDob = new javax.swing.JLabel();
        jLabelDobYear = new javax.swing.JLabel();
        jTextFieldDobYear = new javax.swing.JTextField();
        jLabelDobMonth = new javax.swing.JLabel();
        jTextFieldDobMonth = new javax.swing.JComboBox<>();
        jLabelDobDay = new javax.swing.JLabel();
        jTextFieldDobDay = new javax.swing.JComboBox<>();
        jLabelDos = new javax.swing.JLabel();
        jLabelDosYear = new javax.swing.JLabel();
        jTextFieldDosYear = new javax.swing.JTextField();
        jLabelDosMonth = new javax.swing.JLabel();
        jTextFieldDosMonth = new javax.swing.JComboBox<>();
        jLabelDosDay = new javax.swing.JLabel();
        jTextFieldDosDay = new javax.swing.JComboBox<>();
        jButtonInsert = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonDisplay = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable_display_person = new javax.swing.JTable();
        jTextFieldLastName = new javax.swing.JTextField();
        jButtonDisplayallPerson = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabelAddr_id = new javax.swing.JLabel();
        jTextFieldAddr_id = new javax.swing.JTextField();
        jLabelAddCountry = new javax.swing.JLabel();
        jLabelState = new javax.swing.JLabel();
        jLabelCity = new javax.swing.JLabel();
        jTextFieldState = new javax.swing.JTextField();
        jTextFieldCountry = new javax.swing.JTextField();
        jTextFieldFirstName4 = new javax.swing.JTextField();
        jLabelZipCode = new javax.swing.JLabel();
        jTextFieldCity = new javax.swing.JTextField();
        jLabelAddress = new javax.swing.JLabel();
        jButtonInsert_addr = new javax.swing.JButton();
        jButtonUpdate_addr = new javax.swing.JButton();
        jButtonDelete_addr = new javax.swing.JButton();
        jButtonDisplay_addr = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable_display_address = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButtonDisplayAllAddress = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabelContactNo = new javax.swing.JLabel();
        jTextFieldContactNo = new javax.swing.JTextField();
        jLabelEmail_Id = new javax.swing.JLabel();
        jTextFieldEmail_Id = new javax.swing.JTextField();
        jButtonInsert_cd = new javax.swing.JButton();
        jButtonUpdate_cd = new javax.swing.JButton();
        jButtonDelet_cd = new javax.swing.JButton();
        jButtonDisplay_cd = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_display_cdetails = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButtonDisplayAllContact = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelFirstName.setText("First Name : ");

        jLabelLastName.setText("Last Name :");

        jLabelGender.setText("Gender");

        jTextFieldGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "M", "F" }));

        jLabelCid.setText("Contact Id:");

        jLabelDob.setText("Date Of Birth");

        jLabelDobYear.setText("Year");

        jLabelDobMonth.setText("Month");

        jTextFieldDobMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabelDobDay.setText("Day");

        jTextFieldDobDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jLabelDos.setText("Date Of Storing");

        jLabelDosYear.setText("Year");

        jLabelDosMonth.setText("Month");

        jTextFieldDosMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        jLabelDosDay.setText("Day");

        jTextFieldDosDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jButtonInsert.setText("Insert");
        jButtonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Update");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonDisplay.setText("Display");
        jButtonDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisplayActionPerformed(evt);
            }
        });

        jTable_display_person.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Contact Id", "FirstName", "LastName", "Gender", "Date Of Birth", "Date Of Storing"
            }
        ));
        jScrollPane2.setViewportView(jTable_display_person);

        jButtonDisplayallPerson.setText("DisplayAll");
        jButtonDisplayallPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisplayallPersonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelLastName)
                                    .addComponent(jLabelFirstName))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabelDob)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabelDobYear)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldDobYear, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelDobMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldDobMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabelDobDay, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jTextFieldDobDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelDos)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCid, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelGender, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldCid, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabelDosYear)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldDosYear, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelDosMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldDosMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelDosDay, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldDosDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonInsert)
                        .addGap(26, 26, 26)
                        .addComponent(jButtonUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDelete)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDisplay))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jButtonDisplayallPerson)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFirstName)
                    .addComponent(jTextFieldFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelLastName)
                    .addComponent(jTextFieldLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelGender)
                    .addComponent(jTextFieldGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCid)
                    .addComponent(jTextFieldCid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelDob)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDobYear)
                    .addComponent(jTextFieldDobYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDobMonth)
                    .addComponent(jTextFieldDobMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDobDay)
                    .addComponent(jTextFieldDobDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabelDos)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDosYear)
                    .addComponent(jTextFieldDosYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDosMonth)
                    .addComponent(jTextFieldDosMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDosDay)
                    .addComponent(jTextFieldDosDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInsert)
                    .addComponent(jButtonUpdate)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonDisplay))
                .addGap(55, 55, 55)
                .addComponent(jButtonDisplayallPerson)
                .addContainerGap(65, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 973, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Personal Details", jPanel1);

        jLabelAddr_id.setText("Addr Id");

        jLabelAddCountry.setText("Country");

        jLabelState.setText("State");

        jLabelCity.setText("City");

        jLabelZipCode.setText("Zip Code");

        jLabelAddress.setText("Address Line");

        jButtonInsert_addr.setText("Insert");
        jButtonInsert_addr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsert_addrActionPerformed(evt);
            }
        });

        jButtonUpdate_addr.setText("Update");
        jButtonUpdate_addr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdate_addrActionPerformed(evt);
            }
        });

        jButtonDelete_addr.setText("Delete");
        jButtonDelete_addr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelete_addrActionPerformed(evt);
            }
        });

        jButtonDisplay_addr.setText("Display");
        jButtonDisplay_addr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisplay_addrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTable_display_address.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Address Id", "Country", "State", "City", "Zipcode", "Address Line", "Contact_Id"
            }
        ));
        jScrollPane3.setViewportView(jTable_display_address);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel1.setText("Contact Id");

        jButtonDisplayAllAddress.setText("DisplayAll");
        jButtonDisplayAllAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisplayAllAddressActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAddress)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabelZipCode, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextFieldFirstName4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabelCity)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabelState)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldState, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabelAddCountry)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelAddr_id, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldAddr_id)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jButtonInsert_addr)
                                .addGap(10, 10, 10)
                                .addComponent(jButtonUpdate_addr)
                                .addGap(7, 7, 7)
                                .addComponent(jButtonDelete_addr)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonDisplay_addr))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addComponent(jButtonDisplayAllAddress)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAddr_id)
                    .addComponent(jTextFieldAddr_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAddCountry)
                    .addComponent(jTextFieldCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelState)
                    .addComponent(jTextFieldState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCity)
                    .addComponent(jTextFieldCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelZipCode)
                    .addComponent(jTextFieldFirstName4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabelAddress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInsert_addr)
                    .addComponent(jButtonUpdate_addr)
                    .addComponent(jButtonDelete_addr)
                    .addComponent(jButtonDisplay_addr))
                .addGap(18, 18, 18)
                .addComponent(jButtonDisplayAllAddress)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 491, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Address Information", jPanel2);

        jLabelContactNo.setText("Contact No");

        jLabelEmail_Id.setText("Email Id");

        jButtonInsert_cd.setText("Insert");
        jButtonInsert_cd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsert_cdActionPerformed(evt);
            }
        });

        jButtonUpdate_cd.setText("Update");
        jButtonUpdate_cd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdate_cdActionPerformed(evt);
            }
        });

        jButtonDelet_cd.setText("Delete");
        jButtonDelet_cd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelet_cdActionPerformed(evt);
            }
        });

        jButtonDisplay_cd.setText("Display");
        jButtonDisplay_cd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisplay_cdActionPerformed(evt);
            }
        });

        jTable_display_cdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Contact No", "Email Id", "Contact Id"
            }
        ));
        jScrollPane4.setViewportView(jTable_display_cdetails);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
        );

        jLabel2.setText("Contact_Id");

        jButtonDisplayAllContact.setText("DisplayAll");
        jButtonDisplayAllContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDisplayAllContactActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabelEmail_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldEmail_Id, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jButtonInsert_cd)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonUpdate_cd)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonDelet_cd))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldContactNo, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addComponent(jTextField2))))
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDisplay_cd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jButtonDisplayAllContact)
                        .addGap(124, 124, 124)))
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContactNo)
                    .addComponent(jTextFieldContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEmail_Id)
                    .addComponent(jTextFieldEmail_Id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(128, 128, 128)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonInsert_cd)
                    .addComponent(jButtonUpdate_cd)
                    .addComponent(jButtonDelet_cd)
                    .addComponent(jButtonDisplay_cd))
                .addGap(38, 38, 38)
                .addComponent(jButtonDisplayAllContact)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Contact Details", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Below function use for Refreshing all the Text Fields after Doing any DML operations
    private void refresh()
    {
         jTextFieldFirstName.setText("");
         jTextFieldLastName.setText("");
         jTextFieldGender.setSelectedIndex(0);
         jTextFieldCid.setText("");
         jTextFieldDobYear.setText("");
         jTextFieldDobMonth.setSelectedIndex(0);
         jTextFieldDobDay.setSelectedIndex(0);
         jTextFieldDosYear.setText("");
         jTextFieldDosMonth.setSelectedIndex(0);
         jTextFieldDosDay.setSelectedIndex(0);
         jTextField1.setText("");
         jTextFieldAddr_id.setText("");
         jTextFieldCountry.setText("");
         jTextFieldState.setText("");
         jTextFieldCity.setText("");
         jTextFieldFirstName4.setText("");
         jTextArea1.setText("");
         jTextField2.setText("");
         jTextFieldContactNo.setText("");
         jTextFieldEmail_Id.setText("");
         
         
    }
    
    //Below function use for Displaying all the records from Person table based on the contact ID
    private void jButtonDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisplayActionPerformed
        if(!jTextFieldCid.getText().trim().equalsIgnoreCase(""))
        {
            DefaultTableModel model=(DefaultTableModel)jTable_display_person.getModel();
            model.setRowCount(0);
            show_person_in_Jtable();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Enter Valid Contact Id to get the records from Person Table");
        }
        refresh();
    }//GEN-LAST:event_jButtonDisplayActionPerformed
    //Below function use for Deleting all the records from Person table based on the contact ID
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        Connection con;
        CallableStatement cst;
        try {
            con=ContactEditor.getJDBCConnection();
            //Delete from Person table which should cascade to all successive table
            if(!jTextFieldCid.getText().trim().equalsIgnoreCase(""))
            {
                cst=con.prepareCall("{call DeleteFromPerson(?)}");
                cst.setInt(1,Integer.parseInt(jTextFieldCid.getText()));
                cst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Records are Successfully deleted from person table");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Enter Valid Contact Id to delete the records from Person Table");

            }

        } catch (Exception ex) {
            Logger.getLogger(ContactEditorUI.class.getName()).log(Level.SEVERE, null, ex);
        }
         refresh();
    }//GEN-LAST:event_jButtonDeleteActionPerformed
//Below function use for Updating all the records from Person table based on the contact ID
    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        Connection con;
        CallableStatement cst;
        try {
            con=ContactEditor.getJDBCConnection();
            //Delete from Person table which should cascade to all successive table
            if(!jTextFieldCid.getText().trim().equalsIgnoreCase(""))
            {
                cst=con.prepareCall("{call Update_Person_Details(?,?,?)}");
                cst.setInt(1,Integer.parseInt(jTextFieldCid.getText()));
                cst.setString(2,jTextFieldFirstName.getText().isEmpty()?null:jTextFieldFirstName.getText());
                cst.setString(3,jTextFieldLastName.getText().isEmpty()?null:jTextFieldLastName.getText());
                cst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Records are successfully updated in person table");
            }
            //Delete only from Address table using Address ID
            else if (jTextFieldCid.getText().equalsIgnoreCase(""))
            {
                JOptionPane.showMessageDialog(null, "Enter Valid Contact Id to update the records in Person Table");
            }

        } catch (Exception ex) {
            Logger.getLogger(ContactEditorUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();
    }//GEN-LAST:event_jButtonUpdateActionPerformed
    //Below function use for Inserting all the records into Person table
    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed

        if(jTextFieldFirstName.getText().trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "FirstName field must be filled");
        }
        else if(jTextFieldLastName.getText().trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "LastName field must be filled");
        }
        else if(String.valueOf(jTextFieldGender.getSelectedItem()).trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "Gender  must be Selected");
        }
        else if(jTextFieldCid.getText().trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "Contact_Id field must be filled");
        }
        else if(jTextFieldDobYear.getText().trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "Date of Birth Year field must be filled");
        }
        else if(String.valueOf(jTextFieldDobMonth.getSelectedItem()).trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "DOB month field must be Selected");
        }
        else if(String.valueOf(jTextFieldDobDay.getSelectedItem()).trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "DOB Day field must be Selected");
        }
        else if(jTextFieldDosYear.getText().trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "Date of Storing Year field must be filled");
        }
        else if(String.valueOf(jTextFieldDosMonth.getSelectedItem()).trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "DOS Month field must be Selected");
        }
        else if(String.valueOf(jTextFieldDosDay.getSelectedItem()).trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "DOS Day field must be Selected");
        }
        else
        {
            Person p=new Person(
                jTextFieldCid.getText(),
                jTextFieldFirstName.getText(),
                jTextFieldLastName.getText(),
                jTextFieldDobYear.getText() +"-"+ jTextFieldDobMonth.getSelectedItem()+"-"+jTextFieldDobDay.getSelectedItem(),
                jTextFieldDosYear.getText() +"-"+ jTextFieldDosMonth.getSelectedItem()+"-"+jTextFieldDosDay.getSelectedItem(),
                String.valueOf(jTextFieldGender.getSelectedItem())
            );
            p.insert(jTable_display_person);//calling Insert method in Person class 
            refresh(); //Calling refresh methods to clear the text Field after Insertion
        }
        
    }//GEN-LAST:event_jButtonInsertActionPerformed
//Below function use for Inserting all the records into Address table
    private void jButtonInsert_addrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsert_addrActionPerformed

        if(jTextField1.getText().trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "Contact_Id field must be filled");
        }
        else if(jTextFieldAddr_id.getText().trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "AddrId field must be filled");
        }
        else
        {
            int idField = Integer.parseInt(jTextField1.getText());
            Address addr=new Address(
                                    jTextFieldAddr_id.getText(),
                                    jTextFieldCountry.getText(),
                                    jTextFieldState.getText(),
                                    jTextFieldCity.getText(),
                                    jTextFieldFirstName4.getText(),
                                    jTextArea1.getText(),
                                    jTextField1.getText()
                                    
                                );
            addr.insert(jTable_display_address,idField); //Calling Insert Method of Address class
            refresh();//Calling refresh methods to clear the text Field after Insertion
        }
    }//GEN-LAST:event_jButtonInsert_addrActionPerformed
//Below function use for Updating all the records into Address table based on the Address id and Contact Id
    private void jButtonUpdate_addrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdate_addrActionPerformed
       Connection con;
       CallableStatement cst1;
        try {
            con=ContactEditor.getJDBCConnection();
            if(!jTextFieldAddr_id.getText().trim().equalsIgnoreCase(""))
            {
                cst1=con.prepareCall("{call Update_Address_Details(?,?,?,?,?,?)}");
                cst1.setString(1,jTextFieldAddr_id.getText());
                cst1.setString(2,jTextFieldCountry.getText().isEmpty()?null:jTextFieldCountry.getText());
                cst1.setString(3,jTextFieldState.getText().isEmpty()?null:jTextFieldState.getText());
                cst1.setString(4,jTextFieldCity.getText().isEmpty()?null:jTextFieldCity.getText());
                cst1.setString(5,jTextFieldFirstName4.getText().isEmpty()?null:jTextFieldFirstName4.getText());
                cst1.setString(6,jTextArea1.getText().isEmpty()?null:jTextArea1.getText());
                cst1.executeUpdate();
                JOptionPane.showMessageDialog(null, "Records are successfully Updated in Address table");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Enter the Valid Values for Contact Id and Address Id to update");
            }

        } catch (Exception ex) {
            Logger.getLogger(ContactEditorUI.class.getName()).log(Level.SEVERE, null, ex);
        }
         refresh();
    }//GEN-LAST:event_jButtonUpdate_addrActionPerformed

    private void jButtonDelete_addrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelete_addrActionPerformed
       Connection con;
       CallableStatement cst;
        try {
            con=ContactEditor.getJDBCConnection();
            if(!jTextField1.getText().trim().equalsIgnoreCase("") && !jTextFieldAddr_id.getText().trim().equalsIgnoreCase(""))
            {
                cst=con.prepareCall("{call DeleteFromAddress(?,?)}");
                cst.setInt(1,Integer.parseInt(jTextField1.getText()));
                cst.setInt(2,Integer.parseInt(jTextFieldAddr_id.getText()));
                cst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Records are successfully deleted from Address table");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Enter Valid values of Contact Id and Address id to delete");

            }

        } catch (Exception ex) {
            Logger.getLogger(ContactEditorUI.class.getName()).log(Level.SEVERE, null, ex);
        }
         refresh();

    }//GEN-LAST:event_jButtonDelete_addrActionPerformed

    private void jButtonDisplay_addrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisplay_addrActionPerformed
        if(!jTextField1.getText().trim().equalsIgnoreCase("") && !jTextFieldAddr_id.getText().trim().equalsIgnoreCase(""))
        {
            DefaultTableModel model=(DefaultTableModel)jTable_display_address.getModel();
            model.setRowCount(0);
            show_address_in_Jtable();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Address id and Contact Id must be entered for display");
        }
        refresh();
    }//GEN-LAST:event_jButtonDisplay_addrActionPerformed

    private void jButtonInsert_cdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsert_cdActionPerformed
        if(jTextField2.getText().trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "Enter valid values for Contact Id");
        }
        else if(jTextFieldContactNo.getText().trim().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(null, "Enter valid values for ContactNo");
        }
        else
        {
            int idField = Integer.parseInt(jTextField2.getText());
            Contact_Details cd=new Contact_Details(
                                    jTextFieldContactNo.getText(),
                                    jTextFieldEmail_Id.getText(),
                                    jTextField2.getText()
                                    
                                );
            cd.insert(jTable_display_cdetails,idField);
            refresh();
        }
    }//GEN-LAST:event_jButtonInsert_cdActionPerformed

    private void jButtonUpdate_cdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdate_cdActionPerformed
       Connection con;
       CallableStatement cst1;
        try {
            con=ContactEditor.getJDBCConnection();
            if(!jTextField2.getText().trim().equalsIgnoreCase(""))
            {
                cst1=con.prepareCall("{call Update_Contact_Details(?,?,?)}");
                cst1.setInt(1,Integer.parseInt(jTextField2.getText()));
                cst1.setString(2,jTextFieldContactNo.getText().isEmpty()?null:jTextFieldContactNo.getText());
                cst1.setString(3,jTextFieldEmail_Id.getText().isEmpty()?null:jTextFieldEmail_Id.getText());
                cst1.executeUpdate();
                JOptionPane.showMessageDialog(null, "Records are successfully Updated in Contact details table");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Enter Valid values for Contact Id to Update");
            }

        } catch (Exception ex) {
            Logger.getLogger(ContactEditorUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();
    }//GEN-LAST:event_jButtonUpdate_cdActionPerformed

    private void jButtonDelet_cdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelet_cdActionPerformed
       Connection con;
       CallableStatement cst;
        try {
            con=ContactEditor.getJDBCConnection();
            if(!jTextField2.getText().trim().equalsIgnoreCase(""))
            {
                cst=con.prepareCall("{call DeleteFromContactDetails(?)}");
                cst.setInt(1,Integer.parseInt(jTextField2.getText()));
                cst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Records are successfully deleted from Contact details table ");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Enter Valid values of Contact Id  to delete");

            }

        } catch (Exception ex) {
            Logger.getLogger(ContactEditorUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        refresh();
    }//GEN-LAST:event_jButtonDelet_cdActionPerformed

    private void jButtonDisplay_cdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisplay_cdActionPerformed
         if(!jTextField2.getText().trim().equalsIgnoreCase(""))
         {
            DefaultTableModel model=(DefaultTableModel)jTable_display_cdetails.getModel();
            model.setRowCount(0);
            show_contactdetails_in_Jtable();
         }
        else
         {
            JOptionPane.showMessageDialog(null, "Enter valid values for Contact Id to display records");
         }
         refresh();
    }//GEN-LAST:event_jButtonDisplay_cdActionPerformed

    private void jButtonDisplayallPersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisplayallPersonActionPerformed
      DefaultTableModel model=(DefaultTableModel)jTable_display_person.getModel();
       model.setRowCount(0);
       show_personall_in_Jtable();
       
    }//GEN-LAST:event_jButtonDisplayallPersonActionPerformed

    private void jButtonDisplayAllAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisplayAllAddressActionPerformed
       DefaultTableModel model=(DefaultTableModel)jTable_display_address.getModel();
       model.setRowCount(0);
       show_addressall_in_Jtable();
    }//GEN-LAST:event_jButtonDisplayAllAddressActionPerformed

    private void jButtonDisplayAllContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDisplayAllContactActionPerformed
       DefaultTableModel model=(DefaultTableModel)jTable_display_cdetails.getModel();
       model.setRowCount(0);
       //new ContactEditorUI().show_personall_in_Jtable();
       show_contactall_in_Jtable();
    }//GEN-LAST:event_jButtonDisplayAllContactActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ContactEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContactEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContactEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContactEditorUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContactEditorUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelet_cd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDelete_addr;
    private javax.swing.JButton jButtonDisplay;
    private javax.swing.JButton jButtonDisplayAllAddress;
    private javax.swing.JButton jButtonDisplayAllContact;
    private javax.swing.JButton jButtonDisplay_addr;
    private javax.swing.JButton jButtonDisplay_cd;
    private javax.swing.JButton jButtonDisplayallPerson;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonInsert_addr;
    private javax.swing.JButton jButtonInsert_cd;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JButton jButtonUpdate_addr;
    private javax.swing.JButton jButtonUpdate_cd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAddCountry;
    private javax.swing.JLabel jLabelAddr_id;
    private javax.swing.JLabel jLabelAddress;
    private javax.swing.JLabel jLabelCid;
    private javax.swing.JLabel jLabelCity;
    private javax.swing.JLabel jLabelContactNo;
    private javax.swing.JLabel jLabelDob;
    private javax.swing.JLabel jLabelDobDay;
    private javax.swing.JLabel jLabelDobMonth;
    private javax.swing.JLabel jLabelDobYear;
    private javax.swing.JLabel jLabelDos;
    private javax.swing.JLabel jLabelDosDay;
    private javax.swing.JLabel jLabelDosMonth;
    private javax.swing.JLabel jLabelDosYear;
    private javax.swing.JLabel jLabelEmail_Id;
    private javax.swing.JLabel jLabelFirstName;
    private javax.swing.JLabel jLabelGender;
    private javax.swing.JLabel jLabelLastName;
    private javax.swing.JLabel jLabelState;
    private javax.swing.JLabel jLabelZipCode;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable_display_address;
    private javax.swing.JTable jTable_display_cdetails;
    private javax.swing.JTable jTable_display_person;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextFieldAddr_id;
    private javax.swing.JTextField jTextFieldCid;
    private javax.swing.JTextField jTextFieldCity;
    private javax.swing.JTextField jTextFieldContactNo;
    private javax.swing.JTextField jTextFieldCountry;
    private javax.swing.JComboBox<String> jTextFieldDobDay;
    private javax.swing.JComboBox<String> jTextFieldDobMonth;
    private javax.swing.JTextField jTextFieldDobYear;
    private javax.swing.JComboBox<String> jTextFieldDosDay;
    private javax.swing.JComboBox<String> jTextFieldDosMonth;
    private javax.swing.JTextField jTextFieldDosYear;
    private javax.swing.JTextField jTextFieldEmail_Id;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldFirstName4;
    private javax.swing.JComboBox<String> jTextFieldGender;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JTextField jTextFieldState;
    // End of variables declaration//GEN-END:variables
}
