package java.bgu.spl.mics.application;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/** This is the Main class of the application. You should parse the input file,
 * create the different components of the application, and run the system.
 * In the end, you should output a JSON.
 */
public class Main {
	public static void main(String[] args) {
		if(args.length != 2){
			throw new IllegalArgumentException("Program requires 2 arguments: input.json path, output.json path!");
		}
		Gson gson = new Gson();
		try {
			JsonReader reader = new JsonReader(new FileReader(args[0]));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
