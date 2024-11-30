/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Method;

import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ALE PERDANA PUTRA
 */
public class Status {
    protected Integer nyawa;
    protected Integer atkMin;
    protected Integer atkMax;

    public Integer getNyawa() {
        return nyawa;
    }

    public void setNyawa(Integer nyawa) {
        this.nyawa = nyawa;
    }

    public Integer getAtkMin() {
        return atkMin;
    }

    public void setAtkMin(Integer atkMin) {
        this.atkMin = atkMin;
    }

    public Integer getAtkMax() {
        return atkMax;
    }

    public void setAtkMax(Integer atkMax) {
        this.atkMax = atkMax;
    }
    
        public void updateStatus(DefaultTableModel model, String name) {
        model.addRow(new Object[]{
            name,
            getNyawa(),
            getAtkMin() + " - " + getAtkMax()
        });
    }
}
