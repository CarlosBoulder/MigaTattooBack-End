package com.example.MigaTattoAgenda.projections;

import java.time.LocalDateTime;

public interface LastSessionProjection {
    Long getCostumer_id();

    String getCostumer_name();

    String getCostumer_lastname();

    Long getTattoo_id();

    String getTattoo_name();

    LocalDateTime getSession_date();
}
