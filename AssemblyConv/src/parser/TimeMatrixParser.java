package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timematrix.TimeMatrix;

/**
 * This class is a parser for the time matrix file of the time for moving from one task to another.
 * The time matrix file is a CSV file separated with semicolons.
 * Each line starts with the name/id of the task it's moving from, except the first row.
 * The first line contains the name/id of the task it's moving to
 * For example:
 * ;Take top;Put top in fixture;Take button;...
 * Start;3;7.0710678119;7.2801098893;...
 * Take top;0;8.0622577483;7.0710678119;...
 * .
 * .
 * .
 */
public class TimeMatrixParser {

	/**
	 * Parses the time matrix file and returns a TimeMatrix object
	 * @param m The time matrix file
	 * @return The TimeMatrix object
	 * @throws IOException
	 */
	public static TimeMatrix parse(File m) throws IOException {
		TimeMatrix matrix = new TimeMatrix();

		ArrayList<String> columns = new ArrayList<>();

		BufferedReader reader = new BufferedReader(new FileReader(m));

		// Read columns
		String line = reader.readLine();

		if (line.split(";")[0].length() != 0) {
			reader.close();
			throw new IllegalArgumentException(
					"The first cell needs to be empty");
		}

		Matcher matcher = Pattern.compile(";(?:([\\w \\-,]+))").matcher(line); /* Matching the first line */
		while (matcher.find()) {
			if (matcher.group(1) == null) {
				columns.add(matcher.group(2));
			} else {
				columns.add(matcher.group(1));
			}
		}

		while ((line = reader.readLine()) != null) {
			matcher = Pattern.compile("(?:([\\d.]+|[\\w \\-,]+))") /* Matching lines that are not the first */
					.matcher(line);
			String fromId = null;
			int toI = 0;
			while (matcher.find()) {
				try {
					int time = (int) Math.round(Double.parseDouble(matcher
							.group()));
					matrix.addTime(fromId, columns.get(toI), time);
					toI++;
				} catch (Exception e) { /* If we cannot cast the matched string to a double it must be the id string */
					fromId = matcher.group();
				}
			}
		}

		reader.close();

		return matrix;

	}
}
