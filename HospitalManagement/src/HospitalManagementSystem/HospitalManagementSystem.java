package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;



public class HospitalManagementSystem {
	private static final String url="jdbc:mysql://localhost:3306/hospital";
	private static final String username="root";
	private static final String password="#Shruti28";
	
	public static void main(String[] args) {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		Scanner sc=new Scanner(System.in);
		try
		{
			Connection connection=DriverManager.getConnection(url,username,password);
			
			Patient patient=new Patient(connection, sc);
			Doctor doctor=new Doctor(connection);
			
			while(true)
			{
				System.out.println("HOSPITAL MANAGEMENT SYSTEM");
				System.out.println("1. Add Patient");
				System.out.println("2. View Patient");
				System.out.println("3. View Doctors");
				System.out.println("4. Book Appointment");
				System.out.println("5. Exit");
				
				System.out.println("Enter Your Choice: ");
				int ch=sc.nextInt();
				switch(ch)
				{
					case 1:
						patient.addPatient();
						System.out.println();
						break;
					case 2:
						patient.viewPatient();
						System.out.println();
						break;
					case 3:
						doctor.viewDoctors();
						System.out.println();
						break;
					case 4:
						bookAppointment(patient, doctor, connection, sc);
						System.out.println();
						break;
					case 5:
						return;
					default:
						System.out.println("Enter Valid Choice!!");
				}
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void bookAppointment(Patient patient,Doctor doctor,Connection connection,Scanner scanner)
	{
		System.out.println("Enter Patient ID:");
		int patientID=scanner.nextInt();
		
		System.out.println("Enter Doctor ID:");
		int doctorID=scanner.nextInt();
		
		System.out.println("Enter Date (YYYY-MM-DD):");
		String appointmentDate=scanner.next();
		
		if(patient.getPatientById(patientID) && doctor.getDoctorById(doctorID))
		{
			if(checkDoctorAvailability(doctorID,appointmentDate,connection))
			{
				String appointmentQuery="insert into appointments(patient_id,doctor_id,appointment_date) values(?,?,?)";
				try
				{
					PreparedStatement preparedStatemnet=connection.prepareStatement(appointmentQuery);
					preparedStatemnet.setInt(1, patientID);
					preparedStatemnet.setInt(2, doctorID);
					preparedStatemnet.setString(3, appointmentDate);
					int rowsAffected=preparedStatemnet.executeUpdate();
					if(rowsAffected>0)
						System.out.println("Appointment Booked!");
					else
						System.out.println("Failed to book Appointment!");
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
			else
				System.out.println("Doctor Not Available on this date!");
		}
		else
			System.out.println("Either Doctor or Patient Doesn't Exist!!!");
			
	}

	public static boolean checkDoctorAvailability(int doctorID, String appointmentDate,Connection connection) 
	{
		String query="select count(*) from appointments where doctor_id=? and appointment_date=?";
		try
		{
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, doctorID);
			preparedStatement.setString(2, appointmentDate);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				int count=resultSet.getInt(1);
				if(count==0)
					return true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
