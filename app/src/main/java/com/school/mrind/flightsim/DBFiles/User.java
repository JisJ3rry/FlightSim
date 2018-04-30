package com.school.mrind.flightsim.DBFiles;

import android.arch.persistence.room.*;
import android.support.annotation.NonNull;

@Entity(indices = {@Index(value = {"nome"}, unique = true)})

public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "classname")
    public String classname;

    @ColumnInfo(name = "studentnum")
    public int studentnum;

    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "interrogato")
    public boolean interrogato;

    public int getUid(){
        return uid;
    }

    public String getClassname(){
        return classname;
    }

    public void setClassname(String classname){
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