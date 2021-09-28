package controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import models.Customer;
import models.Account;
import daos.AccountDAOCommands;
import daos.CustomerDAOCommands;
import utils.ConnectionFactory;
import services.DAOCheck;
public class AccountController {
    private static Javalin javalin;
    public static void init(Javalin app)    {
        javalin = app;
        /*
         The reason for changing my variable to client because I want to stay consistent with what was on Project sheet.
          I went ahead and made this class and the customer class consistent with Postman. Most of my past classes were
           consistent with my table in SQL which had my databases.
         */
        app.get("/client/:clientID/accounts", AccountController::getAllClientAccounts);
        app.get("/client/:clientID/accounts/:accountID", AccountController::getClientAccountByID);
        app.get("/client/:clientID/accounts?amountLessThan=:amount", AccountController::getClientAccountLessThan);
        app.get("/client/:clientID/accounts?amountGreaterThan=:amount", AccountController::getClientAccountGreaterThan);
        app.get("/client/:clientID/accounts?amountLessThan=:maxamount&amountGreaterThan=:minamount", AccountController::getClientAccountRange);
        app.patch("/client/:clientID/accounts/:accountID", AccountController::depositOrWithdraw);
        app.patch("/clientF/:clientFrom/clientT/:clientTo/accounts/:accountFrom/transfer/:accountTo", AccountController::transferAccount);
        app.post("/client/:clientID/accounts/:balance", AccountController::insertAccount);
        app.put("/client/:clientID/accounts/:accountID/:amount", AccountController::updateAccount);
        app.delete("/client/:clientID/accounts/:accountID", AccountController::deleteAccount);
    }

    public static void getAllClientAccounts(Context ctx) {
        AccountDAOCommands dao = new AccountDAOCommands(ConnectionFactory.getConnection());
        int clientID = Integer.parseInt(ctx.pathParam("clientID"));
        ctx.json(dao.getAllClientAccounts(clientID));
    }

    public static void getClientAccountByID(Context ctx) {
        AccountDAOCommands dao = new AccountDAOCommands(ConnectionFactory.getConnection());
        int clientID = Integer.parseInt(ctx.pathParam("clientID"));
        int accountID = Integer.parseInt(ctx.pathParam("accountID"));
        if (!DAOCheck.AccountExists(dao, clientID, accountID)) {
            ctx.status(404);
            ctx.result("No account exists");
        }
        else {
            ctx.json(dao.getClientAccountByID(clientID, accountID));
        }

    }

    public static void getClientAccountLessThan(Context ctx) {
        AccountDAOCommands accountDAO = new AccountDAOCommands(ConnectionFactory.getConnection());
        CustomerDAOCommands clientDAO = new CustomerDAOCommands(ConnectionFactory.getConnection());
        int clientID = Integer.parseInt(ctx.pathParam("clientID"));
        float amount = Float.parseFloat(ctx.pathParam("amount"));
        if (!DAOCheck.CustomerExists(clientDAO, clientID)) {
            ctx.status(404);
            ctx.result("No such client exists");
        }
        else {
            ctx.json(accountDAO.getClientAccountsByMaxAmt(clientID, amount));
        }

    }

    public static void getClientAccountGreaterThan(Context ctx) {
        AccountDAOCommands dao = new AccountDAOCommands(ConnectionFactory.getConnection());
        CustomerDAOCommands clientDAO = new CustomerDAOCommands(ConnectionFactory.getConnection());
        int clientID = Integer.parseInt(ctx.pathParam("clientID"));
        float amount = Float.parseFloat(ctx.pathParam("amount"));
        if (!DAOCheck.CustomerExists(clientDAO, clientID)) {
            ctx.status(404);
            ctx.result("No such client exists");
        }
        else {
            ctx.json(dao.getClientAccountsByMinAmt(clientID, amount));
        }

    }

    public static void getClientAccountRange(Context ctx) {
        AccountDAOCommands dao = new AccountDAOCommands(ConnectionFactory.getConnection());
        CustomerDAOCommands clientDAO = new CustomerDAOCommands(ConnectionFactory.getConnection());
        int clientID = Integer.parseInt(ctx.pathParam("clientID"));
        float minAmount = Float.parseFloat(ctx.pathParam("minAmount"));
        float maxAmount = Float.parseFloat(ctx.pathParam("maxAmount"));
        if (!DAOCheck.CustomerExists(clientDAO, clientID)) {
            ctx.status(404);
            ctx.result("No such client exists");
        }
        else {
            ctx.json(dao.getClientAccountsByRange(clientID, minAmount, maxAmount));
        }

    }

    public static void insertAccount(Context ctx) {
        AccountDAOCommands dao = new AccountDAOCommands(ConnectionFactory.getConnection());
        int clientID = Integer.parseInt(ctx.pathParam("clientID"));
        float balance = Float.parseFloat(ctx.pathParam("balance"));
        dao.createAccount(clientID, balance);
        ctx.status(201);
    }

    public static void updateAccount(Context ctx) {
        AccountDAOCommands dao = new AccountDAOCommands(ConnectionFactory.getConnection());
        int clientID = Integer.parseInt(ctx.pathParam("clientID"));
        int accountID = Integer.parseInt(ctx.pathParam("accountID"));
        float amount = Float.parseFloat(ctx.pathParam("amount"));
        if (!DAOCheck.AccountExists(dao, clientID, accountID)) {
            ctx.status(404);
            ctx.result("No such account exists");
        }
        else {
            dao.update(accountID, amount);
        }

    }

    public static void deleteAccount(Context ctx) {
        AccountDAOCommands dao = new AccountDAOCommands(ConnectionFactory.getConnection());
        int accountID = Integer.parseInt(ctx.pathParam("accountID"));
        int clientID = Integer.parseInt(ctx.pathParam("clientID"));
        if (!DAOCheck.AccountExists(dao, clientID, accountID)) {
            ctx.status(404);
            ctx.result("No such account exists");
        }
        else {
            dao.deleteAccount(accountID);
        }

    }

    public static void depositOrWithdraw(Context ctx) {
        AccountDAOCommands dao = new AccountDAOCommands(ConnectionFactory.getConnection());
        int accountID = Integer.parseInt(ctx.pathParam("accountID"));
        int clientID = Integer.parseInt(ctx.pathParam("clientID"));

        if (!DAOCheck.AccountExists(dao, clientID, accountID)) {
            ctx.status(404);
            ctx.result("No such account exists");
        }

        else {
            String[] jsonObject = jsonParser(ctx.body());
            if (jsonObject[0].equals("deposit")) {
                dao.deposit(accountID, Float.parseFloat(jsonObject[1]));
            }

            else if (jsonObject[0].equals("withdraw")) {
                Account account = dao.getClientAccountByID(clientID, accountID);
                float balance = account.getBalance();
                if (DAOCheck.InsufficientFunds(balance, Float.parseFloat(jsonObject[1]))) {
                    ctx.status(422);
                    ctx.result("Insufficient funds");
                }
                else {
                    dao.withdraw(accountID, Float.parseFloat(jsonObject[1]));
                }
            }
        }
    }

    public static void transferAccount(Context ctx) {
        AccountDAOCommands accountDAO = new AccountDAOCommands(ConnectionFactory.getConnection());
        CustomerDAOCommands clientDAO = new CustomerDAOCommands(ConnectionFactory.getConnection());
        int clientFrom = Integer.parseInt(ctx.pathParam("clientFrom"));
        int clientTo = Integer.parseInt(ctx.pathParam("clientTo"));
        int accountFrom = Integer.parseInt(ctx.pathParam("accountFrom"));
        int accountTo = Integer.parseInt(ctx.pathParam("accountTo"));

        String[] jsonObject = jsonParser(ctx.body());

        if (!DAOCheck.CustomerExists(clientDAO, clientFrom)) {
            ctx.status(404);
            ctx.result("No such client exists");
        }

        else if (!DAOCheck.AccountExists(accountDAO, clientFrom, accountFrom)) {
            ctx.status(404);
            ctx.result("No such account exists");
        }

        else if (!DAOCheck.AccountExists(accountDAO, clientTo, accountTo)) {
            ctx.status(404);
            ctx.result("No such account exists");
        }

        else if (DAOCheck.InsufficientFunds(Float.parseFloat(jsonObject[1]), Float.parseFloat(jsonObject[1]))) {
            ctx.status(422);
            ctx.result("Insufficient funds");
        }

        else {
            accountDAO.withdraw(accountFrom, Float.parseFloat(jsonObject[1]));
            accountDAO.deposit(accountTo, Float.parseFloat(jsonObject[1]));
        }
    }

    public static String[] jsonParser(String json) {
        String[] finishedJSON = new String[2];
        String[] parser = json.split("\"");
        finishedJSON[0] = parser[1];
        parser = parser[2].split(":");
        parser = parser[1].split("}");
        finishedJSON[1] = parser[0];
        return finishedJSON;
    }
}
