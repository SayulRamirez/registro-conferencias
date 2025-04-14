package com.registroconferencias.repositories.projections;

import java.time.LocalDate;
import java.time.LocalTime;

public interface SessionInfo {

    Long getIdSession();

    Long getIdRoom();

    String getNameRoom();

    LocalDate getDate();

    LocalTime getStartTime();

    String getTitle();

    String getDescription();

    boolean isSoldOut();

    String getStreet();

    String getNumber();

    String getColony();

    String getZipCode();

    String getCity();

    String getState();
}
