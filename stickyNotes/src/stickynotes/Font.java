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
public class Font 
{
            String fontFamily;
            int fontSize;
            String fontColor;
            String name;
            Font(String name)
            {
                this.name=name;
                
                
            }
            Font(String fontFamily,int fontSize,String fontColor,String name)
            {
                this.fontFamily=fontFamily;
                this.fontSize=fontSize;
                this.fontColor=fontColor;
                this.name=name;
            }
            public void setfontFamily(String font)
            {
                fontFamily=font;
                
                
            }
            public void setFontSize(int size)
            {
                fontSize=size;
                
            }
            public void setFontColor(String color)
            {
                 fontColor=color;
                
            }
            public String getFontColor()
            {
                
                return fontColor;
                
            }
            public String getFontFamily()
            {
                return fontFamily;
                
            }
            public int getFontSize()
            {
                return fontSize;
                
            }
 
}
