/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dekses.jersey.docker.demo;

import java.util.Comparator;
import model.Miembro;

/**
 *
 * @author cspor
 */
class NumberComparator implements Comparator<Miembro> {

    public int compare(Miembro o1, Miembro o2) {
        int num1 = o1.getPuntos();
        int num2 = o2.getPuntos();
        if (num1 > num2) {
            return 1;
        }
        if (num1 < num2) {
            return -1;
        } else {
            return 0;
        }
    }
}
