package org.trnt.safeflood.model;

import java.io.Serializable;
import java.util.Objects;

public class AreaEventoId implements Serializable {
    
    public Long idAreaRisco;
    public Long idEvento;
    
    public AreaEventoId() {}
    
    public AreaEventoId(Long idAreaRisco, Long idEvento) {
        this.idAreaRisco = idAreaRisco;
        this.idEvento = idEvento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AreaEventoId that = (AreaEventoId) o;
        return Objects.equals(idAreaRisco, that.idAreaRisco) && Objects.equals(idEvento, that.idEvento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAreaRisco, idEvento);
    }
}
