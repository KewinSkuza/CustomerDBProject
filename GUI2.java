package oosdProject;

import java.util.Calendar;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;



public class GUI2 extends JFrame {
	static final String DATABASE_URL = "jdbc:mysql://localhost/jdbcproject";

    public static void main(String[] args) {
    	
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI2().setVisible(true);
            }//run
        });//invokeLater
    }//main
    
    private JTable table;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JButton create;
    private JButton update;
    private JButton delete;
    private JButton queryDatabase;
    private JPanel buttonPanel;
    private JPanel txtFieldPanel;
    private JTextField txtField1;
    private JTextField txtField2;
    private JTextField txtField3;
    private JTextField txtField4;
    private JTextField txtField5;
    private JTextField txtField6;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JMenuBar menuBar;
    private JMenu customerTableMenu;
    private JMenuItem customerTableItem;
    private JMenuItem productsTableItem;
    private JMenuItem invoiceTableItem;
    private String lastName;
    private String firstName;
    private String address;
    private int phoneNumber;
    private String email;
    private String productName;
    private String description;
    private int price;
    private int delID;
    private String distributor;
    private int quantity;
    private Calendar calendar;
    private Date orderDate;
    private int noOfDays = 14;
    private Date deliveryDate;
    private int personid;
    private int productid;

//Loads in data from persons table in database and modifies text fields and labels to fit in 
  public void customerTable() {
	  loadData("persons"); 
	  label1.setText("Last Name");
	  txtField1.setVisible(true);
	  label2.setText("First Name");
	  txtField2.setVisible(true);
	  label3.setText("Address");
	  txtField3.setVisible(true);
	  label4.setText("Phone Number");
	  txtField4.setVisible(true);
	  label5.setText("Email");
	  txtField5.setVisible(true);
	  label6.setText("Person ID");
	  txtField6.setVisible(true);
	  
	  //create item in persons table
	  create.addActionListener(new ActionListener(){
	  		public void actionPerformed(ActionEvent e){
	  			lastName = txtField1.getText();
	  			firstName = txtField2.getText();
	  			address = txtField3.getText();
	  			phoneNumber = Integer.parseInt(txtField4.getText());
	  			email = txtField5.getText();
	  			CreatePersons.inPersons(firstName, lastName, address, phoneNumber, email);
	  			loadData("persons"); 
	  			
				txtField1.setText("");
				txtField2.setText("");
				txtField3.setText("");
				txtField4.setText("");
				txtField5.setText("");
				txtField6.setText("");
	  		}//actionPerformed
		  });//actionListener
	  
	  //delete item from persons table
	  delete.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
				delID = Integer.parseInt(txtField6.getText());
				DeletePersons.delPersons(delID);
	  			loadData("persons");
  			
				txtField1.setText("");
				txtField2.setText("");
				txtField3.setText("");
				txtField4.setText("");
				txtField5.setText("");
				txtField6.setText("");
		}//actionPerformed
	  });//actionListener
	  
	  //update an item in the persons table
	  update.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e) {
		  		lastName = txtField1.getText();
		  		firstName = txtField2.getText();
		  		address = txtField3.getText();
		  		phoneNumber = Integer.parseInt(txtField4.getText());
		  		email = txtField5.getText();
				delID = Integer.parseInt(txtField6.getText());
				UpdatePersons.UpPersons(delID, phoneNumber, firstName, lastName, address, email);
		  		loadData("persons");
	  		
		  		txtField1.setText("");
		  		txtField2.setText("");
		  		txtField3.setText("");
		  		txtField4.setText("");
		  		txtField5.setText("");
		  		txtField6.setText("");
		  }//actionPerformed
	  });//actionListener
  }//customerTable

//Loads in data from products table in database and modifies text fields and labels to fit in 
  public void productsTable() {
	  loadData("products");
	  label1.setText("Product Name");
	  txtField1.setVisible(true);
	  label2.setText("Description");
	  txtField2.setVisible(true);
	  label3.setText("Price");
	  txtField3.setVisible(true);
	  label4.setText("");
	  txtField4.setVisible(false);
	  label5.setText("");
	  txtField5.setVisible(false);
	  label6.setText("Product ID");
	  txtField6.setVisible(true);
	  
	  //add item to the products table
	  create.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
				  productName = txtField1.getText();
				  description = txtField2.getText();
				  price = Integer.parseInt(txtField3.getText());
				  CreateProducts.inProducts(price, productName, description);
				  loadData("products");
				  
				  txtField1.setText("");
				  txtField2.setText("");
				  txtField3.setText("");
				  txtField4.setText("");
				  txtField5.setText("");
				  txtField6.setText("");
		  }//actionPerformed  
	  });//actionListener
	  
	  //delete item from products tables
	  delete.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
				  delID = Integer.parseInt(txtField6.getText());
				  DeleteProducts.delProducts(delID);
		  		  loadData("products");
  			
			      txtField1.setText("");
			      txtField2.setText("");
			      txtField3.setText("");
			      txtField4.setText("");
			      txtField5.setText("");
			      txtField6.setText("");
		}//actionPerformed
	  });//actionListener
	  
	  //update item in products table
	  update.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e) {
				  productName = txtField1.getText();
				  description = txtField2.getText();
				  price = Integer.parseInt(txtField3.getText());
				  delID = Integer.parseInt(txtField6.getText());
				  UpdateProducts.upProducts(delID, productName, description, price);
				  loadData("products");
	  		
				  txtField1.setText("");
				  txtField2.setText("");
				  txtField3.setText("");
				  txtField4.setText("");
				  txtField5.setText("");
				  txtField6.setText("");
		  }//actionPerformed
	  });//actionListener
  }//productsTable
  
//Loads in data from invoice table in database and modifies text fields and labels to fit in 
  public void invoiceTable() {
	  loadData("invoice");
	  label1.setText("Distributor");
	  txtField1.setVisible(true);
	  label2.setText("Quantity");
	  txtField2.setVisible(true);
	  label3.setText("Person ID");
	  txtField3.setVisible(true);
	  label4.setText("Product ID");
	  txtField4.setVisible(true);
	  label5.setText("");
	  txtField5.setVisible(false);
	  label6.setText("Invoice ID");
	  txtField6.setVisible(true);
	  
	  //Create item in invoice table
	  create.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  distributor = txtField1.getText();
			  quantity = Integer.parseInt(txtField2.getText());
	
			  calendar = Calendar.getInstance();
			  orderDate = new Date(calendar.getTime().getTime());
			  
			  calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
			  deliveryDate = new Date(calendar.getTime().getTime());
			  
			  personid = Integer.parseInt(txtField3.getText());
			  productid = Integer.parseInt(txtField4.getText());
			  
			  CreateInvoice.createIn(distributor, quantity, orderDate, deliveryDate, personid, productid);
			  
			  loadData("invoice");
			  
			  txtField1.setText("");
			  txtField2.setText("");
			  txtField3.setText("");
			  txtField4.setText("");
			  txtField5.setText("");
			  txtField6.setText("");
		  }//actionPerformed
	  });//actionListener
	  
	  //Delete item from invoice table
	  delete.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
			  delID = Integer.parseInt(txtField6.getText());
			  DeleteInvoice.delInvoice(delID);
	  		  loadData("invoice");
			
		      txtField1.setText("");
		      txtField2.setText("");
		      txtField3.setText("");
		      txtField4.setText("");
		      txtField5.setText("");
		      txtField6.setText("");
		  }
	  });
	  
	  //Update item in invoice table
	  update.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e) {
				  distributor = txtField1.getText();
				  quantity = Integer.parseInt(txtField2.getText());
				  personid = Integer.parseInt(txtField3.getText());
				  productid = Integer.parseInt(txtField4.getText());
				  delID = Integer.parseInt(txtField6.getText());
				  UpdateInvoice.upInvoice(delID, distributor, quantity, personid, productid);
				  loadData("invoice");
	  		
				  txtField1.setText("");
				  txtField2.setText("");
				  txtField3.setText("");
				  txtField4.setText("");
				  txtField5.setText("");
				  txtField6.setText("");
		  }//actionPerformed
	  });//actionListener
  }//invoiceTable
  
    public GUI2() throws HeadlessException {
//terminates the program when x button is pressed
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        table = new JTable(tableModel);
        
        //adds a scroll pane to the table
        getContentPane().add(new JScrollPane(table), BorderLayout.EAST);
        
        //sets the layout of the panel field to flowLayout and attaches that field
        txtFieldPanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) txtFieldPanel.getLayout();
        flowLayout.setVgap(15);
        flowLayout.setHgap(15);
        flowLayout.setAlignment(FlowLayout.LEFT);
        getContentPane().add(txtFieldPanel, BorderLayout.CENTER);
        
        //Label #1
        label1 = new JLabel("");
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        label1.setVerticalAlignment(SwingConstants.TOP);
        txtFieldPanel.add(label1);
        
        //Text Field Number 1
        txtField1 = new JTextField();
        txtField1.setHorizontalAlignment(SwingConstants.LEFT);
        txtFieldPanel.add(txtField1);
        txtField1.setColumns(10);
        txtField1.setVisible(false);
        
        //Label #2
        label2 = new JLabel("");
        label2.setVerticalAlignment(SwingConstants.TOP);
        label2.setHorizontalAlignment(SwingConstants.LEFT);
        txtFieldPanel.add(label2);
        
        //Text Field Number 2 
        txtField2 = new JTextField();
        txtField2.setHorizontalAlignment(SwingConstants.LEFT);
        txtFieldPanel.add(txtField2);
        txtField2.setColumns(10);
        txtField2.setVisible(false);
        
        //Label #3
        label3 = new JLabel("");
        label3.setVerticalAlignment(SwingConstants.TOP);
        label3.setHorizontalAlignment(SwingConstants.LEFT);
        txtFieldPanel.add(label3);
        
        //Text Field Number 3
        txtField3 = new JTextField();
        txtField3.setHorizontalAlignment(SwingConstants.LEFT);
        txtFieldPanel.add(txtField3);
        txtField3.setColumns(10);
        txtField3.setVisible(false);
        
        //Label #4
        label4 = new JLabel("");
        label4.setVerticalAlignment(SwingConstants.TOP);
        label4.setHorizontalAlignment(SwingConstants.LEFT);
        txtFieldPanel.add(label4);
        
        //Text Field Number 4
        txtField4 = new JTextField();
        txtField4.setHorizontalAlignment(SwingConstants.LEFT);
        txtFieldPanel.add(txtField4);
        txtField4.setColumns(10);
        txtField4.setVisible(false);
        
        //Label #5
        label5 = new JLabel("");
        label5.setVerticalAlignment(SwingConstants.TOP);
        label5.setHorizontalAlignment(SwingConstants.LEFT);
        txtFieldPanel.add(label5);
      
        //Text Field Number 5
        txtField5 = new JTextField();
        txtField5.setHorizontalAlignment(SwingConstants.LEFT);
        txtFieldPanel.add(txtField5);
        txtField5.setColumns(10);
        txtField5.setVisible(false);
        
        //Label #6
        label6 = new JLabel("");
        txtFieldPanel.add(label6);
        
        //Text Field Number 6
        txtField6 = new JTextField();
        txtField6.setHorizontalAlignment(SwingConstants.LEFT);
        txtFieldPanel.add(txtField6);
        txtField6.setColumns(10);
        txtField6.setVisible(false);
        
        buttonPanel = new JPanel();
        create = new JButton("Create");
        update = new JButton("Update");
        delete = new JButton("Delete");
        queryDatabase = new JButton("Query Database");
        buttonPanel.add(create);
        buttonPanel.add(update);
        buttonPanel.add(delete);
        buttonPanel.add(queryDatabase);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        
        setSize(640, 580);
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        customerTableMenu = new JMenu("Change Table");
        menuBar.add(customerTableMenu);
        
        customerTableItem = new JMenuItem("Customer Table");
        customerTableMenu.add(customerTableItem);
        
        productsTableItem = new JMenuItem("Products Table");
        customerTableMenu.add(productsTableItem);
        
        invoiceTableItem = new JMenuItem("Invoice Table");
        customerTableMenu.add(invoiceTableItem);
        
        customerTableItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
          	  	tableModel.setRowCount(0);
            	customerTable();
            }
        });//Customer actionListener
        
        productsTableItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {	
          	  	tableModel.setRowCount(0);
                productsTable();
            }
        });//Products actionListener
        
        invoiceTableItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
          	  	tableModel.setRowCount(0);
                invoiceTable();
            }
        });//Invoice actionListener
        
    	queryDatabase.addActionListener(new ActionListener() {
  		  	public void actionPerformed(ActionEvent e) {
  			  new QueryDatabase();
  		  	}
  	  	});
           
    }//GUI2 throws

    private void loadData(String table) {
    	Connection connection = null; // manages connection
	    Statement statement = null; // query statement

        try {
        	connection = DriverManager.getConnection( DATABASE_URL, "root", "password" );
        	statement = connection.createStatement();
        	ResultSet resultSet = statement.executeQuery("SELECT * FROM " + table);
        	ResultSetMetaData metaData = resultSet.getMetaData();

            // Names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }//for

            // Data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            
            while (resultSet.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(resultSet.getObject(i));
                }//for
                data.add(vector);
            }//while
            
            tableModel.setDataVector(data, columnNames);
        }//try 
        catch (Exception e) {
        	
        	e.printStackTrace();
        }//catch
    }//loadData
    
    
}//GUI2