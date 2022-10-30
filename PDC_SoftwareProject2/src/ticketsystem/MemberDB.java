package ticketsystem;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class MemberDB extends DBManager {
    public MemberDB() {
        super();
        createTable();
    }
    
    // Query the MEMBERS table for entries containing the input username.
    public boolean containsMember(String username) {
        String sqlQuery = "SELECT * FROM MEMBERS WHERE USERNAME='"+username+"'";
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                return true;
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // Return true if a generated member ID already exists in the MEMBERS table.
    public boolean containsMemberID(int memberID) {
        String sqlQuery = "SELECT * FROM MEMBERS WHERE MEMBER_ID="+memberID;
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                return true;
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    // Delete entry from the MEMBERS table matching the member's username.
    public void deleteMember(Member member) {
        String memberDelete = "DELETE FROM MEMBERS WHERE USERNAME='"+member.getUsername()+"'";
        try {
            statement.executeUpdate(memberDelete);
        } 
        catch (SQLException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Update PA_EMAIL field in the MEMBERS table where username matches the member's username.
    public void updatePayAccount(Member member) {
        String paUpdate = "";
        if (member.getPayAccount() == null) {
            // Member has removed their Pay Account, so set PA_EMAIL to be null.
            paUpdate = "UPDATE MEMBERS SET PA_EMAIL = null WHERE USERNAME = '"+member.getUsername()+"'";
        }
        else {
            paUpdate = "UPDATE MEMBERS SET PA_EMAIL = '"+member.getPayAccount().getEmail()+"' WHERE USERNAME = '"+member.getUsername()+"'";
        }
        try {
            statement.executeUpdate(paUpdate);
        } 
        catch (SQLException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Update a member's password in the MEMBERS table.
    public void changePassword(Member member, String newPassword) {
        String passwordUpdate = "UPDATE MEMBERS SET PASSWORD = '"+newPassword+"' WHERE USERNAME = '"+member.getUsername()+"'";
        try {
            statement.executeUpdate(passwordUpdate);
        } 
        catch (SQLException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Query MEMBERS table for an entry matching the input username, return a Member object passing in member fields.
    public Member loadMember(String username) {
        String sqlQuery = "SELECT * FROM MEMBERS WHERE USERNAME='"+username+"'";
        try {
            ResultSet rs = statement.executeQuery(sqlQuery);
            if (rs.next()) {
                if (rs.getString("PA_EMAIL") == null) {
                    // Return a Member object that does not have a PayAccount.
                    return new Member(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("FULLNAME"), rs.getInt("MEMBER_ID"), rs.getDate("MEMBERSHIP_EXPIRY").toLocalDate());
                }
                else {
                    return new Member(rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("FULLNAME"), new PayAccDB().loadPayAcc(rs.getString("PA_EMAIL")), rs.getInt("MEMBER_ID"), rs.getDate("MEMBERSHIP_EXPIRY").toLocalDate());
                }
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // Insert Member object fields into the MEMBERS table.
    public void saveMember(Member member) {
        String insertMember = "";
        if (member.getPayAccount() == null) {
            // Insert null in place of PA_EMAIL.
            insertMember = "INSERT INTO MEMBERS VALUES ("+member.getMemberID()+", '"
                +Date.valueOf(member.getExpiry())+"', '"
                +member.getUsername()+"', '"
                +member.getPassword()+"', "
                +member.getFullname()+"', null)";
        }
        else {
            insertMember = "INSERT INTO MEMBERS VALUES ("+member.getMemberID()+", '"
                +Date.valueOf(member.getExpiry())+"', '"
                +member.getUsername()+"', '"
                +member.getPassword()+"', '"
                +member.getFullname()+"', '"
                +member.getPayAccount().getEmail()+"')";
        }
        try {
            statement.executeUpdate(insertMember);
        } 
        catch (SQLException ex) {
            Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void createTable() {
        if (!this.containsTable("MEMBERS")) {
            String memberCreate = "CREATE TABLE MEMBERS(MEMBER_ID INT, MEMBERSHIP_EXPIRY DATE, USERNAME VARCHAR(20), PASSWORD VARCHAR(20), FULLNAME VARCHAR(40), PA_EMAIL VARCHAR(40))";
            try {
                statement.executeUpdate(memberCreate);
            } 
            catch (SQLException ex) {
                Logger.getLogger(MemberDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
