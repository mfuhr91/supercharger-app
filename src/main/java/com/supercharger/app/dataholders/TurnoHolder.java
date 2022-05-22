package com.supercharger.app.dataholders;

import com.supercharger.app.models.Turno;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TurnoHolder {

    private Turno turno;

    private TurnoHolder() {
    }

    private static final class InstanceHolder {
        private final static TurnoHolder instance = new TurnoHolder();
    }

    public static TurnoHolder getInstance() {
        return InstanceHolder.instance;
    }
}
