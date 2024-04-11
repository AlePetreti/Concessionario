package concessionario.view;

public class Event {
    
    private TipoEvento tipo;

    public Event(TipoEvento tipo) {
        this.tipo = tipo;
    }

    public TipoEvento getTipoEvento() {
        return this.tipo;
    }
}
