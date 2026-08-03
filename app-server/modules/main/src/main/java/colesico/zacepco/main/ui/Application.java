package colesico.zacepco.main.ui;

import colesico.framework.httpserver.HttpServer;
import colesico.framework.ioc.IocBuilder;
import colesico.zacepco.srv.dbstorage.DBStorage;
import colesico.zacepco.srv.filestorage.FileStorage;

public class Application {

    private static void start() {
        var ioc = IocBuilder.create().build();

        // initialize
        ioc.instance(FileStorage.class);
        ioc.instance(DBStorage.class);

        // start http server
        ioc.instance(HttpServer.class).start();
    }

    static void main(String[] args) {
        IO.println("Zacepco server is starting...");
        long startTime = System.nanoTime();
        start();
        long endTime = System.nanoTime();
        long durationNano = endTime - startTime;
        double durationMillis = durationNano / 1_000_000.0;
        IO.println(String.format("Server has been started in %.2f ms", durationMillis));
    }

}
