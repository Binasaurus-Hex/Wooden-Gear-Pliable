package UI;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import game.Game;

public class TextBox{
	private ArrayList<String> lines;
	private boolean centred = true;
	private double x,y;
	@SuppressWarnings("unused")
	private double width,height;
	private Game game;
	private Font font;
	private FontMetrics fontMetrics;
	
	
	public TextBox(double x,double y,Font font,Game game){
		this.x = x;
		this.y = y;
		this.game = game;
		this.font = font;
		this.lines = new ArrayList<String>();
		fontMetrics = this.game.getFontMetrics(this.font);
	}
	
	/*
	 * breaks up the inputted block of text into an list of lines
	 */
	private ArrayList<String> getLines(String text){
		String[] lineArray = text.split("\n");
		ArrayList<String> lineList = new ArrayList<String>();
		for(String str:lineArray){
			lineList.add(str);
		}
		return lineList;
	}
	
	private void setWidth(){
		double newWidth = 0;
		for(String str:lines){
			double lineWidth = fontMetrics.stringWidth(str);
			if(newWidth<lineWidth){
				newWidth = lineWidth;
			}
		}
		this.width = newWidth;
	}
	
	private void setHeight(){
		int numLines = lines.size();
		int blockHeight = fontMetrics.getHeight()*numLines;
		this.height = blockHeight;
	}
	
	
	/*
	 * adds an inputted block of text, lines separated by \n 
	 */
	public void addText(String text){
		ArrayList<String> newLines = getLines(text);
		lines.addAll(newLines);
	}
	
	/*
	 * adds a single line of text
	 */
	public void addLine(String str){
		lines.add(str);
	}
	
	
	private int getTextWidth(String text,Graphics g){
		int lineWidth = g.getFontMetrics().stringWidth(text);
		return lineWidth;
	}

	
	public void update() {
		/*
		 * update the width and height of the text box according to the width of the widest line of text,
		 * and the number of lines of text in the text box 
		 */
		setWidth();
		setHeight();
		
	}
	
	public void clear(){
		lines.clear();
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(font);
		if(centred){
			for(int line = 0;line<lines.size();line++){
				String text = lines.get(line);
				double lineHalfWidth = getTextWidth(text,g)/2;
				int lineX = (int)(x-lineHalfWidth);
				int lineY = (int)(y-(height/2)+((line+1)*font.getSize()));
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
				g2d.drawString(text, lineX, lineY);
			}
		}
		
	}

}
