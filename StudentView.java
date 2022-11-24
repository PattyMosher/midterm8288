package midtermCST8288;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentView extends Application implements Runnable{

	private Button start, stop, clear;
	private TextArea area;
	private StudentManager controller;
	private AtomicBoolean alive;
	Thread thread;

	@Override
	public void init() throws Exception{
		super.init();
		alive = new AtomicBoolean();
		controller = new StudentManager();
	}

	@Override
	public void start( Stage primaryStage) throws Exception{
		area = new TextArea();
		area.setEditable( false);

		start = new Button( "Start");
		stop = new Button( "Stop");
		clear = new Button( "Clear");

		GridPane controlPane = new GridPane();
		controlPane.setHgap( 5);
		controlPane.setVgap( 5);
		controlPane.setPadding( new Insets( 5));
		controlPane.add( start, 0, 0);
		controlPane.add( stop, 0, 1);
		controlPane.add( clear, 0, 2);

		BorderPane rootPane = new BorderPane();
		rootPane.setCenter( area);
		rootPane.setRight( controlPane);

		Scene scene = new Scene( rootPane, 500, 300);
		primaryStage.setScene( scene);
		primaryStage.setTitle( "Student MVC-O");
		primaryStage.addEventHandler( KeyEvent.KEY_RELEASED, ( KeyEvent event) -> {
			if( KeyCode.ESCAPE == event.getCode()){
				primaryStage.hide();
			}
		});
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception{
		super.stop();
		alive.set(false);
	}

	public void registerListeners(){
		start.setOnAction(e -> this.startDataThread(e));
		stop.setOnAction(e -> this.stopDataThread(e));
		clear.setOnAction(e -> area.clear());
		controller.getStudents().addListener(e -> createStudents((Change<? extends Student>) controller.getStudents()));
	}

	public void stopDataThread( ActionEvent e){

	}

	public void startDataThread( ActionEvent e){
		this.run();
		thread = new Thread(this);
		thread.start();
	}

	public void createStudents( Change< ? extends Student> c){
		int u;
		if (c.next() == true) {
			controller.getStudents().add(c);
			u =+1;
		}
		for (int a=0; a < controller.getStudents().size(); a++) {
			area.appendText(c.toString());
		}
	}

	public static void main( String[] args){
		launch( args);
	}

	@Override
	public void run() {
		alive.set(true);
		int i=0;
		while (i <100 && alive.get()==true) {
			thread.sleep(50);
			controller.addStudent("FirstName", "LastName");

		}


	}
}