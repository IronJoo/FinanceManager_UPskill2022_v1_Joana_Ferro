package pt.upskill.projeto2.financemanager.categories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author upSkill 2020
 * <p>
 * ...
 */

public class Category implements Serializable {

    private String name;
    private List<String> tags = new ArrayList<String>();
    private static final long serialVersionUID = -9107819223195202547L;

    public Category(String string) {
        this.name = string;
    }

    /**
     * Função que lê o ficheiro categories e gera uma lista de {@link Category} (método fábrica)
     * Deve ser utilizada a desserialização de objetos para ler o ficheiro binário categories.
     *
     * @param file - Ficheiro onde estão apontadas as categorias possíveis iniciais, numa lista serializada (por defeito: /account_info/categories)
     * @return uma lista de categorias, geradas ao ler o ficheiro
     */
    public static List<Category> readCategories(File file) {
        List<Category> categoryList = null;
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            categoryList = (List<Category>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        }catch (IOException ioe){
            System.out.println("Erro a ler o ficheiro com lista de categorias!");
        }catch (ClassNotFoundException cnfe){
            System.out.println("Nao foi possivel converter para lista de categorias!");
        }
        return categoryList;
    }

    /**
     * Função que grava no ficheiro categories (por defeito: /account_info/categories) a lista de {@link Category} passada como segundo argumento
     * Deve ser utilizada a serialização dos objetos para gravar o ficheiro binário categories.
     * @param file
     * @param categories
     */
    public static void writeCategories(File file, List<Category> categories) {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(categories);
            objectOutputStream.close();
            fileOutputStream.close();
        }catch (IOException ioe){
            System.out.println("Erro a salvar lista de categorias no ficheiro!");
        }
    }

    public boolean hasTag(String tag) {
        if (tags.contains(tag))
            return true;
        return false;
    }

    public void addTag(String tag) {
        tags.add(tag);

    }

    public String getName() {
        return name;
    }

}
