/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package stickynotes;

/**
 *
 * @author dilipjain
 */
public class properties 
{
    int uid;
    String name;
    properties(int uid,String name)
    {
        this.uid=uid;
        this.name=name;
        
    }
    public Font fetchFontProperties()
    {
        //fetches font properties from the database
        dbProperties obj=new dbProperties(uid);
        return obj.getFontProperties(name);
        
    }
    public void setFontProperties(Font newObj)
    {
        //sets font properties and updates in database
        dbProperties obj=new dbProperties(uid);
        obj.setFontProperties(newObj);
        
    }
    
}        



