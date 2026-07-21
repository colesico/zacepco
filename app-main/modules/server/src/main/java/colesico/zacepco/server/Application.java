package colesico.zacepco.server;

import colesico.framework.httpserver.HttpServer;
import colesico.framework.ioc.IocBuilder;

public class Application {

    static void main(String[] args) {
        IO.println("Zacepco server is starting...");
        long startTime = System.nanoTime();
        IocBuilder.create().build().instance(HttpServer.class).start();
        long endTime = System.nanoTime();
        long durationNano = endTime - startTime;
        double durationMillis = durationNano / 1_000_000.0;
        IO.println(String.format("Server has been started in %.2f ms", durationMillis));
    }

}
