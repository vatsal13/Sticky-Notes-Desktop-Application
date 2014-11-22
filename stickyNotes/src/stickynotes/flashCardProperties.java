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
public class flashCardProperties extends properties
{
        int uid;
        //name i.e whether for notes or cards
        String name;
        flashCardProperties(int uid,String name)
        {
            super(uid,name);
            this.uid=uid;
            this.name=name;
            
        }


}
