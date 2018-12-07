package game;

public class ScoreCalculator {
	private double time;
	private Game game;

	public ScoreCalculator(Game game) {
		this.game = game;
		time = 0;
	}
	
	public void update(){
		time+=(1.0/60);
		int score = (int) (1000000/time);
		game.setScore(score);
	}
	
	public void reset(){
		time = 0;
	}
	

}
