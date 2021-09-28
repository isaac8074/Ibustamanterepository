package controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import daos.CustomerDAOCommands;
import utils.ConnectionFactory;
import services.DAOCheck;

public class CustomerController {
    private static Javalin javalin;

    public static void init(Javalin app) {
        javalin = app;
        app.get("/client", CustomerController::getAllClients);
        app.get("/client/:id", CustomerController::getClientByID);
        app.post("/client/:name", CustomerController::insertClient);
        app.put("/client/:id/:name", CustomerController::updateClient);
        app.delete("/client/:id", CustomerController::deleteClient);
    }

    public static void getAllClients(Context ctx) {
        CustomerDAOCommands dao = new CustomerDAOCommands(ConnectionFactory.getConnection());
        ctx.json(dao.getAllClients());
        ctx.status(200);
    }

    public static void getClientByID(Context ctx) {
        CustomerDAOCommands dao = new CustomerDAOCommands(ConnectionFactory.getConnection());
        Integer id = Integer.parseInt(ctx.pathParam("id"));
        if (!DAOCheck.CustomerExists(dao, id)) {
            ctx.status(400);
            ctx.result("No such client found.");
        }

        ctx.json(dao.getClientByID(id));
    }

    public static void insertClient(Context ctx) {
        CustomerDAOCommands dao = new CustomerDAOCommands(ConnectionFactory.getConnection());
        String name = ctx.pathParam("name");
        dao.createClient(name);
        ctx.status(201);
    }

    public static void updateClient(Context ctx) {
        CustomerDAOCommands dao = new CustomerDAOCommands(ConnectionFactory.getConnection());
        Integer id = Integer.parseInt(ctx.pathParam("id"));
        String name = ctx.pathParam("name");
        if (!DAOCheck.CustomerExists(dao, id)) {
            ctx.status(400);
            ctx.result("No such client found.");
        }
        else {
            dao.updateClient(id, name);
        }

    }

    public static void deleteClient(Context ctx) {
        CustomerDAOCommands dao = new CustomerDAOCommands(ConnectionFactory.getConnection());
        Integer id = Integer.parseInt(ctx.pathParam("id"));
        if (!DAOCheck.CustomerExists(dao, id)) {
            ctx.status(400);
            ctx.result("No such client found.");
        }
        else {
            dao.deleteClient(id);
            ctx.status(205);
        }
    }

}