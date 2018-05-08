package com.rogzart.proyecto_interfaces.Adultos;

/**
 * Created by dany on 15/03/2018.
 */
import java.util.ArrayList;
public class AdultosContenido {

    private static ArrayList<Adultos> personList=new ArrayList();
    public static ArrayList getPersonList(){
        return personList;
    }
    public static void loadPersonList(){
        Adultos person;
        person=new Adultos();
        person.setId(1);
        person.setNombre("Alvaro");
        person.setApellidop("Garcia");
        person.setApellidom("Bernal");
        person.setDiabetico(Boolean.TRUE);
        personList.add(person);
        person=new Adultos();
        person.setId(2);
        person.setNombre("Rodrigo");
        person.setApellidop("Hurtado");
        person.setApellidom("Dominguez");
        person.setDiabetico(Boolean.TRUE);
        personList.add(person);
        person=new Adultos();
        person.setId(2);
        person.setNombre("Steven");
        person.setApellidop("Johnson");
        person.setApellidom("Rico");
        person.setDiabetico(Boolean.FALSE);
        personList.add(person);
    }
}
