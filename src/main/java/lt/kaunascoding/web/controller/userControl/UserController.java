package lt.kaunascoding.web.controller.userControl;

import lt.kaunascoding.web.model.UserSession;
import lt.kaunascoding.web.model.mysql.MysqlCommands;
import lt.kaunascoding.web.model.mysql.classes.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserController {

    public boolean canLogin(User vartotojas) {
        MysqlCommands comm = new MysqlCommands();
        boolean can = false;
        can = comm.checkLogin(vartotojas);
        if(can){
            new UserSession(comm.getUser(vartotojas));
            comm.updateUser(vartotojas);
        }
        return can;
    }

    public boolean Register(User vartotojas){
        vartotojas.setRegDate(new java.sql.Date(System.currentTimeMillis()));
        vartotojas.setLastLoginDate(new Timestamp(System.currentTimeMillis()));
        vartotojas.setLevel("user");
        vartotojas.setPassword(Sha256.getSha256(vartotojas.getPassword()));
        MysqlCommands comm = new MysqlCommands();
        boolean can = false;
        if(comm.userExists(vartotojas)){
            can = false;
        }else{
            can = true;
        }
        if(can){
            comm.insertNewUser(vartotojas);
            new UserSession(vartotojas);
        }
        return can;
    }

    public boolean isEmailGood(String email){
        boolean good = false;
        if(email.contains("@") && email.contains(".")){
            good = true;
        }
        else{
            good=false;
        }
        return good;
    }
}
