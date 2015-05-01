import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.lang.Object;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
* Class to find all the given words in a 2d array of characters.
*/
public class WordSearch extends Canvas {
	//Constants that need to be plugged in depending on the input file
	private static final int cGRIDWIDTH = 20;
	private static final int cGRIDHEIGHT = 20;
	private static final int cCELLWIDTH = 40;
	private static final int cCELLHEIGHT = 40;
	private static final String cFIRSTWORD = "BLINKY";

	private char[][] grid;
	private List<Line> wordLocations;

	/**
	*	A sub-class which stores the location of a word in the form of a line.
	*/
	public class Line {
		public int x1, x2, y1, y2;

		/**
		*	Constructor for a line.
		*	@param x1 x coordinate of the starting point
		*	@param y1 y coordinate of the starting point
		* 	@param x2 x coordinate of the ending point
		*	@param y2 y coordinate of the ending point
		*/
		public Line(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}
	}

	/**
	*	Checks the grid to see if the word appears virtically in the grid.
	*	@param s the string being searched for.
	*	@return the line of the location of the string if it exists, null otherwise. 
	*/
	private Line searchVirtical(String s) {
		for (int i = 0; i < cGRIDWIDTH; i++) {
			for (int j = 0; j <= cGRIDHEIGHT - s.length(); j++) {
				StringBuilder forward = new StringBuilder();
				StringBuilder backward = new StringBuilder();
				for (int k = 0; k < s.length(); k++) {
					forward.append(Character.toString(grid[i][j + k]));
					backward.append(Character.toString(grid[i][j + s.length() - k - 1]));
				}
				if (forward.toString().equals(s)) {
					return new Line(i, j, i, j + s.length() - 1);
				} else if (backward.toString().equals(s)) {
					return new Line(i, j + s.length() - 1, i, j);
				}
			}
		}
		return null;
	}

	/**
	*	Checks the grid to see if the word appears horizontally in the grid.
	*	@param s the string being searched for.
	*	@return the line of the location of the string if it exists, null otherwise. 
	*/
	private Line searchHorizontal(String s) {
		for (int i = 0; i <= cGRIDWIDTH - s.length(); i++) {
			for (int j = 0; j < cGRIDHEIGHT; j++) {
				StringBuilder forward = new StringBuilder();
				StringBuilder backward = new StringBuilder();
				for (int k = 0; k < s.length(); k++) {
					forward.append(Character.toString(grid[i + k][j]));
					backward.append(Character.toString(grid[i + s.length() - k - 1][j]));
				}
				if (forward.toString().equals(s)) {
					return new Line(i, j, i + s.length() - 1, j);
				} else if (backward.toString().equals(s)) {
					return new Line(i + s.length() - 1, j, i, j);
				}
			}
		}
		return null;
	}

	/**
	*	Checks the grid to see if the word appears diagonally from top left to bottom right in the grid.
	*	@param s the string being searched for.
	*	@return the line of the location of the string if it exists, null otherwise. 
	*/
	private Line searchSouthEast(String s) {
		for (int i = 0; i <= cGRIDWIDTH - s.length(); i++) {
			for (int j = 0; j <= cGRIDHEIGHT - s.length(); j++) {
				StringBuilder forward = new StringBuilder();
				StringBuilder backward = new StringBuilder();
				for (int k = 0; k < s.length(); k++) {
					forward.append(Character.toString(grid[i + k][j + k]));
					backward.append(Character.toString(grid[i + s.length() - k - 1][j + s.length() - k - 1]));
				}
				if (forward.toString().equals(s)) {
					return new Line(i, j, i + s.length() - 1, j + s.length() - 1);
				} else if (backward.toString().equals(s)) {
					return new Line(i + s.length() - 1, j + s.length() - 1, i, j);
				}
			}
		}
		return null;
	}

	/**
	*	Checks the grid to see if the word appears diagonally from top right to bottom left in the grid.
	*	@param s the string being searched for.
	*	@return the line of the location of the string if it exists, null otherwise. 
	*/
	private Line searchNorthEast(String s) {
		for (int i = 0; i <= cGRIDWIDTH - s.length(); i++) {
			for (int j = s.length() - 1; j < cGRIDHEIGHT; j++) {
				StringBuilder forward = new StringBuilder();
				StringBuilder backward = new StringBuilder();
				for (int k = 0; k < s.length(); k++) {
					forward.append(Character.toString(grid[i + k][j - k]));
					backward.append(Character.toString(grid[i + s.length() - k - 1][j - s.length() + k + 1]));
				}
				if (forward.toString().equals(s)) {
					return new Line(i, j, i + s.length() - 1, j - s.length() + 1);
				} else if (backward.toString().equals(s)) {
					return new Line(i + s.length() - 1, j - s.length() + 1, i, j);
				}
			}
		}
		return null;
	}

	/**
	*	Constructor for the WordSearch structure containing the grid and word locations.
	*	@param fileName the name of the input file containing the data.
	*/
	public WordSearch(String fileName) {
		try {
			grid = new char[cGRIDWIDTH][cGRIDHEIGHT];
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String line = in.readLine();
			int row = 0;
			while (line != null && row < cGRIDHEIGHT) {
				String[] lineNums = line.split("\\s+");
				for (int col = 0; col < cGRIDWIDTH; col++) {
					grid[col][row] = lineNums[col].charAt(0);
				}
				line = in.readLine();
				row++;
			}
			List<String> words = new ArrayList<String>();
			while (!line.split("\\s+")[0].equals(cFIRSTWORD)) {
				line = in.readLine();
			}
			while (line != null) {
				words.add(line.split("\\s+")[0]);
				line = in.readLine();
			}
			in.close();
			wordLocations = new ArrayList<Line>();
			for (String s : words) {
				Line horizontal = searchHorizontal(s);
				Line virtical = searchVirtical(s);
				Line northEast = searchNorthEast(s);
				Line southEast = searchSouthEast(s);
				if (horizontal != null) {
					wordLocations.add(horizontal);
				} else if (virtical != null) {
					wordLocations.add(virtical);
				} else if (northEast != null) {
					wordLocations.add(northEast);
				} else if (southEast != null) {
					wordLocations.add(southEast);
				} else {
					System.out.println("ERROR: \"" + s + "\" not found in grid.");
					System.exit(0);
				}
			}
		} catch (IOException e) {
			System.out.println("Error: IOException");
			System.exit(0);
		}
	}

	/**
	*	Converts grid coordinates to pixel coordinates in the X axis.
	*	@param gridX the grid location in X coordinates.
	*	@return the input coordinates converted to pixel coordinates.
	*/
	private int widthGridToPixel(int gridX) {
		return gridX * cCELLWIDTH + cCELLWIDTH / 2;
	}

	/**
	*	Converts grid coordinates to pixel coordinates in the Y axis.
	*	@param gridY the grid location in Y coordinates.
	*	@return the input coordinates converted to pixel coordinates.
	*/
	private int heightGridToPixel(int gridY) {
		return gridY * cCELLHEIGHT + cCELLHEIGHT / 2;
	}

	/**
	*	Draws the board representing the solved puzzle.
	*	@param g the graphics object.
	*/
	public void paint(Graphics g) {
		for (int i = 0; i <= cGRIDHEIGHT; i++) {
			g.drawLine(0, i * cCELLHEIGHT, cGRIDWIDTH * cCELLWIDTH, i * cCELLHEIGHT);
		}
        for (int i = 0; i <= cGRIDWIDTH; i++) {
			g.drawLine(i * cCELLWIDTH, 0, i * cCELLWIDTH, cGRIDHEIGHT * cCELLHEIGHT);
		}
		for (int i = 0; i < cGRIDWIDTH; i++) {
			for (int j = 0; j < cGRIDHEIGHT; j++) {
				g.drawString(Character.toString(grid[i][j]), widthGridToPixel(i) - 5, heightGridToPixel(j) + 5);
			}
		}
		for (Line l : wordLocations) {
			g.drawLine(widthGridToPixel(l.x1), heightGridToPixel(l.y1), widthGridToPixel(l.x2), heightGridToPixel(l.y2));
		}
	}

	/**
	*	Main method finds the greatest path of a triangle with 100 rows.
	*	@param args should be passed only the pathname of a valid triangle file
	*/
	public static void main(String[] args) {
		if (args.length != 0) {
			System.out.println("Error: No expected arguments");
			System.exit(0);
		}
		JFrame f = new JFrame("Word Search:");
        WordSearch x = new WordSearch("word-search.txt");
        f.add(x);
        f.pack();
        f.setVisible(true);
        f.setSize(cGRIDWIDTH*cCELLWIDTH + 10,cGRIDHEIGHT*cCELLHEIGHT + 30);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


}