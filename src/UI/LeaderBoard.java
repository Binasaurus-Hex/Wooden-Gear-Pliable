package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import game.Game;
import game.User;
import gameObjects.GameObject;

public class LeaderBoard extends UI_Object{
	private String scoreFile;
	private ArrayList<User> users;
	private Font font = Game.menuFontMedium;

	/**
	 * defualt constructor for leaderBoard UI object
	 * @param double x
	 * @param double y
	 * @param double width
	 * @param double height
	 * @param MenuID id
	 * @param Game game
	 */
	public LeaderBoard(double x,double y,double width,double height,MenuID id,Game game) {
		super(x,y,width,height,id,game);
		
		users = new ArrayList<User>();
		text = new TextBox((x+halfWidth),(y+halfHeight),font,game);
		String userDir = System.getProperty("user.home")+"/WoodenGearPliable";
		File folder = new File(userDir);
		boolean made = folder.mkdir();
		scoreFile = userDir + "/scores.txt";
		
		try {
			loadScores(scoreFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setText();
		
	}
	
	/**
	 * loads the scores into the LeaderBoard object
	 * @throws IOException
	 */
	private void loadScores(String filename) throws IOException{
		String line;
		users.clear();
		BufferedReader in = new BufferedReader(new FileReader(filename));
		while((line = in.readLine())!=null){
			String[] entryStrings = line.split(":",2);
			if(entryStrings.length==2){
				String name = entryStrings[0];
				Integer score = Integer.valueOf(entryStrings[1]);
				users.add(new User(name,score));
			}
		}
		in.close();
		orderList();
	}
	
	/**
	 * saves the current scores stored in the LeaderBoard object to the file
	 * @throws IOException
	 */
	private void saveScores(String filename) throws IOException{
		FileWriter out = new FileWriter(scoreFile);
		for(User user:users){
			String output = String.format("%s:%d\n",user.getName(),user.getScore());
			out.write(output);
		}
		out.close();
	}
	
	/**
	 * adds the specified score and accosiated name to the Leaderboard object
	 * @param Integer score
	 * @param String name
	 */
	public void addScore(String name,Integer score){
		boolean newUser = true;
		for(User user:users){
			if(user.getName().equals(name)){
				newUser = false;
				if(score>user.getScore()){
					user.setScore(score);
				}
			}
		}
		if(newUser){
			users.add(new User(name,score));
		}
		orderList();
		try {
			setText();
			saveScores(scoreFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void orderList(){
		Collections.sort(users,Collections.reverseOrder());
	}
	
	/**
	 * returns a list of the top 10 users ranked by score
	 * @return ArrayList<User> top10users
	 */
	public List<User> getTop10Users(){
		if(users.size()<10){
			return users;
		}
		else{
			List<User> top10 = users.subList(0, 10);
			return top10;
		}
		
	}
	
	/**
	 * sets the text of the UI object to be the top 10 scores
	 */
	private void setText(){
		text.clear();
		String textBlock = "";
		for(User user:getTop10Users()){
			textBlock+=String.format("%d. %s : %d pts\n\n", 
										users.indexOf(user)+1,
										user.getName(),
										user.getScore());
		}
		text.addText(textBlock);
	}

	@Override
	public void update(CopyOnWriteArrayList<GameObject> objects) {
		try {
			loadScores(scoreFile);
			//System.out.println("loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		text.update();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)(x),(int)(y-50),(int)width,(int)height);
		g.setColor(Color.white);
		text.render(g);
		
		
	}

}
