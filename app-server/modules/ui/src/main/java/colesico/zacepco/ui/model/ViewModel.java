package colesico.zacepco.ui.model;


import colesico.framework.service.LocalParam;

abstract public class ViewModel {

    @LocalParam
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
