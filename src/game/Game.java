package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.concurrent.CopyOnWriteArrayList;

import gameObjects.Camera;
import gameObjects.GameObject;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private int windowWidth = 1920;
	private int windowHeight = 1080;
	@SuppressWarnings("unused")
	private Window window;
	private Handler handler;
	private Handler mainMenuHandler;
	private Handler pauseMenuHandler;
	private Handler controlMenuHandler;
	private Handler winMenuHandler;
	private String name = "Wooden Gear Pliable";
	private Thread thread;
	private boolean running = false;
	public GameState currentState;
	private int fps;
	public Sound gameMusic;
	public Sound menuMusic;
	public Sound winSound;
	public boolean musicOn = true;
	public Camera camera;
	public Key key;
	public Mouse mouse;
	private int score;
	private ScoreCalculator scoreCalculator;
	
	public Game(){
		LeaderBoard scores = new LeaderBoard();
		Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		this.setCursor(cursor);
		scoreCalculator = new ScoreCalculator(this);
		key = new Key();
		mouse = new Mouse();
		//initializing the main game music
		gameMusic = new Sound("/sounds/music/nick game loop.wav");
		menuMusic = new Sound("/sounds/music/Music.wav");
		winSound = new Sound("/sounds/effects/other/tuturu.wav");
		
		//initialising the default state of the game 
		currentState = GameState.MainMenu;
		
		//initialising the window
		window = new Window(windowWidth,windowHeight,this,name);
		
		/*
		 * initialising all the game objects and
		 * adding all the objects to their respective handlers
		 */
		
		//handler for objects in the snooker 'world'
		handler = new Handler(initObjects());
		
		//handler for main menu objects
		MainMenuCreator mainMenu = new MainMenuCreator(this);
		mainMenuHandler = new Handler(mainMenu.getObjects());
		
		//handler for pause menu objects
		PauseMenuCreator pauseMenu = new PauseMenuCreator(this);
		pauseMenuHandler = new Handler(pauseMenu.getObjects());
		
		//handler for the control menu objects
		ControlMenuCreator controlMenu = new ControlMenuCreator(this);
		controlMenuHandler = new Handler(controlMenu.getObjects());
		
		WinMenuCreator winMenu = new WinMenuCreator(this);
		winMenuHandler = new Handler(winMenu.getObjects());
		
		
		/*
		 * initialising input for the game 
		 */
		MouseInput mouseInput = new MouseInput(this);
		this.addMouseMotionListener(mouseInput);
		this.addMouseListener(mouseInput);
		
		KeyInput keyInput = new KeyInput(this);
		this.addKeyListener(keyInput);
		
	}
	
	public int getWindowWidth() {
		return windowWidth;
	}


	public int getWindowHeight() {
		return windowHeight;
	}


	public Handler getHandler() {
		return handler;
	}
	
	public Handler getMainMenuHandler() {
		return mainMenuHandler;
	}

	public void setMainMenuHandler(Handler mainMenuHandler) {
		this.mainMenuHandler = mainMenuHandler;
	}

	public Handler getPauseMenuHandler() {
		return pauseMenuHandler;
	}

	public void setPauseMenuHandler(Handler pauseMenuHandler) {
		this.pauseMenuHandler = pauseMenuHandler;
	}
	
	public Handler getControlMenuHandler() {
		return controlMenuHandler;
	}

	public void setControlMenuHandler(Handler controlMenuHandler) {
		this.controlMenuHandler = controlMenuHandler;
	}
	
	public Handler getWinMenuHandler() {
		return winMenuHandler;
	}

	public void setWinMenuHandler(Handler winMenuHandler) {
		this.winMenuHandler = winMenuHandler;
	}
	
	
	public int getFps() {
		return fps;
	}

	//loop of the entire game
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double updatesPerSecond = 60;
		double nanoSecondsPerUpdate = 100000000 / updatesPerSecond;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta +=(now-lastTime) / nanoSecondsPerUpdate;
			lastTime = now;
			while(delta >=1){
				update();
				delta--;
			}
			if(running) render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				fps = frames;
				frames = 0;
			}
		}
		stop();
	}
	
	public synchronized void start(){
		running = true;
		thread = new Thread(this);
		thread.run();	
	}
	
	public synchronized void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * updates the game differenly depending on game state
	 */
	private void update(){
		switch(currentState){
		case Game:
			menuMusic.stop();
			scoreCalculator.update();
			handler.update();
			if(musicOn) {
				if(!gameMusic.isPlaying())gameMusic.loop();
			}
			else gameMusic.stop();
			break;
		case PauseMenu:
			gameMusic.pause();
			pauseMenuHandler.update();
			break;
		case MainMenu:
			if(!menuMusic.isPlaying())//menuMusic.loop();
			gameMusic.stop();
			mainMenuHandler.update();
			//main menu update
			break;
		case Controls:
			controlMenuHandler.update();
			break;
		case Settings:
			break;
		case Win:
			winMenuHandler.update();
		default:
			break;
		}
		
		
	}
	
	/*
	 * renders the game
	 */
	private void render(){
		if(this.getBufferStrategy()==null){
			this.createBufferStrategy(3);
		}
		else{
			BufferStrategy buffer = this.getBufferStrategy();
			Graphics g = buffer.getDrawGraphics();
			
			//sets a black background
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, windowWidth, windowHeight);
			switch(currentState){
			case Game:
				handler.render(g);
				break;
			case PauseMenu:
				handler.render(g);
				
				Graphics2D g2d = (Graphics2D)g;
				
				g2d.translate(camera.getX()-(windowWidth/2),camera.getY()-(windowHeight/2));
				Color fade = new Color(0, 0, 0, 150);
				g2d.setColor(fade);
				g2d.fillRect(0, 0, windowWidth, windowHeight);
				pauseMenuHandler.render(g2d);
				break;
			case MainMenu:
				mainMenuHandler.render(g);
				break;
			case Controls:
				controlMenuHandler.render(g);
				break;
			case Settings:
				break;
			case Win:
				handler.render(g);
				Color fadewin = new Color(0, 255, 0, 150);
				g.setColor(fadewin);
				g.fillRect(0, 0, windowWidth, windowHeight);
				Graphics2D g2d2 = (Graphics2D)g;
				g2d2.translate(camera.getX()-(windowWidth/2),camera.getY()-(windowHeight/2));
				winMenuHandler.render(g2d2);
				
			default :
				break;
			}
			
			buffer.show();
			g.dispose();
		}
	}
	
	//control for game states
	public void pause(){
		currentState = GameState.PauseMenu;
	}
	
	public void play(){
		currentState = GameState.Game;
	}
	
	public void menu(){
		currentState = GameState.MainMenu;
	}
	
	public void settings(){
		currentState = GameState.Settings;
	}
	
	public void controls(){
		currentState = GameState.Controls;
	}
	
	public void win() {
		winSound.play();
		currentState = GameState.Win;
	}
	
	public void lose() {
		currentState = GameState.Lose;
	}
	
	private CopyOnWriteArrayList<GameObject> initObjects(){
		ObjectCreator objectCreator = new ObjectCreator(this);
		CopyOnWriteArrayList<GameObject> objects = objectCreator.getObjects();
		return objects;
	}
	
	public void reset(){
		scoreCalculator.reset();
		handler.setObjects(initObjects());
	}
	
	public static void main(String[] a){
		Game game = new Game();
		game.start();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
