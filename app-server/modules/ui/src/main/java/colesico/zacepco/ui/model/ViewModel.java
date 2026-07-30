package colesico.zacepco.ui.model;


abstract public class ViewModel {

    protected Notice notice;

    public ViewModel() {
    }

    public ViewModel(Notice notice) {
        this.notice = notice;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }
}
