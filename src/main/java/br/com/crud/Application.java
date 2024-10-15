package br.com.crud;

import br.com.crud.service.CategoryService;
import br.com.crud.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>MENÚ PRINCIPAL<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("0. Salir");
            System.out.println("1. Category");
            System.out.println("2. Post");
            int opcion = 0;
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.err.println("¡La opción no es válida! solo numeros - " + e);
                continue;
            }
            switch (opcion) {
                case 0:
                    return;
                case 1:
                    categoryService.menu(scanner);
                    break;
                case 2:
                    postService.menu(scanner);
                    break;
                default:
                    System.out.println("¡La opción no es válida!");
            }
        }
    }
}