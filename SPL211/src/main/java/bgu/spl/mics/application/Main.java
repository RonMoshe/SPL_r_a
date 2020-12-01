package java.bgu.spl.mics.application;

import java.bgu.spl.mics.application.passiveObjects.Input;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;

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
		try(JsonReader reader = new JsonReader(new FileReader(args[0]))){
			Input inp = gson.fromJson(reader, Input.class);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
