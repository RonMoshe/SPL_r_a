package java.bgu.spl.mics.application;

import java.bgu.spl.mics.application.messages.AttackEvent;
import java.bgu.spl.mics.application.passiveObjects.Attack;
import java.bgu.spl.mics.application.passiveObjects.Ewoks;
import java.bgu.spl.mics.application.passiveObjects.Input;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.bgu.spl.mics.application.services.LandoMicroservice;
import java.bgu.spl.mics.application.services.R2D2Microservice;
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
			Input json = gson.fromJson(reader, Input.class);
			Ewoks ewoks = Ewoks.getInstance(json.getEwoks());
			LandoMicroservice landoMS = new LandoMicroservice(json.getLando());
			R2D2Microservice R2D2MS = new R2D2Microservice(json.getR2D2());
			Attack[] attacks = json.getAttacks();
			System.out.println("read");
			//Input json = Input.

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
