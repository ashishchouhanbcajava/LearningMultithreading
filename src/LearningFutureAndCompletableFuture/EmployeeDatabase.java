package LearningFutureAndCompletableFuture;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeDatabase {

	public static List<Employee> getAllEmployees() throws StreamReadException, DatabindException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("C:\\Users\\DELL\\Desktop\\LearningMultithreading\\src\\Employees.json");

		List<Employee> list = mapper.readValue(file, new TypeReference<List<Employee>>() {
		});
		return list;
	}
}
