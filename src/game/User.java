package game;

public class User implements Comparable<User>{
	private String name;
	private Integer score;

	public User(String name,Integer score) {
		this.name = name;
		this.score = score;
	}

	@Override
	public int compareTo(User user) {
		return this.score.compareTo(user.score);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	@Override
	public String toString(){
		return String.format("Name: %s, Score: %d ", name,score);
	}
	

}
