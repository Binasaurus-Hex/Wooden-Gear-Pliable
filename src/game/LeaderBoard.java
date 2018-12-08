package game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class LeaderBoard {
	private File scoreFile;
	private SortedMap<Integer,String> scores;

	public LeaderBoard() {
		scoreFile = new File("res/storage/scores.txt");
		scores = new TreeMap<Integer,String>();
		try {
			loadScores();
			saveScores();
			//System.out.println(getTop10Scores());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadScores() throws IOException{
		
		String line;
		
		BufferedReader in = new BufferedReader(new FileReader(scoreFile));
		while((line = in.readLine())!=null){
			String[] entryStrings = line.split(":",2);
			if(entryStrings.length==2){
				Integer key = Integer.valueOf(entryStrings[1]);
				String value = entryStrings[0];
				scores.put(key, value);
			}
		}
		in.close();
	}
	
	private void saveScores() throws IOException{
		FileWriter out = new FileWriter(scoreFile);
		for(Map.Entry<Integer,String> entry:scores.entrySet()){
			String line = String.format("%s:%d\n",entry.getValue(),entry.getKey());
			out.write(line);
		}
		out.close();
	}
	
	public void addScore(Integer score,String name){
		
	}
	
	public Map<Integer,String> getTop10Scores(){
		SortedMap<Integer,String> top10 = new TreeMap<Integer,String>();
		top10.putAll(scores);
		if(top10.size()>10){
			int itemsToRemove = top10.size()-10;
			for(int i = 0;i<itemsToRemove;i++){
				Integer lastKey = top10.firstKey();
				String lastValue = top10.get(lastKey);
				top10.remove(lastKey, lastValue);
			}
		}
		return top10;
	}

}
