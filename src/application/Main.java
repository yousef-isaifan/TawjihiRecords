package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Main extends Application {
	public static TawjihiDS<TawjihiRecords> list = new TawjihiDS<>();
	public static File thisFile;
	public static DNode<TawjihiRecords> holder = new DNode<>();

	@Override
	public void start(Stage stage) {
		Font font13 = new Font(13);
		Font font15 = new Font(15);
		Font font18 = new Font(18);
		Font font20 = new Font(20);

//##############################################################################

		BorderPane bp = new BorderPane();
		Label label1 = new Label("Welcome to Records Manger");
		label1.setFont(new Font(25));
		bp.setTop(label1);
		BorderPane.setAlignment(label1, Pos.CENTER);

		Label filePath = new Label();
		filePath.setFont(font15);

		RadioButton buttonL = new RadioButton("Literary");
		RadioButton buttonS = new RadioButton("Scientific");
		buttonL.setFont(font15);
		buttonS.setFont(font15);
		buttonL.setDisable(true);
		buttonS.setDisable(true);
		ToggleGroup tg2 = new ToggleGroup();
		buttonL.setToggleGroup(tg2);
		buttonS.setToggleGroup(tg2);

		HBox hb1 = new HBox();
		hb1.getChildren().add(buttonL);
		hb1.getChildren().add(buttonS);
		hb1.setSpacing(20);

		Label label2 = new Label("Choose Region and Branch:");
		label2.setFont(font20);
		HBox label2HB = new HBox();
		label2HB.getChildren().add(label2);

		Button logBtn = new Button("       Login       ");
		logBtn.setFont(font13);
		logBtn.setDisable(true);

		Button loadBtn = new Button("     Load File     ");
		loadBtn.setFont(font13);

		HBox loadHbox = new HBox();
		loadHbox.getChildren().addAll(logBtn, loadBtn);
		loadHbox.setSpacing(20);

		GridPane gp = new GridPane();
		gp.add(label2HB, 0, 1);
		gp.add(hb1, 0, 2);
		gp.add(loadHbox, 0, 3);
		GridPane.setHalignment(loadBtn, HPos.LEFT);

		gp.setHgap(20);
		gp.setVgap(9);
		gp.setAlignment(Pos.TOP_CENTER);
		bp.setCenter(gp);

		Scene scene = new Scene(bp, 550, 300);
		stage.setScene(scene);
		stage.setMinWidth(550);
		stage.setMinHeight(350);

		
		
// ------------------------------Options Scene Start-----------------------------------
		
		
		
		Label optionsLabel = new Label("Chose option from list then click next:");
		optionsLabel.setFont(new Font(20));

		String[] options = { "Insert", "Delete", "Update", "Find", "Traverse", "Height", "All by grade" };
		ComboBox<String> cBox = new ComboBox<String>(FXCollections.observableArrayList(options));
		cBox.resize(200, 0);

		Button optionsNextBtn = new Button("    Next    ");
		optionsNextBtn.setFont(font13);
		optionsNextBtn.setDisable(true);

		Button optionsBackBtn = new Button("    Back    ");
		optionsBackBtn.setFont(font13);

		HBox optionsHB = new HBox();
		optionsHB.getChildren().add(optionsNextBtn);
		optionsHB.getChildren().add(optionsBackBtn);
		optionsHB.setSpacing(25);

		GridPane optionsGP = new GridPane();
		optionsGP.add(optionsLabel, 0, 0);
		optionsGP.add(cBox, 0, 1);
		optionsGP.add(optionsHB, 0, 2);

		optionsGP.setHgap(20);
		optionsGP.setVgap(9);
		optionsGP.setAlignment(Pos.CENTER);

		Scene optionsListScene = new Scene(optionsGP, 550, 300);

		Label seatLabel = new Label("Seat Number: ");
		Label branchLabel = new Label("Branch: ");
		Label avgLabel = new Label("Average: ");
		Label resultLabel = new Label();// To give output on screen.

		seatLabel.setFont(font15);
		branchLabel.setFont(font15);
		avgLabel.setFont(font15);
		resultLabel.setFont(font15);

		TextField insertSeatNo = new TextField();
		TextField insertAvg = new TextField();

		Button insertBtn = new Button("   Insert   ");
		insertBtn.setFont(font13);

		Button insertBackBtn = new Button("     Back     ");
		insertBackBtn.setFont(font13);

		HBox insertHB = new HBox();
		insertHB.getChildren().add(insertBtn);
		insertHB.getChildren().add(insertBackBtn);
		insertHB.setSpacing(20);

		GridPane insertGp = new GridPane();
		insertGp.add(seatLabel, 0, 0);
		insertGp.add(insertSeatNo, 1, 0);
		insertGp.add(avgLabel, 0, 1);
		insertGp.add(insertAvg, 1, 1);
		insertGp.add(insertHB, 0, 2);
		insertGp.add(resultLabel, 0, 3);

		insertGp.setHgap(20);
		insertGp.setVgap(9);

		insertGp.setAlignment(Pos.CENTER);

		Scene insertScene = new Scene(insertGp, 550, 300);

		
		
//------------------------Delete Scene----------------------------
		
		
		
		Label dSeatLabel = new Label("Seat Number: ");
		dSeatLabel.setFont(font15);
		Label deleteResult = new Label();
		deleteResult.setFont(font15);

		TextField dSeatNo = new TextField();

		Button deleteBtn = new Button("   Delete   ");
		Button deleteBack = new Button("     Back     ");

		HBox deleteHbox = new HBox();
		deleteHbox.getChildren().add(deleteBtn);
		deleteHbox.getChildren().add(deleteBack);
		deleteHbox.setSpacing(20);

		GridPane deleteGp = new GridPane();
		deleteGp.add(dSeatLabel, 0, 0);
		deleteGp.add(dSeatNo, 1, 0);
		deleteGp.add(deleteHbox, 1, 1);
		deleteGp.add(deleteResult, 0, 2);
		deleteGp.setAlignment(Pos.CENTER);
		deleteGp.setHgap(20);
		deleteGp.setVgap(9);

		Scene deleteScene = new Scene(deleteGp, 550, 300);

		
		
		// -------------------------------------------

		
		
		Label upadteLabel = new Label("Seat Number: ");
		upadteLabel.setFont(font15);

		TextField upadteSeatNo = new TextField();

		Label updateResult = new Label();
		updateResult.setFont(font15);

		Label upadteAvgLabel = new Label("New Avg: ");
		upadteAvgLabel.setFont(font15);
		TextField upadteAvgTf = new TextField();

		Button updateBtn = new Button("   Update   ");
		Button updateBack = new Button("     Back     ");

		HBox updateHbox = new HBox();
		updateHbox.getChildren().addAll(updateBtn, updateBack);
		updateHbox.setSpacing(20);

		GridPane updateGp = new GridPane();
		updateGp.add(updateResult, 0, 0);
		updateGp.add(upadteLabel, 0, 1);
		updateGp.add(upadteSeatNo, 1, 1);
		updateGp.add(upadteAvgLabel, 0, 2);
		updateGp.add(upadteAvgTf, 1, 2);
		updateGp.add(updateHbox, 0, 3);

		updateGp.setAlignment(Pos.CENTER);
		updateGp.setHgap(20);
		updateGp.setVgap(9);

		Scene updateScene = new Scene(updateGp, 550, 300);

		Label findlabel = new Label("Seat Number: ");
		findlabel.setFont(font15);
		Label findResult = new Label();
		findResult.setFont(font15);

		TextField findTf = new TextField();

		Button findNextBtn = new Button("     Next     ");
		findNextBtn.setFont(font13);

		Button findPrevBtn = new Button("   Previous   ");
		findPrevBtn.setFont(font13);

		Button findBack = new Button("     Back     ");
		findBack.setFont(font13);

		HBox findHb = new HBox();
		findHb.getChildren().add(findNextBtn);
		findHb.getChildren().add(findPrevBtn);
		findHb.getChildren().add(findBack);
		findHb.setSpacing(20);

		HBox findHb2 = new HBox();
		findHb2.getChildren().add(findlabel);
		findHb2.getChildren().add(findTf);
		findHb2.setSpacing(25);

		GridPane findGp = new GridPane();
		findGp.add(findResult, 0, 0);
		findGp.add(findHb2, 0, 1);
		findGp.add(findHb, 0, 2);
		findGp.setAlignment(Pos.CENTER);
		findGp.setHgap(20);
		findGp.setVgap(9);

		Scene findScene = new Scene(findGp, 550, 300);

		
//-------------------------Traverse Scene----------------------------------
		
		
		Label traverseLable = new Label("Choose tree to traverse");
		traverseLable.setFont(font15);

		Button traverseBackBtn = new Button("     Back     ");
		traverseBackBtn.setFont(font13);

		TextArea traverseTA = new TextArea();
		traverseTA.setEditable(false);
		traverseTA.setPrefSize(350, 220);
		traverseTA.setFont(font15);

		RadioButton seatAVL = new RadioButton("Seat AVL");
		RadioButton gradeAVl = new RadioButton("Grades AVl");
		seatAVL.setFont(font15);
		gradeAVl.setFont(font15);
		ToggleGroup traverseGroup = new ToggleGroup();
		seatAVL.setToggleGroup(traverseGroup);
		gradeAVl.setToggleGroup(traverseGroup);

		HBox traverseHB = new HBox();
		traverseHB.getChildren().addAll(seatAVL, gradeAVl);
		traverseHB.setSpacing(20);

		GridPane traverseGP = new GridPane();
		traverseGP.add(traverseLable, 0, 0);
		traverseGP.add(traverseHB, 0, 1);
		traverseGP.add(traverseTA, 0, 2);
		traverseGP.add(traverseBackBtn, 0, 3);

		traverseGP.setAlignment(Pos.CENTER);
		traverseGP.setHgap(20);
		traverseGP.setVgap(12);

		Scene traverseScene = new Scene(traverseGP, 550, 350);

		
//------------------------------------------------------------------------

		
		Label seatAvlHeight = new Label();
		seatAvlHeight.setFont(font18);

		Label gradesAvlHeight = new Label();
		gradesAvlHeight.setFont(font18);

		Button heightBackBtn = new Button("     Back     ");
		heightBackBtn.setFont(font13);

		GridPane heaghtGP = new GridPane();
		heaghtGP.add(seatAvlHeight, 0, 0);
		heaghtGP.add(gradesAvlHeight, 0, 1);
		heaghtGP.add(heightBackBtn, 0, 2);

		heaghtGP.setAlignment(Pos.CENTER);
		heaghtGP.setHgap(20);
		heaghtGP.setVgap(9);

		Scene heightScene = new Scene(heaghtGP, 550, 300);

		
//------------------------------------------------------------------------

		
		Label allByGrade = new Label("Grede: ");
		allByGrade.setFont(font15);

		TextField allByGradeTF = new TextField();

		HBox allByGradeHB = new HBox();
		allByGradeHB.getChildren().add(allByGrade);
		allByGradeHB.getChildren().add(allByGradeTF);
		allByGradeHB.setSpacing(20);

		TextArea allByGradeTA = new TextArea();
		allByGradeTA.setEditable(false);
		allByGradeTA.setPrefSize(360, 200);
		allByGradeTA.setEditable(false);
		allByGradeTA.setFont(font15);

		Button allByGradeFindBtn = new Button("     Find     ");
		allByGradeFindBtn.setFont(font13);

		Button allByGradeBackBtn = new Button("     Back     ");
		allByGradeBackBtn.setFont(font13);

		HBox allByGradeHB2 = new HBox();
		allByGradeHB2.getChildren().add(allByGradeFindBtn);
		allByGradeHB2.getChildren().add(allByGradeBackBtn);
		allByGradeHB2.setSpacing(20);

		GridPane allByGradeGp = new GridPane();
		allByGradeGp.add(allByGradeHB, 0, 0);
		allByGradeGp.add(allByGradeTA, 0, 1);
		allByGradeGp.add(allByGradeHB2, 0, 2);
		allByGradeGp.setAlignment(Pos.CENTER);
		allByGradeGp.setHgap(20);
		allByGradeGp.setVgap(9);

		Scene allByGradeScene = new Scene(allByGradeGp, 550, 350);

//##############################################################################

		
		loadBtn.setOnAction(e -> {
			logBtn.setDisable(true);
			buttonL.setSelected(false);
			buttonS.setSelected(false);
			buttonL.setDisable(true);
			buttonS.setDisable(true);
			FileChooser fc = new FileChooser();
			fc.getExtensionFilters().addAll(new ExtensionFilter("Record File (*.CSV)", "*.CSV"));
			thisFile = fc.showOpenDialog(null);
			if (thisFile != null) {
				buttonL.setDisable(false);
				buttonS.setDisable(false);
				list.clear();
			}
		});

		buttonL.setOnAction(e -> {
			if (buttonL.isSelected())
				logBtn.setDisable(false);
		});

		buttonS.setOnAction(e -> {
			if (buttonS.isSelected())
				logBtn.setDisable(false);
		});

		logBtn.setOnAction(e -> {
			stage.setScene(optionsListScene);
			try {
				if (buttonL.isSelected()) {
					fileRead(thisFile, "Literary");
				} else {
					fileRead(thisFile, "Scientific");
				}
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		});

		cBox.setOnAction(e -> {
			if (cBox.getValue() != null)
				optionsNextBtn.setDisable(false);
		});
		optionsNextBtn.setOnAction(e -> {
			String result = (String) cBox.getValue();
			if (result.equals("Insert")) {
				stage.setScene(insertScene);
			}
			if (result.equals("Delete")) {
				stage.setScene(deleteScene);
			}
			if (result.equals("Update")) {
				stage.setScene(updateScene);
			}
			if (result.equals("Find")) {
				stage.setScene(findScene);
			}
			if (result.equals("Traverse")) {
				stage.setScene(traverseScene);
			}
			if (result.equals("Height")) {
				stage.setScene(heightScene);
				String seat = "Seat AVL Height:  " + Integer.toString(list.seatNoAvlHeight());
				String grade = "Grades AVL Height:  " + Integer.toString(list.gradesAvlHeight());
				seatAvlHeight.setText(seat);
				gradesAvlHeight.setText(grade);
			}
			if (result.equals("All by grade")) {
				stage.setScene(allByGradeScene);
			}
			cBox.valueProperty().set(null);// To clear chosen option from cBox.
			optionsNextBtn.setDisable(true);
		});

		optionsBackBtn.setOnAction(e -> {
			// To reset options to normal.
			label2.setText("Choose File and Branch:");
			optionsNextBtn.setDisable(true);
			stage.setScene(scene);
		});

		
//-------------------------------------------------------------------------

		
		insertBtn.setOnAction(e -> {// To check if user entered data or not.
			if (!insertSeatNo.getText().equals("") && !insertAvg.getText().equals("")) {
				int seatNumber = Integer.parseInt(insertSeatNo.getText());
				double avg = Double.parseDouble(insertAvg.getText());
				String branchStr;
				if (buttonL.isSelected()) {// To find what branch selected when user lodged in.
					branchStr = "Literary";
				} else {
					branchStr = "Scientific";
				}
				if (seatNumber > 0) {// To check if seat number in accepted range.
					if (avg >= 1 && avg <= 100) {// To check if grade in accepted range.
						resultLabel.setText(list.insertRecord(seatNumber, branchStr, avg));
					} else {
						resultLabel.setText("Grade must be between 50 and 99.9!");
					}
				} else {
					resultLabel.setText("Seat number must be 8 digits!");
				}
			} else {
				resultLabel.setText("You must enter seat number and avg!");
				resultLabel.setFont(font13);
			}
		});

		
		insertBackBtn.setOnAction(e -> {
			stage.setScene(optionsListScene);
			insertSeatNo.clear();
			insertAvg.clear();
			resultLabel.setText("");
			resultLabel.setFont(font15);
		});

//-------------------------------------------------------------------------

		
		deleteBtn.setOnAction(e -> {
			int seatNo = Integer.valueOf(dSeatNo.getText());
			deleteResult.setText(list.delete(seatNo));
		});

		
		deleteBack.setOnAction(e -> {
			stage.setScene(optionsListScene);
			dSeatNo.clear();
			deleteResult.setText("");
			dSeatNo.setFocusTraversable(true);
		});

		
//-------------------------------------------------------------------------

		updateBtn.setOnAction(e -> {
			if (upadteSeatNo.getText() != "" && upadteAvgTf.getText() != "") {
				int seatNo = Integer.valueOf(upadteSeatNo.getText());
				double avg = Double.valueOf(upadteAvgTf.getText());
				updateResult.setText(list.update(seatNo, avg));
			} else {
				updateResult.setText("You have to enter seat number and new avg!!");
			}
		});

		
		updateBack.setOnAction(e -> {
			stage.setScene(optionsListScene);
			upadteSeatNo.setText("");
			upadteAvgTf.setText("");
			updateResult.setText("");
		});

//-------------------------------------------------------------------------

		
		findTf.setOnAction(e -> {
			int seatNo = Integer.valueOf(findTf.getText());
			holder = list.findBySeat(seatNo);
			if (holder != null) {
				findResult.setText(getFindStr(holder));
			} else {
				findResult.setText("Not found!");
			}
		});

		
		findNextBtn.setOnAction(e -> {
			if (!(findTf.getText() == "" || findResult.getText() == "Not found!"
					|| findResult.getText() == "You have to enter valid seat number")) {
				holder = holder.next;
				if (holder != null) {
					findResult.setText(getFindStr(holder));
				} else {
					findResult.setText("Not found!");
				}
			} else {
				findResult.setText("You have to enter valid seat number");
			}
		});

		
		findPrevBtn.setOnAction(e -> {
			if (!(findTf.getText() == "" || findResult.getText() == "Not found!"
					|| findResult.getText() == "You have to enter valid seat number")) {
				holder = holder.prev;
				if (holder != null) {
					findResult.setText(getFindStr(holder));
				} else {
					findResult.setText("Not found!");
				}
			} else {
				findResult.setText("You have to enter valid seat number");
			}

		});

		
		findBack.setOnAction(e -> {
			stage.setScene(optionsListScene);
			findTf.setText("");
			findResult.setText("");
		});

//-------------------------------------------------------------------------

		
		seatAVL.setOnAction(e -> {
			traverseTA.setText(list.seatNoAVLTrav());
		});
		
		
		gradeAVl.setOnAction(e -> {
			traverseTA.setText(list.gradesAVLTrav());
		});

		
		traverseBackBtn.setOnAction(e -> {
			stage.setScene(optionsListScene);
			traverseTA.setText("");
			seatAVL.setSelected(false);
			gradeAVl.setSelected(false);
		});

//-------------------------------------------------------------------------

		
		heightBackBtn.setOnAction(e -> {
			stage.setScene(optionsListScene);
			gradesAvlHeight.setText("");
			seatAvlHeight.setText("");
		});

		
//-------------------------------------------------------------------------		

		allByGradeFindBtn.setOnAction(e -> {
			if (allByGradeTF.getText() != "") {
				int grade = Integer.valueOf(allByGradeTF.getText());
				allByGradeTA.setText(list.getAllByGrade(grade));
			}
		});

		
		allByGradeBackBtn.setOnAction(e -> {
			stage.setScene(optionsListScene);
			allByGradeTA.setText("");
		});

		
//-------------------------------------------------------------------------

		Image icon = new Image(getClass().getResourceAsStream("/image/grades.png"));
		stage.getIcons().add(icon);
		stage.setTitle("Records Management System");
		stage.show();
	}

	
	public static void main(String[] args) {
		launch(args);
	}

//=======================================================================
	
	public String getFindStr(DNode<TawjihiRecords> old) {
		String newStr = "Seat Number: " + old.data.getSeatNumber() + "\n" + "Branch: " + old.data.getBranch() + "\n"
				+ "Avg: " + old.data.getAvg();
		return newStr;
	}

	
// Method to read data from files.
	public static void fileRead(File file, String bran) throws FileNotFoundException {
		if (file.exists()) {// To check if the file exists.
			Scanner fScan = new Scanner(file);
			while (fScan.hasNext()) {// Loop for westStudents.
				String[] str = fScan.nextLine().split(",");// Read and split.
				int seatNo = Integer.parseInt(str[0]);// Convert string to integer;
				String branch = str[1].trim();// To save branch name.
				double avg = Double.parseDouble(str[2]);// Convert string to double.
				if (branch.equals(bran)) {// To read specific branch.
					// To insertRecord will check repetition before insert data.
					list.insertRecord(seatNo, branch, avg);
				}
			}
			fScan.close();
		}
	}
}
