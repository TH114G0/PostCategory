package br.com.crud.service;

import br.com.crud.model.CategoryModel;
import br.com.crud.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>MENÚ CATEGORIA<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("0. Salir");
            System.out.println("1. Crea categoría");
            System.out.println("2. Editar categoria");
            System.out.println("3. Ver categoria");
            System.out.println("4. Borrar categoria");
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
                    createCategory(scanner);
                    break;
                case 2:
                    editCategory(scanner);
                    break;
                case 3:
                    viewCategory(scanner);
                    break;
                case 4:
                    deleteCategory(scanner);
                    break;
                default:
                    System.out.println("¡La opción no es válida!");
            }
        }
    }

    private void createCategory(Scanner scanner) {
        CategoryModel categoryModel = new CategoryModel();
        PostService postService = new PostService();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>CREA CATEGORIA<<<<<<<<<<<<<<<<<<<<<<<<");

        System.out.print("Escribe el nombre de la categoria: ");
        categoryModel.setName(scanner.nextLine());

        categoryModel.setPostModelList(postService.CreatePostModelList(scanner, categoryModel));

        try {
            categoryRepository.save(categoryModel);
            System.out.println("Categoria creada exitosamente - " + categoryModel);
        } catch (Exception e) {
            System.err.println("Erro al guardar Categoria - " + e.getMessage());
        }

    }

    private void editCategory(Scanner scanner) {
        CategoryModel categoryModel = new CategoryModel();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>EDITAR CATEGORIA<<<<<<<<<<<<<<<<<<<<<<<<");

        System.out.print("Introduzca el ID de categoria: ");
        categoryModel.setId(scanner.nextLong());
        scanner.nextLine();

        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(categoryModel.getId());
        if (categoryModelOptional.isPresent()) {
            categoryModel = categoryModelOptional.get();

            System.out.print("Escribe el nombre de la categoria: ");
            categoryModel.setName(scanner.nextLine());

            try {
                categoryRepository.save(categoryModel);
                System.out.println("Categoria editada exitosamente - " + categoryModel);
            } catch (Exception e) {
                System.err.println("Erro al editar Categoria - " + e.getMessage());
            }
        } else {
            System.out.println("No se encontró el ID de la categoria");
        }
    }

    private void viewCategory(Scanner scanner) {
        CategoryModel categoryModel = new CategoryModel();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>VER CATEGORIA<<<<<<<<<<<<<<<<<<<<<<<<");

        System.out.print("Introduzca el ID de categoria: ");
        categoryModel.setId(scanner.nextLong());
        scanner.nextLine();

        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(categoryModel.getId());
        if (categoryModelOptional.isPresent()) {
            categoryModel = categoryModelOptional.get();
            System.out.println(categoryModel);
        } else {
            System.out.println("No se encontró el ID de la categoria");
        }
    }

    private void deleteCategory(Scanner scanner) {
        CategoryModel categoryModel = new CategoryModel();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>BORRAR POST<<<<<<<<<<<<<<<<<<<<<<<<");

        System.out.print("Introduzca el ID de categoria: ");
        categoryModel.setId(scanner.nextLong());
        scanner.nextLine();

        Optional<CategoryModel> categoryModelOptional = categoryRepository.findById(categoryModel.getId());
        if (categoryModelOptional.isPresent()) {
            categoryModel = categoryModelOptional.get();
            try {
                categoryRepository.deleteById(categoryModel.getId());
                System.out.println("Categoría eliminada exitosamente");
            } catch (Exception e) {
                System.out.println("Error al eliminar categoría");
            }
        } else {
            System.out.println("No se encontró el ID de la categoria");
        }
    }
}
