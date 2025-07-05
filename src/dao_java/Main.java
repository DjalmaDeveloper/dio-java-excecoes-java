package dao_java;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.com.dio.dao.UserDAO;
import br.com.dio.model.MenuOption;
import br.com.dio.model.UserModel;

public class Main {
	
	private final static UserDAO dao = new UserDAO();
	
	private final static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		while (true) {
			System.out.println("Bem vindo ao cadastro de usuários, selecione a operação desejada");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Excluir");
			System.out.println("4 - Buscar por identificador");
			System.out.println("5 - Listar");
			System.out.println("6 - Sair");
			int userInput = sc.nextInt();
			MenuOption selectedOption = MenuOption.values()[userInput -1];
			switch (selectedOption) {
			case SAVE -> {
				UserModel user = dao.save(requestToSave());
				System.out.printf("Usuário cadastrado %s", user);
			}
			case UPDATE -> {
				UserModel user = dao.update(requestToUpdate());
				System.out.printf("Usuário atualizado %s", user);
			}
			case DELETE -> {
				dao.delete(requestId());
				System.out.println("Usuário excluído");
			}
			case FIND_BY_ID -> {
				long id = requestId();
				UserModel user = dao.findById(id);
				System.out.printf("Usuário com id %s", id);
				System.out.println(user);
			}
			case FIND_ALL -> {
				List<UserModel> users = dao.findAll();
				System.out.println("Usuários cadastrados");
				System.out.println("====================");
				users.forEach(System.out::println);
				System.out.println("==========fim==========");

			}
			case EXIT -> {
				System.exit(0);
			}
		}
	}
}
	
	private static long requestId() {
		System.out.println("Informe o identificador do usuário");
		return sc.nextLong();
	}
	
	private static UserModel requestToSave() {
		System.out.println("Informe o nome do usuário");
		String name = sc.next();
		System.out.println("Informe o e-mail do usuário");
		String email = sc.next();
		System.out.println("Informe a data de nascimento do usuário (dd/MM/yyyy)");
		String birthDateString = sc.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		OffsetDateTime birthDate = OffsetDateTime.parse(birthDateString, formatter);
		return new UserModel(0, name, email, birthDate);
	}
	
	private static UserModel requestToUpdate() {
		System.out.println("Informe o identificador do usuário");
		long id = sc.nextLong();
		System.out.println("Informe o nome do usuário");
		String name = sc.next();
		System.out.println("Informe o e-mail do usuário");
		String email = sc.next();
		System.out.println("Informe a data de nascimento do usuário (dd/MM/yyyy)");
		String birthDateString = sc.next();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		OffsetDateTime birthDate = OffsetDateTime.parse(birthDateString, formatter);
		return new UserModel(id, name, email, birthDate);
	}
}
