package com.example.agendaapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "citas")
public class Cita {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id_cita")
    public String idCita;
    @ColumnInfo(name = "nombre_cliente")
    public String nomCliente;
    @ColumnInfo(name = "tel_cliente")
    public String telCliente;
    @ColumnInfo(name = "asunto_cliente")
    public String asuntoCita;
    @ColumnInfo(name = "hora_cita")
    public String horaCita;
    @ColumnInfo(name = "dia_cita")
    public String diaCita;
}
