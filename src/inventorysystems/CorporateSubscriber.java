/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventorysystems;

/**
 *
 * @author daugd
 */
public class CorporateSubscriber extends Subscriber{
    
    private double monthlyFees;
    private boolean editable;

    public CorporateSubscriber(double monthlyFees, boolean editable, int subscriberID, String restrictions, boolean readOnly) {
        super(subscriberID, restrictions, readOnly);
        this.monthlyFees = monthlyFees;
        this.editable = editable;
    }

    public CorporateSubscriber(double monthlyFees, boolean editable, int subscriberID) {
        super(subscriberID);
        this.monthlyFees = monthlyFees;
        this.editable = editable;
    }
    

    public double getMonthlyFees() {
        return monthlyFees;
    }

    public void setMonthlyFees(double monthlyFees) {
        this.monthlyFees = monthlyFees;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    
    public boolean canShareEditable() {
        return false;
    }
    
    
}
