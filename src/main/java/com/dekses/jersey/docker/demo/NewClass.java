/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dekses.jersey.docker.demo;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.ArrayList;
import model.Miembro;

/**
 *
 * @author cspor
 */
public class NewClass {

    public static void main(String[] args) throws SQLException {
        DB.DB_Requests db_Requests = new DB.DB_Requests();

        ArrayList<Miembro> list = new ArrayList<Miembro>();
        list = db_Requests.Miembros();
        System.out.println(list.size());
        while (!list.isEmpty()) {
            int index = 0;
            int v = list.get(0).getPuntos();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getPuntos() > v) {
                    index = i;
                    v = list.get(i).getPuntos();
                }

            }

            System.out.println(list.remove(index));

        }
    }
}
