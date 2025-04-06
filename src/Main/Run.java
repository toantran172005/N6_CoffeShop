package Main;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.UIManager;

import Database.DatabaseConnection;
import Frames.customerFrame;

public class Run {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new customerFrame();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
