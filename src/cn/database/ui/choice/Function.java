/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.database.ui.choice;

/**
 *
 * @author MK
 * @param <T>
 * @param <V>
 * @param <Z>
 */
@FunctionalInterface
public interface Function<T, V, Z> {

    public abstract void sort(T t, V v, Z z1, Z z2);
}
