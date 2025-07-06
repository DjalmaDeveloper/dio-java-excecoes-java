package dao_java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import br.com.dio.dao.UserDAO;
import br.com.dio.exception.CustomException;
import br.com.dio.exception.EmptyStorageException;
import br.com.dio.exception.UserNotFoundException;
import br.com.dio.exception.ValidatorException;
import br.com.dio.model.MenuOption;
import br.com.dio.model.UserModel;
import br.com.dio.validator.UserValidator;

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
				try {
					UserModel user = dao.save(requestToSave());
					System.out.printf("Usuário cadastrado %s", user);
				}
				catch (CustomException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			case UPDATE -> {
				try {
					UserModel user = dao.update(requestToUpdate());
					System.out.printf("Usuário atualizado %s", user);
				}
				catch(UserNotFoundException | EmptyStorageException e) {
					System.out.println(e.getMessage());
				}
				catch(CustomException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				finally {
					System.out.println("====================");
				}
			}
			case DELETE -> {
				try {
					dao.delete(requestId());
					System.out.println("Usuário excluído");
				}
				catch(UserNotFoundException | EmptyStorageException e) {
					System.out.println(e.getMessage());
				}
				finally {
					System.out.println("====================");
				}
			}
			case FIND_BY_ID -> {
				try {
					long id = requestId();
					UserModel user = dao.findById(id);
					System.out.printf("Usuário com id %s", id);
					System.out.println(user);
				}
				catch(UserNotFoundException | EmptyStorageException e) {
					System.out.println(e.getMessage());
				}
				finally {
					System.out.println("====================");
				}
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
		LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
		UserModel user = new UserModel(0, name, email, birthDate);
		UserModel model = new UserModel();
		return model = validateInputs(0, name, email, birthDate);
	}
	
	private static UserModel validateInputs(final long id, final String name, final String email, final LocalDate birthDate){
		UserModel user = new UserModel(id, name, email, birthDate);
		try {
			UserValidator.verifyModel(user);
			return user;
		}
		catch (ValidatorException e) {
			throw new CustomException("O seu usuário contém erros: " + e.getMessage(), e);
		}
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
		LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
		return validateInputs(id, name, email, birthDate);
	}
}
