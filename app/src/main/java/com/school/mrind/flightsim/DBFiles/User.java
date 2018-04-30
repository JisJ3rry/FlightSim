package com.school.mrind.flightsim.DBFiles;

import android.arch.persistence.room.*;

@Entity(primaryKeys = {"classname", "studentnum"})

public class User {
    public String classname;
    public int studentnum;

    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "interrogato")
    public boolean interrogato;

    public String getClassnum(){
        return classname;
    }

    public void setClassnum(String classname){
        this.classname = classname;
    }

    public int getStudentnum(){
        return studentnum;
    }

    public void setStudentnum(int studentnum){
        this.studentnum = studentnum;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public boolean isInterrogato(){
        return interrogato;
    }

    public void setInterrogato(boolean interrogato){
        this.interrogato = interrogato;
    }

}