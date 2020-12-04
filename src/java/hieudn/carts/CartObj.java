/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieudn.carts;

import hieudn.blos.ShoppingBLO;
import hieudn.entities.Cake;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author hp
 */
public class CartObj implements Serializable {

    private String username;
    private Map<Cake, Integer> items;
    private int total;
    private int count = 0;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Map<Cake, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Cake, Integer> items) {
        this.items = items;
    }

    public void addItemToCart(Cake c) {
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        int quantity = 1;
        if (this.items.containsKey(c)) {
            quantity = this.items.get(c) + 1;
        }
        this.items.put(c, quantity);
    }

    public int getTotal() {
        count++;
        if (count != 0) {
            total = 0;
        }
        for (Map.Entry<Cake, Integer> m : items.entrySet()) {
            total += m.getValue() * m.getKey().getCakePrice();
        }

        return total;

    }

//    public int getTotal(Cake c){
//        return items.get(c) + c.getCakePrice();
//    }
    public void removeFromCart(String cakeId) {
        ShoppingBLO blo = new ShoppingBLO();
        Cake c = blo.getCakeById(cakeId);
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(c)) {
            this.items.remove(c);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

}
