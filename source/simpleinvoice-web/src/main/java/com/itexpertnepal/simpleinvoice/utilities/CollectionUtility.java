/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itexpertnepal.simpleinvoice.utilities;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author binay
 */
public class CollectionUtility {

    public static Collection<String> filter(Collection<String> collection, String filterString) {
        Collection<String> filteredList = new ArrayList();
        for (String s : collection) {
            if (s.contains(filterString)) {
                filteredList.add(s);
            }
        }
        return filteredList;
    }

}
