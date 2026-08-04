package colesico.zacepco.common.srv.service;

public interface AppListener {

    void onStartApp();

    default void onStopApp() {

    }

}
