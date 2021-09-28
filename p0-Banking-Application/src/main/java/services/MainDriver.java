package services;

import io.javalin.Javalin;
import utils.ConnectionFactory;
import controllers.CustomerController;
import controllers.AccountController;
import java.sql.Connection;

public class MainDriver {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(3650);
        Connection conn = ConnectionFactory.getConnection();
        CustomerController.init(app);
        AccountController.init(app);
    }
}
