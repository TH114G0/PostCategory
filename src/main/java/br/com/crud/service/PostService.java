package br.com.crud.service;

import br.com.crud.model.CategoryModel;
import br.com.crud.model.PostModel;
import br.com.crud.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>MENÚ POST<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("0. Salir");
            System.out.println("1. Editar");
            System.out.println("2. Ver");
            System.out.println("3. Borrar");
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
                    editPost(scanner);
                    break;
                case 2:
                    viewPost(scanner);
                    break;
                case 3:
                    deletePost(scanner);
                    break;
                default:
                    System.out.println("¡La opción no es válida!");
            }
        }
    }

    public List<PostModel> CreatePostModelList(Scanner scanner, CategoryModel categoryModel) {
        List<PostModel> postModelList = new ArrayList<>();

        while (true) {
            PostModel postModel = new PostModel();
            System.out.print("Escribe el título de la publicación: ");
            postModel.setTitle(scanner.nextLine());

            System.out.print("Escribe contenido de publicación: ");
            postModel.setContent(scanner.nextLine());

            try {
                postModel.setCategory(categoryModel);

                postModelList.add(postModel);
                System.out.println("Publicación creada exitosamente - " + postModel);

                System.out.println("¿Quieres agregar otra publicación? (s/n)");
                String resp = scanner.nextLine().trim().toUpperCase();
                if (resp.equals("N")) {
                    break;
                }else if(!resp.equals("S")) {
                    System.out.println("Opción no válida! solo s para continuar y n para salir!");
                }
            }catch (Exception e) {
                System.err.print("Erro al guardar publicación - " + e);
            }
        }
        return postModelList;
    }

    private void editPost(Scanner scanner) {
        PostModel postModel = new PostModel();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>EDITAR POST<<<<<<<<<<<<<<<<<<<<<<<<");

        System.out.print("Introduzca el ID de categoria: ");
        postModel.setId(scanner.nextLong());
        scanner.nextLine();

        Optional<PostModel> postModelOptional = postRepository.findById(postModel.getId());
        if (postModelOptional.isPresent()) {
            postModel = postModelOptional.get();

            System.out.print("Escribe el título de la publicación: ");
            postModel.setTitle(scanner.nextLine());

            System.out.print("Escribe contenido de publicación: ");
            postModel.setContent(scanner.nextLine());

            try {
                postRepository.save(postModel);
                System.out.println("Publicación editada exitosamente - " + postModel);
            } catch (Exception e) {
                System.err.println("Erro al editar publicación - " + e.getMessage());
            }
        }else {
            System.out.println("No se encontró el ID de la publicación");
        }
    }

    private void viewPost(Scanner scanner) {
        PostModel postModel = new PostModel();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>VER POST<<<<<<<<<<<<<<<<<<<<<<<<");

        System.out.print("Introduzca el ID de categoria: ");
        postModel.setId(scanner.nextLong());
        scanner.nextLine();

        Optional<PostModel> postModelOptional = postRepository.findById(postModel.getId());
        if (postModelOptional.isPresent()) {
            postModel = postModelOptional.get();
            System.out.println(postModel);
        }else {
            System.out.println("No se encontró el ID de la publicación");
        }
    }

    private void deletePost(Scanner scanner) {
        PostModel postModel = new PostModel();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>VER POST<<<<<<<<<<<<<<<<<<<<<<<<");

        System.out.print("Introduzca el ID de categoria: ");
        postModel.setId(scanner.nextLong());
        scanner.nextLine();

        Optional<PostModel> postModelOptional = postRepository.findById(postModel.getId());
        if (postModelOptional.isPresent()) {
           try {
               postRepository.deleteById(postModel.getId());
               System.out.println("publicación eliminada exitosamente");
           }catch (Exception e) {
               System.out.println("Error al eliminar publicación");
           }
        }else {
            System.out.println("No se encontró el ID de la publicación");
        }
    }
}
