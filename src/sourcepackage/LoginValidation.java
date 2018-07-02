/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sourcepackage;

/**
 *
 * @author KELECHI
 */
public class LoginValidation {
    private Administrator adminDetails;
    private Student studentDetails;
    
    public boolean validateAdmin(Administrator admin) {
        Administrator admin_details = new DatabaseOperations().getAdminDetails(admin.getId());
        boolean adminIsValid = false;
        if (admin_details != null) {
            String username = admin.getId();
            String password = admin.getPassword();
            String dbusername = admin_details.getId();
            String dbpassword = admin_details.getPassword();

            if (username.equals(dbusername) && password.equals(dbpassword)) {
                adminIsValid = true;
                adminDetails = admin_details;
            }
        }
        return adminIsValid;
    }
    
    public Administrator getAdminDetails(){
        return adminDetails;
    }

    public boolean validateStudent(Student student) {
        Student student_details = new DatabaseOperations().getStudentDetails(student.getID());
        boolean studentIsValid = false;
        if (student_details != null) {
            String username = student_details.getID(), password = student_details.getPassword();
            if (student.getID().equals(username) && student.getPassword().equals(password)) {
                studentIsValid = true;
                studentDetails = student_details;
            }
        }
        return studentIsValid;
    }
    
    public Student getStudentDetails(){
        return studentDetails;
    }
}
