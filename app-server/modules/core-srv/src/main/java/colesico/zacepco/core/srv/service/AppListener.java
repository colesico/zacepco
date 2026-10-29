package colesico.zacepco.core.srv.service;

public interface AppListener {

    void onStartApp();

    default void onStopApp() {

    }

}
