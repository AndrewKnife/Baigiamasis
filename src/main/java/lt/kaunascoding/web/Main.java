package lt.kaunascoding.web;


import lt.kaunascoding.web.model.mysql.MysqlConnector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        Connection as = new MysqlConnector().Connect();
    }
}
